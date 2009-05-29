/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.Point;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author Marek SMM
 */

public class Server implements Runnable {
    ServerSocket serverSocket;
    Socket clientSocket;
    int coq;
    
    int[] direction;
    Point food;
    Point nFood;
    
    public boolean newDirection;
    public boolean newFood;
    public boolean newGame;
    
    public boolean eatedFood;
    
    public int snakesCount;
    
    ArrayList<Integer> Direction;
    ArrayList<Thread> Threads;
    
    
    Server() {
        food = new Point();
        nFood = new Point();
        
        direction = new int[2];
        coq = 0;
        
        Direction = new ArrayList<Integer>();
        Threads = new ArrayList<Thread>();
        
        newGame = false;
        newFood = false;
        newDirection = false;
        
        eatedFood = false;
    }
    
    public void startServer() {
        try {
            int port = 9900;
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Port je uz obsazen.");
            System.exit(-1);
        }
        
        try {
            while(true) {
                clientSocket = serverSocket.accept();
                Direction.add(new Integer(0));
                Thread t = new Thread(this);
                Threads.add(t);
                t.setName(String.valueOf(coq));
                t.start();
                coq ++;
            }
        } catch (IOException e) {
            System.out.println("Pripojeni selhalo.");
            System.exit(-1);
        }
    }
    
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            
            out.println("Welcome");
            
            String input = "";
            
            while(!input.equals("Bye.")) {
                input = in.readLine();
                
                System.out.println(input);
                
                if(input.equals("N")) {
                    newGame = true;
                    System.out.println(newGame);
                }
                if(input.charAt(0) == 'S') {
                    snakesCount = Integer.parseInt(input.substring(2, input.length()));
                    System.out.println(snakesCount);
                    out.println("S-" + snakesCount);
                } else if(input.charAt(0) == 'D') {
                    int i = findChar(input, '-', 2);
                    newDirection = true;
                    int snake = Integer.parseInt(input.substring(2, i));
                    direction[snake] = Integer.parseInt(input.substring(i + 1));
                    out.println("D-" + snake + "-" + direction[snake]);
                } else if(input.charAt(0) == 'F') {
                    newFood = true;
                    int i = findChar(input, ',', 1);
                    food.x = Integer.parseInt(input.substring(2, i));
                    food.y = Integer.parseInt(input.substring(i + 1));
                    out.println("F-" + food.x + "," + food.y);
                }
                
                if(eatedFood) {
                    System.out.println("eated food - server");
                    out.println("F-" + nFood.x + "," + nFood.y);
                    eatedFood = false;
                }
            }
            
            
            System.out.println("Shutting down!");
            systemIn.close();
            in.close();
            out.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Chyba vytvareni Streamu!");
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

