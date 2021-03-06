package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.BlogNotFoundException;
import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.BlogService;
import com.SE1614.Group6.Service.CategoryService;
import com.SE1614.Group6.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Objects;

@Controller
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private CategoryService cateService;

    @Autowired
    private UserService userService;

    @GetMapping("/blogs")
    public String showBlogList(Model model){
        List<Blog> listBlogs = service.listAllActiveBlog();
        model.addAttribute("listBlogs",listBlogs);
        return "blogs";
    }

    @GetMapping("/manage-blogs")
    public String manageBlog(Model model){
        List<Blog> listBlogs = service.listAll();
        model.addAttribute("listBlogs",listBlogs);
        return "manage-blogs";
    }

    @GetMapping("/blogs/new")
    public String showNewForm(Model model){
        List<Category> listCategories = cateService.listAll();
        List<User> listUsers = userService.listAll();
        model.addAttribute("blog",new Blog());
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("listUsers",listUsers);
        model.addAttribute("pageTitle","Add New Blog");
        return "blog_form";
    }

    @PostMapping("/blogs/save")
    public String saveBlog(Blog blog, RedirectAttributes ra,@RequestParam("fileImage") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        blog.setImage_Link("/blog_image/"+fileName);
        Blog blog1 = service.save(blog);

        String uploadDir = "./blog_image/";
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
        ra.addFlashAttribute("message","Blog saved successfully!");
        return "redirect:/manage-blogs";
    }

    @GetMapping("/blogs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        List<Category> listCategories = cateService.listAll();
        List<User> listUsers = userService.listAll();
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("listUsers",listUsers);
        try {
            Blog blog = service.get(id);
            model.addAttribute("blog",blog);
            model.addAttribute("pageTitle","Edit Blog ID: "+id);
            return "blog_form";
        } catch (BlogNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/manage-blogs";
        }
    }

    @GetMapping("/blogs/delete/{id}")
    public String deleteBlog(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","Blog Id " + id + " deleted successfully!");
        } catch (BlogNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/manage-blogs";
    }

    @GetMapping("/blog-details/{id}")
    public String showBlogDetails(@PathVariable("id") Integer id,Model model) throws BlogNotFoundException {
        Blog blog = service.get(id);
        List<Category> listCategories = cateService.listAll();
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("blog",blog);
        return "blog-details";
    }

    @GetMapping("/blog/category/{id}")
    public String showBlogWithCategory(@PathVariable("id") Integer id,Model model){
        Category filter = cateService.getById(id);
        List<Blog> listBlogs = service.listAllWithCategory(filter);
        List<Category> listCategories = cateService.listAll();
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("listBlogs",listBlogs);
        model.addAttribute("activeId",id);
        return "blog-category";
    }

    @GetMapping("/blog/search")
    public String searchBlogWithTitle(@RequestParam("title") String title, Model model) {
        List<Blog> listBlogs = service.searchByTitle(title);
        List<Category> listCategories = cateService.listAll();
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("listBlogs",listBlogs);
        return "blog-category";
    }
}
