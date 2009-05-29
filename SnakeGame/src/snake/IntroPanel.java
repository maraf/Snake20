/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author Marek SMM
 */
public class IntroPanel extends JPanel {
    public IntroPanel(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setVisible(true);
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
     * Method that draws intro image on screen.
     * 
     * @param g graphics context
     */
    @Override
    public void paint(Graphics g) {
        Image im = this.getToolkit().getImage("textures/snake.gif");
        g.drawImage(im, 0, 0, this);
    }
    
    /**
     * 
     * Method that returns this class name & visibility flag.
     * 
     * @return
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", visibility: " + this.isVisible();
    }
}
