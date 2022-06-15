package com.SE1614.Group6.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WedController {
    @GetMapping("/home")
    private String home(){
        return "index";
    }
    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/shop")
    public String shop() {
        return "shop";
    }
    @GetMapping("/shop_details")
    public String shop_details() {
        return "shop-details";
    }
    @GetMapping("/shopping_cart")
    public String shopping_cart() {
        return "shopping-cart";
    }
    @GetMapping("/checkout")
    public String checkout() {
        return "checkout";
    }
    @GetMapping("/blog_details")
    public String blog_details() {
        return "blog-details";
    }
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/sign-up")
    public String signup() {
        return "sign-up";
    }
    @GetMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }
}
