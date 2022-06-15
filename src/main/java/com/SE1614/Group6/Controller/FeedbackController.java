package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Service.FeedbackService;
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
    @Autowired private FeedbackService service;

    @GetMapping("/feedback")
    public String showFeedbackList(Model model){
        List<Feedback> listFeedback = service.listAllFeedback();
        model.addAttribute("listFeedback",listFeedback);
        return "feedback";
    }

    @GetMapping("/feedback/new")
    public String showNewForm(Model model){
        model.addAttribute("feedback",new Feedback());
        model.addAttribute("pageTitle","Add New Feedback");
        return "feedback_form";
    }

    @PostMapping("/feedback/save")
    public String saveFeedback(Feedback feedback, RedirectAttributes ra){
        service.save(feedback);
        ra.addFlashAttribute("message","feedback saved successfully!");
        return "redirect:/feedback";
    }

}
