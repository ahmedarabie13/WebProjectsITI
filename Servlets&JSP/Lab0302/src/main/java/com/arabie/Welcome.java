package com.arabie;

import com.arabie.entities.UserEntity;
import jakarta.servlet.*;

import java.io.*;
import java.sql.*;
import java.util.concurrent.*;

import jakarta.servlet.http.*;


public class Welcome extends HttpServlet {
    private ConcurrentLinkedDeque<UserEntity> retrievedUsers = new ConcurrentLinkedDeque<>();
    private Connection conn;
    private ServletContext servletContext;

    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        conn = (Connection) servletContext.getAttribute("Connection");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String userName = request.getParameter("userName");
        servletContext.setAttribute("userName", userName);
        out.println("<center>");
        out.println("<BR><h1><b>Search Form</b></h1>");
        out.println("<br><h3>Welcome " + userName + "</h3>");
        out.println("<FORM method= POST>");
        out.println("<br><h3>Please Enter First Name,Last Name or any part of them.</h3>");
        out.println("<b>Name: </b> <INPUT TYPE=TEXT NAME=name>");
        out.println("  <INPUT TYPE=SUBMIT VALUE=Submit>");
        out.println("</FORM>");
        out.println("<BR><BR>");
        out.println("<table style=\"width: 90%;text-align: center;\">"
                + "  <tr>" +
                "       <th>First Name</th>" +
                "       <th>Last Name</th>" +
                "       <th>UserName</th>" +
                "       <th>Password</th>" +
                "   </tr>");
        for (UserEntity user : retrievedUsers) {
            out.println("<tr>"
                    + "<td>" + user.getFirstName() + "</td>"
                    + "<td>" + user.getLastName() + "</td>"
                    + "<td>" + user.getUserName() + "</td>"
                    + "<td>" + user.getPassword() + "</td>"
                    + "</tr>");
        }
        out.println("</table> "
                + "</center>");
        retrievedUsers.clear();

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchElement = request.getParameter("name");
        final String SQL_SELECT = "Select * from user where first_name like ? or last_name like ?;";
        PreparedStatement pStmt = null;
        try {
            pStmt = conn.prepareStatement(SQL_SELECT);
            pStmt.setString(1, "%" + searchElement + "%");
            pStmt.setString(2, "%" + searchElement + "%");
            ResultSet resultSet = pStmt.executeQuery();
            retrievedUsers.clear();
            while (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                retrievedUsers.add(user);
            }
            String userName = (String) servletContext.getAttribute("userName");
            response.sendRedirect("WelcomeServletUrl?userName=" + userName);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}