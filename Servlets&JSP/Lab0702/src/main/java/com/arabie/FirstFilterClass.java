package com.arabie;

import java.io.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class FirstFilterClass implements Filter{
    public void init(FilterConfig filterConfig){

    }
    public void destroy(){

    }
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
        try{
            System.out.println("Entering First Filter");
            PrintWriter printWriter =new PrintWriter(new FileWriter(new File("D:\\ipData.txt"),true),true);
            
            printWriter.println(request.getRemoteAddr());
            chain.doFilter(request,response);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Exiting First Filter");
    }
}