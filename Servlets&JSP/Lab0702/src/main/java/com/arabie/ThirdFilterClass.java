package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ThirdFilterClass implements Filter{
    public void init(FilterConfig filterConfig){

    } 
    public void destroy(){

    }
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
        System.out.println("Entering Third Filter");
        try{
            chain.doFilter(request,response);
        }
        catch(Exception e){
            e.printStackTrace();
        }        System.out.println("Exiting Third Filter");
    }
}