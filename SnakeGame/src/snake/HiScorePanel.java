package snake;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * Class that representing panel with hi-scores.
 * 
 * @author Marek SMM
 */
public class HiScorePanel extends JPanel {
    private JPanel pnHead;
    private JPanel pnContent;
    
    private Choice chLevel;
    private Choice chMaze;
    private JTextPane tpHiscores;
    
    /**
     * 
     * Contructor that creates WelcomePanel with bounds 0, 0, width, height.
     * Display all components & top 18 of hi-score list from database.
     * 
     * @param width panel's width
     * @param height panel's height
     */
    public HiScorePanel(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setLayout(new BorderLayout());
        this.setBackground(new Color(50, 210, 50));
        
        pnHead = new JPanel();
        pnHead.setLayout(new FlowLayout());
        pnHead.setBackground(this.getBackground());
        
        chLevel = new Choice();
        chLevel.addItem("All");
        chLevel.addItem("Slow");
        chLevel.addItem("Medium");
        chLevel.addItem("Fast");
        chLevel.addItem("Extreme Fast");
        chLevel.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setHiScores(chLevel.getSelectedIndex(), chMaze.getSelectedIndex());
            }
        });
        
//        pnHead.add(new JLabel("Level: "));
        pnHead.add(chLevel);
        
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        List<MazeData> mdl = db.selectAllMazes();
        db.closeConnection();
        chMaze = new Choice();
        chMaze.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setHiScores(chLevel.getSelectedIndex(), chMaze.getSelectedIndex());
            }
        });
        chMaze.addItem("All");
        for(int i = 0; i < mdl.size(); i ++) {
            chMaze.addItem(mdl.get(i).getName());
        }
        
        pnHead.add(chMaze);
        
        pnContent = new JPanel();
        pnContent.setLayout(new FlowLayout());
        pnContent.setBackground(this.getBackground());
        
        tpHiscores = new JTextPane();
        tpHiscores.setBackground(this.getBackground());
        tpHiscores.setEditable(false);
        pnContent.add(tpHiscores);
        
        this.add(pnHead, BorderLayout.NORTH);
        this.add(pnContent, BorderLayout.CENTER);
    }
    
    /**
     * 
     * Method that display hi-score list & call parent method setVisible(b)
     * 
     * @param b
     */
    @Override
    public void setVisible(boolean b) {
        if(b) {
            setHiScores(chLevel.getSelectedIndex(), chMaze.getSelectedIndex());
        } else {
            tpHiscores.setText("");
        }
        super.setVisible(b);
    }
    
    /**
     * 
     * Method that display hi-score list from all levels.
     * 
     */
    @ Deprecated
    public void setHiScores() {
        String s = "";
        
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        List<Score> scl = db.selectHighScoresData(0, 0);
        db.closeConnection();
        
        String[] arr = {"Slow", "Medium", "Fast", "Ext. Fast"};
        
        for(int i = 0; i < scl.size(); i ++) {
            if(scl.get(i).getName().length() > 8) {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getMaze() + "\t" + scl.get(i).getScore() + "pts\n";
            } else {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t\t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getMaze() + "\t" + scl.get(i).getScore() + "pts\n";
            }
        }
        
        tpHiscores.setText(s);
    }
    
    /**
     * 
     * Method that display hi-score list form selected level.
     * 
     * @param l level of game
     */
    @ Deprecated
    public void setHiScores(int l) {
        String s = "";
        
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        List<Score> scl = db.selectHighScoresData(l, 0);
        db.closeConnection();
        
        String[] arr = {"Slow", "Medium", "Fast", "Ext. Fast"};
        
        for(int i = 0; i < scl.size(); i ++) {
            if(scl.get(i).getName().length() > 8) {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getMaze() + "\t" + scl.get(i).getScore() + "pts\n";
            } else {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t\t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getMaze() + "\t" + scl.get(i).getScore() + "pts\n";
            }
        }
        
        tpHiscores.setText(s);
    }
    
    /**
     * 
     * Method that display hi-score list from selected level & maze.
     * 
     * @param l level of game
     * @param m maze id
     */
    public void setHiScores(int l, int m) {
        String s = "";
        
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        List<Score> scl = db.selectHighScoresData(l, m);
        List<MazeData> mdl = db.selectAllMazes();
        db.closeConnection();
        
        String[] arr = {"Slow", "Medium", "Fast", "Ext. Fast"};
        
        if(scl.size() == 0) {
            s = "There are no data to display!";
        } else {
            for(int i = 0; i < scl.size(); i ++) {
                if(scl.get(i).getName().length() > 8) {
                    s += (i + 1) + ". " + scl.get(i).getName() + " \t " + arr[scl.get(i).getLevel() - 1] + "\t    " + mdl.get(scl.get(i).getMaze() - 1).getName() + "\t    " + scl.get(i).getScore() + "pts\n";
                } else {
                    s += (i + 1) + ". " + scl.get(i).getName() + " \t\t " + arr[scl.get(i).getLevel() - 1] + "\t    " + mdl.get(scl.get(i).getMaze() - 1).getName() + "\t    " + scl.get(i).getScore() + "pts\n";
                }
            }
        }
        
        tpHiscores.setText(s);
    }
    
    /**
     * 
     * Method that returns this class name & visibility flag.
     * 
     * @return this class name & visibility flag
     */
    @Override
    public String toString() {
        return this.getClass().getName() + ", visibility: " + this.isVisible();
    }
}
