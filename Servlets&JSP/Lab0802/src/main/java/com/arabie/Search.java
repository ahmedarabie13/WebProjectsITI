package com.arabie;

import com.arabie.daos.UserDao;
import com.arabie.entities.UserEntity;
import jakarta.servlet.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import jakarta.servlet.http.*;


public class Search extends HttpServlet {
    private List<UserEntity> retrievedUsers = new ArrayList<>();
    private Connection conn;
    private ServletContext servletContext;

    public void init(ServletConfig config) throws ServletException {
        servletContext = config.getServletContext();
        conn = (Connection) servletContext.getAttribute("Connection");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchElement = request.getParameter("name");
        UserDao userDao = new UserDao(conn);
        retrievedUsers = userDao.searchUsers(searchElement);
    
		RequestDispatcher requestDispatcher =request.getRequestDispatcher("SearchView.jsp");
        request.setAttribute("SearchedUsers",retrievedUsers);
        requestDispatcher.forward(request,response);

    }

}