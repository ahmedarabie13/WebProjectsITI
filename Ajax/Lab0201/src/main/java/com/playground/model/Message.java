package com.playground.model;

public class Message {

    private  int id;
    private  String timeStamp;
    private  String msgContent;
    private int senderId;
    private  User sender;


    public int getSenderId() {
        return senderId;


    }


    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", timeStamp='" + timeStamp + '\'' +
                ", msgContent='" + msgContent + '\'' +
                '}';
    }
}
