package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * 
 * Class representing game view.
 * 
 * @author Marek SMM
 */
public class GamePanel extends JPanel {
    
    public List<Snake> Snakes;
    public List<Food> Foods;
    public Maze ThisMaze;
    
    /**
     * 
     * Contructor that creates lists & set bounds of this pannel
     * 
     * @param width sets panel width
     * @param height sets panel height
     */
    public GamePanel(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setVisible(true);
        
        Snakes = new ArrayList<Snake>();
        Foods = new ArrayList<Food>();
        ThisMaze = new Maze();
    }
    
    /**
     * 
     * Method that draws image with game on the screen
     * 
     * @param g graphics context
     */
    @Override
    public void paint(Graphics g) {
        Image offIm = createImage(450, 310);
        Graphics og = offIm.getGraphics();
        
        og.setColor(new Color(0, 100, 0));
        og.fillRect(0, 0, offIm.getWidth(null), offIm.getHeight(null));
        og.setColor(Color.black);
        
        ThisMaze.paintMaze(og, 10);
        
        for(int i = 0; i < Foods.size(); i ++) {
            Foods.get(i).paintFood(og, 10);
        }
        
        for(int i = 0; i < Snakes.size(); i ++) {
            Snakes.get(i).paintPSnake(og, 10);
        }
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(offIm, 10, 10, this);
    }
    
    /**
     * 
     * Method that re-writes parent method, this only calls paint(g)
     * 
     * @param g graphics context
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    /**
     * 
     * Method that load actual maze
     * 
     * @param md maze informations
     */
    public void setActualMaze(MazeData md) {
        ThisMaze.loadMazeFromFile(md.getPath());
    }
    
    /**
     * 
     * Method that returns this class name & visibility flag.
     * 
     * @return this class name & visibility flag
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", visibility: " + this.isVisible();
    }
}
