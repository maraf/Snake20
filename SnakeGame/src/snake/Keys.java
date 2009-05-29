package snake;

import java.awt.event.KeyEvent;

/**
 * 
 * Class that representing movement keys for snake.
 * 
 * @author Marek SMM
 */
public class Keys {
    private int Up;
    private int Right;
    private int Down;
    private int Left;
    
    /**
     * 
     * Contructor that creates default keys where are sets to default
     * (arrows key)
     */
    public Keys() {
        this(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT);
    }
    
    /**
     * 
     * Contructor that sets each key to each according value 
     * on according index in array if array's length is 4.
     * Array index:     Key:
     * 0                Up
     * 1                Right
     * 2                Down
     * 3                Left
     * @param keys array with values for each key
     */
    public Keys(int[] keys) {
        if(keys.length != 4) {
            Up = KeyEvent.VK_UP;
            Right = KeyEvent.VK_RIGHT;
            Down = KeyEvent.VK_DOWN;
            Left = KeyEvent.VK_LEFT;
        } else {
            Up = keys[0];
            Right = keys[1];
            Down = keys[2];
            Left = keys[3];
        }
    }
    
    /**
     * 
     * Contructor that sets each key to each passed value.
     * 
     * @param up value for up key
     * @param right value for right key
     * @param down value for down ley
     * @param left value for left key
     */
    public Keys(int up, int right, int down, int left) {
        Up = up;
        Right = right;
        Down = down;
        Left = left;
    }
    
    /**
     * 
     * Method that returns all keys as int array.
     * Array index:     Key:
     * 0                Up
     * 1                Right
     * 2                Down
     * 3                Left
     * 
     * @return all keys as int array
     */
    public int[] getKeys() {
        int[] returnArray = {Up, Right, Down, Left};
        return returnArray;
    }
    
    /**
     * 
     * Method that returns one key selected by i.
     * i:               Key:
     * 0                Up
     * 1                Right
     * 2                Down
     * 3                Left
     * 
     * @param i
     * @return value of selected key
     */
    public int getKey(int i) {
        if(i < 0 && i > 3) return 0;
        int[] returnArray = {Up, Right, Down, Left};
        return returnArray[i];
    }
    
    /**
     * 
     * Method that returns one key selected by String s.
     * 
     * @param s String name of key
     * @return value of selected key
     */
    public int getKey(String s) {
        if(s.toLowerCase().equals("up")) {
            return Up;
        } else if(s.toLowerCase().equals("right")) {
            return Right;
        } else if(s.toLowerCase().equals("down")) {
            return Down;
        } else if(s.toLowerCase().equals("left")) {
            return Left;
        } else {
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns key name selected by value of key.
     * 
     * @param i value of key
     * @return name of key
     */
    public String getKeyName(int i) {
        if(Up == i) return "Up";
        if(Right == i) return "Right";
        if(Down == i) return "Down";
        if(Left == i) return "Left";
        
        return "";
    }
    
    /**
     * 
     * Method that returns index in array of keys (Up, Right, Down, Left)
     * selected by value of key in i.
     * 
     * @param i value of key
     * @return index in array of keys (Up, Right, Down, Left)
     */
    public int getKeyIndex(int i) {
        int[] returnArray = {Up, Right, Down, Left};
        for(int j = 0; j < 4; j ++) {
            if(returnArray[j] == i) {
                return j;
            }
        }
        
        return -1;
    }
    
    /**
     * 
     * Method that sets each key to each according value 
     * on according index in array if array's length is 4.
     * Array index:     Key:
     * 0                Up
     * 1                Right
     * 2                Down
     * 3                Left
     * @param keys array with values for each key
     */
    public void setKeys(int[] keys) {
        if(keys.length != 4) {
            System.err.println("array length must 4! Keys sets to default.");
            Up = KeyEvent.VK_UP;
            Right = KeyEvent.VK_RIGHT;
            Down = KeyEvent.VK_DOWN;
            Left = KeyEvent.VK_LEFT;
        } else {
            Up = keys[0];
            Right = keys[1];
            Down = keys[2];
            Left = keys[3];
        }
    }
    
    /**
     * 
     * Method that sets value of one key selected by index 
     * in array of all keys.
     * i:               Key:
     * 0                Up
     * 1                Right
     * 2                Down
     * 3                Left
     * 
     * @param i index in array
     * @param value value of key
     */
    public void setKey(int i, int value) {
        switch(i) {
            case 0: Up = value; break;
            case 1: Right = value; break;
            case 2: Down = value; break;
            case 3: Left = value; break;
            default: System.err.println("i value must be from 0 to 3!"); break;
        }
    }
    
    /**
     * 
     * Method sets value of one key selected by String name of key.
     * 
     * @param s String name of key
     * @param value value of key
     */
    public void setKey(String s, int value) {
        if(s.toLowerCase().equals("up")) {
            Up = value;
        } else if(s.toLowerCase().equals("right")) {
            Right = value;
        } else if(s.toLowerCase().equals("down")) {
            Down = value;
        } else if(s.toLowerCase().equals("left")) {
            Left = value;
        } else {
            System.err.println("i value must be from 0 to 3!");
        }
    }
    
    /**
     * 
     * Method that returns this class name & values of all keys.
     * 
     * @return this class name & values of all keys
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", Up: " + this.getKey(0) + ", Right: " + this.getKey(1) + ", Down: " + this.getKey(2) + ", Left: " + this.getKey(3);
    }
}
