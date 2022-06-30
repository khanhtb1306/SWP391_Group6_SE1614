package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.ProductRepository;
import com.SE1614.Group6.Service.CategoryService;
import com.SE1614.Group6.Service.FeedbackService;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



    @Controller
    public class ProductController {
        @Autowired
        private ProductService service;
        @Autowired
        private CategoryService serviceC;


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
            List<Category> listCategories = serviceC.listAll();
            model.addAttribute("listCategories",listCategories);
            return "product_form";
        }

        @PostMapping("/product/save")
        public String saveProduct(Product product,RedirectAttributes ra){
            service.saveProduct(product);
            ra.addFlashAttribute("message","products saved successfully!");
            return "redirect:/product";
        }


        @GetMapping("/shop/search")
        public String search(Model model, @RequestParam(name = "name", required = false) String name) {
            List<Product> search = null;
            if (StringUtils.hasText(name)) {
                search = service.search(name);
            } else {
                search = service.listAllProduct();
            }
            model.addAttribute("listProduct", search);
            List<Category> listCategories = serviceC.listAll();
            model.addAttribute("listCategories",listCategories);
            return "shop";
        }
    }
