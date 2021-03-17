package com.arabie;

import com.google.gson.Gson;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends HttpServlet {
    private List<MessageDto> messages = new ArrayList<>();
    private Integer id = 0;
//    private Integer lastId = 0;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String sender = request.getParameter("name");
        String message = request.getParameter("message");
        synchronized (id) {
            id++;
        }
        MessageDto messageDto = new MessageDto(id, sender, message);
        synchronized (messages) {
            messages.add(messageDto);
        }
        out.println(buildGsonFromObject());
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println(buildGsonFromObject());
    }
    private String buildGsonFromObject() {
        Gson gsonUser = new Gson();
        return gsonUser.toJson(messages);
    }
}