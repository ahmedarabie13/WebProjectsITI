package com.arabie;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Main extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("I am inside the init method");
    }

    private Cookie cookie = new Cookie("test", "test");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        if (request.getParameter("visited") == null) {
            response.addCookie(cookie);
            response.sendRedirect("TestCookiesUrl?visited=true");
        } else {
            if (request.getCookies() == null) {
                out.println("<h1>Cookies are not Enabled</h1>");
            } else if (request.getCookies().length == 0) {
                out.println("<h1>Cookies are not Enabled</h1>");
            } else {
                out.println("<h1>Cookies are Enabled</h1>");
            }
        }
    }
}