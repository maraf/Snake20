package snake;

import java.awt.Point;

/**
 * 
 * Class that representing one snake point.
 * 
 * @author Marek SMM
 */
public class SnakePoint extends Point {
    private int TextureIndex;
    private boolean EatedFood;
    private int Direction;
    
    /**
     * 
     * Default contructor that sets all properties to zero-values
     */
    public SnakePoint() {
        this(0, 0, 0, false, -1);
    }
    
    /**
     * 
     * Contructor that creates java.awt.Point with coordinates(x, y).
     * TextureIndex sets to 0, EatedFood to false & Direction to -1.
     * 
     * @param x x coordinate of point
     * @param y y coordinate of point
     */
    public SnakePoint(int x, int y) {
        this(x, y, 0, false, -1);
    }
    
    /**
     * 
     * Contructor that creates java.awt.Point with coordinates(x, y).
     * TextureIndex sets to t, EatedFood to false & Direction to -1.
     * 
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @param t texture index
     */
    public SnakePoint(int x, int y, int t) {
        this(x, y, t, false, -1);
    }
    
    /**
     * 
     * Contructor that creates java.awt.Point with coordinates(x, y).
     * TextureIndex sets to t, EatedFood to e & Direction to d.
     * 
     * @param x x coordinate of point
     * @param y y coordinate of point
     * @param t texture index
     * @param e eated food
     * @param d direction
     */
    public SnakePoint(int x, int y, int t, boolean e, int d) {
        super(x, y);
        
        TextureIndex = t;
        EatedFood = e;
        Direction = d;
    }
    
    /**
     * 
     * Contructor that creates java.awt.Point with coordinates (p.x, p.y).
     * TextureIndex sets to 0, EatedFood to false & Direction to -1.
     * 
     * @param p java.awt.Point, x & y coordinates
     */
    public SnakePoint(Point p) {
        this(p.x, p.y, 0, false, -1);
    }
    
    /**
     * 
     * Contructor that creates java.awt.Point with coordinates (p.x, p.y).
     * TextureIndex sets to t, EatedFood to false & Direction to -1.
     * 
     * @param p java.awt.Point, x & y coordinates
     * @param t texture index
     */
    public SnakePoint(Point p, int t) {
        this(p.x, p.y, t, false, -1);
    }
    
    /**
     * 
     * Method that returns TextureIndex.
     * 
     * @return texture index
     */
    public int getTextureIndex() {
        return TextureIndex;
    }
    
    /**
     * 
     * Method that returns EatedFood,
     * true if there is eated food on this point,
     * false if not.
     * 
     * @return eated food
     */
    public boolean isEatedFood() {
        return EatedFood;
    }
    
    /**
     * 
     * Method that returns Direction of this point,
     * the key which has been pressed when this point was the first one.
     * 
     * @return direction of this point
     */
    public int getDirection() {
        return Direction;
    }
    
    /**
     * 
     * Method that sets TextureIndex to t.
     * 
     * @param t texture index
     */
    public void setTextureIndex(int t) {
        TextureIndex = t;
    }
    
    /**
     * 
     * Method that sets EatedFood to b.
     * 
     * @param b eated food
     */
    public void setEatedFood(boolean b) {
        EatedFood = b;
    }
    
    /**
     * 
     * Method that sets Direction of this point.
     * 
     * @param d direction of this point
     */
    public void setDirection(int d) {
        Direction = d;
    }
    
    /**
     * 
     * Method that returns this class name, x & y coordinate and direction.
     * 
     * @return this class name, x & y coordinate and direction
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", x: " + this.getX() + ", y: " + this.getY() + ", direction: " + this.getDirection();
    }
}
