package com.SE1614.Group6.Controller.cart;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/shop")
public class RemoveToCartController {
    @Autowired
    private HttpSession session;


    @GetMapping("/removecart")
    public String RemoveToCart(@RequestParam(name = "id") Integer id){
        Order order= (Order) session.getAttribute("order");
        if (order!=null){
            List<OrderDetail> listOrder=order.getOrder_details();
            for (OrderDetail item:listOrder) {
                if (item.getProduct().getId()==id){
                    listOrder.remove(item);
                    break;
                }
            }
            if (listOrder.isEmpty()){
                session.removeAttribute("order");
            }
            session.setAttribute("order",order);
        }
        return "redirect:/shopping-cart";
    }
}
