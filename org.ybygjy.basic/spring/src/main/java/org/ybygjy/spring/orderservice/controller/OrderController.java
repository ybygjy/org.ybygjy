package org.ybygjy.spring.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.ybygjy.spring.orderservice.dao.impl.OrderDaoImpl4JDBCTemplate;
import org.ybygjy.spring.orderservice.entity.Order;

/**
 * 订单管理
 * @author WangYanCheng
 * @version 2016年10月7日
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderDaoImpl4JDBCTemplate orderDao;
    @RequestMapping("/")
    public ModelAndView listOrder(ModelAndView mv) {
        List<Order> orderList = this.orderDao.select(null);
        mv.addObject("orderList", orderList);
        mv.setViewName("/order/list");
        return mv;
    }
    @RequestMapping("/newOrder")
    public ModelAndView newOrder(ModelAndView mv) {
        Order order = new Order();
        mv.addObject("order", order);
        mv.setViewName("/order/OrderForm");
        return mv;
    }
    @RequestMapping(value="/saveOrder", method=RequestMethod.POST)
    public ModelAndView saveOrder(@ModelAttribute Order order) {
        this.orderDao.saveOrUpdate(order);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping("/deleteOrder")
    public ModelAndView deleteOrder(long orderId) {
        Order order = new Order();
        order.setId(orderId);
        this.orderDao.delete(order);
        return new ModelAndView("redirect:/");
    }
    @RequestMapping("/editOrder")
    public ModelAndView editOrder(long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order = this.orderDao.findById(order);
        ModelAndView mv = new ModelAndView("/order/OrderForm");
        mv.addObject("order", order);
        return mv;
    }
}
