package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Marek SMM
 */
public class Snake {
    private int Points[][];
    private List<Point> PPoints;
    private int Direction;
    private boolean IsChangedDirection;
    private int Score;
    private String PlayerName;
    private Color SnakeColor;
    private Keys MovementKeys;
    
    private static int usedColors = 0;
    private final static Color[] Colors = {Color.RED, Color.BLUE, Color.GREEN, 
                                           Color.YELLOW, Color.ORANGE, Color.WHITE,
                                           Color.BLACK, Color.CYAN, Color.GRAY,
                                           Color.PINK, Color.MAGENTA};
    /**
     * 
     * Contructor that creates Snake object and all properties sets 
     * to default values
     */
    public Snake() {
        PlayerName = "";
        Score = 0;
        Points = new int[3][2];
//        usedColors = 0;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
//        usedColors ++;
        usedColors = (usedColors % 10) + 1;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = 3 - i;
            Points[i][1] = 2;
        }
        
        PPoints = new ArrayList<Point>();
        
        for(int i = 0; i < 3; i ++) {
            PPoints.add(new Point(3 - i, 2));
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
        Direction = MovementKeys.getKey("Right");
        IsChangedDirection = false;
    }
    
    /**
     * 
     * Contructor that creates Snake object and sets its offset(x, y) on the map
     * 
     * @param offSetX x coordinate on the map
     * @param offSetY y coordinate on the map
     */
    public Snake(int offSetX, int offSetY) {
        PlayerName = "";
        Score = 0;
        Points = new int[3][2];
        Direction = KeyEvent.VK_RIGHT;
//        usedColors = 0;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
//        usedColors ++;
        usedColors = (usedColors % 10) + 1;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - 1 - i + offSetX;
            Points[i][1] = offSetY;
        }
        
        PPoints = new ArrayList<Point>();
        
        for(int i = 0; i < 3; i ++) {
            PPoints.add(new Point(3 - i, 2));
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
        Direction = MovementKeys.getKey("Right");
        IsChangedDirection = false;
    }
    
    /**
     * 
     * Contructor that creates Snake object and sets its offset(x, y) on the map 
     * and its length
     * 
     * @param offSetX x coordinate on the map
     * @param offSetY y coordinate on the map
     * @param length snake length
     */
    public Snake(int offSetX, int offSetY, int length) {
        PlayerName = "";
        Score = 0;
        Points = new int[length][2];
        Direction = KeyEvent.VK_RIGHT;
//        usedColors = 0;
        
        System.out.println(usedColors);
        SnakeColor = new Color(Colors[usedColors].getRGB());
//        usedColors ++;
        usedColors = (usedColors % 10) + 1;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - i - 1 + offSetX;
            Points[i][1] = offSetY;
        }
        
        PPoints = new ArrayList<Point>();
        
        for(int i = 0; i < 3; i ++) {
            PPoints.add(new Point(3 - i, 2));
        }
        
