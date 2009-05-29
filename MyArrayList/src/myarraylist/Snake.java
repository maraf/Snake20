/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myarraylist;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

/**
 *
 * @author Marek SMM
 */
public class Snake {
    private int Points[][];
    private int Direction;
    private Color SnakeColor;
    private Keys MovementKeys;
    
    private static int usedColors;
    private final static Color[] Colors = {Color.RED, Color.BLUE, Color.GREEN, 
                                           Color.YELLOW, Color.ORANGE, Color.WHITE,
                                           Color.BLACK, Color.CYAN, Color.GRAY};
    
    public Snake() {
        Points = new int[3][2];
        Direction = KeyEvent.VK_RIGHT;
        usedColors = 0;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
        usedColors ++;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = 3 - i;
            Points[i][1] = 2;
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
    }
    
    public Snake(int offSetX, int offSetY) {
        Points = new int[3][2];
        Direction = KeyEvent.VK_RIGHT;
        usedColors = 0;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
        usedColors ++;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - 1 - i + offSetX;
            Points[i][1] = offSetY;
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
    }
    
    public Snake(int offSetX, int offSetY, int lenght) {
        Points = new int[lenght][2];
        Direction = KeyEvent.VK_RIGHT;
        usedColors = 0;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
        usedColors ++;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - i - 1 + offSetX;
            Points[i][1] = offSetY;
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
    }
    
    public Snake(int offSetX, int offSetY, int lenght, Keys keys) {
        Points = new int[lenght][2];
        Direction = KeyEvent.VK_RIGHT;
        usedColors = 0;
        
        System.out.println(usedColors);
        SnakeColor = new Color(Colors[usedColors].getRGB());
        usedColors ++;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - i - 1 + offSetX;
            Points[i][1] = offSetY;
        }
        
        MovementKeys = keys;
    }
    
    public int getLength() {
        return Points.length;
    }
    
    public Color getColor() {
        return SnakeColor;
    }
    
    public int getDirection() {
        return Direction;
    }
    
    public int[][] getPoints() {
        return Points;
    }
    
    public int[] getPoint(int i) {
        return Points[i];
    }
    
    public Keys getMovementKeys() {
        return MovementKeys;
    }
    
    public void setDirection(int d) {
        Direction = d;
    }
    
    public Point moveSnake(int width, int height) {
        int x = Points[Points.length - 1][0];
        int y = Points[Points.length - 1][1];
        for(int i = Points.length - 1; i >= 1 ; i --) {
            Points[i][0] = Points[i - 1][0];
            Points[i][1] = Points[i - 1][1];
        }
        if(Direction == MovementKeys.getKey("Up")) {
            Points[0][0] = Points[1][0];
            if(Points[0][1] == 0)
                Points[0][1] = height - 1;
            else
                Points[0][1] = Points[1][1] - 1;
        } else if(Direction == MovementKeys.getKey("Down")) {
            Points[0][0] = Points[1][0];
            if(Points[0][1] == height - 1)
                Points[0][1] = 0;
            else
                Points[0][1] = Points[1][1] + 1;
        } else if(Direction == MovementKeys.getKey("Left")) {
            if(Points[0][0] == 0)
                Points[0][0] = width - 1;
            else
                Points[0][0] = Points[1][0] - 1;
            Points[0][1] = Points[1][1];
        } else if(Direction == MovementKeys.getKey("Right")) {
            if(Points[0][0] == width - 1)
                Points[0][0] = 0;
            else
                Points[0][0] = Points[1][0] + 1;
            Points[0][1] = Points[1][1];
        }
        return new Point(x, y);
    }
    
    public void increaseSnake(int x, int y) {
        int[][] iPomSnake = new int[Points.length][2];
        
        for(int i = 0; i < Points.length; i ++) {
            iPomSnake[i][0] = Points[i][0];
            iPomSnake[i][1] = Points[i][1];
        }
        
        Points = new int[Points.length + 1][2];
        
        for(int i = 0; i < iPomSnake.length; i ++) {
            Points[i][0] = iPomSnake[i][0];
            Points[i][1] = iPomSnake[i][1];
        }
        
        Points[Points.length - 1][0] = x;
        Points[Points.length - 1][1] = y;
    }
    
    public void paintSnake(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(SnakeColor);
        for(int i = 0; i < Points.length; i ++) {
            g.fillRect(Points[i][0] * boxSize, Points[i][1] * boxSize, boxSize, boxSize);
        }
        g.setColor(pomColor);
    }
}
