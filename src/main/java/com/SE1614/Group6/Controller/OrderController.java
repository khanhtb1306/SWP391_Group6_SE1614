package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Order_status;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Repo.OrderDetailRepository;
import com.SE1614.Group6.Repo.OrderRepository;
import com.SE1614.Group6.Repo.ProductRepository;
import com.SE1614.Group6.Service.CartService;
import com.SE1614.Group6.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    CartService cartService;
    @Autowired
    OrderService orderService;
    @Autowired
    HttpSession session;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

//    @GetMapping("/shopping-cart2")
//    public String index(Model model){
//        List<OrderDetail> orderDetails = cartService.data();
//        model.addAttribute("LIST_OD",orderDetails);
//        model.addAttribute("LIST_P",this.productRepository.findAll());
//        model.addAttribute("TOTAL",cartService.total());
//        System.out.println(cartService.total());
//        return "shopping-cart";
//    }

    @GetMapping("/checkout2")
    public String add(Model model){
        Order order = orderService.add();
        model.addAttribute("LIST_OD",this.orderDetailRepository.findAllByOrder(order));
        model.addAttribute("LIST_P",this.productRepository.findAll());
        Integer total = 0;
        for (OrderDetail od : this.orderDetailRepository.findAllByOrder(order)) {
            for (Product p : this.productRepository.findAll()) {
                if (p.getId()==od.getProduct().getId()){
                    total += od.getQuantity()*od.getUnit_price();
                }
            }
    }
        model.addAttribute("TOTAL",total);
    return "checkout";

}
    @GetMapping("admin/order")
    public String showOrderDetails(Model model) {
        model.addAttribute("listOrder",orderRepository.findAll());
        return "ordermanager";
    }
    @GetMapping("admin/order/orderdetail/{id}")
    public String showDetail(Model model,@PathVariable(name = "id") Integer id) {
        Order order = orderRepository.findById(id).get();
        model.addAttribute("orderDetail", order.getOrder_details());
        return "orderdetailmanager";
    }
    @ModelAttribute("status")
    public List<Order_status> getStatus() {
        List<Order_status> list=new ArrayList<>();
        for (Order_status vp: Order_status.values()) {
            list.add(vp);
        }
        return list;
    }
    @GetMapping("admin/order/confirm/{id}")
    public String confirm(@PathVariable(name = "id") Integer id) {
        try {
            Order order = orderRepository.findById(id).get();
            order.setOrder_status(Order_status.SUBMITTED);
            this.orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/order";
    }
    @GetMapping("admin/order/cancel/{id}")
    public String cancel(@PathVariable(name = "id") Integer id) {
        try {
            Order order = orderRepository.findById(id).get();
            order.setOrder_status(Order_status.UNSUBMITTED);
            this.orderRepository.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/admin/order";
    }
}

