package com.arabie;
import jakarta.servlet.*;
import java.io.*;

public class GetterServlet implements Servlet{
    private ServletConfig servletConfig;
    public void init(ServletConfig config) throws ServletException
    {
        servletConfig=config;
        System.out.println("I am inside the init method");
    }

    public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException
    {
        ServletContext servletContext = servletConfig.getServletContext();
        Object contextObj =servletContext.getAttribute("StringObject");
        PrintWriter out = response.getWriter();
//        if(contextObj instanceof String){
        out.println((String) contextObj);
//        }
        String str= servletContext. getInitParameter("DollarExchangeRate");
        out.println("GetterDollarExchangeRate is: " + str);
    }

    public void destroy()
    {
        System.out.println("I am inside the destroy method");
    }

    public String getServletInfo()
    {
        return null;
    }

    public ServletConfig getServletConfig()
    {
        return null;
    }

}