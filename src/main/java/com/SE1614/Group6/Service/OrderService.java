package com.SE1614.Group6.Service;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.OrderDetailRepository;
import com.SE1614.Group6.Repo.OrderRepository;
import com.SE1614.Group6.Repo.ProductRepository;
import com.SE1614.Group6.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

@Service
public class OrderService {
    private static final String ATT_CART_NAME = "myCart";
    @Autowired
    private HttpServletRequest request;
    @Autowired
    HttpSession session;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    ProductRepository productRepository;
    public Order add() {
        HttpSession session = request.getSession();
        //lấy danh sách sản phẩm về
        Object obj = session.getAttribute(ATT_CART_NAME);
        if (obj != null) {
            //ép thành map
            Map<Integer, OrderDetail> map = (Map<Integer, OrderDetail>) obj;
            Order order = new Order();
            Order idOrder1 = new Order();
            try {
                order.setId(null);
                User nUser = this.userRepository.findById(1).get();
                order.setSale(nUser);
                order.setUser(nUser);
                idOrder1 = orderRepository.save(order);

            }catch (Exception e){
                e.printStackTrace();
            }

            Integer total = 0;

            for (Map.Entry<Integer, OrderDetail> entry : map.entrySet()) {
                Optional<Product> product = productRepository.findById(Integer.valueOf(entry.getKey()));
                OrderDetail orderDetails = entry.getValue();
                orderDetails.setOrder(idOrder1);
                System.out.printf("-----"+orderDetails.getProduct().getId());
                orderDetailRepository.save(orderDetails);

                total += orderDetails.getQuantity() * orderDetails.getUnit_price();
                product.get().setQuantity(product.get().getQuantity() - entry.getValue().getQuantity());
                productRepository.save(product.get());
            }
            order.setTotal_price(total);
            Order idOrder = orderRepository.save(order);
            session.removeAttribute(ATT_CART_NAME);

            return  idOrder;
        } else {

            return null;
        }
    }
}
