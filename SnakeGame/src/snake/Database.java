package snake;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Class that includes methods for work with database.
 * 
 * @author Marek SMM
 */
public class Database {
    private Connection Conn = null;
    private Statement Stmt;
    private boolean IsOpen;
    
//    public final static String DEFAULT_HOST = "mysql.webzdarma.cz:3306";
//    public final static String DEFAULT_DB_NAME = "papaya";
//    public final static String DEFAULT_USER = "papaya";
//    public final static String DEFAULT_PASSWD = "t83qs2";
    
    public final static String DEFAULT_HOST = "localhost";
    public final static String DEFAULT_DB_NAME = "snake20";
    public final static String DEFAULT_USER = "root";
    public final static String DEFAULT_PASSWD = "";
    
    /**
     * 
     * Contructor that only sets flag IsOpen to false.
     */
    public Database() {
        IsOpen = false;
    }
    
    /**
     * 
     * Contructor that tries to open connection with database.
     * 
     * @param host server's hostname
     * @param database database with snake's data
     * @param user username to login
     * @param passwd user's password
     */
    public Database(String host, String database, String user, String passwd) {
        IsOpen = false;
        this.openConnection(host, database, user, passwd);
    }
    
    /**
     * 
     * Method that tries to open connection to databse with default values
     * 
     * @return returns success
     */
    public boolean openConnectionWithDefaultValues() {
        return this.openConnection(this.DEFAULT_HOST, this.DEFAULT_DB_NAME, this.DEFAULT_USER, this.DEFAULT_PASSWD);
    }
    
