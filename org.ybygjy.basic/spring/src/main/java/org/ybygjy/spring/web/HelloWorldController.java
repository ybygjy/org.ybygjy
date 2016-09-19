package org.ybygjy.spring.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

/**
 * Service Controller
 * @author WangYanCheng
 * @version 2016年9月19日
 */
public class HelloWorldController implements org.springframework.web.servlet.mvc.Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", "HelloWorld");
        modelAndView.setViewName("HelloWorld");
        return modelAndView;
    }
}
