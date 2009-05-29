/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;
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
    
    @Deprecated
    int[] direction;
    @Deprecated
    Point food;
    
    @Deprecated
    boolean newDirection;
    @Deprecated
    boolean newFood;
    @Deprecated
    boolean newGame;
    
    Thread t;
    
    TransferData Input;
    TransferData Output;
    
    Client() {
        food = new Point();
        direction = new int[3];
        
        newFood = false;
        newDirection = false;
        
        Input = new TransferData();
        Output = new TransferData();
    }
    
    public void startClient() {
        t = new Thread(this);
        t.start();
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
                //System.out.println(response);
                if(input.equals("Bye.")) {
                    break;
                }
                
                if(response.equals("N")) {
                    newGame = true;
                    out.println("N");
                } else
                //input = systemIn.readLine();
                if(response.charAt(0) == 'D') {
                    int i = findChar(response, '-', 2);
                    //int snake = Integer.parseInt(response.substring(2, i));
                    //direction[snake] = Integer.parseInt(response.substring(i + 1));
                    //System.out.println("Snake: " + snake + " changed direction to: " + direction[snake]);
                    
                    int s = Integer.parseInt(response.substring(2, i));
                    Input.newDirection[s] = true;
                    Input.direction[s] = Integer.parseInt(response.substring(i + 1));
                    System.out.println("Snake: " + s + " changed direction to: " + Input.direction[s]);
                } else if(response.charAt(0) == 'F') {
                    int i = findChar(response, ',', 1);
                    //System.out.println(i + "" + input.substring(2, i));
                    //System.out.println(response);
                    //food.x = Integer.parseInt(response.substring(2, i));
                    //food.y = Integer.parseInt(response.substring(i + 1));
                    //System.out.println("New food put: " + food.x + ", " + food.y);
                    
                    Input.newFood = true;
                    Input.food.x = Integer.parseInt(response.substring(2, i));
                    Input.food.y = Integer.parseInt(response.substring(i + 1));
                    System.out.println("New food put: " + Input.food.x + ", " + Input.food.y);
                }
                
                for(int i = 0; i < Output.direction.length; i ++) {
                    if(Output.newDirection[i]) {
                        out.println("D-" + i + "-" + Output.direction[i]);
                        Output.newDirection[i] = false;
                    }
                }
                
                if(Output.newFood) {
                    out.println("F-" + Output.food.x + "," + Output.food.y);
                    Output.newFood = false;
                }
                
                t.sleep(10);
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
}
