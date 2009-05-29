package snake;

/**
 * 
 * Class that representing score values.
 * It is player name, level, maze and score.
 * 
 * @author Marek SMM
 */
public class Score {
    private String Name;
    private int Level;
    private int Maze;
    private int Score;
    
    /**
     * 
     * Contructor that sets all int variables to 0 a Name to empty string.
     * 
     */
    public Score() {
        this("", 0, 0, 0);
    }
    
    /**
     * 
     * Contructor that sets all int variables to 0 a Name to n.
     * 
     * @param n player's name
     */
    public Score(String n) {
        this(n, 0, 0, 0);
    }
    
    /**
     * 
     * Contructor that sets all variables.
     * 
     * @param n player's name
     * @param l score's level
     * @param m score's maze
     * @param hs score value
     */
    public Score(String n, int l, int m, int hs) {
        Name = n;
        Level = l;
        Maze = m;
        Score = hs;
    }
    
    /**
     * 
     * Method that returns player's name.
     * 
     * @return player name
     */
    public String getName() {
        return Name;
    }
    
    /**
     * 
     * Method that returns score's level.
     * 
     * @return score's level
     */
    public int getLevel() {
        return Level;
    }
    
    /**
     * 
     * Method that returns score's maze.
     * 
     * @return score's maze
     */
    public int getMaze() {
        return Maze;
    }
    
    /**
     * 
     * Method that returns score value.
     * 
     * @return returns score value
     */
    public int getScore() {
        return Score;
    }
    
    /**
     * 
     * Method that returns string Name + Score separated by " = ".
     * 
     * @return string Name + Score separated by " = ".
     */
    public String getNameAndHs() {
        return Name + " = " + Score + "; ";
    }
    
    /**
     * 
     * Method that returns string formed as SQL row.
     * ( "'" + Name + "', " + Level + ", " + Maze + ", " + Score )
     * 
     * @return string formed as SQL row
     */
    public String getSQLRow() {
        return "'" + Name + "', " + Level + ", " + Maze + ", " + Score;
    }
    
    /**
     * 
     * Method that sets player name.
     * 
     * @param s player name
     */
    public void setName(String s) {
        Name = s;
    }
    
    /**
     * 
     * Method that sets score's level.
     * 
     * @param l score's level
     */
    public void setLevel(int l) {
        Level = l;
    }
    
    /**
     * 
     * Method that sets score's maze.
     * 
     * @param m score's maze
     */
    public void setMaze(int m) {
        Maze = m;
    }
    
    /**
     * 
     * Method that sets score value.
     * 
     * @param hs score value
     */
    public void setScore(int hs) {
        Score = hs;
    }
    
    /**
     * 
     * Method that returns this class name, player name, maze index, level & score.
     * 
     * @return this class name, player name, maze index, level & score
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", name: " + this.getName() + ", maze: " + this.getMaze() + ", level: " + this.getLevel() + ", score: " + this.getScore();
    }
}
