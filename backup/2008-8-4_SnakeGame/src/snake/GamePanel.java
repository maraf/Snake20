package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Marek SMM
 */
public class GamePanel extends JPanel {
//    private Image offIm;
    
    public List<Snake> Snakes;
    public List<Food> Foods;
    public List<Maze> Mazes;
    
    private int ActualMaze;
    
    public GamePanel(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setVisible(true);
        
//        offIm = createImage(width, height);
        
        Snakes = new ArrayList<Snake>();
        Foods = new ArrayList<Food>();
        Mazes = new ArrayList<Maze>();
        
        Maze mz = new Maze();
        mz.loadMazeFromFile("maze.txt");
        Mazes.add(mz);
        
        ActualMaze = 0;
    }
    
//    public void setImage(Image im) {
//        offIm = im;
//    }
    
    public void setElements(List<Snake> s, List<Food> f) {
        Snakes = s;
        Foods = f;
    }
    
    @Override
    public void paint(Graphics g) {
        Image offIm = createImage(450, 300);
        Graphics og = offIm.getGraphics();
        
//        og.setColor(Color.orange);
        og.setColor(new Color(0, 100, 0));
        og.fillRect(0, 0, offIm.getWidth(null), offIm.getHeight(null));
        og.setColor(Color.black);
        
        Mazes.get(ActualMaze).paintMaze(og, 10);
        
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
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public void setActualMaze(int m) {
        ActualMaze = m;
    }
    
    public int getActulMaze() {
        return ActualMaze;
    }
}
