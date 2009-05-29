package snake;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marek SMM
 */
public class Database {
    private Connection Conn = null;
    private Statement Stmt;
    private boolean IsOpen;
    
    public final static String DEFAULT_HOST = "localhost";
    public final static String DEFAULT_DB_NAME = "snake20";
    public final static String DEFAULT_USER = "root";
    public final static String DEFAULT_PASSWD = "";
    
    Database() {
        IsOpen = false;
    }
    
    Database(String host, String database, String user, String passwd) {
        this.openConntection(host, database, user, passwd);
    }
    
    public boolean openConntection(String host, String database, String user, String passwd) {
        if(!this.isOpen()) {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Conn = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "","" + user + "", "" + passwd + "");
                Stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                IsOpen = true;
                return true;
            } catch(Exception e) {
                System.err.println("Exception: " + e.getMessage());
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean createHighScoresTable() {
        if(this.isOpen()) {
            try {
                Stmt.execute("CREATE TABLE `highscores` (`id` INT NOT NULL AUTO_INCREMENT, `name` TINYTEXT NOT NULL, `level` INT NOT NULL, `maze` INT NOT NULL, `hs` INT NOT NULL, PRIMARY KEY ( `id` ));");
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    public boolean execute(String s) {
        if(this.isOpen()) {
            try {
                Stmt.execute(s);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
    }
    
    public ResultSet select(String s) throws SQLException {
        return Stmt.executeQuery(s);
    }
    
    public boolean insertHighScoreData(Score sc) {
        if(this.isOpen()) {
            if(sc.getName().length() > 0) {
                try {
                    Stmt.execute("INSERT INTO `highscores` (`name`, `level`, `maze`, `hs`) VALUES (" + sc.getSQLRow() + ");");
                    return true;
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
    
    public boolean closeConnection() {
        if(this.isOpen()) {
            try {
                Conn.close();
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
    
    public boolean isOpen() {
        return IsOpen;
    }
    
    @Deprecated
    public static String getStHost() {
        return "localhost";
    }
    
    @Deprecated
    public static String getStDatabase() {
        return "snake20";
    }
    
    @Deprecated
    public static String getStUser() {
        return "root";
    }
    
    @Deprecated
    public static String getStPasswd() {
        return "";
    }
}
