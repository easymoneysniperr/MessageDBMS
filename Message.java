/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;
import java.sql.Date; // date package 

// This class is for the Message data types , setters and getters for ID, text, data, reciever and status 
/**
 *
 * @author Syed Turab 
 */
public class Message {
    private int ID; // private integer for ID
    private String text; // private string for text
    private Date date; // private Data type for date (libary: import java.sql.Date;) 
    private Receiver receiver;  //  to call receiver class 
    private int status; // status 

    public Message() { // no argument constructor 

    }
 /*Below are the setters and getters*/ 
    public Message(String text, Date date, Receiver receiver, int status) {
        this.text = text;
        this.date = date;
        this.receiver = receiver;
        this.status = status;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public int getStatus() {
        return status;
    }

    public int getID() {
        return ID;
    }

}


