package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Feedback;
import com.SE1614.Group6.Model.Product;

import com.SE1614.Group6.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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




}
