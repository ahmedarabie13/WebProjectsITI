package com.arabie;

import jakarta.servlet.*;

import java.io.*;

import jakarta.servlet.http.*;

import java.sql.*;

public class Main extends HttpServlet {

    private Connection conn;

    public void init(ServletConfig config) throws ServletException {

        ServletContext servletContext = config.getServletContext();
        conn = (Connection) servletContext.getAttribute("Conn");

    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String query = request.getParameter("query");
        Statement statement = null;
        ResultSet resultSet;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<style>\n" +
                "table,th, td{\n" +
                "  border: 1px solid grey;\n" +
                "  }\n" +
                "</style>");
        out.println("<div style=\"margin: 10px;\">");
        out.println("<h1><b>SQL Tool</b></h1>");
        out.println("<textarea name=query form=\"queryForm\" style=\"width: 769px; height: 231px;\">" + ((query == null) ? "" : query) + "</textarea>\n");
        out.println("<FORM id=\"queryForm\" method= GET>");
        out.println("<BR><BR> <INPUT TYPE=SUBMIT VALUE=Execute>");
        out.println("</FORM>");
        out.println("<BR><BR>");
        out.println("<BR><BR>");
        if (!(query == null || query.equals(""))) {
            try {
                query = query.toLowerCase().trim();
                statement = conn.createStatement();

                if (query.startsWith("select")) {
                    resultSet = statement.executeQuery(query);
                    var metaData = resultSet.getMetaData();

                    if (metaData.getColumnCount() > 0) {
                        out.println("<table style=\"width: 50%;text-align: center;\">"
                                + "  <tr>");
                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                            out.println("<th>" + metaData.getColumnLabel(i) + "</th>");
                        }
                        out.println("</tr>");
                        while (resultSet.next()) {
                            out.println("<tr>");
                            for (int columnCnt = 1; columnCnt <= metaData.getColumnCount(); columnCnt++) {
                                out.println("<td>" + resultSet.getString(columnCnt) + "</td>");
                            }
                            out.println("</tr>");
                        }
                        out.println("</table> ");

                    }
                    out.println("</div>");
                } else if (query.startsWith("update")) {
                    int updatedRows = statement.executeUpdate(query);
                    out.println("<p>" + updatedRows + "  row(s) updated successfully</p>");
                } else if (query.startsWith("insert")) {
                    statement.executeUpdate(query);
                    out.println("<p>1 row inserted successfully</p>");
                } else if (query.startsWith("delete")) {
                    int deletedRows = statement.executeUpdate(query);
                    out.println("<p>" + deletedRows + "  row(s) deleted successfully</p>");
                } else {
                    out.println("<p style=\"color:red;\">Unsupported operation</p>");
                }
            } catch (SQLException e) {
                out.println("<p style=\"color:red;\">" + e.getMessage() + "</p>");
                e.printStackTrace();
            }
        }

    }


}
