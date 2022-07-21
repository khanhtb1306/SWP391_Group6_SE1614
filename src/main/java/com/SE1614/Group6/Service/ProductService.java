package com.SE1614.Group6.Service;

import com.SE1614.Group6.Exception.ProductNotFoundException;
import com.SE1614.Group6.Model.*;

import com.SE1614.Group6.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Query(value = "select * from Product ORDER BY p.original_price DESC",nativeQuery = true)
    public List<Product> OrderbyDesc() {
       return  repo.OrderbyDesc();
    }
    @Query(value = "select * from Product ORDER BY p.original_price ASC ",nativeQuery = true)
    public List<Product> OrderbyASC() {
        return  repo.OrderbyASC();
    }
    public List<Product> searchByName(String name){
        if(name !=null){
            return repo.findProductByNameContaining(name);
        }
        return (List<Product>) repo.findAll();
    }

    public List<Product> getProductbyCategoryid(Integer id){

            return repo.getProductByCategoryid(id);

    }
    public List<Product> getProductByCategory(Category cat) {
        return repo.getProductByCategory(cat);
    }
}
