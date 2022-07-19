package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.BlogNotFoundException;
import com.SE1614.Group6.Exception.CategoryNotFoundException;
import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.CategoryRepository;
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
public class CategoryController {
        @Autowired
        private CategoryService service;

        @GetMapping("/categories")
        public String listCategories(Model model){
            List<Category> listCategories = service.listAll();
            model.addAttribute("listCategories",listCategories);
            return "categories";
        }

    @GetMapping("/categories/new")
    public String showCategoryNewForm(Model model){
        model.addAttribute("category",new Category());
        return "category_form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(Category category){
        service.save(category);
        return "redirect:/categories";
    }


    @GetMapping("/categories/delete/{id}")
    public String deleteBlog(@PathVariable("id") Integer id,RedirectAttributes ra){
        try {
            service.delete(id);
            ra.addFlashAttribute("message","Category Id " + id + " deleted successfully!");
        } catch (CategoryNotFoundException e) {
            ra.addFlashAttribute("message",e.getMessage());
        }
        return "redirect:/categories";
    }
}
