package com.SE1614.Group6.Repo;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer> {
//    @Query("SELECT o from OrderDetail o WHERE o.order.id = ?1")
    List<OrderDetail> findAllByOrder(Order order);
}
