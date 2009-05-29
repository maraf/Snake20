package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * 
 * Class representing food for snake.
 * 
 * @author Marek SMM
 */

public class Food {
    private int X;
    private int Y;
    private boolean Eaten;
    private int Snake;
    private int Value;
    private Color FoodColor;
    private boolean IsBonusFood;
    
    private final static int FOOD_VALUE = 1;
    private final static Color FOOD_COLOR = new Color(255, 187, 80);
    private final static Color BONUS_FOOD_COLOR = new Color(255, 100, 0);
    
    /**
     * 
     * Contructor that only sets value of this food.
     * X & Y coordinates set to 0 Value to 1.
     * 
     * @param value value of this food
     */
    public Food() {
        this(0, 0, FOOD_VALUE, FOOD_COLOR, false);
    }
    
    /**
     * 
     * Contructor that only sets value of this food.
     * X & Y coordinates set to 0 Value to value.
     * 
     * @param value value of this food
     */
    public Food(int value) {
        this(0, 0, value, FOOD_COLOR, false);
    }
    
    /**
     * 
     * Contructor that only sets value of this food.
     * X & Y coordinates set to x & y.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param value value of this food
     */
    public Food(int x, int y, int value) {
        this(x, y, value, FOOD_COLOR, false);
    }
    
    /**
     * 
     * Contructor that only sets value of this food.
     * X & Y coordinates set to x & y.
     * FoodColor sets to c.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param value value of this food
     * @param c food color
     */
    public Food(int x, int y, int value, Color c) {
        this(x, x, value, c, false);
    }
    
    /**
     * 
     * Contructor that only sets value of this food.
     * X & Y coordinates set to x & y.
     * FoodColor sets to c & flag IsBonusFood sets to bonus.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param value value of this food
     * @param c food color
     */
    public Food(int x, int y, int value, Color c, boolean bonus) {
        X = x;
        Y = y;
        Value = value;
        Eaten = false;
        Snake = -1;
        FoodColor = c;
        IsBonusFood = bonus;
    }
    
    /**
     * 
     * Method that returns X & Y coordinates of food as java.awt.Point.
     * 
     * @return X & Y coordinates of food as java.awt.Point
     */
    public Point getPoint() {
        return new Point(X, Y);
    }
    
    /**
     * 
     * Method that returns X coordinate of food.
     * 
     * @return X coordinate of food
     */
    public int getX() {
        return X;
    }
    
    /**
     * 
     * Method that returns Y coordinate of food.
     * 
     * @return Y coordinate of food
     */
    public int getY() {
        return Y;
    }
    
    /**
     * 
     * Method that returns if food is eaten or not.
     * 
     * @return
     */
    public boolean isEaten() {
        return Eaten;
    }
    
    /**
     * 
     * Method that 'eat' this food, sets who eat it 
     * & returns if this is bonus food.
     * 
     * @param snake snake who 'eat' this food
     * @return returns if this is bonus food.
     */
    public boolean eat(int snake) {
        if(!Eaten) {
            Eaten = true;
            Snake = snake;
            return IsBonusFood;
        }
        return IsBonusFood;
    }
    
    /**
     * 
     * Method that paints this by graphics which is passed.
     * boxSize is size of one dot, one point.
     * 
     * @param g graphics context
     * @param boxSize size of one point
     */
    public void paintFood(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(FoodColor);
        if(!Eaten) {
            g.fillOval(X * boxSize + 1, Y * boxSize + 1, boxSize - 3, boxSize - 3);
        }
        g.setColor(pomColor);
    }
    
    /**
     * 
     * Method that returns value of this food.
     * 
     * @return value of this food.
     */
    public int getValue() {
        return Value;
    }
    
    /**
     * 
     * Method that returns this class name, x & y coordinate and value.
     * 
     * @return this class name, x & y coordinate and value
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", x: " + this.getX() + ", y: " + this.getY() + ", value: " + this.getValue();
    }
    
    /**
     * 
     * Static method that returns random possitioned food with max X value
     * maxX ,max Y value maxY & Value is set to value.
     * 
     * @param maxX maximum value of X coordinate
     * @param maxY maximum value of Y coordinate 
     * @param value value of returned food
     * @return food instance
     */
    public static Food createFood(int maxX, int maxY, int value) {
        return new Food(((int)(Math.random() * (maxX - 1))), ((int)(Math.random() * (maxY - 1))), value);
    }
    
    /**
     * 
     * Static method that returns random possitioned bonus-food with max X value
     * maxX ,max Y value maxY & Value is set to value.
     * 
     * @param maxX maximum value of X coordinate
     * @param maxY maximum value of Y coordinate 
     * @param value value of returned food
     * @return food instance
     */
    public static Food createBonusFood(int maxX, int maxY, int value) {
        return new Food(((int)(Math.random() * (maxX - 1))), ((int)(Math.random() * (maxY - 1))), value, BONUS_FOOD_COLOR, true);
    }
}
