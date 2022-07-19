package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Blog_status;
import com.SE1614.Group6.Model.Category;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import java.util.List;

@Repository
public interface BlogRepository extends CrudRepository<Blog, Integer> {
    public Long countById(Integer id);
    public List<Blog> getBlogByCategory(Category cat);

    public List<Blog> getBlogByBlogStatus(Blog_status status);
    public List<Blog> findBlogByTitleContaining(String keyword);

    public List<Blog> findFirst3ByOrderByUpdateDateDesc();

}
