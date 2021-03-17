package com.arabie;
import jakarta.servlet.*;
import java.io.*;

public class Main implements Servlet{
	private ServletConfig servletConfig;
	public void init(ServletConfig config) throws ServletException
	{
		servletConfig=config;
		// System.out.println("I am inside the init method");
	}
	
	public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		// out.println("<br>Welcome to Servlets and JSP Course");
		String dbAddress = servletConfig.getInitParameter("DatabaseAddress")+"<br>";
        String userName = servletConfig.getInitParameter("userName")+"<br>";
        String password = servletConfig.getInitParameter("password")+"<br>";
        out.println("User Name is:  " + userName);
        out.println("Password is:  " + password);
        out.println("Database Address is:  " + dbAddress);
		System.out.println("I am inside the service method");
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