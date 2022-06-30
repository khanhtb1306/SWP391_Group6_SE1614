package com.SE1614.Group6.Controller.cart;

import com.SE1614.Group6.Exception.ProductNotFoundException;
import com.SE1614.Group6.Model.Order;
import com.SE1614.Group6.Model.OrderDetail;
import com.SE1614.Group6.Model.Product;
import com.SE1614.Group6.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/shop")
public class AddToCartController {

    @Autowired
    private ProductService itemsDao;

    @Autowired
    private HttpSession session;


    @GetMapping("/addtocart")
    public String addToCart(@RequestParam(name = "id") Integer id, @RequestParam(name = "quantity", required = false, defaultValue = "1") Integer quantity) {
        try {
          Product items = this.itemsDao.getProductById(id);
            if (session.getAttribute("order") == null) {
                Order order = new Order();
                OrderDetail orderdetail = new OrderDetail();
                orderdetail.setProduct(items);
                orderdetail.setQuantity(quantity);
                orderdetail.setUnit_price(items.getOriginal_price());
                List<OrderDetail> list = new ArrayList<>();
                list.add(orderdetail);
                order.setOrder_details(list);
                session.setAttribute("order", order);
            } else {
                Order order = (Order) session.getAttribute("order");
                List<OrderDetail> listOrder = order.getOrder_details();
                boolean check = false;
                for (OrderDetail item : listOrder) {
                    if (item.getProduct().getId() == items.getId()) {
                        item.setQuantity(item.getQuantity() + quantity);
                        check = true;
                    }
                    if (item.getQuantity()>itemsDao.getProductById(item.getProduct().getId()).getQuantity()){
                        item.setQuantity(item.getProduct().getQuantity());
                        session.setAttribute("error","Sản phẩm này đã đạt số lượng tối đa");
                    }
                }
                if (check == false) {
                    OrderDetail orderdetail = new OrderDetail();
                    orderdetail.setProduct(items);
                    orderdetail.setQuantity(quantity);
                    orderdetail.setUnit_price(items.getOriginal_price());
                    listOrder.add(orderdetail);
                }
                session.setAttribute("order", order);
            }
            return "redirect:/shopping-cart2";
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/cart/plus")
    public String cartPlus(@RequestParam(name = "id") Integer id){
        Order order = (Order) session.getAttribute("order");
        List<OrderDetail> listOrder = order.getOrder_details();
        for (OrderDetail item : listOrder) {
            if (item.getProduct().getId() == id) {
                item.setQuantity(item.getQuantity() + 1);
            }
            try {
                if (item.getQuantity()>itemsDao.getProductById(item.getProduct().getId()).getQuantity()){
                    item.setQuantity(item.getProduct().getQuantity());
                    session.setAttribute("error","Sản phẩm này đã đạt số lượng tối đa");
                }
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return "redirect:/shopping-cart2";
    }
    @GetMapping("/cart/minus")
    public String cartMinus(@RequestParam(name = "id") Integer id){
        Order order = (Order) session.getAttribute("order");
        List<OrderDetail> listOrder = order.getOrder_details();
        int check = 0;
        OrderDetail orderdetail = new OrderDetail();
        if (listOrder.size() > 0) {
            for (OrderDetail item : listOrder) {
                if (item.getProduct().getId() == id) {
                    item.setQuantity(item.getQuantity() - 1);
                    if (item.getQuantity() == 0) {
                        orderdetail = item;
                        check++;
                    }
                }
            }
            if (check != 0) {
                listOrder.remove(orderdetail);
                if (listOrder.isEmpty()) {
                    session.removeAttribute("order");
                }
            }
        }
        return "redirect:/shopping-cart2";
    }
}
