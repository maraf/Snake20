package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * Snake game class.
 * 
 * @author Marek SMM
 * @version 2.0
 */

public class SnakeGame extends JFrame implements Runnable {
    private Thread t;
    
    boolean GameOver;
    boolean GamePause;
    
    boolean Who;
    
    private MenuBar mbMain;
    
    private Menu mnGame;
    private Menu mnConfig;
    
    private MenuItem miGAhiScores;
    
    private MenuItem miGAnewGame;
    private MenuItem miGApnGameause;
    private MenuItem miGAgameOver;
    private MenuItem miGAclose;
    
    private MenuItem miCOconfig;
    
    private int GameWidth;
    private int GameHeight;
    private int GameSpeed;
    private int GameLevel;
    
    private ConfigPanel pnConfig;
    private GamePanel pnGame;
    private HiScorePanel pnHiScore;
    private IntroPanel pnIntro;
    
    private Panel pnContent;
    private Panel pnStatusBar;
    private JLabel lbStatusText;
    
    private static final int DEFAULT_SPEED = 400;
    
    private long TimeStamp;
    
    /**
     * 
     * Contructor that initialize JFrame, panels (GamePanel, HiScorePanel, ConfigPanel, IntroPanel ),
     * call method for load textures, icon.
     * 
     */
    public SnakeGame() {
        this.setBounds(20, 20, 480, 400);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("Snake :: Version 2.0");
        this.addWindowListener(new cWindowAdapter());
        this.addKeyListener(new cKeyListener());
        
        GameWidth = 45;
        GameHeight = 31;
        GameSpeed = DEFAULT_SPEED;
        GameLevel = 1;
        
        
        GameOver = true;
        GamePause = false;
        
        setMenu();
        
        pnContent = new Panel();
        pnContent.setLayout(null);
        pnContent.setVisible(true);
        pnContent.setBounds(0, 0, 480, 335);
        this.add(pnContent);
        
        pnStatusBar = new Panel();
        pnStatusBar.setBounds(0, 335, 480, 25);
        pnStatusBar.setLayout(null);
        pnStatusBar.setVisible(true);
        pnStatusBar.setBackground(new Color(50, 50, 20));
        lbStatusText = new JLabel("Snake :: Version 2.0 :: 08.08.2008");
        lbStatusText.setBounds(5, 0, 470, 20);
        lbStatusText.setForeground(Color.WHITE);
        pnStatusBar.add(lbStatusText);
        this.add(pnStatusBar);
        
        pnGame = new GamePanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnGame);
        pnGame.setVisible(false);
        
