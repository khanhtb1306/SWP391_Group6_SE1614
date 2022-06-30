//package com.SE1614.Group6.Controller.cart;
//
//import com.SE1614.Group6.Model.Order;
//import com.SE1614.Group6.Model.OrderDetail;
//import com.SE1614.Group6.Model.Product;
//import com.SE1614.Group6.Model.User;
//import com.SE1614.Group6.Service.OrderDetailService;
//import com.SE1614.Group6.Service.OrderService;
//import com.SE1614.Group6.Service.ProductService;
//import com.SE1614.Group6.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//import java.util.List;
//
//@Controller
//@RequestMapping("/shop")
//public class CartOrderController {
//    @Autowired
//    private HttpSession session;
//
//    @Autowired
//    private ProductService itemsDao;
//
//    @Autowired
//    private OrderService orderDao;
//
//    @Autowired
//    private OrderDetailService orderDetailsDao;
//
//    @GetMapping("addtoorder")
//    public String addOrder() {
//        Order orderSession = (Order) session.getAttribute("order");
//        if (orderSession != null) {
//            User user = (User) session.getAttribute("user");
//                List<OrderDetail> listOrder = orderSession.getOrder_details();
//                List<Product> itemsList = itemsDao.listAllProduct();
//                order.setUserDatHang(user);
//                order.setIdGame(idGame);
//                order.setLocation(location);
//                order.setOrderdetails(listOrder);
//                order.setDatecreate(new Date());
//                order.setStatus(1);
//                BigDecimal total= (BigDecimal) session.getAttribute("total");
//                order.setTotal(total);
//                    try {
//                        this.orderDao.insert(order);
//                        for (OrderDetail item : listOrder) {
//                            OrderDetail orderDetail = new OrderDetail();
//                            orderDetail.setOrder(order);
//                            orderDetail.setNickGame(null);
//                            orderDetail.setQuantity(item.getQuantity());
//                            orderDetail.setPrice(item.getPrice());
//                            orderDetail.setItems(item.getItems());
//                            this.orderDetailsDao.insert(orderDetail);
//                            for (Items i : itemsList) {
//                                if (item.getItems().getId() == i.getId()) {
//                                    i.setQuantity(i.getQuantity() - item.getQuantity());
//                                    this.itemsDao.update(i);
//                                    System.out.println(i.getQuantity());
//                                }
//                            }
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    session.removeAttribute("order");
//                    return "redirect:/shop";
//                } else {
//                return "redirect:/shopping-cart";
//            }
//    }
//}