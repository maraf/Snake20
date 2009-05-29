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

public class Server implements Runnable {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int coq;
    
    @Deprecated
    public int[] direction;
    @Deprecated
    public Point food;
    @Deprecated
    public Point nFood;
    
    @Deprecated
    public boolean newDirection;
    @Deprecated
    public boolean newFood;
    @Deprecated
    public boolean newGame;
    
    @Deprecated
    public boolean eatedFood;
    
    @Deprecated
    public int snakesCount;
    
    @Deprecated
    ArrayList<Integer> Direction;
    @Deprecated
    ArrayList<Thread> Threads;
    
    public Writer ServerWriter;
    public Reader ServerReader;
    
    public TransferData Input;
    public TransferData Output;
    
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
        
        Input = new TransferData();
        Output = new TransferData();
    }
    
    public void startServer() {
        try {
            int port = 9900;
            serverSocket = new ServerSocket(port);
            System.out.println("Server socket created.");
        } catch (IOException e) {
            System.out.println("Port je uz obsazen.");
            System.exit(-1);
        }
        
        try {
            while(true) {
                System.out.println("Waiting for hosts ...");
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
        Thread thisThread = Thread.currentThread();
        
        System.out.println("Host connected.");
        
        try {
            //PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            ServerWriter = new Writer(clientSocket.getOutputStream());
            //BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ServerReader = new Reader(clientSocket.getInputStream());
            BufferedReader systemIn = new BufferedReader(new InputStreamReader(System.in));
            
            System.out.println("Reader/Writer created.");
            
            ServerWriter.sendMessage("Welcome.");
            
            String input = "";
            System.out.println("Ready ...");
            
            while(!input.equals("Bye.")) {
                if(ServerReader.isMessage()) {
                    input = ServerReader.readMessage();
                
                System.out.println(input);
                
                if(input.equals("N")) {
                    newGame = true;
                    //System.out.println(newGame);
                }
                
                if(input.charAt(0) == 'S') {
                    snakesCount = Integer.parseInt(input.substring(2, input.length()));
                    System.out.println(snakesCount);
                    ServerWriter.sendMessage("S-" + snakesCount);
                } else if(input.charAt(0) == 'D') {
                    int i = findChar(input, '-', 2);
                    //newDirection = true;
                    //int snake = Integer.parseInt(input.substring(2, i));
                    //direction[snake] = Integer.parseInt(input.substring(i + 1));
                    //ServerWriter.sendMessage("D-" + snake + "-" + direction[snake]);
                    
                    int s = Integer.parseInt(input.substring(2, i));
                    Input.newDirection[s] = true;
                    Input.direction[s] = Integer.parseInt(input.substring(i + 1));
                } else if(input.charAt(0) == 'F') {
                    int i = findChar(input, ',', 1);
                    //newFood = true;
                    //food.x = Integer.parseInt(input.substring(2, i));
                    //food.y = Integer.parseInt(input.substring(i + 1));
                    //ServerWriter.sendMessage("F-" + food.x + "," + food.y);
                    
                    Input.newFood = true;
                    Input.food.x = Integer.parseInt(input.substring(2, i));
                    Input.food.y = Integer.parseInt(input.substring(i + 1));
                }
                
                   
            }
                
                /*if(eatedFood) {
                    System.out.print("eated food - server: ");
                    System.out.println(ServerWriter.sendMessage("F-" + nFood.x + "," + nFood.y));
                    System.out.println("2");
                    eatedFood = false;
                }
                
                if(newDirection) {
                    ServerWriter.sendMessage("D-0-" + direction[0]);
                    newDirection = false;
                }*/
                
                if(Output.newFood) {
                    System.out.print("eated food - server: ");
                    System.out.println(ServerWriter.sendMessage("F-" + Output.food.x + "," + Output.food.y));
                    System.out.println("2");
                    Output.newFood = false;
                }
                
                for(int i = 0; i < Output.direction.length; i ++) {
                    if(Output.newDirection[i]) {
                        ServerWriter.sendMessage("D-" + i + "-" + Output.direction[i]);
                        Output.newDirection[i] = false;
                    }
                }
                
                thisThread.sleep(10);
                
            }
            
            System.out.println("Shutting down!");
            systemIn.close();
            ServerReader.close();
            ServerWriter.close();
            serverSocket.close();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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

