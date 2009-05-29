/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marek SMM
 */
public class Reader implements Runnable {
    BufferedReader in;
    Thread t;
    
    String Message;
    boolean newMessage;
    
    Reader(InputStream is) {
        in = new BufferedReader(new InputStreamReader(is));
        Message = new String();
        newMessage = false;
        
        t = new Thread(this);
        t.start();
    }

    public void run() {
        try {
            while ((Message = in.readLine()).equals("Bye.")) {
                if(!Message.equals("")) {
                    newMessage = true;
                }
                
                System.out.println("Message: " + Message);
            }
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String readMessage() {
        return Message;
    }
    
    public boolean isMessage() {
        return newMessage;
    }
    
    public void close() {
        try {
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
