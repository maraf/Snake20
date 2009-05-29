/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package netcommunication2;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marek SMM
 */
public class Client implements Runnable {
    Socket clientSocket = null;
    
    int[] direction;
    Point food;
    
    boolean newDirection;
    boolean newFood;
    
    Thread t;
    
    Client() {
        food = new Point();
        direction = new int[3];
        
        newFood = false;
        newDirection = false;
        
        t = new Thread(this);
        t.start();
    }
    
    public int findChar(String s, char c, int co) {
        for(int i = 0; i < s.length(); i ++) {
            if(s.charAt(i) == c) {
                co --;
                if(co == 0)
                    return i;
            }
        }
        
        return -1;
    }

    public void run() {
        try {
            String ip = "localhost";
            int port = 9900;
            clientSocket = new Socket(ip, port);
        } catch (UnknownHostException ex) {
            System.out.println("Nepodarilo se najit (DNS, NIS atp.) hostitele");
            System.exit(-1);
        } catch (IOException ex) {
            System.out.println("Nepodarilo se spojit s hostitelem");
            System.exit(-1);
        }
        
        try {
            clientSocket.setSoTimeout(50000);
        } catch (SocketException ex) {
            System.out.println("Nepodarilo se nastavit timeout");
        }
        
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println(in.readLine());
            //out.println(systemIn.readLine());
            String input = "";
            String response = "";
            
            while(!response.equals("Bye.")) {
                response = in.readLine();
                System.out.println(response);
                if(input.equals("Bye.")) {
                    break;
                }
                
                //input = systemIn.readLine();
                if(response.charAt(0) == 'D') {
                    int i = findChar(input, '-', 2);
                    int snake = Integer.parseInt(response.substring(2, i));
                    direction[snake] = Integer.parseInt(response.substring(i + 1));
                    System.out.println("Snake: " + snake + " changed direction to: " + direction[snake]);
                } else if(response.charAt(0) == 'F') {
                    int i = findChar(response, ',', 1);
                    //System.out.println(i + "" + input.substring(2, i));
                    System.out.println(response);
                    food.x = Integer.parseInt(response.substring(2, i));
                    food.y = Integer.parseInt(response.substring(i + 1));
                    System.out.println("New food put: " + food.x + ", " + food.y);
                }
                //out.println(input);
                
                t.sleep(100);
            }
            
            
            System.out.println("Shutting down!");
            systemIn.close();
            in.close();
            out.close();
            clientSocket.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            System.out.print("Doslo k chybe I/O.");
            System.exit(-1);
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Client();
    }
}
