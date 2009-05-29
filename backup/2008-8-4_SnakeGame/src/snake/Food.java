package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author Marek SMM
 */

/*
 * COMMENTS:
 * 
 * - pridat priznak bonus food, pri sezrani BF, nepridavat F
 * 
 */

public class Food {
    private int X;
    private int Y;
    private boolean Eaten;
    private int Snake;
    private int Value;
    private Color FoodColor;
    private boolean isBonusFood;
    
    private final static Color FOOD_COLOR = new Color(255, 187, 80);
    private final static Color BONUS_FOOD_COLOR = new Color(255, 100, 0);
    
    public Food(int value) {
        X = 0;
        Y = 0;
        Value = value;
        Eaten = false;
        Snake = -1;
        FoodColor = FOOD_COLOR;
        isBonusFood = false;
    }
    
    public Food(int x, int y, int value) {
        X = x;
        Y = y;
        Value = value;
        Eaten = false;
        Snake = -1;
        FoodColor = FOOD_COLOR;
        isBonusFood = false;
    }
    
    public Food(int x, int y, int value, Color c) {
        X = x;
        Y = y;
        Value = value;
        Eaten = false;
        Snake = -1;
        FoodColor = c;
        isBonusFood = false;
    }
    
    public Food(int x, int y, int value, Color c, boolean bonus) {
        X = x;
        Y = y;
        Value = value;
        Eaten = false;
        Snake = -1;
        FoodColor = c;
        isBonusFood = bonus;
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
    
    public boolean eat(int snake) {
        if(!Eaten) {
            Eaten = true;
            Snake = snake;
            return isBonusFood;
        }
        return isBonusFood;
    }
    
    public void paintFood(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(FoodColor);
        if(!Eaten) {
            g.fillOval(X * boxSize, Y * boxSize, boxSize, boxSize);
        }
        g.setColor(pomColor);
    }
    
    public int getValue() {
        return Value;
    }
    
    public static Food createFood(int maxX, int maxY, int value) {
        return new Food(((int)(Math.random() * (maxX - 1))), ((int)(Math.random() * (maxY - 1))), value);
    }
    
    public static Food createBonusFood(int maxX, int maxY, int value) {
        return new Food(((int)(Math.random() * (maxX - 1))), ((int)(Math.random() * (maxY - 1))), value, BONUS_FOOD_COLOR, true);
    }
    
    public static Food addFood(int x, int y, int value) {
        return new Food(x, y, value);
    }
}
