package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class CookiesFilter implements Filter{
    private Cookie cookie = new Cookie("test", "test");

    public void init(FilterConfig filterConfig){

    }
    public void destroy(){

    }
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            if (request.getParameter("visited") == null) {
                ((HttpServletResponse) response).addCookie(cookie);
                ((HttpServletResponse) response).sendRedirect("TestCookiesUrl?visited=true");
                return;
            } else {
                if (((HttpServletRequest) request).getCookies() == null) {
                    out.println("<center><h4>Cookies are not Enabled</h4><br><br>");
                    request.setAttribute("Enabled","False");
                } else if (((HttpServletRequest) request).getCookies().length == 0) {
                    out.println("<center><h4>Cookies are not Enabled</h4><br><br>");
                    request.setAttribute("Enabled","False");
                } else {
                    out.println("<center><h4>Cookies are Enabled</h4><br><br>");
                    request.setAttribute("Enabled","True");
                }
            }
            chain.doFilter(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}