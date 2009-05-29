package snake;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Marek SMM
 */
public class WelcomePanel extends JPanel {
    JPanel pnHead;
    JPanel pnContent;
    
    Choice chLevel;
    JTextPane tpHiscores;
    
    public WelcomePanel(int width, int height) {
        this.setBounds(0, 0, width, height);
        this.setBackground(Color.RED);
        this.setLayout(new BorderLayout());
        
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
                if(chLevel.getSelectedIndex() == 0) {
                    setHiScores();
                } else {
                    setHiScores(chLevel.getSelectedIndex());
                }
            }
        });
        pnHead.add(chLevel);
        
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
    
    @Override
    public void setVisible(boolean b) {
        if(b) {
                if(chLevel.getSelectedIndex() == 0) {
                    setHiScores();
                } else {
                    setHiScores(chLevel.getSelectedIndex());
                }
        }
        super.setVisible(b);
    }
    
    public void setHiScores() {
        String s = "";
        
        Database DB = new Database(Database.DEFAULT_HOST, Database.DEFAULT_DB_NAME, Database.DEFAULT_USER, Database.DEFAULT_PASSWD);
        List<Score> scl = DB.selectHighScoresData();
        DB.closeConnection();
        
        String[] arr = {"Slow", "Medium", "Fast", "Ext. Fast"};
        
        for(int i = 0; i < scl.size(); i ++) {
            if(scl.get(i).getName().length() > 8) {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getScore() + "pts\n";
            } else {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t\t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getScore() + "pts\n";
            }
        }
        
        tpHiscores.setText(s);
    }
    
    public void setHiScores(int l) {
        String s = "";
        
        Database DB = new Database(Database.DEFAULT_HOST, Database.DEFAULT_DB_NAME, Database.DEFAULT_USER, Database.DEFAULT_PASSWD);
        List<Score> scl = DB.selectHighScoresData(l);
        DB.closeConnection();
        
        String[] arr = {"Slow", "Medium", "Fast", "Ext. Fast"};
        
        for(int i = 0; i < scl.size(); i ++) {
            if(scl.get(i).getName().length() > 8) {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getScore() + "pts\n";
            } else {
                s += (i + 1) + ". " + scl.get(i).getName() + " \t\t " + arr[scl.get(i).getLevel() - 1] + "\t" + scl.get(i).getScore() + "pts\n";
            }
        }
        
        tpHiscores.setText(s);
    }
}
