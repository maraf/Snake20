package snake;

/**
 * 
 * Class that representing 3 key values for load maze.
 * Id, maze name and path to file with maze.
 * 
 * @author Marek SMM
 */
public class MazeData {
    private int Id;
    private String Name;
    private String Path;
    
    /**
     * 
     * Contructor that sets Id to 0 and Name & Path to empty strings
     * 
     */
    public MazeData() {
        this(0, "", "");
    }
    
    /**
     * 
     * Contructor that sets Id to i, Name to value of n & Path to value of p
     * 
     * @param i Maze id
     * @param n Maze name
     * @param p Path to file with maze definition
     */
    public MazeData(int i, String n, String p) {
        Id = i;
        Name = n;
        Path = p;
    }
    
    /**
     * 
     * Method that returns maze id
     * 
     * @return maze id
     */
    public int getId() {
        return Id;
    }
    
    /**
     * 
     * Method that returns maze name
     * 
     * @return maze name
     */
    public String getName() {
        return Name;
    }
    
    /**
     * 
     * Method that returns path to file with maze definition
     * 
     * @return path to file with maze definition
     */
    public String getPath() {
        return Path;
    }
    
    /**
     * 
     * Method that sets maze id
     * 
     * @param i maze id
     */
    public void setId(int i) {
        Id = i;
    }
    
    /**
     * 
     * Method that sets name of maze
     * 
     * @param n Name of maze
     */
    public void setName(String n) {
        Name = n;
    }
    
    /**
     * 
     * Methos that sets path to file with maze definition
     * 
     * @param p Path to file with maze definition
     */
    public void setPath(String p) {
        Path = p;
    }
    
    /**
     * 
     * Methof that returns this class name, id, name & path of this maze.
     * 
     * @return this class name, id, name & path of this maze
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", id: " + this.getId() + ", name: " + this.getName() + ", path: " + this.getPath();
    }
}
