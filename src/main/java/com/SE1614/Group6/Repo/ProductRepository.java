package com.SE1614.Group6.Repo;


import com.SE1614.Group6.Model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


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

    @Query(value="select * from Product where name = ?1",nativeQuery = true)
    List<Product> search(String keyword);
    List<Product> findByNameContaining(String name);
    List<Product> findByCategoryContaining(String category);

    @Override
    long count();



}