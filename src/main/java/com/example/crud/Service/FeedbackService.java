package com.example.crud.Service;
import com.example.crud.Model.Feedback;
import com.example.crud.Repo.FeedbackRepository;
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
