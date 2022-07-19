package com.SE1614.Group6.Controller.cart;

import com.SE1614.Group6.Exception.UserNotFoundException;
import com.SE1614.Group6.Model.*;
import com.SE1614.Group6.Repo.OrderDetailRepository;
import com.SE1614.Group6.Repo.OrderRepository;
import com.SE1614.Group6.Repo.UserRepository;
import com.SE1614.Group6.Service.ProductService;
import com.SE1614.Group6.Service.UserService;
import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class CartOrderController {
    @Autowired
    private HttpSession session;

    @Autowired
    private ProductService itemsDao;

    @Autowired
    private OrderRepository orderDao;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailsDao;
    @Autowired
    private UserService userService1;
    @GetMapping("addtoorder/{email}")
    public String addOrder(@RequestParam(name = "") String address, String first_name, String last_name, String notes, String phone, String email,@PathVariable("email") String email1) throws UserNotFoundException {
        Order orderSession = (Order) session.getAttribute("order");
        if (orderSession != null) {
            //Bao gio set thi xoa dong 47 di mo cmt lai donng 46
            User user = userService1.get(email1);
            session.setAttribute("user",user);


            System.out.println(email1);
           //User user = this.userRepository.findById(11).get();
            User usersale = this.userRepository.findById(1).get();
            List<OrderDetail> listOrder = orderSession.getOrder_details();
            List<Product> itemsList = itemsDao.listAllProduct();
            Order order = new Order();
            order.setUser(user);
            order.setOrder_details(listOrder);
            order.setOrder_date(new Date());
            order.setSale(usersale);
            order.setFirst_name(first_name);
            order.setLast_name(last_name);
            order.setAddress(address);
            order.setPhone(phone);
            order.setEmail(email);
            order.setNotes(notes);

            order.setOrder_status(Order_status.UNSUBMITTED);
            int total = (int) session.getAttribute("total");
            order.setTotal_price(total);
            try {
                this.orderDao.save(order);
                for (OrderDetail item : listOrder) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setQuantity(item.getQuantity());
                    orderDetail.setUnit_price(item.getUnit_price());
                    orderDetail.setProduct(item.getProduct());
                    this.orderDetailsDao.save(orderDetail);
                    for (Product i : itemsList) {
                        if (item.getProduct().getId() == i.getId()) {
                            i.setQuantity(i.getQuantity() - item.getQuantity());
                            this.itemsDao.saveProduct(i);
                            System.out.println(i.getQuantity());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            session.removeAttribute("order");
            return "redirect:/shop";
        } else {
            return "redirect:/shopping-cart";
        }
    }

}