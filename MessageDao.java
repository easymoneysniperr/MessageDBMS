/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package assignment2msgdb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


//This class provides access to DB



/**
 *
 * @author Syed Turab a
 */
public class MessageDao {
private final Connection DBConnection; // database connection 

    private PreparedStatement selectPreparedStatement; // private prepared statement for selection declaration 
    private PreparedStatement insertPreparedStatement;// private prepared statement for inserting  declaration 
    private PreparedStatement updatePreparedStatement; // // private prepared statement for updating  declaration 
    private Statement countStatement; // countstatement 

    public MessageDao(Connection DBConnection) {
        this.DBConnection = DBConnection; // getting connection the database 
    }

    public void prepareStatements() throws SQLException { // SQL exceptions 
        prepareInsertStatement();
        prepareUpdateStatement();
        prepareSelectStatement();
        prepareCountStatement();
    }

    
    // executing the insert queries, updating 
    public void executeInsertQuery(Message message) throws SQLException {

        insertPreparedStatement.setString(1, message.getText());
        insertPreparedStatement.setDate(2, message.getDate());
        insertPreparedStatement.setInt(3, message.getReceiver().getID());
        insertPreparedStatement.setInt(4, message.getStatus());
        insertPreparedStatement.executeUpdate();

    }

    public List<Message> getMessagesByID(int messageID) throws SQLException {
        List<Message> messages = new ArrayList<Message>();
        selectPreparedStatement.setInt(1, messageID);

        // executing select SQL stetement
        ResultSet rs = selectPreparedStatement.executeQuery();

        while (rs.next()) {

            Message message = new Message();
            message.setID(rs.getInt(1));
            message.setText(rs.getString(2));
            message.setDate(rs.getDate(3));
            message.setReceiver(new Receiver(rs.getInt(4)));
            message.setStatus(rs.getInt(5));
            messages.add(message);
        }
        return messages;
    }

    // getting the counts from the database 
    public int getCountOfRecords() throws SQLException {
        int value=0;
        ResultSet rs = countStatement.executeQuery(Constants.COUNT_SQL);
        
        while(rs.next()) {
            value= rs.getInt(1);
        }
        return value;
    }
    
    //updating the database 
    public void updateStatusRecord(int messageID, int newStatus) throws SQLException {
        updatePreparedStatement.setInt(1, ++newStatus);
        updatePreparedStatement.setInt(2, messageID);
        updatePreparedStatement.executeUpdate();
       		
    }
    private void prepareSelectStatement() throws SQLException {
        selectPreparedStatement = DBConnection.prepareStatement(Constants.SELECT_SQL);
    }

    private void prepareUpdateStatement() throws SQLException {
       updatePreparedStatement = DBConnection.prepareStatement(Constants.UPDATE_SQL);
    }

    private void prepareInsertStatement() throws SQLException {
        insertPreparedStatement = DBConnection.prepareStatement(Constants.INSERT_SQL);
    }

    private void prepareCountStatement() throws SQLException {
        countStatement = DBConnection.createStatement();
    }
    
    // closing the statements 
    public void closeStatements() {

        try {
            if (selectPreparedStatement != null) {
                selectPreparedStatement.close();
            }

            if (insertPreparedStatement != null) {
                selectPreparedStatement.close();
            }

            if (updatePreparedStatement != null) {
                selectPreparedStatement.close();
            }
            
            if(countStatement!= null ) {
                countStatement.close();
            }

        } catch (SQLException ex) {
            System.out.println("Couldn't close statements" + ex);
        } 

    }
}

