package com.SE1614.Group6;

import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.CategoryRepository;
import com.SE1614.Group6.Repo.FeedbackRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//test real database
@Rollback(false)//let data not update in database
public class FeedBackRepositoryTest {
    @Autowired
    private FeedbackRepository repo;


   @Test
    public void testCreateFeedback(){
        Feedback temp1 = new Feedback();
        User temp2 = new User();
        Product temp3 = new Product();
       temp1.setComment("good product");
       temp1.setStar(12);
       temp2.setId(1);
       temp3.setId(1);
       temp1.setUser(temp2);
       temp1.setProduct(temp3);
       Feedback temp =repo.save(temp1);
        Assertions.assertThat(temp.getId()).isGreaterThan(0);
   }
    @Test
    public void testCreateFeedback2(){
        Feedback temp1 = new Feedback();
        User temp2 = new User();
        Product temp3 = new Product();
        temp1.setComment("good product");
        temp1.setStar(1233333333);
        temp2.setId(1);
        temp3.setId(1);
        temp1.setUser(temp2);
        temp1.setProduct(temp3);
        Feedback temp =repo.save(temp1);
        Assertions.assertThat(temp.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<Feedback> feedbacks = repo.findAll();
        Assertions.assertThat(feedbacks).hasSizeGreaterThan(0);
    }

    @Test
    public void testUpdate1(){
        Integer id=24;
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Feedback feedback=optionalFeedback.get();
        feedback.setComment("New comment");
        repo.save(feedback);

        Feedback updatedFeedback=repo.findById(id).get();
        Assertions.assertThat(updatedFeedback.getComment()).isEqualTo("New comment");
    }
    @Test
    public void testUpdate2(){
        Integer id=24;
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Feedback feedback=optionalFeedback.get();
        feedback.setComment("comment");
        repo.save(feedback);

        Feedback updatedFeedback=repo.findById(id).get();
        Assertions.assertThat(updatedFeedback.getComment()).isEqualTo("New comment");
    }
    @Test
    public void testGet(){
        Integer id=24;
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Assertions.assertThat(optionalFeedback).isPresent();
    }

    @Test
    public void testGetNotExist(){
        Integer id=100;
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Assertions.assertThat(optionalFeedback).isNotPresent();
    }

    @Test
    public void testDelete(){
        Integer id=24;
        repo.deleteById(id);
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Assertions.assertThat(optionalFeedback).isNotPresent();
    }
    @Test
    public void testDelete2(){
        Integer id=100;
        repo.deleteById(id);
        Optional<Feedback> optionalFeedback = repo.findById(id);
        Assertions.assertThat(optionalFeedback).isNotPresent();
    }
}
