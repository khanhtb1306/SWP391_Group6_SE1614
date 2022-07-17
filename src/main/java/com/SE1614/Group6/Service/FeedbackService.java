package com.SE1614.Group6.Service;
import com.SE1614.Group6.Exception.FeedBackNotFoundException;
import com.SE1614.Group6.Exception.ProductNotFoundException;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
    public Feedback getFeedbackById(Integer id) throws FeedBackNotFoundException {
        Optional<Feedback> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new FeedBackNotFoundException("Could not find any feedback with ID " + id);
    }

    public void delete(Integer id) throws FeedBackNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new FeedBackNotFoundException("Could not find any feedback with ID " + id);
        }
        repo.deleteById(id);
    }
}