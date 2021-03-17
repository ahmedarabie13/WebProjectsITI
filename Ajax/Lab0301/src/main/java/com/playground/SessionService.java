package com.playground;

import com.playground.model.User;
import jakarta.websocket.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionService {

    private  static  SessionService sessionService = null;
    private static Map<Session, User> users = new ConcurrentHashMap<>();

    private  SessionService(){}

    public  Map<Session, User> getUsers(){
        return  users;
    }


    public  static  SessionService getInstance(){

        if(sessionService == null){
            synchronized (SessionService.class){
                if(sessionService == null){
                    sessionService = new SessionService();
                }
            }
        }
        return  sessionService;
    }

}
