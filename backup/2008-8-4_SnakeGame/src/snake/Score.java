package snake;

/**
 *
 * @author Marek SMM
 */
public class Score {
    private String Name;
    private int Level;
    private int Maze;
    private int HighScore;
    
    Score() {
        Name = "";
        Level = 0;
        Maze = 0;
        HighScore = 0;
    }
    
    Score(String n) {
        Name = n;
        Level = 0;
        Maze = 0;
        HighScore = 0;
    }
    
    Score(String n, int l, int m, int hs) {
        Name = n;
        Level = l;
        Maze = m;
        HighScore = hs;
    }
    
    public String getName() {
        return Name;
    }
    
    public int getLevel() {
        return Level;
    }
    
    public int getMaze() {
        return Maze;
    }
    
    public int getScore() {
        return HighScore;
    }
    
    public String getNameAndHs() {
        return Name + " = " + HighScore + "; ";
    }
    
    public String getSQLRow() {
        return "'" + Name + "', " + Level + ", " + Maze + ", " + HighScore;
    }
    
    public void setName(String s) {
        Name = s;
    }
    
    public void setLevel(int l) {
        Level = l;
    }
    
    public void setMaze(int m) {
        Maze = m;
    }
    
    public void setHighScore(int hs) {
        HighScore = hs;
    }
}
