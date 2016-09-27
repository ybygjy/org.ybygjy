package org.ybygjy.spring.web;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Service Controller
 * @author WangYanCheng
 * @version 2016年9月19日
 */
@Controller
@RequestMapping("/helloWorld")
public class HelloWorldController implements ServletContextAware {
    private ServletContext servletContext;
    @RequestMapping("/sayHello")
    public String sayHello() {
        System.out.println("HelloWorld");
        this.beforeAction();
        return "HelloWorld";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
    private void beforeAction() {
        ApplicationContext appCtx = WebApplicationContextUtils.getWebApplicationContext(this.servletContext);
        LoginAction loginAction = (LoginAction) appCtx.getBean("loginActionSession");
        loginAction.setPath("/sessionScope");
        loginAction.doWork();
        LoginAction globalAction = (LoginAction) appCtx.getBean("globalAction");
        globalAction.setPath("/globalSessionScope");
        globalAction.doWork();
        LoginAction applicationAction = (LoginAction) appCtx.getBean("applicationAction");
        applicationAction.setPath("/applicationScope");
        applicationAction.doWork();
    }
}
