package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.BlogNotFoundException;
import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.BlogService;
import com.SE1614.Group6.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService service;

    @Autowired
    private CategoryService cateService;

    @GetMapping("/blogs")
    public String showBlogList(Model model){
        List<Blog> listBlogs = service.listAll();
        model.addAttribute("listBlogs",listBlogs);
        return "blogs";
    }

    @GetMapping("/blogs/new")
    public String showNewForm(Model model){
        List<Category> listCategories = cateService.listAll();
        model.addAttribute("blog",new Blog());
        model.addAttribute("listCategories",listCategories);
        model.addAttribute("pageTitle","Add New Blog");
        return "blog_form";
    }

    @PostMapping("/blogs/save")
    public String saveBlog(Blog blog, RedirectAttributes ra){
        service.save(blog);
        ra.addFlashAttribute("message","Blog saved successfully!");
        return "redirect:/blogs";
    }

    @GetMapping("/blogs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra){
        try {
            Blog blog = service.get(id);
            model.addAttribute("blog",blog);
            model.addAttribute("pageTitle","Edit Blog ID: "+id);
            return "blog_form";
        } catch (BlogNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
            return "redirect:/blogs";
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
        return "redirect:/blogs";
    }
}
