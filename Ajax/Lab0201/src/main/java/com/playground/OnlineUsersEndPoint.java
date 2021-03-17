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
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/onlineusers")
public class OnlineUsersEndPoint {


    @OnOpen
    public  void onOpen( Session session){
        System.out.println("Online users endpoint");

    }

    @OnMessage
    public  void onMessage(String msg , Session session){
        System.out.println("Online users endpoint got msg");

        Gson gson = new Gson();

        try{
            session.getBasicRemote().sendText(gson.toJson(SessionService.getInstance().getUsers().values()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session){


    }

}
