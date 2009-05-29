/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myarraylist;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Marek SMM
 */
public class Main extends JFrame {

    List<Snake> Snakes;
    List<Food> Foods;
    List<KeyEvent> Keys;
    
    
    
    private int foodIndex;
    
    
    
    public Main() {
        Snakes = new ArrayList<Snake>();
        Snakes.add(new Snake(0, 3, 4, new Keys(KeyEvent.VK_0, KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3)));
        Snakes.add(new Snake(0, 6, 7));
        Snakes.add(new Snake(0, 8, 5, new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Z, KeyEvent.VK_A)));
        
        Foods = new ArrayList<Food>();
        Foods.add(new Food(14, 8));
        Foods.add(new Food(10, 3));
        Foods.add(new Food(4, 9));
        Foods.add(new Food(1, 1));
        
        Keys = new ArrayList<KeyEvent>();        
        
        //firstSnake = new Snake();
        
        this.setBounds(20, 20, 350, 350);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(1);
            }
        });
        
        this.addKeyListener(new KeyAdapter() {
            int c = 0;
            @Override
            public void keyPressed(KeyEvent e) {
                Keys.add(e);
                //System.out.println(e.getKeyChar());
                for(int i = 0; i < Snakes.size(); i ++) {
                    if(inArray(Snakes.get(i).getMovementKeys().getKeys(), e.getKeyCode())) {
                        Snakes.get(i).setDirection(e.getKeyCode());
                        Point p = Snakes.get(i).moveSnake(30, 30);
                        if(isFoodOn(p.x, p.y)) {
                            Snakes.get(i).increaseSnake(p.x, p.y);
                            Foods.get(indexOfFood(p.x, p.y)).eat(i);
                        }
                        //if(i == 0 && (c % 10 == 0)) Snakes.get(i).increaseSnake(p.x, p.y);
                        //else if(i == 1 && (c % 6 == 0)) Snakes.get(i).increaseSnake(p.x, p.y);
                        //else if(i == 2 && (c % 13 == 0)) Snakes.get(i).increaseSnake(p.x, p.y);
                        repaint();
                        System.out.println(Snakes.get(i).getDirection());
                        c ++;
                    }
                }
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                Keys.remove(e);
            }
        });
        
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
        
        for(int i = 0; i < Foods.size(); i ++) {
            Foods.get(i).paintFood(og, 10);
        }
        
        for(int i = 0; i < Snakes.size(); i ++) {
            Snakes.get(i).paintSnake(og, 10);
        }
        
        g.drawImage(offImage, 0, 60, this);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public boolean isFoodOn(int x, int y) {
        for(int i = 0; i < Foods.size(); i ++) {
            if(!Foods.get(i).isEaten() && Foods.get(i).getX() == x && Foods.get(i).getY() == y) {
                foodIndex = i;
                return true;
            }
        }
        
        return false;
    }
    
    public int indexOfFood(int x, int y) {
        for(int i = 0; i < Foods.size(); i ++) {
            if(!Foods.get(i).isEaten() && Foods.get(i).getX() == x && Foods.get(i).getY() == y) {
                return i;
            }
        }
        
        return -1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        new Main().setVisible(true);
        
        /*List myStringList = new ArrayList();
        myStringList.add(new String("Hello world!"));
        
        System.out.print(myStringList.get(0));*/
    }

}
