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

    public void saveFeedback(Feedback feedback) {
        repo.save(feedback);
    }
    public List<Feedback> findFeedbackByUserId(int id){return (List<Feedback>) repo.findFeedbackByUserId(id);}

    public List<Feedback> findFeedbackByProductId(int id){return (List<Feedback>) repo.findFeedbackByProductId(id);}
}