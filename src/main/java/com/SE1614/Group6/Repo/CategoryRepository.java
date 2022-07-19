package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
    public Category getById(Integer id);

    public Long countById(Integer id);
    @Query(value = "SELECT * FROM Category WHERE product_id = ?1",nativeQuery = true)
    List<Feedback> findFeedbackByProductId(int id);
}
