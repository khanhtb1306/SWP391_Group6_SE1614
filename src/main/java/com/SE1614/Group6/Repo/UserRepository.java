package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
