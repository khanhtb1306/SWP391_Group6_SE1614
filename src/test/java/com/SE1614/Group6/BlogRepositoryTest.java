package com.SE1614.Group6;

import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.BlogRepository;
import com.SE1614.Group6.Repo.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//test real database
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository repo;

    /*@Test
    public void testAddBlog(){
        Blog blog=new Blog();
        blog.setTitle("This is new blog");
        blog.setImage_Link("/img/blog/1.jpg");
        blog.setUpdate_Date("22/09/2012");
        blog.setViews("12312423");
        Blog savedUser=repo.save(blog);

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isGreaterThan(0);
    }*/

    @Test
    public void testListAll(){
        Iterable<Blog> users = repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);
    }

    @Test
    public void testUpdate(){
        Integer id=1;
        Optional<Blog> optionalUser = repo.findById(id);
        Blog blog=optionalUser.get();
        blog.setTitle("New title");
        repo.save(blog);

        Blog updatedBlog=repo.findById(id).get();
        Assertions.assertThat(updatedBlog.getTitle()).isEqualTo("New title");
    }

    @Test
    public void testGet(){
        Integer id=1;
        Optional<Blog> optionalBlog = repo.findById(id);
        Assertions.assertThat(optionalBlog).isPresent();
    }

    @Test
    public void testGetNotExist(){
        Integer id=100;
        Optional<Blog> optionalBlog = repo.findById(id);
        Assertions.assertThat(optionalBlog).isNotPresent();
    }

    @Test
    public void testDelete(){
        Integer id=1;
        repo.deleteById(id);
        Optional<Blog> optionalBlog = repo.findById(id);
        Assertions.assertThat(optionalBlog).isNotPresent();
    }

    /*@Test
    public void testDeleteNotSuccess(){
        Integer id=100;
        repo.deleteById(id);
        Optional<Blog> optionalBlog = repo.findById(id);
        Assertions.assertThat(optionalBlog).isNotPresent();
    }*/

    @Test
    public void testGetBlogByCategory(){
        Iterable<Blog> listBlog = repo.findBlogByTitleContaining("cve");
        Assertions.assertThat(listBlog).hasSizeGreaterThan(0);
    }

    @Test
    public void testGetBlogByCategoryNotExist(){
        Iterable<Blog> listBlog = repo.findBlogByTitleContaining("not exist");
        Assertions.assertThat(listBlog).hasSize(0);
    }
}
