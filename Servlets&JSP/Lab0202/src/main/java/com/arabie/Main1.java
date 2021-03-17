package com.arabie;
import jakarta.servlet.*;
import java.io.*;

public class Main1 implements Servlet{
	public void init(ServletConfig config) throws ServletException
	{
		System.out.println("I am inside the init method");
	}
	
	public void service(ServletRequest request,ServletResponse response) throws ServletException, IOException
	{
		response.setContentType("application/xml");
		PrintWriter out = response.getWriter();
		// out.println("<User>Ahmed</User>");
		out.println("<student>\n<name>Tanmay</name>\n<grade>A</grade>\n</student>");		
		// out.println("\t jan \t feb \t march \t total");
		// out.println("salary \t100 \t200 \t300 \t=sum(B2:D2)");
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