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
       String t1 = "2000-06-02";

       temp1.setProduct_detail("good product");
       temp1.setDetail("good product");
       temp1.setImages("cho.jpg");
       temp1.setName("anh con cho");
       temp1.setQuantity(12);
       temp1.setOriginal_price(12);
       temp1.setSale_price(11);
       temp1.setTitle("new title");
       temp1.setUpdate_date(Date.valueOf(t1));

       Product temp =repo.save(temp1);
        Assertions.assertThat(temp.getId()).isGreaterThan(0);
   }
    @Test
    public void testListAll(){
        Iterable<Product> products = repo.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
    }

    @Test
    public void testUpdate(){
        Integer id=6;
        Optional<Product> optionalProduct = repo.findById(id);
        Product product=optionalProduct.get();
        product.setName("anh con meo");
        repo.save(product);

        Product updatedProduct=repo.findById(id).get();
        Assertions.assertThat(updatedProduct.getName()).isEqualTo("anh con meo");
    }

    @Test
    public void testGet(){
        Integer id=1;
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
    public void testDelete(){
        Integer id=2;
        repo.deleteById(id);
        Optional<Product> optionalProduct = repo.findById(id);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
    public void testSearch(){
       String name = "tranh";
       repo.findProductByNameContaining(name);
       Iterable<Product> optionalProduct = repo.findProductByNameContaining(name);
        Assertions.assertThat(optionalProduct.equals(optionalProduct));
    }
}
