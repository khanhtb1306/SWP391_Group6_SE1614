package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Integer> {
    public Category getById(Integer id);

    public Long countById(Integer id);
}
