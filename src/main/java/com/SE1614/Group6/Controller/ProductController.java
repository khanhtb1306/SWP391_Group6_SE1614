package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.ProductRepository;
import com.SE1614.Group6.Service.FeedbackService;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



    @Controller
    public class ProductController {
        @Autowired
        private ProductService service;

        @GetMapping("/product")
        public String showProductList(Model model){
            List<Product> listProduct = service.listAllProduct();
            model.addAttribute("listProduct",listProduct);
            return "product";
        }

        @GetMapping("/product/new")
        public String showNewForm(Model model){
            model.addAttribute("product",new Product());
            model.addAttribute("pageTitle","Add New Product");
            return "product_form";
        }

        @PostMapping("/product/save")
        public String saveProduct(Product product){
            service.save(product);

            return "redirect:/product";
        }

    }
