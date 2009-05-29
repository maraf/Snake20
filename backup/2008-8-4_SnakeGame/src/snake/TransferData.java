/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package snake;

import java.awt.Point;

/**
 *
 * @author Marek SMM
 */
public class TransferData {
    public int[] direction;
    public Point food;
    
    public boolean[] newDirection;
    public boolean newFood;
    
    TransferData() {
        direction = new int[2];
        food = new Point();
       
        newDirection = new boolean[2];
        newDirection[0] = false;
        newDirection[1] = false;
        
        newFood = false;
    }
}
