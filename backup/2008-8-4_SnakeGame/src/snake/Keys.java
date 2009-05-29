package snake;

import java.awt.event.KeyEvent;

/**
 *
 * @author Marek SMM
 */
public class Keys {
    private int Up;
    private int Right;
    private int Down;
    private int Left;
    
    public Keys() {
        Up = KeyEvent.VK_UP;
        Right = KeyEvent.VK_RIGHT;
        Down = KeyEvent.VK_DOWN;
        Left = KeyEvent.VK_LEFT;
    }
    
    public Keys(int[] keys) {
        if(keys.length < 3) {
            // throw KeyException
        }
        Up = keys[0];
        Right = keys[1];
        Down = keys[2];
        Left = keys[3];
    }
    
    public Keys(int up, int right, int down, int left) {
        Up = up;
        Right = right;
        Down = down;
        Left = left;
    }
    
    public int[] getKeys() {
        int[] returnArray = {Up, Right, Down, Left};
        return returnArray;
    }
    
    public int getKey(int i) {
        if(i < 0 && i > 3) return 0;
        int[] returnArray = {Up, Right, Down, Left};
        return returnArray[i];
    }
    
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
    
    public String getKeyName(int i) {
        if(Up == i) return "Up";
        if(Right == i) return "Right";
        if(Down == i) return "Down";
        if(Left == i) return "Left";
        
        return "";
    }
    
    public int getKeyIndex(int i) {
        int[] returnArray = {Up, Right, Down, Left};
        for(int j = 0; j < 4; j ++) {
            if(returnArray[j] == i) {
                return j;
            }
        }
        
        return -1;
    }
    
    public void setKeys(int[] keys) {
        if(keys.length < 3) {
            // throw KeyException
        }
        Up = keys[0];
        Right = keys[1];
        Down = keys[2];
        Left = keys[3];
    }
    
    public void setKey(int i, int value) {
        switch(i) {
            case 0: Up = value; break;
            case 1: Right = value; break;
            case 2: Down = value; break;
            case 3: Left = value; break;
            default: /* throw KeyException */ break;
        }
    }
    
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
            // throw KeyException
        }
    }
}
