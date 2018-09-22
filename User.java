/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;

/**
 *
 * @author Syed Turab 
 */

// Abstract , super class User 
abstract class User {

    protected int ID;

    public User(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    
   
}

