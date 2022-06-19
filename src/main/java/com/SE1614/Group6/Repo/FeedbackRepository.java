package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer>  {
    @Override
    Iterable<Feedback> findAll();

    @Override
    <S extends Feedback> S save(S entity);

    @Override
    <S extends Feedback> Iterable<S> saveAll(Iterable<S> entities);
    
    @Query(value = "SELECT * FROM Feedback WHERE user_id = ?1",nativeQuery = true)
    List<Feedback> findFeedbackByUserId(int id);
}
