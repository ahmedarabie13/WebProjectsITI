package com.arabie;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import java.util.*;

public class MyContextListener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent sce){
        try{
            Scanner scanner = new Scanner(new File("D:\\data.txt"));
            while(scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void contextDestroyed(ServletContextEvent sce){
        try{
            ServletContext ServletContext = sce.getServletContext();
            Integer numOfRequests = (Integer) ServletContext.getAttribute("numOfRequests");
            System.out.println("inside destroy");
            FileWriter fileWriter = new FileWriter(new File("D:\\data.txt"));
            PrintWriter printWriter = new PrintWriter(fileWriter,true);
            printWriter.println("Number Of Requests: "+numOfRequests);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}