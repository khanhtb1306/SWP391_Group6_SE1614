package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer>  {
    @Override
    Iterable<Feedback> findAll();

    @Override
    <S extends Feedback> S save(S entity);

    @Override
    <S extends Feedback> Iterable<S> saveAll(Iterable<S> entities);
}
