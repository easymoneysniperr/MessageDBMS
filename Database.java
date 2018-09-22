/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;
import java.sql.*;
import java.util.*;

// this class provides connection to database 
/**
 *
 * @author Syed Turab 
 */

 public class Database {
    private Connection connection;

    private Database() {

    }

    public static Database getInstance() {
        return new Database();
    }
    
    //get Connection to Database
    public Connection getConnection() {
        if (connection == null) {

            try {
                Class.forName(Constants.DB_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Where is your MySQL JDBC Driver?");
                e.printStackTrace();

            }

            try {
                connection = DriverManager.getConnection(
                        Constants.DB_CONNECTION, Constants.DB_USER, Constants.DB_PASSWORD);
            } catch (SQLException ex) {
                System.out.println("Connection Failed! Check output console");
                ex.printStackTrace();
            }
        }

        return connection;
    }
    
    public void closeConnection() {
        if(connection!=null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println("Couln't close connection to DB"+ex);
            }
        }
    }

}
 





