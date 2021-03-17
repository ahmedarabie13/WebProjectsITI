package com.arabie;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;

public class Main extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<FORM method= POST>");
		out.println("<BR> Username: <INPUT TYPE=TEXT NAME=userName>");
		out.println("<BR> Password: <INPUT TYPE=PASSWORD NAME=password>");
		out.println("<BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
		out.println("</FORM>");
		String error = request.getParameter("Error");
		if(error!=null)
			if(error.equals("true"))
				out.println("<BR> <span>incorrect data</span>");
	}

	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		// request.setParameter("Error","true");
		// request.setParameter("Error","false");
		if(userName.equals("ahmed") && password.equals("1420")) 
		{
	
			// response.setParameter("Error","false");
			response.sendRedirect("WelcomeServletUrl?userName="+ userName +"&Error="+ "false");
	
		}
		else { 
	
			// response.setParameter("Error","true");
			response.sendRedirect("LoginServletUrl?Error="+ "true");
	
		}		
	}
}