package com.SE1614.Group6.Service;

import com.SE1614.Group6.Exception.ProductNotFoundException;
import com.SE1614.Group6.Model.Feedback;

import com.SE1614.Group6.Model.Product;

import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

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
    public Product getProductById(Integer id) throws ProductNotFoundException {
        Optional<Product> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new ProductNotFoundException("Could not find any products with ID " + id);
    }

    public void delete(Integer id) throws ProductNotFoundException {
        Long count = repo.countById(id);
        if(count == null || count == 0){
            throw new ProductNotFoundException("Could not find any products with ID " + id);
        }
        repo.deleteById(id);
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
