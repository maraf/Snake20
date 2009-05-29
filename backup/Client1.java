/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package netcommunication2;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author Marek SMM
 */
public class Client {
    Socket clientSocket = null;
    
    int[] direction;
    Point food;
    
    boolean newDirection;
    boolean newFood;
    
    Client() {
        food = new Point();
        direction = new int[2];
        
        newFood = false;
        newDirection = false;

        try {
            //BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            //System.out.print("Zadejte adresu serveru: ");
            //String ip = systemIn.readLine();
            //System.out.print("Zadejte cislo portu: ");
            //int port = Integer.parseInt(systemIn.readLine());
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
            out.println(systemIn.readLine());
            String input = "";
            String response = "";
            while(!input.equals("Bye.")) {
                response = in.readLine();
                System.out.println(response);
                if(response.equals("Bye.")) {
                    break;
                }
                
                input = systemIn.readLine();
                if(input.charAt(0) == 'D') {
                    int i = findChar(input, '-', 2);
                    int snake = Integer.parseInt(input.substring(2, i));
                    direction[snake] = Integer.parseInt(input.substring(i + 1));
                    //System.out.println("Snake: " + snake + " changed direction to: " + direction[snake]);
                } else if(input.charAt(0) == 'F') {
                    int i = findChar(input, ',', 1);
                    //System.out.println(i + "" + input.substring(2, i));
                    food.x = Integer.parseInt(input.substring(2, i));
                    food.y = Integer.parseInt(input.substring(i + 1));
                    System.out.println("New food put: " + food.x + ", " + food.y);
                }
                out.println(input);
            }
            
            
            System.out.println("Shutting down!");
            systemIn.close();
            in.close();
            out.close();
            clientSocket.close();
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
