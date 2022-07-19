package com.SE1614.Group6.Controller;

import com.SE1614.Group6.Model.Order_status;
import com.SE1614.Group6.Model.User;
import com.SE1614.Group6.Repo.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class HistoryController {
    @Autowired
    HttpSession session;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    HttpServletRequest request;

    @GetMapping("/history")
    public String history(Model model ) {
        User user = (User) session.getAttribute("user");
        // lấy user đang đăng nhập truyền vào trong cái id của findAll
        model.addAttribute("listOrder",orderRepository.findAllByUser(user.getId()));
        return "history";
    }
    @ModelAttribute("status")
    public List<Order_status> getStatus() {
        List<Order_status> list=new ArrayList<>();
        for (Order_status vp: Order_status.values()) {
            list.add(vp);
        }
        return list;
    }

}