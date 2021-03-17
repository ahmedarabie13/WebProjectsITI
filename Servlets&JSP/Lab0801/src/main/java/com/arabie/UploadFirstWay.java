package com.arabie;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "UploadFileServlet", urlPatterns = {"/upload"})
@MultipartConfig
public class UploadFirstWay extends HttpServlet{
    public void doPost(HttpServletRequest request,HttpServletResponse response){
        try {
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);
            PrintWriter out = response.getWriter();
            filePart.write(fileName);
            out.println("New file: " + fileName + " has been uploaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {

                // String[] names=content.substring(content.indexOf('=')+1).trim().split("\\");
                // System.out.print("names: "+names[names.length-1]);
                // return names[names.length-1];
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
            return null;
        }

} 