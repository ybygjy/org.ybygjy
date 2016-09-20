package org.ybygjy.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Service Controller
 * @author WangYanCheng
 * @version 2016年9月19日
 */
@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController {
    @RequestMapping("/sayHello")
    public String sayHello() {
        System.out.println("HelloWorld");
        return "HelloWorld";
    }
}
