package com.SE1614.Group6;

import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private BlogService service;

    @GetMapping("")
    public String showHomePage(Model model){
        List<Blog> listBlogs = service.listSortedBlogByDateAndActive();
        model.addAttribute("listBlogs",listBlogs);
        return "index";
    }
}
