/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package myarraylist;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Marek SMM
 */
public class Food {
    private int X;
    private int Y;
    private boolean Eaten;
    private int Snake;
    private Color FoodColor;
    
    public Food() {
        X = 0;
        Y = 0;
        Eaten = false;
        Snake = -1;
        FoodColor = Color.DARK_GRAY;
    }
    
    public Food(int x, int y) {
        X = x;
        Y = y;
        Eaten = false;
        Snake = -1;
        FoodColor = Color.DARK_GRAY;
    }
    
    public Point getPoint() {
        return new Point(X, Y);
    }
    
    public int getX() {
        return X;
    }
    
    public int getY() {
        return Y;
    }
    
    public boolean isEaten() {
        return Eaten;
    }
    
    public void eat(int snake) {
        if(!Eaten) {
            Eaten = true;
            Snake = snake;
        }
    }
    
    public void paintFood(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(FoodColor);
        if(!Eaten) {
            g.fillOval(X * boxSize, Y * boxSize, boxSize, boxSize);
        }
        g.setColor(pomColor);
    }
}
