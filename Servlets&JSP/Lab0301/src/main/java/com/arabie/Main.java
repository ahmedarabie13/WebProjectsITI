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
		RequestDispatcher rd; 
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<FORM method= POST>");
		out.println("<BR> Username: <INPUT TYPE=TEXT NAME=userName>");
		out.println("<BR> Password: <INPUT TYPE=PASSWORD NAME=password>");
		out.println("<BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
		out.println("</FORM>");

		String error = (String)request.getAttribute("Error");
		if(error!=null)
			if(error.equals("true"))
				out.println("<BR> <span>incorrect data</span>");
		if(userName.equals("ahmed") && password.equals("1420")) 
		{
			rd= request.getRequestDispatcher("WelcomeServletUrl");
			rd.forward(request,response);
							
		}
		else { 
			// request.setAttribute("Error","true");
			// request.setAttribute("Visited","true");	
			rd= request.getRequestDispatcher("LoginServletUrl");
			String visited = (String)request.getAttribute("Visited");
			if(visited!=null){
				if(visited.equals("false")){
					System.out.println("not");
					request.setAttribute("Visited","true");
					request.setAttribute("Error","true");
					rd.forward(request,response);
				}
			}
			else{
				System.out.println("notVisited");
				request.setAttribute("Visited","true");
				request.setAttribute("Error","true");
				rd.forward(request,response);	
			}
			
		}		
	}
}