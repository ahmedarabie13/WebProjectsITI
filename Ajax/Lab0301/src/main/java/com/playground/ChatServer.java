package com.playground;

import com.google.gson.Gson;
import com.playground.model.Message;
import com.playground.model.User;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/chatendpoint/{data}")
public class ChatServer {

    private static Map<Session,User> users = SessionService.getInstance().getUsers();
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    private static AtomicInteger counter  = new AtomicInteger(0);
    private static AtomicInteger msgIdCounter  = new AtomicInteger(0);


    private static ConcurrentLinkedDeque<Message> messages = new ConcurrentLinkedDeque<>();

    @OnOpen
    public  void onOpen(@PathParam("data") String jsonString, Session session)   {

        System.out.println("New Session, No of active now are: " + (counter.getAndIncrement()+1));

        Gson gson = new Gson();
        String str = "{"+jsonString +"}";
        User user =  gson.fromJson(str, User.class);

        user.setId(atomicInteger.getAndIncrement());

        users.put(session,user);

        System.out.println(user);
        System.out.println("I opened");

        try {
            session.getBasicRemote().sendText(gson.toJson(messages));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnMessage
    public  void onMessage(String msg , Session session){
        System.out.println("I got a msgG");

        Message newMsg = new Message();
        newMsg.setMsgContent(msg);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        newMsg.setTimeStamp(dtf.format(now));
        newMsg.setId(msgIdCounter.getAndIncrement());
        User sender = users.get(session);
        newMsg.setSenderId(sender.getId());
        newMsg.setSender(sender);

        messages.add(newMsg);

        Gson gson = new Gson();


        users.entrySet().forEach(sessionUserEntry -> {
            try {
                sessionUserEntry.getKey().getBasicRemote().sendText(gson.toJson(messages));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @OnClose
    public void onClose(Session session){

        System.out.println( counter.decrementAndGet());
        System.out.println("EP Closed");
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        users.remove(session);
    }

}
