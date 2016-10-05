package org.ybygjy.spring.orderservice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.ybygjy.spring.orderservice.dao.SysUserDao;
import org.ybygjy.spring.orderservice.entity.SysUser;

@Controller
public class LoginController {
    @Autowired
    private SysUserDao sysUserDao;
    private static final String loginUri = "/amazeui/login.jsp";
    private static final String welcomeUri = "/amazeui/admin-index.jsp";
    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView(loginUri);
        return mv;
    }
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response) {
        String userNo = request.getParameter("user_no");
        String password = request.getParameter("password");
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(userNo);
        sysUser = sysUserDao.selectOne(sysUser);
        ModelAndView mv = new ModelAndView(loginUri);
        if (null != sysUser && sysUser.getPassword().equals(password)) {
            request.getSession(true).setAttribute("APP_LOGIN_USER", sysUser);
            request.setAttribute("loginSysUser", sysUser);
            mv.addObject("sysUserInfo", sysUser);
            mv.setViewName(welcomeUri);
        } else {
            request.setAttribute("message", "用户名或密码错误!");
        }
        return mv;
    }
    @RequestMapping(value="/logout")
    public ModelAndView executeLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).invalidate();
        return new ModelAndView(loginUri);
    }
}
