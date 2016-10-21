package org.ybygjy.spring.orderservice.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping("/list")
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
        if (order.getSendTime() == null) {
            order.setSendTime(new Date());
        }
        this.orderDao.saveOrUpdate(order);
        return this.listOrder(new ModelAndView());
    }
    @RequestMapping("/deleteOrder")
    public ModelAndView deleteOrder(@RequestParam("order_id")long orderId) {
        Order order = new Order();
        order.setId(orderId);
        this.orderDao.delete(order);
        return new ModelAndView("/order/list");
    }
    @RequestMapping("/editOrder")
    public ModelAndView editOrder(@RequestParam("order_id")long orderId) {
        Order order = new Order();
        order.setId(orderId);
        order = this.orderDao.findById(order);
        if (null == order) {
            throw new RuntimeException("订单不存在！");
        }
        ModelAndView mv = new ModelAndView("/order/OrderForm");
        mv.addObject("order", order);
        return mv;
    }
}
