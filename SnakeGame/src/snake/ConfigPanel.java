package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * 
 * Class representing panel for game configuration.
 * 
 * @author Marek SMM
 */
public class ConfigPanel extends JPanel {
    private int Level;
    private int NumberOfPlayers;
    private String Maze;
    
    JPanel pnHead;
    JPanel pnContent;
    
    JPanel pnSettings;
    JPanel pnPlayer1;
    JPanel pnPlayer2;
    JPanel pnPlayer3;
    JPanel pnPlayer4;
    
    JButton btBack;
    
    JButton btSettings;
    JButton btPlayer1;
    JButton btPlayer2;
    JButton btPlayer3;
    JButton btPlayer4;
    
    JLabel lbSENoP;
    Choice chSENoP;
    JLabel lbSELevel;
    Choice chSELevel;
    JLabel lbSEMaze;
    Choice chSEMaze;
    JButton btSEOk;
    
    JLabel lbP1Name;
    JTextField tfP1Name;
    JLabel lbP1Up;
    JTextField tfP1Up;
    JLabel lbP1Right;
    JTextField tfP1Right;
    JLabel lbP1Down;
    JTextField tfP1Down;
    JLabel lbP1Left;
    JTextField tfP1Left;
    JButton btP1Ok;
    
    JLabel lbP2Name;
    JTextField tfP2Name;
    JLabel lbP2Up;
    JTextField tfP2Up;
    JLabel lbP2Right;
    JTextField tfP2Right;
    JLabel lbP2Down;
    JTextField tfP2Down;
    JLabel lbP2Left;
    JTextField tfP2Left;
    JButton btP2Ok;
    
    JLabel lbP3Name;
    JTextField tfP3Name;
    JLabel lbP3Up;
    JTextField tfP3Up;
    JLabel lbP3Right;
    JTextField tfP3Right;
    JLabel lbP3Down;
    JTextField tfP3Down;
    JLabel lbP3Left;
    JTextField tfP3Left;
    JButton btP3Ok;
    
    JLabel lbP4Name;
    JTextField tfP4Name;
    JLabel lbP4Up;
    JTextField tfP4Up;
    JLabel lbP4Right;
    JTextField tfP4Right;
    JLabel lbP4Down;
    JTextField tfP4Down;
    JLabel lbP4Left;
    JTextField tfP4Left;
    JButton btP4Ok;
    
    private String[] PlayersNames;
    private int[] PlayersSnakeColors;
    private boolean[] PlayersKeysOk;
    
    private List<Keys> PlayersKeys;
    
    private final static String DEFAULT_P1_NAME = "Player 1";
    private final static String DEFAULT_P2_NAME = "Player 2";
    private final static String DEFAULT_P3_NAME = "Player 3";
    private final static String DEFAULT_P4_NAME = "Player 4";
    
    private final static Keys DEFAULT_KEYS[] = {
        new Keys(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT),
        new Keys(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A),
        new Keys(KeyEvent.VK_T, KeyEvent.VK_H, KeyEvent.VK_G, KeyEvent.VK_F),
        new Keys(KeyEvent.VK_I, KeyEvent.VK_L, KeyEvent.VK_K, KeyEvent.VK_J)
    };
    
