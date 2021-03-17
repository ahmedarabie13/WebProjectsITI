package com.arabie;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;

public class Welcome extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		out.println("<h1>Welcome "+userName+"</h1>");
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
	
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String userName = request.getParameter("userName");
		out.println("<h1>Welcome "+userName+"</h1>");
		// String userName = request.getParameter("userName");
		// String password = request.getParameter("password");
		// request.setParameter("Error","true");
		// request.setParameter("Error","false");
		// If(userName.equals("ahmed") && password.equals("1420")) 
		// {
	
		// 	response.setParameter("Error","false");
		// 	response.sendRedirect("WelcomeServletURL?userName="+ userName);
	
		// }
		// else { 
	
		// 	response.setParameter("Error","true");
		// 	response.sendRedirect("MySecondServlet?userName="+ userName + "&password=" + password);
	
		// }		
	}
}