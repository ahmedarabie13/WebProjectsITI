package com.arabie;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class MyRequestListener implements ServletRequestListener {

    public void requestDestroyed(ServletRequestEvent sre){
        // ServletContext servletContext=sre.getServletContext();
        // Object obj= servletContext.getAttribute("numOfRequests");
        // System.out.println("distroy");
        // if(obj instanceof Integer){
        //     Integer num = (Integer)obj;
        //     if(num==null){
        //         // num=1;
        //     }else{
        //         num--;
        //     }
        //     servletContext.setAttribute("numOfRequests",num);
        //     System.out.println(num);
        // }
        // servletContext.setAttribute("pla","pla");
        Integer num = (Integer) sre.getServletContext().getAttribute("numOfRequests");
        System.out.println(num);

    }
    public void requestInitialized(ServletRequestEvent sre){
        ServletContext servletContext=sre.getServletContext();
        Object obj= servletContext.getAttribute("numOfRequests");
        System.out.println("distroy");

        if(obj instanceof Integer){
            Integer num = (Integer)obj;
            if(num==null){
                num=1;
            }else{
                num++;
            }
            servletContext.setAttribute("numOfRequests",num);
        }
        else if(obj == null){
            servletContext.setAttribute("numOfRequests", Integer.valueOf(1));
        }
        
        servletContext.setAttribute("pla","hi");

    }

}