package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Main extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("uName");
		PrintWriter out = response.getWriter();
		if(name.equals("Ahmed")){
			out.println("Valid");
			System.out.println("valid");
		}
		else {
			out.println("inValid");
			System.out.println("inValid");
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
}