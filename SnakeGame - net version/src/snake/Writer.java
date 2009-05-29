/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marek SMM
 */
public class Writer implements Runnable {
    PrintWriter out;
    String Message;
    Thread t;
    
    Writer(OutputStream os) {
        out = new PrintWriter(os, true);
        Message = new String();
        
        t = new Thread(this);
        t.start();
    }
    
    public void run() {
        while(!Message.equals("Bye.")) {
            //System.out.println("Message: " + Message);
            if(Message.length() == 0) {
                try {
                    t.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Writer.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                out.println(Message);
                System.out.println(Message);
                Message = "";
            }
        }
    }
    
    public int sendMessage(String s) {
        Message = s;
        return Message.length();
    }
    
    public void close() {
        out.close();
    }
}
