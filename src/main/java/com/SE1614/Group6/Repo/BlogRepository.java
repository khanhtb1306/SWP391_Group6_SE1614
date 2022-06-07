package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Blog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer> {
    public Long countById(Integer id);
}
