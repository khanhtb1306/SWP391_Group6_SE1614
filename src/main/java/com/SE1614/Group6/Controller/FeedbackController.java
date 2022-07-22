package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.FeedBackNotFoundException;
import com.SE1614.Group6.Exception.ProductNotFoundException;
import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.FeedbackService;
import com.SE1614.Group6.Service.ProductService;
import com.SE1614.Group6.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService service;
    @Autowired
    private UserService serviceU;

    @Autowired
    private ProductService serviceP;

    @GetMapping("/feedback")
    public String showFeedbackList(Model model) {
        List<Feedback> listFeedback = service.listAllFeedback();
        model.addAttribute("listFeedback", listFeedback);
        return "feedback";
    }

    @GetMapping("/feedback/new/{id}/{userid}")
    public String showNewForm(@PathVariable("id") Integer id,@PathVariable("userid") Integer userid, Model model) {
        try {
            model.addAttribute("feedback", new Feedback());
            model.addAttribute("pageTitle", "Your Comment");
            User listUsers = serviceU.get(userid);
            model.addAttribute("listUsers", listUsers);
            Product listProduct = serviceP.getProductById(id);
            model.addAttribute("listProduct", listProduct);
        } catch (ProductNotFoundException | UserNotFoundException e) {
        }
        return "feedback_form";
    }

    @PostMapping("/feedback/save")
    public String saveFeedback(Feedback feedback, RedirectAttributes ra) {
        service.saveFeedback(feedback);
        ra.addFlashAttribute("message", "feedback saved successfully!");
        return "redirect:/shop";
    }

    @GetMapping("/feedback/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Feedback feedback = service.getFeedbackById(id);
            model.addAttribute("feedback", feedback);
            model.addAttribute("pageTitle", "Edit Feedback ID: " + id);
            List<User> listUsers = serviceU.listAll();
            model.addAttribute("listUsers", listUsers);
            List<Product> listProduct = serviceP.listAllProduct();
            model.addAttribute("listProduct", listProduct);
            return "feedback_form";
        } catch (FeedBackNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/feedback";
        }
    }

    @GetMapping("/feedback/delete/{id}")
    public String deleteFeedback(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "Feedback Id " + id + " deleted successfully!");
        } catch (FeedBackNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/feedback";
    }

}
