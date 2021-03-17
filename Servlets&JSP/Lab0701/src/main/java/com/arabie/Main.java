package com.arabie;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Main implements Servlet{
	public void init(ServletConfig config) throws ServletException
	{
		System.out.println("I am inside the init method");
	}
	
	public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<br>Welcome to Servlets and JSP Course");
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