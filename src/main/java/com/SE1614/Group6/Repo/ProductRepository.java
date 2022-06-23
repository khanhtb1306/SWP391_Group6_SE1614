package com.SE1614.Group6.Repo;


import com.SE1614.Group6.Model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Override
    <S extends Product> S save(S entity);

    @Override
    <S extends Product> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Product> findById(Integer integer);

    @Override
    Iterable<Product> findAll();

    @Override
    Iterable<Product> findAllById(Iterable<Integer> integers);

    @Override
    long count();
}