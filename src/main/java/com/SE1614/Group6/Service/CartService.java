package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    private static final String ATT_CART_NAME = "myCart";
    @Autowired
    private HttpServletRequest request;
    @Autowired
    HttpSession session;
    @Autowired
    ProductService productService;


    public List<OrderDetail> data() {
        HttpSession session = request.getSession();

        Order order = new com.SE1614.Group6.Model.Order();
        Product product = new Product();
        product.setId(5);
        Product product1 = new Product();
        product1.setId(7);
        Map<Integer, OrderDetail> map =new HashMap<>();
        map.put(5,new OrderDetail(null,order,product,2,10000));
        map.put(7,new OrderDetail(null,order,product1,3,20000));
        session.setAttribute(ATT_CART_NAME,map);
        Object obj = session.getAttribute(ATT_CART_NAME);
        Map<Integer, OrderDetail> map1 = (Map<Integer, OrderDetail>) obj;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Map.Entry<Integer, OrderDetail> entry : map.entrySet()) {
            orderDetails.add(entry.getValue());
        }

        return orderDetails;
    }
    public Integer total() {
        HttpSession session = request.getSession();
        Object obj = session.getAttribute(ATT_CART_NAME);
        Map<Integer, OrderDetail> map = (Map<Integer, OrderDetail>) obj;
        Integer total = 0;
        List<Product> products = productService.listAllProduct();
        for (Map.Entry<Integer, OrderDetail> entry : map.entrySet()) {
            for (Product p : products
            ) {
                if (p.getId().equals(entry.getValue().getProduct().getId())) {
                    total +=  entry.getValue().getQuantity() * entry.getValue().getUnit_price();
                }
            }
        }
        return total;
    }
}
