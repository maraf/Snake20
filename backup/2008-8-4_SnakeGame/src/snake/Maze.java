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
 * @author Marek SMM
 */
public class Maze {
    private List<Point> Points;
    private String MazeName;
    
    public final static Color DEFAULT_MAZE_COLOR = new Color(240, 240, 240);
    
    public Maze() {
        Points = new ArrayList<Point>();
    }
    
    public Maze(List<Point> p) {
        Points = new ArrayList<Point>();
        Points = p;
    }
    
    public Maze(int[][] p) {
        Points = new ArrayList<Point>();
        if(p.length > 0 && p[0].length == 2) {
            for(int i = 0; i < p.length; i ++) {
                Points.add(new Point(p[i][0], p[i][1]));
            }
        } else {
            // throw exception
        }
    }
    
    public void addPoint(Point p) {
        Points.add(new Point(p));
    }
    
    public void addPoint(int[] p) {
        if(p.length == 2) {
            Points.add(new Point(p[0], p[1]));
        }
    }
    
    public void deletePoint(int i) {
        Points.remove(i);
    }
    
    public void deletePoint(int x, int y) {
        for(int i = 0; i < Points.size(); i ++) {
            if((int) Points.get(i).getX() == x && (int) Points.get(i).getY() == y) {
                Points.remove(i);
                return ;
            }
        }
    }
    
    public Point getPoint(int i) {
        return Points.get(i);
    }
    
    public int[] getPointI(int i) {
        int[] retArr = new int[2];
        retArr[0] = (int) Points.get(i).getX();
        retArr[1] = (int) Points.get(i).getY();
        return retArr;
    }
    
    public int getPointX(int i) {
        return (int) Points.get(i).getX();
    }
    
    public int getPointY(int i) {
        return (int) Points.get(i).getY();
    }
    
    public int getLength() {
        return Points.size();
    }
    
    public String getMazeName() {
        return MazeName;
    }
    
    public void setMazeName(String s) {
        MazeName = s;
    }
    
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
                        System.out.println("P: " + p[0] + "; " + p[1]);
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
    
    public void paintMaze(Graphics g, int boxSize) {
        Color pomColor = g.getColor();
        g.setColor(DEFAULT_MAZE_COLOR);
        for(int i = 0; i < Points.size(); i ++) {
            g.fillRect((int) Points.get(i).getX() * boxSize, (int) Points.get(i).getY() * boxSize, boxSize, boxSize);
        }
        g.setColor(pomColor);
    }
}
