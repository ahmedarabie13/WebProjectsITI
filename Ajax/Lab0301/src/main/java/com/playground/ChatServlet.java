package com.playground;


import com.google.gson.Gson;
import com.playground.model.Message;
import com.playground.model.User;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;



public class ChatServlet extends HttpServlet{

	private static Map<HttpSession,User> users = new ConcurrentHashMap<>();
	private static AtomicInteger userId = new AtomicInteger(0);

	AtomicInteger counter = new AtomicInteger(0);

	private static List<Message> messages = new CopyOnWriteArrayList<>();
	private static AtomicInteger msgId = new AtomicInteger(0);

	public void init(ServletConfig config) throws ServletException {


	}


	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

//		if(req.getParameter("init") != null){
//			System.out.println("First Time");
//		}

		resp.setContentType("text/event-stream");
		resp.setCharacterEncoding("UTF-8");
		resp.setHeader("Cache-Control", "no-cache");
		resp.setHeader("Connection", "keep-alive");

		PrintWriter out = resp.getWriter();

		Gson gson  = new Gson();

		String hdr =  req.getHeader("Last-Event-ID");
		List<Message> unsentMsgs = new ArrayList<>();
		if(hdr != null){
			int lastId = Integer.parseInt(hdr);

//			lastId++;

			System.out.println("Last id HEADER"+ lastId);
//			Integer id = (Integer) req.getSession().getAttribute("lastMsgId");
//			System.out.println("Last id from session" + lastId);

			int i ;
			for( i = lastId ; i < messages.size() ; i++){
				if(i < 0 ) continue;
				unsentMsgs.add( messages.get(i) );
			}
			req.getSession().setAttribute("lastMsgId",i);
		}
		System.out.println("Msg Id generatoe " + msgId.get());


		String jsonUsers = gson.toJson(users.values());
		String jsonMessages = gson.toJson(unsentMsgs);


		out.write("event: users\n");
		out.write("data: "+jsonUsers+"\n\n");

		Integer id = (Integer) req.getSession().getAttribute("lastMsgId");
		Integer lastMsgId = null;
		if(id == 0) {
			lastMsgId = 0;
		}else {
			lastMsgId = msgId.get();
		}
		out.write("id: "+lastMsgId+"\n");
		out.write("event: messages\n");
		out.write("data: "+jsonMessages+"\n\n");
		out.flush();
		out.close();

	}

	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{


		counter.getAndIncrement();
		if(req.getParameter("user") != null){
			System.out.println("New User is Sent");
			String jsonUser = req.getParameter("user");

			System.out.println(jsonUser);

			Gson gson = new Gson();
			User user = gson.fromJson(jsonUser, User.class);
			user.setId(userId.getAndIncrement());
			HttpSession session = req.getSession();
			session.setAttribute("lastMsgId",0);
			users.put(session,user);
		} else if(req.getParameter("logout") != null){

			HttpSession session = req.getSession();
			users.remove(session);
			session.invalidate();

		}else if(req.getParameter("msg") != null){
			String msgContent = req.getParameter("msg");

			HttpSession session = req.getSession();
			User msgSender = users.get(session);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();

			Message newMsg = new Message(msgId.getAndIncrement(),dtf.format(now),msgContent,msgSender);
			messages.add(newMsg);
		}

	}
	


}
	