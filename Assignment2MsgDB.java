/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

// Main Class 

/**
 *
 * @author Syed Turab 
 */
public class Assignment2MsgDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Database DB = Database.getInstance(); // Database Connection
        Connection connection = DB.getConnection();
        MessageDao messageDao = new MessageDao(connection); // calling the MessageDao class  
        try {
            messageDao.prepareStatements();
        } catch (SQLException ex) {
            System.out.println("Couldn't create statements:" + ex); // this exception displays when a statement could not be created
        }
        SenderWork work = new SenderWork(messageDao); // new instance of class SenderWork with the MessageDao constructor created above 
        Thread thread = new Thread(work); // creating a new thread with the SenderWork constructor  work 
       thread.setDaemon(false); //dameon thread is set to false  boolean
        thread.start(); // starting the thread 

        Scanner scanner = new Scanner(System.in); // scanner 
        while (true) { 
            System.out.println("1-Read Messages"); // User presses 1  to make entry
            System.out.println("2-Exit"); // user presses 2 to exit
            System.out.println("Make your choice:"); // user enters their choice
            int menuSelect = scanner.nextInt(); //  user input is scanned

            if (menuSelect == 1) { // if the user selects 1 

                int receiverID; // integer type for reciver ID 
                System.out.println("Enter Receiver ID: "); // User is prompted to enter a user ID
                receiverID = scanner.nextInt(); 
                List<Message> messages = null; // Array list called messages is set to empty/null
                try {
                    messages = messageDao.getMessagesByID(receiverID); // the messages array list checks the messageDao call to get messages from the database based on recieverID
                    
                    if(messages.isEmpty()) // if the corresponding ID has no message
                    {
                        System.out.println("There is no Messages"); // the program displays that there are no messages with that ID
                        continue;
                    }
                    for(Message msg:messages) { // however if the user entered ID has a a message the progrsam gets the ID for the user to read the message
                        System.out.println("ID:"+msg.getID()); // getting ID to read the message
                        System.out.println("Status:"+msg.getStatus()); // getting the message status 
                        System.out.println("Date:"+msg.getDate()); // getting the date
                        System.out.println("-------------------------");  
                    }
                    System.out.println("Enter ID to read message"); // The user now enters the ID to read the message 
                    int ID= scanner.nextInt(); // scans the ID 
                    Message message= null; 
                    for(Message msg:messages) {
                        if(msg.getID()==ID) // if messageid is the same as the reciever ID then the message is able to display 
                     message=msg; 
                    }
                    if(message==null) { // if the ID entered is incorrect 
                        System.out.println("Incorrect input");
                    }
                    else {
                        System.out.println(message.getText()); // else now the message is extracted from the database and displayed
                        messageDao.updateStatusRecord(ID, message.getStatus());
                    }
                } catch (SQLException ex) {
                    System.out.println("Couldn't execute  query:" + ex); // expection if say the user enters a message ID that is not valid such as a char etc..
                }

            } else {
                work.stop(); 
                System.exit(0);

            }

        }
    }
}

    
    