    /**
     * 
     * Contructor that creates whole GUI / Setup interface for users.
     * 
     * @param width sets panel width
     * @param height sets panel height
     */
    public ConfigPanel(int width, int height) {
        NumberOfPlayers = 1;
        Level = 1;
        Maze = "";
        
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(new Color(0, 155, 0));
        
        pnHead = new JPanel();
        pnHead.setBounds(20, 20, this.getWidth() - 50, 35);
        pnHead.setBackground(new Color(50, 210, 50));
        this.add(pnHead);
        
        pnContent = new JPanel();
        pnContent.setBounds(20, 70, this.getWidth() - 50, this.getHeight() - 90);
        pnContent.setBackground(new Color(50, 210, 50));
        pnContent.setLayout(new FlowLayout());
        this.add(pnContent);
        
        btSettings = new JButton("Settings");
        btSettings.setFocusable(false);
        btSettings.addActionListener(new HeadButtonListener());
        pnHead.add(btSettings);
        
        btPlayer1 = new JButton("Player 1");
        btPlayer1.setFocusable(false);
        btPlayer1.addActionListener(new HeadButtonListener());
        pnHead.add(btPlayer1);
        
        btPlayer2 = new JButton("Player 2");
        btPlayer2.setFocusable(false);
        btPlayer2.addActionListener(new HeadButtonListener());
        pnHead.add(btPlayer2);
        
        btPlayer3 = new JButton("Player 3");
        btPlayer3.setFocusable(false);
        btPlayer3.addActionListener(new HeadButtonListener());
        pnHead.add(btPlayer3);
        
        btPlayer4 = new JButton("Player 4");
        btPlayer4.setFocusable(false);
        btPlayer4.addActionListener(new HeadButtonListener());
        pnHead.add(btPlayer4);
        
        pnSettings = new JPanel();
        pnSettings.setBackground(pnContent.getBackground());
        pnSettings.setLayout(new GridLayout(4, 2, 30, 10));
        
        lbSENoP = new JLabel("Number of players: ");
        pnSettings.add(lbSENoP);
        chSENoP = new Choice();
        chSENoP.addItem("1");
        chSENoP.addItem("2");
        chSENoP.addItem("3");
        chSENoP.addItem("4");
        pnSettings.add(chSENoP);
        lbSELevel = new JLabel("Game level: ");
        pnSettings.add(lbSELevel);
        chSELevel = new Choice();
        chSELevel.addItem("Slow");
        chSELevel.addItem("Medium");
        chSELevel.addItem("Fast");
        chSELevel.addItem("Extreme Fast");
        pnSettings.add(chSELevel);
        lbSEMaze = new JLabel("Maze: ");
        pnSettings.add(lbSEMaze);
        chSEMaze = new Choice();
        
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        List<MazeData> mdl = db.selectAllMazes();
        db.closeConnection();
        
        for(int i = 0; i < mdl.size(); i ++) {
            chSEMaze.addItem(mdl.get(i).getName());
        }
        
        pnSettings.add(chSEMaze);
        btSEOk = new JButton("Save");
        btSEOk.setMargin(new Insets(0, 0, 0, 0));
        btSEOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("NoP = \t" + Integer.parseInt(chSENoP.getSelectedItem()));
                System.out.println("Lvl = \t" + (chSELevel.getSelectedIndex() + 1));
                System.out.println("Maze = \t" + (chSEMaze.getSelectedItem()));
                
                NumberOfPlayers = Integer.parseInt(chSENoP.getSelectedItem());
                Level = chSELevel.getSelectedIndex() + 1;
                Maze = chSEMaze.getSelectedItem();
            }
        });
                
        pnSettings.add(new Label());
        pnSettings.add(btSEOk);
        
        pnSettings.validate();
        pnContent.add(pnSettings);
        
        
        PlayersNames = new String[4];
        PlayersSnakeColors = new int[4];
        PlayersKeysOk = new boolean[4];
        
        PlayersKeys = new ArrayList<Keys>();
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        
        /* ------------ P1 START ----------------------- */
        pnPlayer1 = new JPanel();
        pnPlayer1.setBackground(pnContent.getBackground());
        pnPlayer1.setLayout(new GridLayout(6, 2, 30, 5));
        pnPlayer1.setVisible(false);
        
        lbP1Name = new JLabel("Name: ");
        pnPlayer1.add(lbP1Name);
        tfP1Name = new JTextField();
        tfP1Name.setText(DEFAULT_P1_NAME);
        tfP1Name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(tfP1Name.getText().length() == 0) {
                    tfP1Name.setText(DEFAULT_P1_NAME);
                }
            }
        });
        PlayersNames[0] = DEFAULT_P1_NAME;
        pnPlayer1.add(tfP1Name);
        lbP1Up = new JLabel("Key for up: ");
        pnPlayer1.add(lbP1Up);
        tfP1Up = new JTextField();
        tfP1Up.setEditable(false);
        tfP1Up.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Up.addKeyListener(new PlayerKeyListener(0, 0));
        tfP1Up.setText(String.valueOf((char) DEFAULT_KEYS[0].getKey(0)).toLowerCase());
        PlayersKeys.get(0).setKey(0, DEFAULT_KEYS[0].getKey(0));
        pnPlayer1.add(tfP1Up);
        lbP1Right = new JLabel("Key for right: ");
        pnPlayer1.add(lbP1Right);
        tfP1Right = new JTextField();
        tfP1Right.setEditable(false);
        tfP1Right.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Right.addKeyListener(new PlayerKeyListener(0, 1));
        tfP1Right.setText(String.valueOf((char) DEFAULT_KEYS[0].getKey(1)).toLowerCase());
        PlayersKeys.get(0).setKey(1, DEFAULT_KEYS[0].getKey(1));
        pnPlayer1.add(tfP1Right);
        lbP1Down = new JLabel("Key for down: ");
        pnPlayer1.add(lbP1Down);
        tfP1Down = new JTextField();
        tfP1Down.setEditable(false);
        tfP1Down.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Down.addKeyListener(new PlayerKeyListener(0, 2));
        tfP1Down.setText(String.valueOf((char) DEFAULT_KEYS[0].getKey(2)).toLowerCase());
        PlayersKeys.get(2).setKey(0, DEFAULT_KEYS[0].getKey(2));
        pnPlayer1.add(tfP1Down);
        lbP1Left = new JLabel("Key for left: ");
        pnPlayer1.add(lbP1Left);
        tfP1Left = new JTextField();
        tfP1Left.setEditable(false);
        tfP1Left.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Left.addKeyListener(new PlayerKeyListener(0, 3));
        tfP1Left.setText(String.valueOf((char) DEFAULT_KEYS[0].getKey(3)).toLowerCase());
        PlayersKeys.get(0).setKey(3, DEFAULT_KEYS[0].getKey(3));
        pnPlayer1.add(tfP1Left);
        
        btP1Ok = new JButton("Save");
        btP1Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNames[0] = (tfP1Name.getText().length() > 10) ? tfP1Name.getText().substring(0, 10) : tfP1Name.getText();
                PlayersKeysOk[0] = true;
            }
        });
        pnPlayer1.add(new Label());
        pnPlayer1.add(btP1Ok);
        
        pnContent.add(pnPlayer1);
        /* ------------ P1 END -------------------------- */
        
        
        /* ------------ P2 START ------------------------ */
        pnPlayer2 = new JPanel();
        pnPlayer2.setBackground(pnContent.getBackground());
        pnPlayer2.setLayout(new GridLayout(6, 2, 30, 5));
        pnPlayer2.setVisible(false);
        
        lbP2Name = new JLabel("Name: ");
        pnPlayer2.add(lbP2Name);
        tfP2Name = new JTextField();
        tfP2Name.setText(DEFAULT_P2_NAME);
        tfP2Name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(tfP2Name.getText().length() == 0) {
                    tfP2Name.setText(DEFAULT_P2_NAME);
                }
            }
        });
        PlayersNames[1] = DEFAULT_P2_NAME;
        pnPlayer2.add(tfP2Name);
        lbP2Up = new JLabel("Key for up: ");
        pnPlayer2.add(lbP2Up);
        tfP2Up = new JTextField();
        tfP2Up.setEditable(false);
        tfP2Up.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP2Up.addKeyListener(new PlayerKeyListener(1, 0));
        tfP2Up.setText(String.valueOf((char) DEFAULT_KEYS[1].getKey(0)).toLowerCase());
        PlayersKeys.get(1).setKey(0, DEFAULT_KEYS[1].getKey(0));
        pnPlayer2.add(tfP2Up);
        lbP2Right = new JLabel("Key for right: ");
        pnPlayer2.add(lbP2Right);
        tfP2Right = new JTextField();
        tfP2Right.setEditable(false);
        tfP2Right.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP2Right.addKeyListener(new PlayerKeyListener(1, 1));
        tfP2Right.setText(String.valueOf((char) DEFAULT_KEYS[1].getKey(1)).toLowerCase());
        PlayersKeys.get(1).setKey(1, DEFAULT_KEYS[1].getKey(1));
        pnPlayer2.add(tfP2Right);
        lbP2Down = new JLabel("Key for down: ");
        pnPlayer2.add(lbP2Down);
        tfP2Down = new JTextField();
        tfP2Down.setEditable(false);
        tfP2Down.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP2Down.addKeyListener(new PlayerKeyListener(1, 2));
        tfP2Down.setText(String.valueOf((char) DEFAULT_KEYS[1].getKey(2)).toLowerCase());
        PlayersKeys.get(1).setKey(2, DEFAULT_KEYS[1].getKey(2));
        pnPlayer2.add(tfP2Down);
        lbP2Left = new JLabel("Key for left: ");
        pnPlayer2.add(lbP2Left);
        tfP2Left = new JTextField();
        tfP2Left.setEditable(false);
        tfP2Left.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP2Left.addKeyListener(new PlayerKeyListener(1, 3));
        tfP2Left.setText(String.valueOf((char) DEFAULT_KEYS[1].getKey(3)).toLowerCase());
        PlayersKeys.get(1).setKey(3, DEFAULT_KEYS[1].getKey(3));
        pnPlayer2.add(tfP2Left);
        
        btP2Ok = new JButton("Save");
        btP2Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNames[1] = (tfP2Name.getText().length() > 10) ? tfP2Name.getText().substring(0, 10) : tfP2Name.getText();
                PlayersKeysOk[1] = true;
            }
        });
        pnPlayer2.add(new Label());
        pnPlayer2.add(btP2Ok);
        
        pnContent.add(pnPlayer2);
        /* ------------ P2 END -------------------------- */
        
        
        /* ------------ P3 START ------------------------ */
        pnPlayer3 = new JPanel();
        pnPlayer3.setBackground(pnContent.getBackground());
        pnPlayer3.setLayout(new GridLayout(6, 2, 30, 5));
        pnPlayer3.setVisible(false);
        
        lbP3Name = new JLabel("Name: ");
        pnPlayer3.add(lbP3Name);
        tfP3Name = new JTextField();
        tfP3Name.setText(DEFAULT_P3_NAME);
        tfP3Name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(tfP3Name.getText().length() == 0) {
                    tfP3Name.setText(DEFAULT_P3_NAME);
                }
            }
        });
        PlayersNames[2] = DEFAULT_P3_NAME;
        pnPlayer3.add(tfP3Name);
        lbP3Up = new JLabel("Key for up: ");
        pnPlayer3.add(lbP3Up);
        tfP3Up = new JTextField();
        tfP3Up.setEditable(false);
        tfP3Up.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP3Up.addKeyListener(new PlayerKeyListener(2, 0));
        tfP3Up.setText(String.valueOf((char) DEFAULT_KEYS[2].getKey(0)).toLowerCase());
        PlayersKeys.get(2).setKey(0, DEFAULT_KEYS[2].getKey(0));
        pnPlayer3.add(tfP3Up);
        lbP3Right = new JLabel("Key for right: ");
        pnPlayer3.add(lbP3Right);
        tfP3Right = new JTextField();
        tfP3Right.setEditable(false);
        tfP3Right.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP3Right.addKeyListener(new PlayerKeyListener(2, 1));
        tfP3Right.setText(String.valueOf((char) DEFAULT_KEYS[2].getKey(1)).toLowerCase());
        PlayersKeys.get(2).setKey(1, DEFAULT_KEYS[2].getKey(1));
        pnPlayer3.add(tfP3Right);
        lbP3Down = new JLabel("Key for down: ");
        pnPlayer3.add(lbP3Down);
        tfP3Down = new JTextField();
        tfP3Down.setEditable(false);
        tfP3Down.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP3Down.addKeyListener(new PlayerKeyListener(2, 2));
        tfP3Down.setText(String.valueOf((char) DEFAULT_KEYS[2].getKey(2)).toLowerCase());
        PlayersKeys.get(2).setKey(2, DEFAULT_KEYS[2].getKey(2));
        pnPlayer3.add(tfP3Down);
        lbP3Left = new JLabel("Key for left: ");
        pnPlayer3.add(lbP3Left);
        tfP3Left = new JTextField();
        tfP3Left.setEditable(false);
        tfP3Left.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP3Left.addKeyListener(new PlayerKeyListener(2, 3));
        tfP3Left.setText(String.valueOf((char) DEFAULT_KEYS[2].getKey(3)).toLowerCase());
        PlayersKeys.get(2).setKey(3, DEFAULT_KEYS[2].getKey(3));
        pnPlayer3.add(tfP3Left);
        
        btP3Ok = new JButton("Save");
        btP3Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNames[2] = (tfP3Name.getText().length() > 10) ? tfP3Name.getText().substring(0, 10) : tfP3Name.getText();
                PlayersKeysOk[2] = true;
            }
        });
        pnPlayer3.add(new Label());
        pnPlayer3.add(btP3Ok);
        
        pnContent.add(pnPlayer3);
        /* ------------ P3 END -------------------------- */
        
        /* ------------ P4 START ------------------------ */
        pnPlayer4 = new JPanel();
        pnPlayer4.setBackground(pnContent.getBackground());
        pnPlayer4.setLayout(new GridLayout(6, 2, 30, 5));
        pnPlayer4.setVisible(false);
        
        lbP4Name = new JLabel("Name: ");
        pnPlayer4.add(lbP4Name);
        tfP4Name = new JTextField();
        tfP4Name.setText(DEFAULT_P4_NAME);
        tfP4Name.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(tfP4Name.getText().length() == 0) {
                    tfP4Name.setText(DEFAULT_P4_NAME);
                }
            }
        });
        PlayersNames[3] = DEFAULT_P4_NAME;
        pnPlayer4.add(tfP4Name);
        lbP4Up = new JLabel("Key for up: ");
        pnPlayer4.add(lbP4Up);
        tfP4Up = new JTextField();
        tfP4Up.setEditable(false);
        tfP4Up.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP4Up.addKeyListener(new PlayerKeyListener(3, 0));
        tfP4Up.setText(String.valueOf((char) DEFAULT_KEYS[3].getKey(0)).toLowerCase());
        PlayersKeys.get(3).setKey(0, DEFAULT_KEYS[3].getKey(0));
        pnPlayer4.add(tfP4Up);
        lbP4Right = new JLabel("Key for right: ");
        pnPlayer4.add(lbP4Right);
        tfP4Right = new JTextField();
        tfP4Right.setEditable(false);
        tfP4Right.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP4Right.addKeyListener(new PlayerKeyListener(3, 1));
        tfP4Right.setText(String.valueOf((char) DEFAULT_KEYS[3].getKey(1)).toLowerCase());
        PlayersKeys.get(3).setKey(1, DEFAULT_KEYS[3].getKey(1));
        pnPlayer4.add(tfP4Right);
        lbP4Down = new JLabel("Key for down: ");
        pnPlayer4.add(lbP4Down);
        tfP4Down = new JTextField();
        tfP4Down.setEditable(false);
        tfP4Down.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP4Down.addKeyListener(new PlayerKeyListener(3, 2));
        tfP4Down.setText(String.valueOf((char) DEFAULT_KEYS[3].getKey(2)).toLowerCase());
        PlayersKeys.get(3).setKey(2, DEFAULT_KEYS[3].getKey(2));
        pnPlayer4.add(tfP4Down);
        lbP4Left = new JLabel("Key for left: ");
        pnPlayer4.add(lbP4Left);
        tfP4Left = new JTextField();
        tfP4Left.setEditable(false);
        tfP4Left.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP4Left.addKeyListener(new PlayerKeyListener(3, 3));
        tfP4Left.setText(String.valueOf((char) DEFAULT_KEYS[3].getKey(3)).toLowerCase());
        PlayersKeys.get(3).setKey(3, DEFAULT_KEYS[3].getKey(3));
        pnPlayer4.add(tfP4Left);
        
        btP4Ok = new JButton("Save");
        btP4Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNames[3] = (tfP4Name.getText().length() > 10) ? tfP4Name.getText().substring(0, 10) : tfP4Name.getText();
                PlayersKeysOk[3] = true;
            }
        });
        pnPlayer4.add(new Label());
        pnPlayer4.add(btP4Ok);
        
        pnContent.add(pnPlayer4);
        /* ------------ P4 END -------------------------- */
    }
    
    /**
     * 
     * Method that returns number of players in game.
     * 
     * @return number of players
     */
    public int getNoP() {
        return NumberOfPlayers;
    }
    
    /**
     * 
     * Method that returns game level -> Slow, Medium, Fast, Extreme Fast.
     * 
     * @return game level
     */
    public int getLevel() {
        return Level;
    }
    
    /**
     * Method that returns selected item that where is name of maze
     * 
     * @return name of maze
     */
    public MazeData getMaze() {
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        MazeData md = db.selectMaze(chSEMaze.getSelectedItem());
        db.closeConnection();
        return md;
    }
    
    /**
     * 
     * Method that returns index of selected item in maze choice.
     * 
     * @return id of selected maze
     */
    public int getMazeIndex() {
        Database db = new Database();
        db.openConnectionWithDefaultValues();
        MazeData md = db.selectMaze(chSEMaze.getSelectedItem());
        db.closeConnection();
        System.out.println(md.getId());
        return md.getId();
    }
    
    /**
     * 
     * Method that returns name of i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersNames array.
     * 
     * @return (String) i's player's name
     */
    public String getName(int i){
        if(i >= 0 && i < 4) {
            return PlayersNames[i];
        } else {
            return "";
        }
    }
    
    /**
     * 
     * Method that returns instance of Keys object.
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys list
     *
     * @return instance of Keys object
     */
    public Keys getKeys(int i) {
        if(PlayersKeysOk[i]) {
            return PlayersKeys.get(i);
        } else {
            return DEFAULT_KEYS[i];
        }
    }
    
    /**
     * 
     * Method that returns int value for selected key for p-th player.
     * 
     * @param p value from 0 to 3, it is index of PlayersKeys list
     * @param k
     * 
     * @return
     */
    public int getKey(int p, int k) {
        if(p >= 0 && p < 4 && k >= 0 && k < 4) {
            if(PlayersKeysOk[p]) {
                return PlayersKeys.get(p).getKey(k);
            } else {
                return DEFAULT_KEYS[p].getKey(k);
            }
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Up key for i-th player.
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys list
     * 
     * @return int value for Up key for i-th player
     */
    public int getUpKey(int i) {
        if(i >= 0 && i < 4) {
            if(PlayersKeysOk[i]) {
                return PlayersKeys.get(i).getKey(0);
            } else {
                return DEFAULT_KEYS[i].getKey(0);
            }
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Right key for i-th player.
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys list
     * 
     * @return int value for Right key for i-th player
     */
    public int getRightKey(int i) {
        if(i >= 0 && i < 4) {
            if(PlayersKeysOk[i]) {
                return PlayersKeys.get(i).getKey(1);
            } else {
                return DEFAULT_KEYS[i].getKey(1);
            }
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Down key for i-th player.
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys list
     * 
     * @return int value for Down key for i-th player
     */
    public int getDownKey(int i) {
        if(i >= 0 && i < 4) {
            if(PlayersKeysOk[i]) {
                return PlayersKeys.get(i).getKey(2);
            } else {
                return DEFAULT_KEYS[i].getKey(2);
            }
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Left key for i-th player.
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys list
     * 
     * @return int value for Left key for i-th player
     */
    public int getLeftKey(int i) {
        if(i >= 0 && i < 4) {
            if(PlayersKeysOk[i]) {
                return PlayersKeys.get(i).getKey(3);
            } else {
                return DEFAULT_KEYS[i].getKey(3);
            }
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns index of Colors array form class Snake.
     * 
     * @param i value form 0 to 3, it is index of PlayersSnakeColors array
     * 
     * @return index of Colors array form class Snake
     */
    @ Deprecated
    public int getSnakeColorIndex(int i) {
        if(i >= 0 && i < 4) {
            return PlayersSnakeColors[i];
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * Method that validate input value, for each player, every key can be used once.
     * p & k are indexes of array to ignore.
     * 
     * @param e KeyEvent
     * @param tf JTextField to write pressed key
     * @param others JtextField array, other key to compare values with
     * @param p player index
     * @param k key index
     * @param in present key value for this attempt to change
     * 
     * @return actual key value, if it pass through test, returns new value, if not, returns old value
     */
    private int validateInputKey(KeyEvent e, JTextField tf, JTextField[][] others, int p, int k, int in) {
        if(e.getKeyCode() != in) {
            boolean ok = true;
            for(int i = 0; i < others.length; i ++) {
                for(int j = 0; j < others[i].length; j ++) {
                    if(!(i == p && j == k) && others[i][j].getText().equals(String.valueOf(e.getKeyChar()))) {
                        ok = false;
                    }
                }
            }
        
            if(ok) {
                tf.setText(String.valueOf(e.getKeyChar()));
                return e.getKeyCode();
            }
        }
        
        return in;
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
    
    class HeadButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(btSettings.hashCode() == e.getSource().hashCode()) {
                pnSettings.setVisible(true);
            } else {
                pnSettings.setVisible(false);
            }
            
            if(btPlayer1.hashCode() == e.getSource().hashCode()) {
                pnPlayer1.setVisible(true);
            } else {
                pnPlayer1.setVisible(false);
            }
            
            if(btPlayer2.hashCode() == e.getSource().hashCode()) {
                pnPlayer2.setVisible(true);
            } else {
                pnPlayer2.setVisible(false);
            }
            
            if(btPlayer3.hashCode() == e.getSource().hashCode()) {
                pnPlayer3.setVisible(true);
            } else {
                pnPlayer3.setVisible(false);
            }
            
            if(btPlayer4.hashCode() == e.getSource().hashCode()) {
                pnPlayer4.setVisible(true);
            } else {
                pnPlayer4.setVisible(false);
            }
        }
    }
    
    class PlayerKeyListener implements KeyListener {
        private int Player;
        private int KeyIndex;
        
        public PlayerKeyListener(int p, int k) {
            Player = p;
            KeyIndex = k;
        }
        
        public void keyTyped(KeyEvent e) {
            
        }

        public void keyPressed(KeyEvent e) {
                JTextField[][] others = {{tfP1Up, tfP1Right, tfP1Down, tfP1Left}, {tfP2Up, tfP2Right, tfP2Down, tfP2Left}, {tfP3Up, tfP3Right, tfP3Down, tfP3Left}, {tfP4Up, tfP4Right, tfP4Down, tfP4Left}};
                PlayersKeys.get(Player).setKey(KeyIndex, validateInputKey(e, others[Player][KeyIndex], others, Player, KeyIndex, PlayersKeys.get(Player).getKey(KeyIndex)));
        }

        public void keyReleased(KeyEvent e) {
            
        }
    }
}
