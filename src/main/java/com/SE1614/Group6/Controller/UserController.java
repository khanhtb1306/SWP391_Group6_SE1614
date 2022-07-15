package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.RegistrationRequest;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/pages-profile")
    public String pages_profile() {
        return "pages-profile";
    }
    @GetMapping("/admin")
    public String adminHomePage() {
        return "admin";
    }
    @GetMapping("/forgot_pass")
    public String forgot_pass() {
        return "forgot_pass";
    }
    @GetMapping("/pages-confirm-mail")
    public String pages_confirm_mail() { return "pages-confirm-mail"; }
    @GetMapping("/sign-up")
    public String signup() {
        return "sign-up";
    }

    @GetMapping("/admin/admin_customers/add")
    public String showAddCustomerForm(Model model){
        model.addAttribute("customer", new User());
        model.addAttribute("pageTitle", "Add New Customer");
        return "customer_form";
    }
    @PostMapping("/admin/admin_customers/save")
    public String SaveCustomer(@ModelAttribute(name="customer") User user, RedirectAttributes ra,
                               @RequestParam("fileImage")MultipartFile multipartFile) throws IOException {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        user.setAvatar(fileName);
        User user1 = service.save(user);

        String uploadDir = "./user_avatar/" + user1.getId();
        Path uploadPath = Paths.get(uploadDir);

        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        try(InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            throw  new IOException("Could not save uploaded file" + fileName);
        }

        ra.addFlashAttribute("message", "Customer saved successfully!");
        return "redirect:/admin/admin_customers";
    }

    @GetMapping("/admin/admin_customers/edit/{id}")
    public String showEditFormCustomer(@PathVariable("id") Integer id, Model model,RedirectAttributes ra){
        try {
            User user = service.get(id);
            model.addAttribute("customer",user);
            model.addAttribute("pageTitle","Edit Customer ID: "+id);
            return "customer_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/admin/admin_customers";
        }
    }

    @GetMapping("/admin/admin_customers/delete/{id}")
    public String deleteCustomer(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","Customer Id " + id + " deleted successfully!");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/admin/admin_customers";
    }

    @GetMapping("/admin/admin_customers")
    public String adminCustomer(Model model) {
        List<User> list = service.listAll();
        model.addAttribute("listUsers", list);
        return "admin_customers";
    }

}
