package org.ybygjy.spring.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BTraceServlet extends HttpServlet {
    /**
     * serial number
     */
    private static final long serialVersionUID = 3802654659007695958L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Service entrance.......");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.getOutputStream().write("BTrace Monitor...".getBytes());
        System.out.println("Service finished.......");
    }
}
