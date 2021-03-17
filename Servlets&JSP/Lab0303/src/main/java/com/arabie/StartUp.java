package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import javax.naming.*;
import javax.sql.*;
import java.sql.*;

public class StartUp extends HttpServlet {
    DataSource ds;
    Context initContext;
    Context envContext;
    private Connection conn = null;

    public void init(ServletConfig config) throws ServletException {
        try {
            initContext = new InitialContext();
            envContext = (Context) initContext.lookup("java:/comp/env");
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
            conn = ds.getConnection();
            ServletContext servletContext = config.getServletContext();
            servletContext.setAttribute("Connection", conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
