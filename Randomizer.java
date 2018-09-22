/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2msgdb;

import java.nio.charset.Charset;
import java.util.Random;
// this class is used to generate senders and receivers of messages.

//Random character strings are generated as messages.

/**
 *
 * @author Syed Turab 
 */
public class Randomizer {
    
public static Random random = new Random();

    public static char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j','k'};

    public static int generateRandomID(int senderID) {

        int value = random.nextInt(Constants.USERS_MAX_NUMBER) + 1;
        while (value == senderID) {
            value = random.nextInt(Constants.USERS_MAX_NUMBER) + 1;
        }
        return value;
    }

    public static String generateRandomMessage() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 30; i++) {
            builder.append(chars[random.nextInt(Constants.USERS_MAX_NUMBER)]);
        }
        return builder.toString();
    }
}

