package com.SE1614.Group6.Service;


import com.SE1614.Group6.Model.Product;

import com.SE1614.Group6.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
    public List<Product> listAllProduct(){
        return (List<Product>) repo.findAll();
    }

    public void saveProduct(Product product) {
        repo.save(product);
    }

    public List<Product> findByNameContaining(String name) {
        return repo.findByNameContaining(name);
    }

    @Query(value = "select * from Product where " +
            "MATCH(name,original_price) " +
            "AGAINST (?1)", nativeQuery = true)
    public List<Product> search(String keyword) {
        return repo.search(keyword);
    }

    public List<Product> findByCategoryContaining(String category) {
        return repo.findByCategoryContaining(category);
    }
}
