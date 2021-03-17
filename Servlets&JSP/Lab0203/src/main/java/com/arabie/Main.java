package com.arabie;
import jakarta.servlet.*;
import java.io.*;
import jakarta.servlet.http.*;
import java.util.*;

public class Main extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<h1>Headers<h1/><br>");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			out.print("<h2>Header Name: " + headerName);
			String headerValue = request.getHeader(headerName);
			out.println(" Header Value: " + headerValue+"</h2><br/>");
		}
	}

}