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
        out.println("<FORM method= POST>");
        out.println("<h1><b>Registration Form</b></h1>");
        out.println("<BR><BR> FirstName:  <INPUT TYPE=TEXT NAME=firstName>");
        out.println("<BR><BR> LastName:  <INPUT TYPE=TEXT NAME=lastName>");
        out.println("<BR><BR> Username:  <INPUT TYPE=TEXT NAME=userName>");
        out.println("<BR><BR> Password:  <INPUT TYPE=PASSWORD NAME=password>");
        out.println("<BR><BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("  <INPUT TYPE=RESET VALUE=Reset>");
        out.println("</FORM>");
        String error = request.getParameter("Error");
        if (error != null)
            if (error.equals("true"))
                out.println("<BR> <span>invalid data</span>");
        out.println("</center>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String firstName = request.getParameter("firstName");
        String password = request.getParameter("password");
        String lastName = request.getParameter("lastName");

        if (!userName.equals("") && !firstName.equals("") && !password.equals("") && !lastName.equals("")) {
            try {
                final String SQL_INSERT = "INSERT INTO user (`user_name`, `password`, `first_name`, `last_name`) VALUES (?, ?, ?, ?);";
                PreparedStatement pStmt = conn.prepareStatement(SQL_INSERT);
                pStmt.setString(1, userName);
                pStmt.setString(2, password);
                pStmt.setString(3, firstName);
                pStmt.setString(4, lastName);
                pStmt.executeUpdate();
                response.sendRedirect("LoginServletUrl?userName=" + userName);
            } catch (SQLException e) {
                response.sendRedirect("RegisterServletUrl?Error=" + "true");
                e.printStackTrace();
            }

        } else {
            response.sendRedirect("RegisterServletUrl?Error=" + "true");
        }
    }


}