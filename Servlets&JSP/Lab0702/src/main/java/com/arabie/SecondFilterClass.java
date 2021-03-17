package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class SecondFilterClass implements Filter{
    public void init(FilterConfig filterConfig){

    }
    public void destroy(){

    }
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
        try{
            System.out.println("Entering Second Filter");
            String name =(String) request.getParameter("userName");
            if(name!=null){
                if(name.equals("")){
                    if(response instanceof HttpServletResponse){
                        ((HttpServletResponse)response).sendRedirect("View.jsp");
                        return;
                    }
                }
            }
            
            chain.doFilter(request,response);
        }
        catch(Exception e){
            e.printStackTrace();
        }        System.out.println("Exiting Second Filter");
    }
}