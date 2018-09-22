/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;


// This Class has all the constants so the database connection link, database connection , user, password, the queries, and the user constants

/**
 *
 * @author Syed Turab 
 */
public class Constants {
     //DB Connection
    public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/messagedb";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "12345";

    //SQL queries
    public static final String INSERT_SQL = "INSERT INTO MESSAGES (message,datesent,receiverID,status)  VALUES (?,?,?,?)";
    public static final String SELECT_SQL = "SELECT * FROM MESSAGES WHERE RECEIVERID = ?";
    public static final String UPDATE_SQL= "UPDATE MESSAGES SET STATUS = ? WHERE ID = ?;";
    public static final String COUNT_SQL= "SELECT COUNT(ID) FROM MESSAGES";

    //User Constants
    public static final int USERS_MAX_NUMBER = 10;
    public static final int MESSAGES_MAX_NUMBER=10;
}
 





