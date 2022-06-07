package com.SE1614.Group6.Service;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Repo.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired private FeedbackRepository repo;
    public List<Feedback> listAllFeedback(){
        return (List<Feedback>) repo.findAll();
    }

    public void save(Feedback feedback) {
        repo.save(feedback);
    }




}