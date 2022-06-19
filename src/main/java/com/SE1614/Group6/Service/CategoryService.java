package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.CategoryRepository;
import com.SE1614.Group6.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public List<Category> listAll(){
        return (List<Category>) repo.findAll();
    }

    public void save(Category category) {
        repo.save(category);
    }

    public Category getByid(Integer id){
        return repo.getByid(id);
    }
}
