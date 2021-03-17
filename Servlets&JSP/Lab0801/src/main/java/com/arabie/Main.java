package com.arabie;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;

public class Main extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try{
			String fileName="Data.txt";
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition","attachment; filename=\""+fileName+"\"");
			var fileOutputStream = response.getOutputStream();
			var fileInputStream = request.getServletContext().getResourceAsStream("WEB-INF/file1.txt");
			byte[] data = fileInputStream.readAllBytes();
			fileOutputStream.write(data);
			System.out.print("I'm in download do post");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}