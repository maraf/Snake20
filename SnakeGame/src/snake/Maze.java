package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Maze.
 * 
 * @author Marek SMM
 */
public class Maze {
    private List<Point> Points;
    private String MazeName;
    
    public final static Color DEFAULT_MAZE_COLOR = new Color(240, 240, 240);
    
    /**
     * 
     * Contructor that creates empty maze.
     * 
     */
    public Maze() {
        Points = new ArrayList<Point>();
    }
    
    /**
     * 
     * Constructor that creates maze with points specified in passed argument.
     * 
     * @param p maze's points
     */
    public Maze(List<Point> p) {
        Points = new ArrayList<Point>();
        Points = p;
    }
    
    /**
     * 
     * Contructor that creates maze with points specified in passed array.
     * Points are accepted only if array has format [x][2], where x > 0
     * 
     * @param p array with maze's points
     */
    public Maze(int[][] p) {
        Points = new ArrayList<Point>();
        if(p.length > 0 && p[0].length == 2) {
            for(int i = 0; i < p.length; i ++) {
                Points.add(new Point(p[i][0], p[i][1]));
            }
        }
    }
    
    /**
     * 
     * Method that adds point to maze.
     * 
     * @param p new point in maze
     */
    public void addPoint(Point p) {
        Points.add(new Point(p));
    }
    
    /**
     * 
     * Method that adds point to maze passed as int array.
     * 
     * @param p new point in maze
     */
    public void addPoint(int[] p) {
        if(p.length == 2) {
            Points.add(new Point(p[0], p[1]));
        }
    }
    
    /**
     * 
     * Method that deletes point from maze selected by index.
     * 
     * @param i index in maze's points list
     */
    public void deletePoint(int i) {
        Points.remove(i);
    }
    
    /**
     * 
     * Method that deletes point from maze selected by x & y position.
     * 
     * @param x
     * @param y
     */
    public void deletePoint(int x, int y) {
        for(int i = 0; i < Points.size(); i ++) {
            if((int) Points.get(i).getX() == x && (int) Points.get(i).getY() == y) {
                Points.remove(i);
                return ;
            }
        }
    }
    
    /**
     * 
     * Method that clears list with maze's points.
     * 
     */
    public void clear() {
        Points.clear();
    }
    
    /**
     * 
     * Method that returns point selected by index.
     * 
     * @param i index in maze's points list
     * @return maze's point
     */
    public Point getPoint(int i) {
        return Points.get(i);
    }
    
    /**
     * 
     * Method that returns point as int array selected by index.
     * 
     * @param i index in maze's points list
     * @return maze's point as int array
     */
    public int[] getPointI(int i) {
        int[] retArr = new int[2];
        retArr[0] = (int) Points.get(i).getX();
        retArr[1] = (int) Points.get(i).getY();
        return retArr;
    }
    
    /**
     * 
     * Method that returns int value of X coordinate of selected point by index.
     * 
     * @param i index in maze's points list
     * @return x coordinate of selected point
     */
    public int getPointX(int i) {
        return (int) Points.get(i).getX();
    }
    
    /**
     * 
     * Method that returns int value of Y coordinate of selected point by index.
     * 
     * @param i index in maze's points list
     * @return y coordinate of selected point
     */
    public int getPointY(int i) {
        return (int) Points.get(i).getY();
    }
    
    /**
     * 
     * Method that returns count of maze's points.
     * 
     * @return count of maze's points
     */
    public int getLength() {
        return Points.size();
    }
    
    /**
     * 
     * Method that returns maze's name.
     * 
     * @return maze's name
     */
    public String getMazeName() {
        return MazeName;
    }
    
    /**
     * 
     * Method that sets maze's name.
     * 
     * @param s maze's name
     */
    public void setMazeName(String s) {
        MazeName = s;
    }
    
    /**
     * 
     * Method that loads maze's points form file.
     * 
     * @param fileName name of file with maze
     * @return success
     */
    public boolean loadMazeFromFile(String fileName) {
        FileInputStream fis;
        try {
            fis = new FileInputStream(fileName);
            try {
                int c;
                String s = "";
                int[] p = new int[2];
                int i = p[0] = p[1] = 0;
                while((c = fis.read()) != -1) {
                    if(c == '-') {
                        p[i] = Integer.parseInt(s);
                        s = "";
                        i ++;
                    } else if (c == ';') {
                        p[i] = Integer.parseInt(s);
                        s = "";
                        i = 0;
                        Points.add(new Point(p[0], p[1]));
                        p[0] = p[1] = 0;
                    } else {
                        s += String.valueOf(c - 48);
                    }
                }
            } catch (IOException ex) {
                System.err.println("Failed to read maze file!");
                return false;
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File with mazes doesn't exists!!");
            return false;
        }        
        return true;
    }
    
    /**
     * 
     * Method that paints maze.
     * 
     * @param g Graphics of image to paint maze
     * @param boxSize size of one point
     */
    public void paintMaze(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(DEFAULT_MAZE_COLOR);
        for(int i = 0; i < Points.size(); i ++) {
            g.fillRect((int) Points.get(i).getX() * boxSize + 1, (int) Points.get(i).getY() * boxSize + 1, boxSize - 1, boxSize - 1);
        }
        g.setColor(pomColor);
    }
    
    /**
     * 
     * Method that returns this class name, maze's name & count of points.
     * 
     * @return this class name, maze's name & count of points
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", name: " + this.getMazeName() + ", points.length: " + this.getLength();
    }
}
