package com.SE1614.Group6.Repo;


import com.SE1614.Group6.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}