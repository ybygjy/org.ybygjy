package org.ybygjy.spring.orderservice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.ybygjy.spring.orderservice.dao.SysUserDao;
import org.ybygjy.spring.orderservice.entity.SysUser;

@Controller
@RequestMapping("/login")
@SessionAttributes("currentUser")
public class LoginController {
    @Autowired
    private SysUserDao sysUserDao;
    private static final String loginUri = "/login";
    private static final String welcomeUri = "/admin-index";
    /**
     * 初始基础数据
     * @return dataMap
     */
    @ModelAttribute("config_data")
    public Map<String, String> initData() {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("config.def.pagesize", "20");
        return dataMap;
    }
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView defaultView() {
        ModelAndView mv = new ModelAndView(loginUri);
        return mv;
    }
    @RequestMapping(params="method=login", method=RequestMethod.POST)
    public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response, SessionStatus sessionStatus) {
Object obj = request.getAttribute("config_data");
System.out.println("config.data=>" + obj);
        String userNo = request.getParameter("user_no");
        String password = request.getParameter("password");
        SysUser sysUser = new SysUser();
        sysUser.setUserNo(userNo);
        sysUser = sysUserDao.selectOne(sysUser);
        ModelAndView mv = new ModelAndView(loginUri);
        if (null != sysUser && sysUser.getPassword().equals(password)) {
            mv.getModelMap().addAttribute("currentUser", sysUser);
            mv.setViewName(welcomeUri);
        } else {
            request.setAttribute("message", "用户名或密码错误!");
        }
        return mv;
    }
    @RequestMapping(params="method=logout", method=RequestMethod.GET)
    public ModelAndView executeLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession(true).invalidate();
        return new ModelAndView(loginUri);
    }    
}
