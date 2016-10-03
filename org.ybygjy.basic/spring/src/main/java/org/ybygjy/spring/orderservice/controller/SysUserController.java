package org.ybygjy.spring.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.ybygjy.spring.orderservice.entity.SysUser;
import org.ybygjy.spring.orderservice.service.SysUserService;

@Controller
@RequestMapping("/sysuser")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;
    @RequestMapping("/list")
    public String list(@RequestParam("user_no")String userNo, Model model) {
        List<SysUser> sysUserList = this.sysUserService.getAllSysUser(10, 0);
System.out.println(sysUserList);
        model.addAttribute("sysUserList", sysUserList);
        return "/orderservice/list";
    }
}
