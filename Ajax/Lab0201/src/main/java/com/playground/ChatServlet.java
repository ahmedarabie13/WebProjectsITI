package com.playground;


import com.playground.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;



public class ChatServlet extends HttpServlet{

	AtomicInteger counter = new AtomicInteger(0);

	public void init(ServletConfig config) throws ServletException {


	}


	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{


		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
//		resp.setHeader("Cache-Control", "no-cache");
//		resp.setHeader("Connection", "keep-alive");

		PrintWriter out = resp.getWriter();

		out.write("data:  "+counter.get()+"  \n\n");
		out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{


		counter.getAndIncrement();


	}
	


}
	