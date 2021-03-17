package com.arabie;


import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Main extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        System.out.println("I am inside the init method");
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String cookiesStatus = (String) request.getAttribute("Enabled");
        if(cookiesStatus!=null){
            if(cookiesStatus.equals("True"))
                out.println("<h1><b>Welocome Page</b></h1>");
        }
        out.println("</center>");
    }
}