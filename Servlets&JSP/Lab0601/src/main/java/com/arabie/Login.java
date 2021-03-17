package com.arabie;

import jakarta.servlet.*;

import java.io.*;

import jakarta.servlet.http.*;

import java.sql.*;

public class Login extends HttpServlet {

    private Connection conn;

    public void init(ServletConfig config) throws ServletException {

        ServletContext servletContext = config.getServletContext();
        conn = (Connection) servletContext.getAttribute("Connection");

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String userName = request.getParameter("userName");
        PrintWriter out = response.getWriter();
        out.println("<center>");
        out.println("<FORM method= POST>");
        out.println("<h1><b>Login Form</b></h1>");
        out.println("<BR><BR> Username: <INPUT TYPE=TEXT NAME=userName value="+((userName==null)?"":userName)+">");
        out.println("<BR><BR> Password: <INPUT TYPE=PASSWORD NAME=password>");
        out.println("<BR><BR> <INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("</FORM>");
        String error = request.getParameter("Error");
        if (error != null)
            if (error.equals("true"))
                out.println("<BR> <span>incorrect data</span>");
        out.println("</center>");
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String userName = request.getParameter("userName");
            String storedUserName = "";
            String password = request.getParameter("password");
            String storedPassword = "";
            final String SQL_SELECT = "Select * from user where user_name = ?;";

            PreparedStatement pStmt = conn.prepareStatement(SQL_SELECT);
            pStmt.setString(1, userName);
            ResultSet resultSet = pStmt.executeQuery();
            while (resultSet.next()) {
                storedUserName = resultSet.getString("user_name");
                storedPassword = resultSet.getString("password");
            }
            if (userName.equals(storedUserName) && password.equals(storedPassword)) {
                System.out.println("matched");
                response.sendRedirect("WelcomeServletUrl?userName=" + userName);
            } else {
                response.sendRedirect("LoginServletUrl?Error=" + "true");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}