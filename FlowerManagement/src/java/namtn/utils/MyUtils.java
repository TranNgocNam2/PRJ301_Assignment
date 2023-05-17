/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.utils;

import java.util.Random;

/**
 *
 * @author ADMIN
 */
public class MyUtils {

    public static String generateToken() {
        //Define character to use in the token
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        //The length of token
        int length = 10;

        //Create Random Object
        Random random = new Random();
        // Generate a string of the desired length by selecting random characters from the chars string
        StringBuilder tokenBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            tokenBuilder.append(chars.charAt(index));
        }
        String token = tokenBuilder.toString();
        return token;
    }
}
