package com.SE1614.Group6.Service;


import com.SE1614.Group6.Exception.BlogNotFoundException;
import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired private BlogRepository repo;

    public List<Blog> listAll(){
        return (List<Blog>) repo.findAll();
    }

    public List<Blog> listAllWithCategory(Category id){
        return (List<Blog>) repo.getBlogByCategory(id);
    }

    public List<Blog> searchByTitle(String title){
        if(title !=null){
            return repo.findBlogByTitleContaining(title);
        }
        return (List<Blog>) repo.findAll();
    }

    public List<Blog> listSortedBlogByDate(){
        return repo.findFirst3ByOrderByUpdateDateDesc();
    }

    public void save(Blog blog) {
        repo.save(blog);
    }

    public Blog get(Integer id) throws BlogNotFoundException {
        Optional<Blog> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new BlogNotFoundException("Could not find any blog with ID " + id);
    }

    public void delete(Integer id) throws BlogNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new BlogNotFoundException("Could not find any blog with ID " + id);
        }
        repo.deleteById(id);
    }

}
