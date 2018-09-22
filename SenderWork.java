/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


 // This class sends messages to the  mysql database 
 

/**
 *
 * @author Syed Turab 
 */
public class SenderWork implements Runnable { // Runnbale for concurrency
    
    private volatile boolean exit = false;
    private MessageDao messageDao;

    public SenderWork(MessageDao messageDao) {
        this.messageDao = messageDao;
    }
    
    
    
    @Override
    public void run() {
        Database DB = Database.getInstance();
        Connection connection = DB.getConnection();
        
        
        while (true){
            //check count of messages in DB
            try {
                if(messageDao.getCountOfRecords()>=(Constants.MESSAGES_MAX_NUMBER)) {
                   messageDao.closeStatements();
                DB.closeConnection();
                break;
                }
            } catch (SQLException ex) {
                System.out.println("Couldn't execute count query:"+ex);
            }
            Sender sender = new Sender(Randomizer.generateRandomID(0));
            int receiverID= Randomizer.generateRandomID(sender.getID());
            String text= Randomizer.generateRandomMessage();
            
            Receiver receiver = new Receiver(receiverID);
            Message message = new Message(text,new Date(new java.util.Date().getTime()),receiver,0);
            try {
                messageDao.executeInsertQuery(message);
            } catch (SQLException ex) {
               System.out.println("Couldn't execute insert query:"+ex);
            }
            
            if(exit) {
                messageDao.closeStatements();
                DB.closeConnection();
                break;
            }
            
            try {
                Thread.sleep(30000);
            } catch (InterruptedException ex) {
               System.out.println("Thread interrupted:"+ex);
            }
            
        }
        
        
    }
    
    public void stop() {
        exit=true;
   
        
    }
    
}
