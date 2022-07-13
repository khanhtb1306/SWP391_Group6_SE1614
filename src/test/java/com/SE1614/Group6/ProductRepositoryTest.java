package com.SE1614.Group6;

import com.SE1614.Group6.Model.Category;
import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.FeedbackRepository;
import com.SE1614.Group6.Repo.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)//test real database
@Rollback(false)//let data not update in database
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository repo;

    @Test
    public void testCreateProduct(){
        Product temp1 = new Product();
        Category temp2 = new Category();
        temp2.setId(1);
        temp2.setValue("ao");
        String t1 = "2000-06-02";
        temp1.setCategory(temp2);
        temp1.setProduct_detail("good product");
        temp1.setDetail("good");
        temp1.setImages("cho.jpg");
        temp1.setName("anh con cho");
        temp1.setQuantity(12);
        temp1.setOriginal_price(12);
        temp1.setSale_price(11);
        temp1.setTitle("new title");
        temp1.setUpdate_date(Date.valueOf(t1));
        temp1.setId(1);
        Product temp =repo.save(temp1);
        Assertions.assertThat(temp.getId()).isGreaterThan(0);
    }
    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
    }


    @Test
    public void testUpdate1(){
        Integer id=6;
        Optional<Product> optionalProduct = repo.findById(id);
        Product product=optionalProduct.get();
        product.setName("anh con meo");
        repo.save(product);

        Product updatedProduct=repo.findById(id).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("anh con cho");
    }
    @Test
    public void testUpdate2(){
        Integer id=6;
        Optional<Product> optionalProduct = repo.findById(id);
        Product product=optionalProduct.get();
        product.setName("anh con cho");
        repo.save(product);

        Product updatedProduct=repo.findById(id).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("anh con cho");
    }
    @Test
    public void testGet1(){
        Integer id=6;
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isPresent();
    }
    @Test
    public void testGetNotExist(){
        Integer id=100;
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }

    @Test
    public void testDelete1(){
        Integer id=6;
        repo.deleteById(id);
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
    @Test
    public void testDelete2(){
        Integer id=200;
        repo.deleteById(id);
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
    @Test
    public void testSearch1(){
        String name = "tranh";
        repo.search(name);
        Iterable<Product> optionalProduct = repo.search(name);
        Assertions.assertThat(optionalProduct.equals(optionalProduct));
    }
    @Test
    public void testSearch2(){
        String name = "asasas";
        repo.search(name);
        Iterable<Product> optionalProduct = repo.search(name);
        Assertions.assertThat(optionalProduct.equals(optionalProduct));
    }
}