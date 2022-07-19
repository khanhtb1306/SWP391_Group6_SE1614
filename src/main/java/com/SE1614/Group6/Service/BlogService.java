package com.SE1614.Group6.Service;


import com.SE1614.Group6.Exception.BlogNotFoundException;
import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Blog_status;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired private BlogRepository repo;

    public List<Blog> listAll(){
        return (List<Blog>) repo.findAll();
    }

    public List<Blog> listAllActiveBlog(){
        return (List<Blog>) repo.getBlogByBlogStatus(Blog_status.ACTIVE);
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

    public List<Blog> listSortedBlogByDateAndActive(){
        List<Blog> blog = (List<Blog>) repo.getBlogByBlogStatus(Blog_status.ACTIVE);
        blog.sort(Comparator.comparing(Blog::getUpdateDate).reversed());
        List<Blog> newblog = new ArrayList<Blog>();
        for(int i=0;i<Math.min(3,blog.size());i++) {
            newblog.add(blog.get(i));
        }
        return newblog;
    }

    public List<Blog> listSortedBlogByDate(){
        return repo.findFirst3ByOrderByUpdateDateDesc();
    }

    public void save(Blog blog) {
        repo.save(blog);
    }

    public Blog saveAndReturn(Blog blog) {
        return repo.save(blog);
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
