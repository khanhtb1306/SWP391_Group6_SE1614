package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Service.CategoryService;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WedController {
    @Autowired
    private ProductService serviceP;
    @Autowired
    private CategoryService serviceC;

    @GetMapping("/home")
    private String home(){
        return "index";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/shop")
    public String Shop(Model model){
        List<Product> listProduct = serviceP.listAllProduct();
        model.addAttribute("listProduct",listProduct);
        List<Category> listCategories = serviceC.listAll();
        model.addAttribute("listCategories",listCategories);
        return "shop";
    }

    @GetMapping("/shopping-cart")
    public String shopping_cart() {
        return "shopping-cart";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }
}
