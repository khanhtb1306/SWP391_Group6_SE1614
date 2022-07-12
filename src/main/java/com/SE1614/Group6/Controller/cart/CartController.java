package com.SE1614.Group6.Controller.cart;

import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Controller
public class CartController {

    @Autowired
    private HttpSession session;

    @Autowired
    private ProductService iItemsDao;

    @GetMapping("/orderdetails")
    public String showOrderDetails(Model model) {
        return "orderdetails";
    }
    @GetMapping("/shopping-cart2")
    public String getCart(Model model){
      int total =0;
     int quantity=0;
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            total = 0;
            quantity=0;
        } else {
            List<OrderDetail> listOrder = order.getOrder_details();
                for (OrderDetail i : listOrder) {
                        total += i.getQuantity()*i.getUnit_price();
                        quantity+=i.getQuantity();
                }
            }
        model.addAttribute("LIST_P",this.iItemsDao.listAllProduct());
        session.setAttribute("total",total);
        session.setAttribute("quantity",quantity);
        return "shopping-cart";
    }
}
