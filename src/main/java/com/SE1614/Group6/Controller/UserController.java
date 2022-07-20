package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Email.Utility;
import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.UserService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    @Autowired private UserService service;
    @Autowired
    private JavaMailSender mailSender;


    @Controller
    public class DefaultController {

    @RequestMapping("/default")
    public String defaultAfterLogin() {
        User user = new User();
        String role = String.valueOf(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        System.out.println(role);
            if (role.equals("[ADMIN]")){
                return "redirect:/admin";
            }else {
                return "redirect:/";
            }
        }
    }

    @GetMapping("/pages-profile/{email}")
    public String pages_profile(@PathVariable("email") String email, Model model) {

        try {
            User user = service.getEmail(email);

            model.addAttribute("user",user);
            return "pages-profile";
        } catch (UserNotFoundException e) {
            return "redirect:/pages-profile";
        }
    }
    @PostMapping("/pages-profile/{email}")
    public String SaveProfile(@PathVariable("email") String email, @ModelAttribute(name="user") User user,
                              RedirectAttributes ra,
                              @AuthenticationPrincipal User loggedUser,
                               @RequestParam("fileImage")MultipartFile multipartFile, Model model) throws IOException, UserNotFoundException {

        if(user.getPassword().isEmpty()) {
            try {
                User user2 = service.get(user.getId());
                user.setPassword(user2.getPassword());
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


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

        ra.addFlashAttribute("message", "Profile saved successfully!");

        loggedUser.setFirst_name(user.getFirst_name());
        loggedUser.setLast_name(user.getLast_name());
        loggedUser.setEmail(user.getEmail());
        loggedUser.setPhone(user.getPhone());
        loggedUser.setAddress(user.getAddress());
        loggedUser.setAvatar(user.getPhotosImagePath());
        loggedUser.setRole(user.getRole());

        User user2 = service.getEmail(email);
        model.addAttribute("user",user2);

        return "redirect:/pages_profile";
    }
    @GetMapping("/admin")
    public String adminHomePage(Model model) {
            return "admin";
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
                               @AuthenticationPrincipal User loggedUser,
                               @RequestParam("fileImage")MultipartFile multipartFile) throws IOException, UserNotFoundException {
        if(!user.getPassword().isEmpty()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
        }else {
            try {
                User user2 = service.get(user.getId());
                user.setPassword(user2.getPassword());
            } catch (UserNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        user.setAvatar(fileName);
        User user1 = service.save(user);

            String uploadDir = "./user_avatar/" + user1.getId();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsolutePath());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new IOException("Could not save uploaded file" + fileName);
            }
        }else{
            User user2 = service.get(user.getId());
            user.setAvatar(user2.getAvatar());
            User user1 = service.save(user);
        }

        loggedUser.setFirst_name(user.getFirst_name());
        loggedUser.setLast_name(user.getLast_name());
        loggedUser.setEmail(user.getEmail());

        ra.addFlashAttribute("message", "User saved successfully!");
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

    @GetMapping("/forgot_pass")
    public String forgot_pass() {
        return "forgot_pass";
    }

    @PostMapping("/forgot_pass")
    public String SaveForgot_pass(HttpServletRequest request, Model model) throws MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = RandomString.make(45);

        try {
            service.updateResetPassword(token,email);
            String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            sendEmail(email, resetPasswordLink);
            model.addAttribute("message", "We have sent a reset password link to your email. Please check.");


        }catch(UserNotFoundException e) {
            model.addAttribute("error", e.getMessage());
        }catch (UnsupportedEncodingException eu){
            model.addAttribute("error", "Error while sending email.");
        }

        model.addAttribute("pageTitle", "forgot_pass");
        return "forgot_pass";
    }

    public void sendEmail(String recipientEmail, String link)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@shopme.com", "Shopme Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>"
                + "<br>"
                + "<p>Ignore this email if you do remember your password, "
                + "or you have not made the request.</p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

    @GetMapping("/reset_password")
    public String showResetPasswordForm(@Param(value = "token") String token, Model model){

        User user = service.getPass(token);
        if(user == null){
            model.addAttribute("title", "Reset uor password");
            model.addAttribute("message", "Invalid Token");
            return "message";
        }
        model.addAttribute("token", token);

        return "forgot_pass_form";
    }



    @PostMapping("/reset_password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User user = service.getPass(token);
        model.addAttribute("title", "Reset your password");

        if (user == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            service.updatePassword(user, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "message";
    }

    @GetMapping("/403")
    public String message(Model model){
        model.addAttribute("title", "Error");
        model.addAttribute("message", "Đã xảy ra lỗi khi đăng nhập");
        return "message";
    }




    @GetMapping("/changePassword")
    public String ShowChangePasswordForm(){

        return "ChangePassWordForm";
    }
    public boolean isPasswordValid(String encPass, String rawPass) throws DataAccessException {
        return BCrypt.checkpw(rawPass,encPass);
    }

    @PostMapping("/ChangePassword")
    public String ChangePassword( HttpServletRequest request , Model model) throws UserNotFoundException {

        String email = request.getParameter("email");
        String oldPass = request.getParameter("oldPass");
        String newPass = request.getParameter("newPass");

        User user = service.getEmail(email);
        System.out.println(user.getPassword());

        if(isPasswordValid(user.getPassword(), oldPass)) {
            service.ChangePassword(user, newPass);
            model.addAttribute("message", "You have successfully changed your password.");
        }else{
            model.addAttribute("message", "Current Password InCorrect");

        }

        return "ChangePassWordForm";
    }


}
