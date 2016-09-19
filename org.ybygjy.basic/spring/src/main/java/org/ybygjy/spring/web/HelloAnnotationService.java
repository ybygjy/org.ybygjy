package org.ybygjy.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/annotation")
public class HelloAnnotationService {
    
    @RequestMapping(path="doWork", method=RequestMethod.GET)
    public String doWork() {
        return "HelloAnnotation";
    }
}
