package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Blog;
import com.SE1614.Group6.Repo.BlogRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlogServiceTest {

    @Autowired
    private BlogRepository repo;

    @Test
    void listAll() {
        BlogService blogService = new BlogService();
        Assertions.assertThat((List<Blog>) blogService.listAll()).hasSizeGreaterThan(0);
    }

    @Test
    void listAllWithCategory() {
    }

    @Test
    void searchByTitle() {
    }

    @Test
    void save() {
    }

    @Test
    void get() {
    }

    @Test
    void delete() {
    }
}