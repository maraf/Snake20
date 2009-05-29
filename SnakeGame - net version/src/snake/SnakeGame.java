/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Marek SMM
 */

public class SnakeGame extends JFrame implements Runnable {
    List<Snake> Snakes;
    List<Food> Foods;
    
    Thread t;
    
    //Server SnakeServer;//
    ServerThread SnakeServer;
    Client SnakeClient;
    
    boolean GameOver;
    boolean GamePause;
    
    boolean Who;
    
    private MenuBar mbMain;
    
    private Menu mnGame;
    
    private MenuItem miGAnewGame;
    private MenuItem miGAgamePause;
    private MenuItem miGAgameOver;
    private MenuItem miGAclose;
    
    public SnakeGame() {
        Snakes = new ArrayList<Snake>();
        Foods = new ArrayList<Food>();
        
        this.setBounds(20, 20, 370, 370);
        this.addWindowListener(new cWindowAdapter());
        this.addKeyListener(new cKeyListener());
        this.setAlwaysOnTop(true);
        
        GameOver = true;
        GamePause = false;
        
//        Who = true;
        Who = false;
        
        if(Who) {
            SnakeServer = new ServerThread();
        } else {
            SnakeClient = new Client();
            SnakeClient.startClient();
        }
        
        setMenu();
    }
    
    public void newGame() {
        Snakes.add(new Snake(0, 6, 7));
        Snakes.add(new Snake(0, 8, 5, new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Z, KeyEvent.VK_A)));
    
        Foods.add(new Food(14, 8));
        //Foods.add(new Food(10, 3));
        
        miGAnewGame.setEnabled(false);
        miGAgamePause.setEnabled(true);
        miGAgameOver.setEnabled(true);
        
        GameOver = false;
        
        t = new Thread(this);
        
        if(Who) {
            SnakeServer.getServer().ServerWriter.sendMessage("N");
            SnakeServer.getServer().newGame = true;
        }
        