    /**
     * 
     * Method that tries to open connection to database.
     * 
     * @param host server's hostname
     * @param database database with snake's data
     * @param user username to login
     * @param passwd user's password
     * 
     * @return returns success
     */
    public boolean openConnection(String host, String database, String user, String passwd) {
        if(!this.isOpen()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "","" + user + "", "" + passwd + "");
                Stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                IsOpen = true;
                return true;
            } catch(Exception e) {
                System.err.println("Exception: " + e.getMessage());
                IsOpen = false;
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that creates database for saving snake data.
     * 
     * @return returns success
     */
    public boolean createDatabaseSnake20() {
        if(this.isOpen()) {
            try {
                boolean execute = Stmt.execute("CREATE DATABASE snake20;");
                return execute;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that creates table `highscores` for saving hi-scores.
     * Table is created if there is open connection.
     * 
     * @return returns success
     */
    public boolean createHighScoresTable() {
        if(this.isOpen()) {
            try {
                boolean execute = Stmt.execute("CREATE TABLE `highscores` (`id` INT NOT NULL AUTO_INCREMENT, `name` TINYTEXT NOT NULL, `level` INT NOT NULL, `maze` INT NOT NULL, `hs` INT NOT NULL, PRIMARY KEY ( `id` ));");
                return execute;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that creates table `mazes` where are saved informations
     * of maze's name & path to file with maze definition.
     * Table is created if there is open connection.
     * 
     * @return returns success
     */
    public boolean createMazeTable() {
        if(this.isOpen()) {
            try {
                boolean execute = Stmt.execute("CREATE TABLE `mazes` (`id` INT NOT NULL AUTO_INCREMENT, `name` TINYTEXT NOT NULL, `path` TINYTEXT NOT NULL, PRIMARY KEY ( `id` ));");
                return execute;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method for adding default mazes to database.
     * Mazes are added if there is open connection.
     * 
     * @return returns success
     */
    public boolean insertDefaltMazes() {
        if(this.isOpen()) {
            try {
                Stmt.execute("INSERT INTO `mazes` (name, path) VALUES (\"No Maze\", \"nomaze.maze\");");
                Stmt.execute("INSERT INTO `mazes` (name, path) VALUES (\"Box\", \"box.maze\");");
                Stmt.execute("INSERT INTO `mazes` (name, path) VALUES (\"Circle\", \"circle.maze\");");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method for adding maze to database.
     * Maze is added if there is open connection.
     * 
     * @param name Maze's name
     * @param path path to file with maze
     * 
     * @return returns success
     */
    public boolean addMaze(String name, String path) {
        if(this.isOpen()) {
            try {
                boolean execute = Stmt.execute("INSERT INTO `mazes` (name, path) VALUES (\"" + name + "\", \"" + path + "\");");
                return execute;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that executes custom SQL query with no result.
     * SQL query is executed if there is open connection.
     * 
     * @param s string with SQL query
     * 
     * @return returns success
     */
    public boolean execute(String s) {
        if(this.isOpen()) {
            try {
                boolean execute = Stmt.execute(s);
                return execute;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that executes custom SQL query with some result.
     * SQL query is executed if there is open connection.
     * 
     * @param s string with SQL query
     * 
     * @return result data as ResultSet object
     * 
     * @throws java.sql.SQLException
     */
    public ResultSet select(String s) throws SQLException {
        return Stmt.executeQuery(s);
    }
    
    /**
     * 
     * Method that adds hi-score data to database.
     * Data are added if there is open connection.
     * 
     * @param sc Score object with data
     * 
     * @return returns success
     */
    public boolean insertHighScoreData(Score sc) {
        if(this.isOpen()) {
            if(sc.getName().length() > 0) {
                try {
                    Stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    boolean execute = Stmt.execute("INSERT INTO `highscores` (`name`, `level`, `maze`, `hs`) VALUES (" + sc.getSQLRow() + ");");
                    return execute;
                } catch (SQLException ex) {
                    Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that selects all hi-score data from database.
     * If there isn't open connection, returns empty list!
     * 
     * @return returns hi-score data as List&lt;Score&gt; object
     */
    @ Deprecated
    public List<Score> selectHighScoresData() {
        List<Score> scl = new ArrayList<Score>();
        
        if(this.isOpen()) {
            try {
                ResultSet rs = Stmt.executeQuery("SELECT name, level, maze, hs FROM highscores ORDER BY hs DESC LIMIT 18;");
                
                while (rs.next()) {
                    scl.add(new Score(rs.getString("name"), rs.getInt("level"), rs.getInt("maze"), rs.getInt("hs")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return scl;
    }
    
    
    /**
     * 
     * Method that selects all hi-score data from selected level from database.
     * If there isn't open connection, returns empty list!
     * 
     * @param l level of game
     * 
     * @return returns hi-score data as List&lt;Score&gt; object
     */
    @ Deprecated
    public List<Score> selectHighScoresData(int l) {
        List<Score> scl = new ArrayList<Score>();
        
        if(this.isOpen() && l > 0 && l <= 4) {
            try {
                ResultSet rs = Stmt.executeQuery("SELECT name, level, maze, hs FROM highscores WHERE level = " + l + " ORDER BY hs DESC LIMIT 18;");
                
                while (rs.next()) {
                    scl.add(new Score(rs.getString("name"), rs.getInt("level"), rs.getInt("maze"), rs.getInt("hs")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return scl;
    }
    
    /**
     * 
     * Method that selects all hi-score data from selected level from database.
     * If there isn't open connection, returns empty list!
     * 
     * @param l level of game
     * @param m maze id
     * @return returns hi-score data as List&lt;Score&gt; object
     */
    public List<Score> selectHighScoresData(int l, int m) {
        List<Score> scl = new ArrayList<Score>();
        
        if(this.isOpen() && l > -1 && l <= 4) {
            try {
                String query = "SELECT name, level, maze, hs FROM highscores";
                if(l != 0 && m != 0) {
                    query += " WHERE level = " + l + " AND maze = " + m;
                } else if(l != 0) {
                    query += " WHERE level = " + l;
                } else if(m != 0) {
                    query += " WHERE maze = " + m;
                }
                query += " ORDER BY hs DESC LIMIT 18;";
                
                ResultSet rs = Stmt.executeQuery(query);
                
                while (rs.next()) {
                    scl.add(new Score(rs.getString("name"), rs.getInt("level"), rs.getInt("maze"), rs.getInt("hs")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return scl;
    }
    
    /**
     * 
     * Method that selects all mazes from database.
     * IF there isn't open connection, returns empty list!
     * 
     * @return returns maze data as List&lt;MazeData&gt; object
     */
    public List<MazeData> selectAllMazes() {
        List<MazeData> mdl = new ArrayList<MazeData>();
        
        if(this.isOpen()) {
            try {
                ResultSet rs = Stmt.executeQuery("SELECT id, name, path FROM mazes");
                
                while (rs.next()) {
                    mdl.add(new MazeData(rs.getInt("id"), rs.getString("name"), rs.getString("path")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return mdl;
    }
    
    /**
     * 
     * Method that selects one maze from database where `id` is 'i' .
     * If there isn't open connection, returns empty MazeData object.
     * 
     * @param i index in mazes table in database.
     * 
     * @return returns one maze data as MazeData object.
     */
    public MazeData selectMaze(int i) {
        MazeData md = new MazeData();
        
        if(this.isOpen()) {
            try {
                ResultSet rs = Stmt.executeQuery("SELECT id, name, path FROM mazes WHERE id = " + i);
                
                while (rs.next()) {
                    md.setId(rs.getInt("id"));
                    md.setName(rs.getString("name"));
                    md.setPath(rs.getString("path"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return md;
    }
    
    /**
     * 
     * Method that selects one maze from database where `name` is 'n'.
     * If there isn't open connection, returns empty MazeData object.
     * 
     * @param n maze's name.
     * 
     * @return returns one maze data as MazeData object.
     */
    public MazeData selectMaze(String n) {
        MazeData md = new MazeData();
        
        if(this.isOpen()) {
            try {
                ResultSet rs = Stmt.executeQuery("SELECT id, name, path FROM mazes WHERE name = \"" + n + "\"");
                
                while (rs.next()) {
                    md.setId(rs.getInt("id"));
                    md.setName(rs.getString("name"));
                    md.setPath(rs.getString("path"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return md;
    }
    
    /**
     * 
     * Method that closes connection with database.
     * 
     * @return returns success
     */
    public boolean closeConnection() {
        if(this.isOpen()) {
            try {
                Conn.close();
                Conn = null;
                Stmt = null;
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * 
     * Method that returns true if there is open connection & false
     * if there isn't.
     * 
     * @return returns IsOpen flag.
     */
    public boolean isOpen() {
        return IsOpen;
    }
    
    /**
     * 
     * Method that returns this class name & isOpen flag.
     * 
     * @return this class name & isOpen flag
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", isOpen: " + this.isOpen();
    }
}
