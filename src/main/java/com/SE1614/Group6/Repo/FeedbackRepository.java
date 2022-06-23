package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer>  {
    @Override
    <S extends Feedback> S save(S entity);

    @Override
    <S extends Feedback> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Feedback> findById(Integer integer);

    @Override
    Iterable<Feedback> findAll();

    @Override
    Iterable<Feedback> findAllById(Iterable<Integer> integers);

    @Query(value = "SELECT * FROM Feedback WHERE user_id = ?1",nativeQuery = true)
    List<Feedback> findFeedbackByUserId(int id);
    @Query(value = "SELECT * FROM Feedback WHERE product_id = ?1",nativeQuery = true)
    List<Feedback> findFeedbackByProductId(int id);
}
