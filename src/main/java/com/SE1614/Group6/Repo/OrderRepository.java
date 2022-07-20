package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Integer> {
    @Query("select obj from Order obj where obj.user.id=?1")
    List<Order> findAllByUser(Integer id);
}
