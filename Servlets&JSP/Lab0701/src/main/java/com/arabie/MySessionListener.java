package com.arabie;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;

public class MySessionListener implements HttpSessionListener{
    
    public void sessionCreated(HttpSessionEvent se){
        ServletContext context = se.getSession().getServletContext();
        Object obj=context.getAttribute("numOfSessions");
        Integer num;
        System.out.println("Session Created");
        if(obj instanceof Integer){
            num = (Integer) obj;
            num++;
        }    
        else {
            num=1;
        }
        context.setAttribute("numOfSessions",num);
        System.out.println("Number of Sessions: "+num);
        saveNumOfSessions(num);
        
    }

    public void sessionDestroyed(HttpSessionEvent se){
        System.out.println("Session destroyed");
        ServletContext servletContext = se.getSession().getServletContext();
        Object obj = servletContext.getAttribute("numOfSessions");
        Integer num;
        if(obj instanceof Integer){
            num=(Integer) obj;
            if(num>0){
                num--;
            }
        }else {
            num=0;
        }
        servletContext.setAttribute("numOfSessions",num);
        System.out.println("Number of Sessions: "+num);
        saveNumOfSessions(num);
    }

    private void saveNumOfSessions(Integer num){
        try{
            FileWriter fileWriter = new FileWriter(new File("D:\\sessionData.txt"));
            PrintWriter printWriter = new PrintWriter(fileWriter,true);
            printWriter.println("Number of Sessions: "+num);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}