        if(Who) {
            while(!SnakeServer.getServer().newGame) {
                try {
                    t.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            while(!SnakeClient.newGame) {
                try {
                    t.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        t.start();
                    
    }
    
    public void gamePause() {
        if(!GamePause) {
            GamePause = true;
            t = null;
        } else {
            GamePause = false;
            t = new Thread(this);
            t.start();
        }
    }
    
    public void gameOver() {
        if(!GameOver) {
            GameOver = true;
            t = null;
            
            Snakes.clear();
            Foods.clear();
        
            miGAnewGame.setEnabled(true);
            miGAgamePause.setEnabled(false);
            miGAgameOver.setEnabled(false);
        }
    }
    
    public void setMenu() {
        mbMain = new MenuBar();
        
        mnGame = new Menu("Game", false);
        
        miGAnewGame = new MenuItem("New Game");
        miGAgamePause = new MenuItem("Pause");
        miGAgamePause.setEnabled(false);
        miGAgameOver = new MenuItem("Quit");
        miGAgameOver.setEnabled(false);
        miGAclose = new MenuItem("Close");
        miGAnewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        miGAgamePause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePause();
            }
        });
        miGAgameOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOver();
            }
        });
        miGAclose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mnGame.add(miGAnewGame);
        mnGame.add(miGAgamePause);
        mnGame.add(miGAgameOver);
        mnGame.add(miGAclose);
        
        mbMain.add(mnGame);
        this.setMenuBar(mbMain);
    }
    
    public boolean inArray(int array[], int item) {
        for(int i = 0; i < array.length; i ++) {
            if(array[i] == item) return true;
        }
        return false;
    }
    
    @Override
    public void paint(Graphics g) {
        Image offImage = createImage(300, 300);
        Graphics og = offImage.getGraphics();
        
        og.setColor(Color.orange);
        og.fillRect(0, 0, offImage.getWidth(null), offImage.getHeight(null));
        og.setColor(Color.black);
        
        for(int i = 0; i < Foods.size(); i ++) {
            Foods.get(i).paintFood(og, 10);
        }
        
        for(int i = 0; i < Snakes.size(); i ++) {
            Snakes.get(i).paintSnake(og, 10);
        }
        
        g.drawImage(offImage, 10, 60, this);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public boolean isFoodOn(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < Foods.size(); i ++) {
            if((!careOfEaten || !Foods.get(i).isEaten()) && Foods.get(i).getX() == x && Foods.get(i).getY() == y) {
                return true;
            }
        }
        
        return false;
    }
    
    public int indexOfFood(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < Foods.size(); i ++) {
            if((!careOfEaten || !Foods.get(i).isEaten()) && Foods.get(i).getX() == x && Foods.get(i).getY() == y) {
                return i;
            }
        }
        
        return -1;
    }

    public void run() {
        if(Who) Server();
        else Client();
    }
    
    public void Server() {
        Thread thisThread = Thread.currentThread();
        while(thisThread == t) {
            for(int i = 0; i < Snakes.size(); i ++) {
                Point f = Snakes.get(i).getPointP(0);
                if(isFoodOn(f.x, f.y, true)) {
                    Foods.get(indexOfFood(f.x, f.y, true)).eat(i);
                    
                    
                    System.out.println(indexOfFood(f.x, f.y, false) + " ::: " + f.x + " :: " + f.y + 
                            ", eaten = '" + Foods.get(indexOfFood(f.x, f.y, false)).isEaten() + "'");
                    
                    SnakeServer.getServer().Output.newFood = true;
                    Foods.add(Food.createFood(30, 30));
                    SnakeServer.getServer().Output.food.x = Foods.get(Foods.size() - 1).getX();
                    SnakeServer.getServer().Output.food.y = Foods.get(Foods.size() - 1).getY();
                    
                    
                    // pokus ---------------------------------
                    System.out.println(Foods.remove(indexOfFood(f.x, f.y, false)));
                    
                }
                
                Point l = Snakes.get(i).moveSnake(30, 30);
                /*if(isFoodOn(l.x, l.y, false)) {
                    Snakes.get(i).increaseSnake(l.x, l.y);
                    Foods.remove(indexOfFood(l.x, l.y, false));
                }*/
                
                if(SnakeServer.getServer().Output.newFood) {
                    Foods.add(Food.addFood(SnakeServer.getServer().Output.food.x, SnakeServer.getServer().Output.food.y));
                    //SnakeServer.getServer().Output.newFood = false;
                }
                
                repaint();
            } 
            
            repaint();
            
            try {
                t.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
    
    public void Client() {
        Thread thisThread = Thread.currentThread();
        while(thisThread == t) {
            for(int i = 0; i < Snakes.size(); i ++) {
                
                //for(int j = 0; j < SnakeClient.Input.direction.length; j ++) {
                    if(SnakeClient.Input.newDirection[i]) {
                        Snakes.get(i).setDirection(SnakeClient.Input.direction[i]);
                        SnakeClient.Input.newDirection[i] = false;
                    }
                //}
                
                if(SnakeClient.Input.newFood) {
                    Foods.add(Food.addFood(SnakeClient.Input.food.x, SnakeClient.Input.food.y));
                }
                
                Point f = Snakes.get(i).getPointP(i);
                if(isFoodOn(f.x, f.y, true)) {
                    Foods.get(indexOfFood(f.x, f.y, true)).eat(i);
                    System.out.println(indexOfFood(f.x, f.y, false) + " ::: " + f.x + " :: " + f.y);
                    
                    //SnakeClient.Output.newFood = true;
                    //Foods.add(Food.createFood(30, 30));
                    //SnakeClient.Output.food.x = Foods.get(Foods.size() - 1).getX();
                    //SnakeClient.Output.food.y = Foods.get(Foods.size() - 1).getY();
                    
                    // pokus ---------------------------------
                    System.out.println(Foods.remove(indexOfFood(f.x, f.y, false)));
                }
                
                Point l = Snakes.get(i).moveSnake(30, 30);
                /*if(isFoodOn(l.x, l.y, false)) {
                    Snakes.get(i).increaseSnake(l.x, l.y);
                    Foods.remove(indexOfFood(l.x, l.y, false));
                }*/
                
                if(SnakeClient.Output.newFood) {
                    Foods.add(Food.addFood(SnakeClient.Output.food.x, SnakeClient.Output.food.y));
                    //SnakeServer.getServer().Output.newFood = false;
                }
                
                repaint();
            } 
            
            repaint();
            
            try {
                t.sleep(500);
            } catch (InterruptedException e) {}
        }
    }
    
    class cKeyListener implements KeyListener {
        //@Override
        public void keyPressed(KeyEvent e) {
            for(int i = 0; i < Snakes.size(); i ++) {
                if(inArray(Snakes.get(i).getMovementKeys().getKeys(), e.getKeyCode())) {
                    Snakes.get(i).setDirection(e.getKeyCode());
                    //SnakeServer.getServer().direction[0] = e.getKeyCode();
                    //SnakeServer.getServer().newDirection = true;
                    
                    
                    
                    if(Who) {
                        SnakeServer.getServer().Output.direction[i] = e.getKeyCode();
                        SnakeServer.getServer().Output.newDirection[i] = true;
                    } else {
                        SnakeClient.Output.direction[0] = e.getKeyCode();
                        SnakeClient.Output.newDirection[0] = true;
                    }
                    
                    break;
                }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                gameOver();
            } else if(e.getKeyCode() == KeyEvent.VK_P) {
                gamePause();
            } else if(e.getKeyCode() == KeyEvent.VK_N) {
                gameOver();
                newGame();
            }
        }
            
        //@Override
        public void keyReleased(KeyEvent e) {
        }

        //@Override
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    class cWindowAdapter extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }
}