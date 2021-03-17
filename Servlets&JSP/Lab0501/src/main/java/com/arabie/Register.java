package com.arabie;

import jakarta.servlet.*;

import java.io.*;

import jakarta.servlet.http.*;

import java.sql.*;


public class Register extends HttpServlet {
    private Connection conn = null;
    private ServletContext servletContext;

    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        conn = (Connection) servletContext.getAttribute("Connection");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<center>");
        out.println("<FORM action=Register.jsp method= GET>");
        out.println("<h1><b>Registration Form</b></h1>");
        out.println("<BR><BR> FirstName:  <INPUT TYPE=TEXT NAME=firstName>");
        out.println("<BR><BR> LastName:  <INPUT TYPE=TEXT NAME=lastName>");
        out.println("<BR><BR> Username:  <INPUT TYPE=TEXT NAME=userName>");
        out.println("<BR><BR> Password:  <INPUT TYPE=PASSWORD NAME=password>");
        out.println("<BR><BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("  <INPUT TYPE=RESET VALUE=Reset>");
        out.println("</FORM>");
        out.println("</center>");
    }

}