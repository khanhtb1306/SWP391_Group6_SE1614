package com.example.crud.Controller;
import com.example.crud.Model.Feedback;
import com.example.crud.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService service;

    @GetMapping("/feedback")
    public String showUserList(Model model) {
        List<Feedback> listFeedback = service.listAllFeedback();
        model.addAttribute("listFeedback", listFeedback);
        return "feedback";
    }

    @GetMapping("/feedback/new")
    public String showNewForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        model.addAttribute("pageTitle", "Add New Feedback");
        return "feedback_form";
    }

    @PostMapping("/feedback/save")
    public String savefeedback(Feedback feedback, RedirectAttributes ra) {
        service.save(feedback);
        ra.addFlashAttribute("message", "feedback saved successfully!");
        return "redirect:/feedback";
    }
}

