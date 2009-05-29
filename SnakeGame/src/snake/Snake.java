package snake;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Class that representing snake.
 * 
 * @author Marek SMM
 */
public class Snake {
    @Deprecated
    private int Points[][];
    private List<SnakePoint> PPoints;
    private int Direction;
    private int LastDirection;
    private boolean IsChangedDirection;
    private int Score;
    private String PlayerName;
    private Color SnakeColor;
    private int SnakeColorI;
    private Keys MovementKeys;
    
    private static Image[][] Textures;
    
    private static int usedColors = 0;
    private final static Color[] Colors = {Color.RED, Color.BLUE, Color.GREEN, 
                                           Color.YELLOW, Color.ORANGE, Color.WHITE,
                                           Color.BLACK, Color.CYAN, Color.GRAY,
                                           Color.PINK, Color.MAGENTA};
    
    private final static int DEFAULT_LENGTH = 5;
    private final static int DEFAULT_OFFSET_X = 5;
    private final static int DEFAULT_OFFSET_Y = 5;
    
    /**
     * 
     * Contructor that creates Snake object and all properties sets 
     * to default values
     */
    public Snake() {
        this(DEFAULT_OFFSET_X, DEFAULT_OFFSET_Y, DEFAULT_LENGTH, new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT), "");
    }
    
    /**
     * 
     * Contructor that creates Snake object and sets its offset(x, y) on the map
     * 
     * @param offSetX x coordinate on the map
     * @param offSetY y coordinate on the map
     */
    public Snake(int offSetX, int offSetY) {
        this(offSetX, offSetY, DEFAULT_LENGTH, new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT), "");
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
        this(offSetX, offSetY, length, new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT), "");
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
        this(offSetX, offSetY, length, keys, "");
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
     * @param pln player's name
     * 
     */
    public Snake(int offSetX, int offSetY, int length, Keys keys, String pln) {
        PlayerName = pln;
        Score = 0;
        Points = new int[length][2];
        Direction = KeyEvent.VK_RIGHT;
        
        SnakeColor = new Color(Colors[usedColors].getRGB());
        SnakeColorI = usedColors;
        
        usedColors ++;
        usedColors = (usedColors % 4);
        
        
        for(int i = 0; i < Points.length; i ++) {
            Points[i][0] = Points.length - i - 1 + offSetX;
            Points[i][1] = offSetY;
        }
        
        MovementKeys = keys;
        Direction = MovementKeys.getKey("Right");
        
        PPoints = new ArrayList<SnakePoint>();
        
        for(int i = 0; i < length; i ++) {
            PPoints.add(new SnakePoint(length - i - 1 + offSetX, offSetY, 9, false, Direction));
        }
    }
    
    /**
     * 
     * Method that returns snake's length
     * 
     * @return snake length
     */
    @Deprecated
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
    @Deprecated
    public int[][] getPoints() {
        return Points;
    }
    
    /**
     * 
     * Method that returns all snake's points
     * 
     * @return snake points
     */
    public List<SnakePoint> getPPoints() {
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    public SnakePoint getPPointP(int i) {
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
        
        if(!IsChangedDirection && ((d1 - d2) != 2) && ((d2 - d1) != 2) && (d1 != d2)) {
            LastDirection = Direction;
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
    @Deprecated
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
            PPoints.get(i).setTextureIndex(PPoints.get(i - 1).getTextureIndex());
            PPoints.get(i).setEatedFood(PPoints.get(i - 1).isEatedFood());
            PPoints.get(i - 1).setEatedFood(false);
            PPoints.get(i).setDirection(PPoints.get(i - 1).getDirection());
        }
        
        PPoints.get(0).setDirection(getDirection());
        if(Direction == MovementKeys.getKey("Up")) {
            PPoints.get(0).x = PPoints.get(1).x;
            if(PPoints.get(0).y == 0)
                PPoints.get(0).y = height - 1;
            else
                PPoints.get(0).y = PPoints.get(1).y - 1;
            if(LastDirection == MovementKeys.getKey("Right")) {
                PPoints.get(1).setTextureIndex(1);
            } else {
                PPoints.get(1).setTextureIndex(5);
            }
        } else if(Direction == MovementKeys.getKey("Down")) {
            PPoints.get(0).x = PPoints.get(1).x;
            if(PPoints.get(0).y == height - 1)
                PPoints.get(0).y = 0;
            else
                PPoints.get(0).y = PPoints.get(1).y + 1;
            if(LastDirection == MovementKeys.getKey("Right")) {
                PPoints.get(1).setTextureIndex(2);
            } else {
                PPoints.get(1).setTextureIndex(6);
            }
        } else if(Direction == MovementKeys.getKey("Left")) {
            if(PPoints.get(0).x == 0)
                PPoints.get(0).x = width - 1;
            else
                PPoints.get(0).x = PPoints.get(1).x - 1;
            PPoints.get(0).y = PPoints.get(1).y;
            if(LastDirection == MovementKeys.getKey("Up")) {
                PPoints.get(1).setTextureIndex(3);
            } else {
                PPoints.get(1).setTextureIndex(7);
            }
        } else if(Direction == MovementKeys.getKey("Right")) {
            if(PPoints.get(0).x == width - 1)
                PPoints.get(0).x = 0;
            else
                PPoints.get(0).x = PPoints.get(1).x + 1;
            PPoints.get(0).y = PPoints.get(1).y;
            if(LastDirection == MovementKeys.getKey("Up")) {
                PPoints.get(1).setTextureIndex(4);
            } else {
                PPoints.get(1).setTextureIndex(8);
            }
        }
        if(!IsChangedDirection) {
            if(Direction == MovementKeys.getKey("Up") || Direction == MovementKeys.getKey("Down")) {
                PPoints.get(0).setTextureIndex(0);
                PPoints.get(1).setTextureIndex(0);
            } else {
                PPoints.get(0).setTextureIndex(9);
                PPoints.get(1).setTextureIndex(9);
            }
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
    @Deprecated
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
    
    /**
     * 
     * Method that increases snake's length for 1 point with coordinates
     * of x & y
     * 
     * @param x x coordinate of new snake's point
     * @param y y coordinate of new snake's point
     */
    public void increasePSnake(int x, int y) {
        PPoints.add(new SnakePoint(x, y, PPoints.get(PPoints.size() - 1).getTextureIndex()));
    }
    
    /**
     * 
     * Method that paints all snakes points into passed graphics
     * One point size is boxSize
     * 
     * @param g graphics context
     * @param boxSize size of 1 snake's point
     */
    @Deprecated
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
        for(int i = 1; i < PPoints.size() - 1; i ++) {
            if(PPoints.get(i).isEatedFood()) {
                g.setColor(new Color(143, 141, 141));
                g.fillOval((int) PPoints.get(i).getX() * boxSize, (int) PPoints.get(i).getY() * boxSize, boxSize, boxSize);
            }
            
            g.drawImage(Textures[SnakeColorI][PPoints.get(i).getTextureIndex()], (int) PPoints.get(i).getX() * boxSize, (int) PPoints.get(i).getY() * boxSize, null);  
        }
        
        g.drawImage(Textures[SnakeColorI][10 + MovementKeys.getKeyIndex(getDirection())], (int) PPoints.get(0).getX() * boxSize, (int) PPoints.get(0).getY() * boxSize, null);
        g.drawImage(Textures[SnakeColorI][14 + MovementKeys.getKeyIndex(PPoints.get(PPoints.size() - 2).getDirection())], (int) PPoints.get(PPoints.size() - 1).getX() * boxSize, (int) PPoints.get(PPoints.size() - 1).getY() * boxSize, null);
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
    
    /**
     * 
     * Method that loads textures from files passed in names array.
     * 
     * @param names array with names of files with textures
     * @param tk Toolkit to load image
     * @param mt MediaTracker for immediately load
     */
    public static void loadTextures(String[][] names, Toolkit tk, MediaTracker mt) {
        Textures = new Image[names.length][names[0].length];
        for (int i = 0; i < Textures.length; i++) {
            for(int j = 0; j < Textures[i].length; j ++) {
                Textures[i][j] = tk.getImage("textures/" + names[i][j]);
                mt.addImage(Textures[i][j], i);
            }
        }
        try {
            mt.waitForAll();
        } catch (InterruptedException ex) {
            Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
