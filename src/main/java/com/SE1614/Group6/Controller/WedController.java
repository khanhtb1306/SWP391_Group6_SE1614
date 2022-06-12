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
}
