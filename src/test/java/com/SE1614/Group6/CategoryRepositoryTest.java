package com.SE1614.Group6;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Repo.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//test real database
@Rollback(false)//let data not update in database
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository repo;

//    @Test
//    public void testCreateCategory(){
//        Category temp1 = new Category();
//        temp1.setValue("New trends");
//        Category temp =repo.save(temp1);
//        Assertions.assertThat(temp.getId()).isGreaterThan(0);
//    }
}
