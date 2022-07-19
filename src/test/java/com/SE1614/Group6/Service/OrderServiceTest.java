package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void add() {
        Order order = new com.SE1614.Group6.Model.Order();
        Product product = new Product();
        product.setId(4);
        Map<Integer, OrderDetail> map =new HashMap<>();
        map.put(4,new OrderDetail(null,order,product,2,10000));

        Assertions.assertTrue(map.isEmpty());
    }

}