        MovementKeys = new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
        Direction = MovementKeys.getKey("Right");
        IsChangedDirection = false;
    }
    
    /**
     * 
     * Contructor that creates Snake object and sets its offset(x, y) on the map 
     * and its length and movement keys
     * 
     * @param offSetX x coordinate on the map
     * @param offSetY y coordinate on the map
     * @param length snake length
     * @param keys keys object, sets movement keys
     */
    public Snake(int offSetX, int offSetY, int length, Keys keys) {
        PlayerName = "";
        Score = 0;
        Points = new int[length][2];
        Direction = KeyEvent.VK_RIGHT;
//        usedColors = 0;
        
        System.out.println(usedColors);
        SnakeColor = new Color(Colors[usedColors].getRGB());
//        usedColors ++;
        usedColors = (usedColors % 10) + 1;
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - i - 1 + offSetX;
            Points[i][1] = offSetY;
        }
        
        PPoints = new ArrayList<Point>();
        
        for(int i = 0; i < 3; i ++) {
            PPoints.add(new Point(3 - i, 2));
        }
        
        MovementKeys = keys;
        Direction = MovementKeys.getKey("Right");
    }
    
    /**
     * 
     * Method that returns snake's length
     * 
     * @return snake length
     */
    public int getLength() {
        return Points.length;
    }
    
    /**
     * 
     * Method that returns snake's length
     * 
     * @return snake length
     */
    public int getPLength() {
        return PPoints.size();
    }
    
    /**
     * 
     * Method that returns snake's color
     * 
     * @return snake color
     */
    public Color getColor() {
        return SnakeColor;
    }
    
    /**
     * 
     * Method that returns snake's actual direction
     * 
     * @return snake direction
     */
    public int getDirection() {
        return Direction;
    }
    
    /**
     * 
     * Method that returns all snake's points
     * 
     * @return snake points
     */
    public int[][] getPoints() {
        return Points;
    }
    
    /**
     * 
     * Method that returns all snake's points
     * 
     * @return snake points
     */
    public List<Point> getPPoints() {
        return PPoints;
    }
    
    /**
     * 
     * Method that returns int array with values of x & y coordinates
     * of i-th point of snake
     * 
     * @param i index in points array of snake
     * @return i-th point of snake (x, y)
     */
    public int[] getPointI(int i) {
        return Points[i];
    }
    
    /**
     * 
     * Method that returns int value of x coordinate of i-th point of snake
     * 
     * @param i index in points array of snake
     * @return x coordinate of i-th point of snake
     */
    public int getPointIX(int i) {
        return Points[i][0];
    }
    
    /**
     * 
     * Method that returns int value of x coordinate of i-th point of snake
     * 
     * @param i index in points list of snake
     * @return x coordinate of i-th point of snake
     */
    public int getPPointIX(int i) {
        return (int) PPoints.get(i).getX();
    }
    
    /**
     * 
     * Method that returns int value of y coordinate of i-th point of snake
     * 
     * @param i index in points array of snake
     * @return y coordinate of i-th point of snake
     */
    public int getPointIY(int i) {
        return Points[i][1];
    }
    
    /**
     * 
     * Method that returns int value of y coordinate of i-th point of snake
     * 
     * @param i index in points list of snake
     * @return y coordinate of i-th point of snake
     */
    public int getPPointIY(int i) {
        return (int) PPoints.get(i).getY();
    }
    
    /**
     * 
     * Method that returns instance of java.awt.Point class with x & y 
     * coordinates of i-th point of snake
     * 
     * @param i index in points array of snake
     * @return (Point) x & y coordinates of i-th point of snake
     */
    public Point getPointP(int i) {
        return new Point(Points[i][0], Points[i][1]);
    }
    
    /**
     * 
     * Method that returns instance of java.awt.Point class with x & y 
     * coordinates of i-th point of snake
     * 
     * @param i index in points list of snake
     * @return i-th point of snake (x, y)
     */
    public Point getPPointP(int i) {
        return PPoints.get(i);
    }
    
    /**
     * 
     * Method that returns snake's movement keys
     * 
     * @return (Keys) snake movemnet keys
     */
    public Keys getMovementKeys() {
        return MovementKeys;
    }
    
    /**
     * 
     * Method that returns snake's score
     * 
     * @return snake score
     */
    public int getScore() {
        return Score;
    }
    
    /**
     * 
     * Method that returns player's name
     * 
     * @return player name
     */
    public String getPlayerName() {
        return PlayerName;
    }
    
    /**
     * 
     * Method that setups snake's new direction
     * For the first, it tests if the passed value isn't opposite direction,
     * if is, it doesn't do anything
     * if not, it setups new direstion and set flag IsChangedDirection to true,
     * snake direction can be changed only once before snake move!
     * 
     * @param d new direction
     */
    public void setDirection(int d) {
        int d1 = MovementKeys.getKeyIndex(d);
        int d2 = MovementKeys.getKeyIndex(Direction);
        
        if(!IsChangedDirection && ((d1 - d2) != 2) && ((d2 - d1) != 2)) {
            Direction = d;
            IsChangedDirection = true;
        }
    }
    
    /**
     * 
     * Method that setups snake's color
     * 
     * @param c new snake color
     */
    public void setColor(Color c) {
        SnakeColor = c;
    }
    
    /**
     * 
     * Method that setups snake's score
     * 
     * @param s new snake score
     */
    public void setScore(int s) {
        Score = s;
    }
    
    /**
     * 
     * Method that increase snake's score
     * 
     * @param s
     */
    public void increaseScore(int s) {
        Score += s;
    }
    
    /**
     * 
     * Method that setups player's name
     * 
     * @param s setups player's name
     */
    public void setPlayerName(String s) {
        PlayerName = s;
    }
    
    /**
     * 
     * Method that moves snake for 1 point in snake's actual direction
     * and returns java.awt.Point with values of coordinates of last
     * snake's point before move.
     * It setups IsChangedDirection back to false.
     * 
     * @param width map width
     * @param height map height
     * @return (java.awt.Point) point with values of coordinates of last
     * snake's point before move
     */
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
        IsChangedDirection = false;
        return new Point(x, y);
    }
    
    /**
     * 
     * Method that moves snake for 1 point in snake's actual direction
     * and returns java.awt.Point with values of coordinates of last
     * snake's point before move.
     * It setups IsChangedDirection back to false.
     * 
     * @param width map width
     * @param height map height
     * @return (java.awt.Point) point with values of coordinates of last
     * snake's point before move
     */
    public Point movePSnake(int width, int height) {
        Point p = new Point(PPoints.get(PPoints.size() - 1));
        for(int i = PPoints.size() - 1; i >= 1 ; i --) {
            PPoints.get(i).x = PPoints.get(i - 1).x;
            PPoints.get(i).y = PPoints.get(i - 1).y;
        }
        if(Direction == MovementKeys.getKey("Up")) {
            PPoints.get(0).x = PPoints.get(1).x;
            if(PPoints.get(0).y == 0)
                PPoints.get(0).y = height - 1;
            else
                PPoints.get(0).y = PPoints.get(1).y - 1;
        } else if(Direction == MovementKeys.getKey("Down")) {
            PPoints.get(0).x = PPoints.get(1).x;
            if(PPoints.get(0).y == height - 1)
                PPoints.get(0).y = 0;
            else
                PPoints.get(0).y = PPoints.get(1).y + 1;
        } else if(Direction == MovementKeys.getKey("Left")) {
            if(PPoints.get(0).x == 0)
                PPoints.get(0).x = width - 1;
            else
                PPoints.get(0).x = PPoints.get(1).x - 1;
            PPoints.get(0).y = PPoints.get(1).y;
        } else if(Direction == MovementKeys.getKey("Right")) {
            if(PPoints.get(0).x == width - 1)
                PPoints.get(0).x = 0;
            else
                PPoints.get(0).x = PPoints.get(1).x + 1;
            PPoints.get(0).y = PPoints.get(1).y;
        }
        IsChangedDirection = false;
        return p;
    }
    
    /**
     * 
     * Method that increases snake's length for 1 point with coordinates
     * of x & y
     * 
     * @param x x coordinate of new snake's point
     * @param y y coordinate of new snake's point
     */
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
        
        PPoints.add(new Point(x, y));
    }
    
    /**
     * 
     * Method that increases snake's length for 1 point with coordinates
     * of x & y
     * 
     * @param x x coordinate of new snake's point
     * @param y y coordinate of new snake's point
     */
    public void increasePSnake(int x, int y) {
        PPoints.add(new Point(x, y));
    }
    
    /**
     * 
     * Method that paints all snakes points into passed graphics
     * One point size is boxSize
     * 
     * @param g graphics context
     * @param boxSize size of 1 snake's point
     */
    public void paintSnake(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(SnakeColor);
        for(int i = 0; i < Points.length; i ++) {
            g.fillRect(Points[i][0] * boxSize, Points[i][1] * boxSize, boxSize, boxSize);
        }
        g.setColor(pomColor);
    }
    
    /**
     * 
     * Method that paints all snakes points into passed graphics
     * One point size is boxSize
     * 
     * @param g graphics context
     * @param boxSize size of 1 snake's point
     */
    public void paintPSnake(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(SnakeColor);
        for(int i = 0; i < PPoints.size(); i ++) {
            g.fillRect((int) PPoints.get(i).getX() * boxSize + 1, (int) PPoints.get(i).getY() * boxSize + 1, boxSize - 2, boxSize - 2);
        }
        g.setColor(pomColor);
    }
    
    /**
     * 
     * Static method that returns array of all posible snake colors
     * 
     * @return array of all posible snake colors
     */
    public static Color[] getColors() {
        return Colors;
    }
}