        pnHiScore = new HiScorePanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnHiScore);
        pnHiScore.setVisible(false);
        
        pnConfig = new ConfigPanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnConfig);
        pnConfig.setVisible(false);
        
        pnIntro = new IntroPanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnIntro);
        
        
        MediaTracker mt = new MediaTracker(this);
        String[][] names = {{"Texture0_00.gif", "Texture0_01.gif", "Texture0_02.gif", "Texture0_03.gif", 
                             "Texture0_04.gif", "Texture0_05.gif", "Texture0_06.gif", "Texture0_07.gif", 
                             "Texture0_08.gif", "Texture0_09.gif", "Texture0_10.gif", "Texture0_11.gif",
                             "Texture0_12.gif", "Texture0_13.gif", "Texture0_14.gif", "Texture0_15.gif",
                             "Texture0_16.gif", "Texture0_17.gif"}, 
                            {"Texture1_00.gif", "Texture1_01.gif", "Texture1_02.gif", "Texture1_03.gif", 
                             "Texture1_04.gif", "Texture1_05.gif", "Texture1_06.gif", "Texture1_07.gif", 
                             "Texture1_08.gif", "Texture1_09.gif", "Texture1_10.gif", "Texture1_11.gif",
                             "Texture1_12.gif", "Texture1_13.gif", "Texture1_14.gif", "Texture1_15.gif",
                             "Texture1_16.gif", "Texture1_17.gif"}, 
                            {"Texture2_00.gif", "Texture2_01.gif", "Texture2_02.gif", "Texture2_03.gif", 
                             "Texture2_04.gif", "Texture2_05.gif", "Texture2_06.gif", "Texture2_07.gif", 
                             "Texture2_08.gif", "Texture2_09.gif", "Texture2_10.gif", "Texture2_11.gif",
                             "Texture2_12.gif", "Texture2_13.gif", "Texture2_14.gif", "Texture2_15.gif",
                             "Texture2_16.gif", "Texture2_17.gif"}, 
                            {"Texture3_00.gif", "Texture3_01.gif", "Texture3_02.gif", "Texture3_03.gif", 
                             "Texture3_04.gif", "Texture3_05.gif", "Texture3_06.gif", "Texture3_07.gif", 
                             "Texture3_08.gif", "Texture3_09.gif", "Texture3_10.gif", "Texture3_11.gif",
                             "Texture3_12.gif", "Texture3_13.gif", "Texture3_14.gif", "Texture3_15.gif",
                             "Texture3_16.gif", "Texture3_17.gif"}
        };
        Snake.loadTextures(names, this.getToolkit(), mt);
        Image ico = this.getToolkit().getImage("textures/snakeico.gif");
        mt.addImage(ico, 100);
        this.setIconImage(ico);
    }
    
    /**
     * 
     * Method that setups game options.
     * It is called before each game starts.
     * 
     */
    private void configGame() {
        for(int i = 0; i < pnConfig.getNoP(); i ++) {
            pnGame.Snakes.add(new Snake(10 + i, 5 + i, 5, pnConfig.getKeys(i), pnConfig.getName(i)));
            pnGame.Snakes.get(i).setPlayerName(pnConfig.getName(i));
        }
        
        GameLevel = pnConfig.getLevel();
        GameSpeed = DEFAULT_SPEED / GameLevel;
        
        pnGame.setActualMaze(pnConfig.getMaze());
    }
    
    /**
     * 
     * Method that intialize new game.
     * 
     */
    private void newGame() {
        configGame();
        Food nf;
        do {
            nf = Food.createFood(GameWidth, GameHeight, GameLevel);
        } while(isFoodOn(nf.getX(), nf.getY(), false) || isSnakeOn(nf.getX(), nf.getY()) || isMazeOn(nf.getX(), nf.getY()));
        pnGame.Foods.add(nf);
        
        miGAhiScores.setEnabled(false);
        miGAnewGame.setEnabled(false);
        miGApnGameause.setEnabled(true);
        miGAgameOver.setEnabled(true);
        mnConfig.setEnabled(false);
        
        GameOver = false;
        
        if(pnConfig.isVisible()) pnConfig.setVisible(false);
        if(pnHiScore.isVisible()) pnHiScore.setVisible(false);
        if(pnIntro.isVisible()) pnIntro.setVisible(false);
        pnGame.setVisible(true);
        this.requestFocus();
        
        t = new Thread(this);
        t.start();
    }
    
    /**
     * 
     * Method is called when game is paused.
     * It starts / stops thread.
     * 
     */
    private void gamePause() {
        if(!GamePause) {
            GamePause = true;
            t = null;
        } else {
            GamePause = false;
            t = new Thread(this);
            t.start();
            this.requestFocus();
        }
    }
    
    /**
     * 
     * Method that is called when game is over.
     * It saves hi-score to database ( if is greater then 50pts ),
     * clear game panel lists (Snakes, Foods) and clears ThisMaze.
     * 
     */
    private void gameOver() {
        if(!GameOver) {
            GameOver = true;
            t = null;
        
            Database db = new Database();
            if(db.openConnectionWithDefaultValues()) {
                for(int i = 0; i < pnGame.Snakes.size(); i ++) {
                    if(pnGame.Snakes.get(i).getScore() > 50) {
                        db.insertHighScoreData(new Score(pnGame.Snakes.get(i).getPlayerName(), GameLevel, pnConfig.getMazeIndex(), pnGame.Snakes.get(i).getScore()));
                    }
                }
                db.closeConnection();
            }
            
            pnGame.Snakes.clear();
            pnGame.Foods.clear();
            pnGame.ThisMaze.clear();
        
            miGAhiScores.setEnabled(true);
            miGAnewGame.setEnabled(true);
            miGApnGameause.setEnabled(false);
            miGAgameOver.setEnabled(false);
            mnConfig.setEnabled(true);
            
            pnGame.setVisible(false);
            pnHiScore.setVisible(true);
            
            lbStatusText.setText("Snake :: Version 2.0 :: 08.08.2008");
        }
    }
    
    /**
     * 
     * Method that initialize main menu.
     * 
     */
    private void setMenu() {
        mbMain = new MenuBar();
        
        mnGame = new Menu("Game", false);
        
        miGAhiScores = new MenuItem("Hi Scores");
        miGAnewGame = new MenuItem("New Game");
        miGApnGameause = new MenuItem("Pause");
        miGApnGameause.setEnabled(false);
        miGAgameOver = new MenuItem("Quit");
        miGAgameOver.setEnabled(false);
        miGAclose = new MenuItem("Close");
        miGAhiScores.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(pnConfig.isVisible()) pnConfig.setVisible(false);
                if(pnGame.isVisible()) pnGame.setVisible(false);
                if(pnIntro.isVisible()) pnIntro.setVisible(false);
                pnHiScore.setVisible(true);
            } 
        });
        miGAnewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame();
            }
        });
        miGApnGameause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gamePause();
            }
        });
        miGAgameOver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameOver();
            }
        });
        miGAclose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        mnGame.add(miGAhiScores);
        mnGame.addSeparator();
        mnGame.add(miGAnewGame);
        mnGame.add(miGApnGameause);
        mnGame.add(miGAgameOver);
        mnGame.addSeparator();
        mnGame.add(miGAclose);
        
        mbMain.add(mnGame);
        
        mnConfig = new Menu("Config");
        
        miCOconfig = new MenuItem("Config");
        miCOconfig.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!pnConfig.isVisible()) {
                    pnConfig.setVisible(true);
                    pnHiScore.setVisible(false);
                }
            }
        });
        
        mnConfig.add(miCOconfig);
        
        mbMain.add(mnConfig);
        
        this.setMenuBar(mbMain);
    }
    
    /**
     * 
     * Method that returns true if value ( item ) is in array, false otherwise.
     * 
     * @param array array of int values
     * @param item int value to find
     * @return true if value ( item ) is in array, false otherwise
     */
    public boolean inArray(int array[], int item) {
        for(int i = 0; i < array.length; i ++) {
            if(array[i] == item) return true;
        }
        return false;
    }
    
    /**
     * 
     * Method that calls paint method of displaied panel.
     * 
     * @param g graphics context
     */
    @Override
    public void paint(Graphics g) {
        if(pnHiScore.isVisible()) {
            pnHiScore.repaint();
        } else if(pnConfig.isVisible()) {
            pnConfig.repaint();
        } else if(pnIntro.isVisible()) {
            pnIntro.repaint();
        } else {
            pnGame.repaint();
        }
    }
    
    /**
     * 
     * Method that calls paint(g).
     * 
     * @param g
     */
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    /**
     * 
     * Method that returs true if on x & y coordinates is in GamePanel->Foods 
     * some food, false if not.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param careOfEaten flag 'care of eaten' on this food
     * @return true if on x & y coordinates is in GamePanel->Foods some food, false if not
     */
    public boolean isFoodOn(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < pnGame.Foods.size(); i ++) {
            if((!careOfEaten || !pnGame.Foods.get(i).isEaten()) && pnGame.Foods.get(i).getX() == x && pnGame.Foods.get(i).getY() == y) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 
     * Method that returs true if on x & y coordinates is in GamePanel->Snakes
     * some snake, false if not.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return true if on x & y coordinates is in GamePanel->Snakes some snake, false if not
     */
    public boolean isSnakeOn(int x, int y) {
        for(int i = 0; i < pnGame.Snakes.size(); i ++) {
            for(int j = 0; j < pnGame.Snakes.get(i).getPLength(); j ++) {
                if(x == pnGame.Snakes.get(i).getPPointIX(j) && y == pnGame.Snakes.get(i).getPPointIY(j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 
     * Method that returs true if on x & y coordinates is in GamePanel->Snakes
     * some snake but it can't be snake with s index, false otherwise.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return true if on x & y coordinates is in GamePanel->Snakes some snake 
     * but it can't be snake with s index, false otherwise
     */
    public boolean isSnakeOn(int x, int y, int s) {
        for(int i = 0; i < pnGame.Snakes.size(); i ++) {
            for(int j = 0; j < pnGame.Snakes.get(i).getPLength(); j ++) {
                if(!(i == s && j == 0) && x == pnGame.Snakes.get(i).getPPointIX(j) && y == pnGame.Snakes.get(i).getPPointIY(j)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    /**
     * 
     * Method that returns true if on x & y coordinates is in GamePanel->ThisMaze
     * some maze point, false if not.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return true if on x & y coordinates is in GamePanel->ThisMaze some maze point, false if not.
     */
    public boolean isMazeOn(int x, int y) {
        for(int i = 0; i < pnGame.ThisMaze.getLength(); i ++) {
            if(pnGame.ThisMaze.getPointX(i) == x && pnGame.ThisMaze.getPointY(i) == y) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 
     * Method that returns index of food ( GamePanel->Foods ) on coordinates x & y.
     * 
     * @param x X coordinate.
     * @param y Y coordinate.
     * @param careOfEaten flag 'care of eaten' on this food
     * @return index of food ( GamePanel->Foods ) on coordinates x & y
     */
    private int indexOfFood(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < pnGame.Foods.size(); i ++) {
            if((!careOfEaten || !pnGame.Foods.get(i).isEaten()) && pnGame.Foods.get(i).getX() == x && pnGame.Foods.get(i).getY() == y) {
                return i;
            }
        }
        
        return -1;
    }

    /**
     * 
     * Method that is called every game cycle. It moves snakes, creates food
     * and call gameOver if there is some reason.
     */
    private void gameCycle() {
        String statusText;
        statusText = "";
        for(int i = 0; i < pnGame.Snakes.size(); i ++) {
            Point f = pnGame.Snakes.get(i).getPPointP(0);
            if(isFoodOn(f.x, f.y, true)) {
                pnGame.Snakes.get(i).getPPointP(0).setEatedFood(true);
                        
                boolean b = pnGame.Foods.get(indexOfFood(f.x, f.y, true)).eat(i);
                pnGame.Snakes.get(i).increaseScore(pnGame.Foods.get(indexOfFood(f.x, f.y, false)).getValue());
                System.out.println(pnGame.Snakes.get(i).getPlayerName() + " = " + pnGame.Snakes.get(i).getScore());

                if(!b) {
                    if(isTimeForBonusFood()) {
                        Food nf;
                        do {
                            nf = Food.createBonusFood(GameWidth, GameHeight, GameLevel * 5);
                        } while(isFoodOn(nf.getX(), nf.getY(), false) || isSnakeOn(nf.getX(), nf.getY()) || isMazeOn(nf.getX(), nf.getY()));
                        pnGame.Foods.add(nf);
                    }
                    Food nf = null;
                    do {
                        nf = Food.createFood(GameWidth, GameHeight, GameLevel);
                    } while(isFoodOn(nf.getX(), nf.getY(), false) || isSnakeOn(nf.getX(), nf.getY()) || isMazeOn(nf.getX(), nf.getY()));
                    pnGame.Foods.add(nf);
                }
            }
            statusText += pnGame.Snakes.get(i).getPlayerName() + " = " + pnGame.Snakes.get(i).getScore() + "   ";
                
            if(isSnakeOn(f.x, f.y, i) || isMazeOn(f.x, f.y)) {
                gameOver();
                break ;
            }
                
            Point l = pnGame.Snakes.get(i).movePSnake(GameWidth, GameHeight);
            if(isFoodOn(l.x, l.y, false)) {
                pnGame.Snakes.get(i).increasePSnake(l.x, l.y);
                pnGame.Foods.remove(indexOfFood(l.x, l.y, false));
            }
        }
        lbStatusText.setText(statusText);
        
        repaint();
    }
    
    /**
     * 
     * Method that calls gameCycle() method and that wait.
     */
    public void run() {
        Thread thisThread = Thread.currentThread();
        long ts = 0;
        long ti = 0;
        while(thisThread == t) {
            try {
                TimeStamp = new Date().getTime();
                gameCycle();
                
                ti = new Date().getTime();
                ts = GameSpeed - (ti - TimeStamp);
                
                if(ts > 0) {
                    Thread.sleep(ts);
                } else {
                    Thread.sleep(0);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(SnakeGame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * 
     * Method that decide if this round will be added bonus food.
     * 
     * @return if this is time for bonus food
     */
    private boolean isTimeForBonusFood() {
        if(Math.random() > 0.8) return true;
        return false;
    }
    
    /**
     * 
     * Method that returns this class name & visibility flag.
     * 
     * @return this class name & visibility flag
     */
    @Override
    public String toString() {
        return "SnakeGame :: Version 2.0";
    }
    
    private class cKeyListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
            for(int i = 0; i < pnGame.Snakes.size(); i ++) {
                if(inArray(pnGame.Snakes.get(i).getMovementKeys().getKeys(), e.getKeyCode())) {
                    pnGame.Snakes.get(i).setDirection(e.getKeyCode());
                    break;
                }
            }
            
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if(e.getKeyCode() == KeyEvent.VK_P) {
                gamePause();
            } else if(e.getKeyCode() == KeyEvent.VK_N) {
                gameOver();
                newGame();
            } else if(e.getKeyCode() == KeyEvent.VK_Q) {
                gameOver();
            }
        }
            
        public void keyReleased(KeyEvent e) {
            
        }

        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    private class cWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            gamePause();
        }

        @Override
        public void windowActivated(WindowEvent e) {
            gamePause();
        }

        @Override
        public void windowLostFocus(WindowEvent e) {
            gamePause();
        }

        @Override
        public void windowGainedFocus(WindowEvent e) {
            gamePause();
        }
    }
}