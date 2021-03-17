package com.arabie;
import jakarta.servlet.*;
import java.io.*;

public class SetterServlet implements Servlet{
	private ServletConfig servletConfig;
	public void init(ServletConfig config) throws ServletException
	{
		servletConfig=config;
		System.out.println("I am inside the init method");
	}
	
	public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException
	{
		ServletContext servletContext = servletConfig.getServletContext();
		servletContext.setAttribute("StringObject","hi");
		PrintWriter out = response.getWriter();
		String str= servletContext. getInitParameter("DollarExchangeRate");
		out.println("DollarExchangeRate is: " + str);
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