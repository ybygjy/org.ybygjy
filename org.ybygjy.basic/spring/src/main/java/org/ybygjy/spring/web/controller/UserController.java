package org.ybygjy.spring.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.ybygjy.spring.web.entity.User;
import org.ybygjy.spring.web.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/list")
    public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("user.age", request.getParameter("user.age"));
        List<User> userList = this.userService.queryList(dataMap);
        model.addAttribute("userList", userList);
        return "/user/list";
    }
    @RequestMapping("/fireException")
    public String fireException(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("rtn_flag") != null) {
            throw new RuntimeException("异常信息!");
        }
        return "/user/list";
    }
}
