package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 *
 * @author Marek SMM
 */
public class ConfigPanel extends JPanel {
    private int Level;
    private int NumberOfPlayers;
    private int Maze;
    
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
    JLabel lbP1Color;
    Choice chP1Color;
    JButton btP1Ok;
    
    private String[] PlayersNames;
//    private int[] PlayersUpKeys;
//    private int[] PlayersRightKeys;
//    private int[] PlayersDownKeys;
//    private int[] PlayersLeftKeys;
    private int[] PlayersSnakeColors;
    
    private List<Keys> PlayersKeys;
    
    private final static String DEFAULT_P1_NAME = "Player 1";
    private final static String DEFAULT_P2_NAME = "Player 2";
    private final static String DEFAULT_P3_NAME = "Player 3";
    private final static String DEFAULT_P4_NAME = "Player 4";
    
    /**
     * 
     * Contructor that creates whole GUI / Setup interface for users
     * 
     * @param width sets panel width
     * @param height sets panel height
     */
    public ConfigPanel(int width, int height) {
        NumberOfPlayers = 1;
        Level = 1;
        Maze = 0;
        
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
//        pnContent.setBackground(Color.blue);
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
//        pnSettings.setBounds(0, 0, pnContent.getWidth(), pnContent.getHeight());
//        pnSettings.setBackground(Color.red);
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
        chSEMaze.addItem("No Maze");
        pnSettings.add(chSEMaze);
        btSEOk = new JButton("Save");
        btSEOk.setMargin(new Insets(0, 0, 0, 0));
        btSEOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("NoP = " + Integer.parseInt(chSENoP.getSelectedItem()));
                System.out.println("Level = " + (chSELevel.getSelectedIndex() + 1));
                System.out.println("Maze = " + (chSEMaze.getSelectedIndex()));
                
                NumberOfPlayers = Integer.parseInt(chSENoP.getSelectedItem());
                Level = chSELevel.getSelectedIndex() + 1;
                Maze = chSEMaze.getSelectedIndex();
            }
        });
                
        pnSettings.add(new Label());
        pnSettings.add(btSEOk);
        
        pnSettings.validate();
        pnContent.add(pnSettings);
        
        PlayersNames = new String[4];
//        PlayersUpKeys = new int[4];
//        PlayersRightKeys = new int[4];
//        PlayersDownKeys = new int[4];
//        PlayersLeftKeys = new int[4];
        PlayersSnakeColors = new int[4];
        
        PlayersKeys = new ArrayList<Keys>();
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        PlayersKeys.add(new Keys());
        
        pnPlayer1 = new JPanel();
        pnPlayer1.setBackground(pnContent.getBackground());
        pnPlayer1.setLayout(new GridLayout(7, 2, 30, 5));
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
        tfP1Up.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                tfP1Up.setText(String.valueOf(e.getKeyChar()));
                JTextField[] others = {tfP1Right, tfP1Down, tfP1Left};
                PlayersKeys.get(0).setKey(0, validateInputKey(e, tfP1Up, others, PlayersKeys.get(0).getKey(0)));
            }
        });
        pnPlayer1.add(tfP1Up);
        lbP1Right = new JLabel("Key for right: ");
        pnPlayer1.add(lbP1Right);
        tfP1Right = new JTextField();
        tfP1Right.setEditable(false);
        tfP1Right.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Right.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                tfP1Right.setText(String.valueOf(e.getKeyChar()));
                JTextField[] others = {tfP1Up, tfP1Down, tfP1Left};
                PlayersKeys.get(0).setKey(1, validateInputKey(e, tfP1Right, others, PlayersKeys.get(0).getKey(1)));
            }
        });
        pnPlayer1.add(tfP1Right);
        lbP1Down = new JLabel("Key for down: ");
        pnPlayer1.add(lbP1Down);
        tfP1Down = new JTextField();
        tfP1Down.setEditable(false);
        tfP1Down.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Down.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                tfP1Down.setText(String.valueOf(e.getKeyChar()));
                JTextField[] others = {tfP1Up, tfP1Right, tfP1Left};
                PlayersKeys.get(0).setKey(2, validateInputKey(e, tfP1Down, others, PlayersKeys.get(0).getKey(2)));
            }
        });
        pnPlayer1.add(tfP1Down);
        lbP1Left = new JLabel("Key for left: ");
        pnPlayer1.add(lbP1Left);
        tfP1Left = new JTextField();
        tfP1Left.setEditable(false);
        tfP1Left.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        tfP1Left.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                tfP1Left.setText(String.valueOf(e.getKeyChar()));
                JTextField[] others = {tfP1Up, tfP1Right, tfP1Down};
                PlayersKeys.get(0).setKey(3, validateInputKey(e, tfP1Left, others, PlayersKeys.get(0).getKey(3)));
            }
        });
        pnPlayer1.add(tfP1Left);
        lbP1Color = new JLabel("Snake color: ");
        pnPlayer1.add(lbP1Color);
        chP1Color = new Choice();
        String[] colors = {"Red", "Blue", "Green", "Yellow", "Orange", 
                           "White", "Black", "Cyan", "Gray", "Pink", "Magenta"};
        for(int i = 0; i < colors.length; i ++) {
            chP1Color.addItem(colors[i]);
        }
        pnPlayer1.add(chP1Color);
        btP1Ok = new JButton("Save");
        btP1Ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayersNames[0] = tfP1Name.getText();
//                if(!tfP1Up.getText().equals("")) PlayersUpKeys[0] = Integer.parseInt(tfP1Up.getText());
//                if(!tfP1Right.getText().equals("")) PlayersRightKeys[0] = Integer.parseInt(tfP1Right.getText());
//                if(!tfP1Down.getText().equals("")) PlayersDownKeys[0] = Integer.parseInt(tfP1Down.getText());
//                if(!tfP1Left.getText().equals("")) PlayersLeftKeys[0] = Integer.parseInt(tfP1Left.getText());
                PlayersSnakeColors[0] = chP1Color.getSelectedIndex();
//                System.out.println(chP1Color.getSelectedIndex());
//                System.out.println(PlayersUpKeys[0] + "; " + PlayersRightKeys[0] + "; " + PlayersDownKeys[0] + "; " + PlayersLeftKeys[0]);
            }
        });
        pnPlayer1.add(new Label());
        pnPlayer1.add(btP1Ok);
        
        pnContent.add(pnPlayer1);
        
        pnPlayer2 = new JPanel();
        pnPlayer2.setBackground(pnContent.getBackground());
        pnPlayer2.add(new Label("Hello world2!"));
        pnPlayer2.setVisible(false);
        
        pnContent.add(pnPlayer2);
        
        pnPlayer3 = new JPanel();
        pnPlayer3.setBackground(pnContent.getBackground());
        pnPlayer3.add(new Label("Hello world3!"));
        pnPlayer3.setVisible(false);
        
        pnContent.add(pnPlayer3);
        
        pnPlayer4 = new JPanel();
        pnPlayer4.setBackground(pnContent.getBackground());
        pnPlayer4.add(new Label("Hello world4!"));
        pnPlayer4.setVisible(false);
        
        pnContent.add(pnPlayer4);
    }
    
    /**
     * 
     * Method that returns number of players in game
     * 
     * @return number of players
     */
    public int getNoP() {
        return NumberOfPlayers;
    }
    
    /**
     * 
     * Method that returns game level -> Slow, Medium, Fast, Extreme Fast
     * 
     * @return game level
     */
    public int getLevel() {
        return Level;
    }
    
    /**
     * Method that returns selected item that indicates index of maze array
     * 
     * @return index of maze array
     */
    public int getMaze() {
        return Maze;
    }
    
    /**
     * 
     * Method that returns name of i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersNames array
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
     * Method that returns instance of Keys object
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys array
     * 
     * @return instance of Keys object
     */
    public Keys getKeys(int i) {
//        int[] retArray = {PlayersUpKeys[i], PlayersRightKeys[i], PlayersDownKeys[i], PlayersLeftKeys[i]};
//        int[] retArray = new int[4];
//        for(int j = 0; j < 4; j ++) {
//            retArray[j] = PlayersKeys.get(i).getKey(j);
//        }
//        return retArray;
        return PlayersKeys.get(i);
    }
    
    /**
     * 
     * Method that returns int value for Up key for i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys array
     * 
     * @return int value for Up key for i-th player
     */
    public int getUpKey(int i) {
        if(i >= 0 && i < 4) {
            return PlayersKeys.get(i).getKey(0);
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Right key for i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys array
     * 
     * @return int value for Right key for i-th player
     */
    public int getRightKey(int i) {
        if(i >= 0 && i < 4) {
            return PlayersKeys.get(i).getKey(1);
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Down key for i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys array
     * 
     * @return int value for Down key for i-th player
     */
    public int getDownKey(int i) {
        if(i >= 0 && i < 4) {
            return PlayersKeys.get(i).getKey(2);
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns int value for Left key for i-th player
     * 
     * @param i value from 0 to 3, it is index of PlayersKeys array
     * 
     * @return int value for Left key for i-th player
     */
    public int getLeftKey(int i) {
        if(i >= 0 && i < 4) {
            return PlayersKeys.get(i).getKey(3);
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * 
     * Method that returns index of Colors array form class Snake
     * 
     * @param i value form 0 to 3, it is index of PlayersSnakeColors array
     * 
     * @return index of Colors array form class Snake
     */
    public int getSnakeColorIndex(int i) {
        if(i >= 0 && i < 4) {
            return PlayersSnakeColors[i];
        } else {
            // throw Exception
            return 0;
        }
    }
    
    /**
     * Method that validate input value, for each player, every key can be used once
     * 
     * @param e KeyEvent
     * @param tf JTextField to write pressed key
     * @param others JtextField array, other key to compare values with
     * @param in present key value for this attempt to change
     * @return actual key value, if it pass through test, returns new value, if not, returns old value
     */
    private int validateInputKey(KeyEvent e, JTextField tf, JTextField[] others, int in) {
        if(e.getKeyCode() != in) {
            boolean ok = true;
            for(int i = 0; i < others.length; i ++) {
                if(others[i].getText().equals(String.valueOf(e.getKeyChar()))) {
                    ok = false;
                }
            }
        
            if(ok) {
                tf.setText(String.valueOf(e.getKeyChar()));
                return e.getKeyCode();
            }
        }
        
        return in;
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
}
