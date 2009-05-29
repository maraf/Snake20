package snake;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.Timer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Marek SMM
 * @version 2.0
 */

public class SnakeGame extends JFrame /*implements Runnable*/ {
//    private List<Snake> Snakes;
//    private List<Food> Foods;
    
//    private Thread t;
    
    boolean GameOver;
    boolean pnGameause;
    
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
    private int GameMaze;
    
    private ConfigPanel pnConfig;
    private GamePanel pnGame;
    private WelcomePanel pnWelcome;
    
    private Panel pnContent;
    private Panel pnStatusBar;
    private JLabel lbStatusText;
    
    private Database DB;
    
    private static final int DEFAULT_SPEED = 100;
    
    private Timer Timer;
    
    public SnakeGame() {
//        Snakes = new ArrayList<Snake>();
//        Foods = new ArrayList<Food>();
        
        this.setBounds(20, 20, 480, 400);
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        this.setLayout(null);
        this.setTitle("Snake :: Version 2.0");
        this.addWindowListener(new cWindowAdapter());
        this.addKeyListener(new cKeyListener());
        
        GameWidth = 45;
        GameHeight = 30;
        GameSpeed = DEFAULT_SPEED;
        GameLevel = 1;
        GameMaze = 0;
        
        GameOver = true;
        pnGameause = false;
        
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
        pnStatusBar.setBackground(Color.RED);
        lbStatusText = new JLabel("Jak se tam mate lidi??");
        lbStatusText.setBounds(5, 0, 470, 20);
        pnStatusBar.add(lbStatusText);
        this.add(pnStatusBar);
        
        pnGame = new GamePanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnGame);
        pnGame.setVisible(false);
        
        pnWelcome = new WelcomePanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnWelcome);
        pnWelcome.setVisible(false);
        
        pnConfig = new ConfigPanel(pnContent.getWidth(), pnContent.getHeight());
        pnContent.add(pnConfig);
        pnConfig.setVisible(false);
        
//        pnConfig.setVisible(true);
        pnWelcome.setVisible(true);
        pnWelcome.repaint();
        
        DB = new Database(Database.DEFAULT_HOST, Database.DEFAULT_DB_NAME, Database.DEFAULT_USER, Database.DEFAULT_PASSWD);
        List<Score> scl = DB.selectHighScoresData(3);
        
        for(int i = 0; i < scl.size(); i ++) {
            System.out.println((i + 1) + ". " + scl.get(i).getName() + " - " + scl.get(i).getScore() + "pts");
        }
    }
    
    private void configGame() {
        for(int i = 0; i < pnConfig.getNoP(); i ++) {
            pnGame.Snakes.add(new Snake(0, 6 + i, 7 + i, pnConfig.getKeys(i)));
            pnGame.Snakes.get(i).setColor(Snake.getColors()[pnConfig.getSnakeColorIndex(i)]);
            pnGame.Snakes.get(i).setPlayerName(pnConfig.getName(i));
//            System.out.println(pnConfig.getSnakeColorIndex(i));
//            System.out.println(Snake.getColors()[pnConfig.getSnakeColorIndex(i)]);
        }
        
        GameLevel = pnConfig.getLevel();
        GameSpeed = DEFAULT_SPEED / GameLevel;
//        GameMaze = pnConfig.getMaze();
        
//        DB.insertHighScoreData(new Score(pnGame.Snakes.get(0).getPlayerName(), GameLevel, GameMaze, pnGame.Snakes.get(0).getScore()));
    }
    
    private void newGame() {
//        Snakes.add(new Snake(0, 6, 7));
//        Snakes.add(new Snake(0, 8, 5, new Keys(KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Z, KeyEvent.VK_A)));
        
        configGame();
    
        pnGame.Foods.add(new Food(14, 8, GameLevel));
        
        miGAhiScores.setEnabled(false);
        miGAnewGame.setEnabled(false);
        miGApnGameause.setEnabled(true);
        miGAgameOver.setEnabled(true);
        mnConfig.setEnabled(false);
        
        GameOver = false;
        
//        t = new Thread(this);
//        t.start();
        
//        Timer = new Timer();
//        Timer.schedule(new TimerTask() {
//            public void run() {
//                irun();
//            }
//        }, 50, GameSpeed);
        timerInit();
        
        if(pnConfig.isVisible()) pnConfig.setVisible(false);
        if(pnWelcome.isVisible()) pnWelcome.setVisible(false);
        pnGame.setVisible(true);
        this.requestFocus();
    }
    
    private void gamePause() {
        if(!pnGameause) {
            pnGameause = true;
//            t = null;
            timerCancel();
        } else {
            pnGameause = false;
//            t = new Thread(this);
//            t.start();
            timerInit();
        }
    }
    
    private void gameOver() {
        if(!GameOver) {
            GameOver = true;
//            t = null;
            timerCancel();
        
            for(int i = 0; i < pnGame.Snakes.size(); i ++) {
                if(pnGame.Snakes.get(i).getScore() > 50) {
                    DB.insertHighScoreData(new Score(pnGame.Snakes.get(i).getPlayerName(), GameLevel, GameMaze, pnGame.Snakes.get(i).getScore()));
                }
            }
            
            pnGame.Snakes.clear();
            pnGame.Foods.clear();
        
            miGAhiScores.setEnabled(true);
            miGAnewGame.setEnabled(true);
            miGApnGameause.setEnabled(false);
            miGAgameOver.setEnabled(false);
            mnConfig.setEnabled(true);
            
            pnGame.setVisible(false);
            pnWelcome.setVisible(true);
        }
    }
    
    private void timerInit() {
        Timer = new Timer();
        Timer.schedule(new TimerTask() {
            public void run() {
                gameCycle();
            }
        }, 50, GameSpeed);
    }
    
    private void timerCancel() {
        Timer.cancel();
    }
    
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
                pnWelcome.setVisible(true);
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
                if(pnConfig.isVisible()) {
                    pnWelcome.setVisible(true);
                    pnConfig.setVisible(false);
                    System.out.println(pnConfig.isVisible());
                } else {
                    pnConfig.setVisible(true);
                    pnWelcome.setVisible(false);
                    System.out.println(pnConfig.isVisible());
                }
            }
        });
        
        mnConfig.add(miCOconfig);
        
        mbMain.add(mnConfig);
        
        this.setMenuBar(mbMain);
    }
    
    public boolean inArray(int array[], int item) {
        for(int i = 0; i < array.length; i ++) {
            if(array[i] == item) return true;
        }
        return false;
    }
    
    @Override
    public void paint(Graphics g) {
//        Image offImage = createImage(300, 300);
//        Graphics og = offImage.getGraphics();
//        
//        og.setColor(Color.orange);
//        og.fillRect(0, 0, offImage.getWidth(null), offImage.getHeight(null));
//        og.setColor(Color.black);
//        
//        for(int i = 0; i < Foods.size(); i ++) {
//            Foods.get(i).paintFood(og, 10);
//        }
//        
//        for(int i = 0; i < Snakes.size(); i ++) {
//            Snakes.get(i).paintSnake(og, 10);
//        }
        
//        g.drawImage(offImage, 10, 60, this);
        
        if(pnWelcome.isVisible()) {
            pnWelcome.repaint();
        } else if(pnConfig.isVisible()) {
            pnConfig.repaint();
        } else {
//        pnGame.setImage(offImage);
            //pnGame.setElements(Snakes, Foods);
            pnGame.repaint();
        }   
//        pnGame.paint(g);
        
//        Image offTable = createImage(150, 300);
//        Graphics otg = offTable.getGraphics();
//        
//        otg.setColor(Color.GREEN);
//        otg.fillRect(0, 0, offTable.getWidth(null), offTable.getHeight(null));
//        
//        g.drawImage(offTable, 320, 60, this);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    public boolean isFoodOn(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < pnGame.Foods.size(); i ++) {
            if((!careOfEaten || !pnGame.Foods.get(i).isEaten()) && pnGame.Foods.get(i).getX() == x && pnGame.Foods.get(i).getY() == y) {
                return true;
            }
        }
        
        return false;
    }
    
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
    
    public boolean isSnakeOn(int x, int y, int s) {
        for(int i = 0; i < pnGame.Snakes.size(); i ++) {
            //if(i != s) 
            for(int j = 0; j < pnGame.Snakes.get(i).getPLength(); j ++) {
                if(!(i == s && j == 0) && x == pnGame.Snakes.get(i).getPPointIX(j) && y == pnGame.Snakes.get(i).getPPointIY(j)) {
                    return true;
                }
            }
            //}
        }
        
        return false;
    }
    
    public boolean isMazeOn(int x, int y) {
        for(int i = 0; i < pnGame.Mazes.get(pnGame.getActulMaze()).getLength(); i ++) {
            if(pnGame.Mazes.get(pnGame.getActulMaze()).getPointX(i) == x && pnGame.Mazes.get(pnGame.getActulMaze()).getPointY(i) == y) {
                return true;
            }
        }
        return false;
    }
    
    private  int indexOfFood(int x, int y, boolean careOfEaten) {
        for(int i = 0; i < pnGame.Foods.size(); i ++) {
            if((!careOfEaten || !pnGame.Foods.get(i).isEaten()) && pnGame.Foods.get(i).getX() == x && pnGame.Foods.get(i).getY() == y) {
                return i;
            }
        }
        
        return -1;
    }

    private void gameCycle() {
        String statusText;
//        Thread thisThread = Thread.currentThread();
//        while(thisThread == t) {
        lbStatusText.setText("");
        statusText = "";
        for(int i = 0; i < pnGame.Snakes.size(); i ++) {
            Point f = pnGame.Snakes.get(i).getPPointP(0);
            if(isFoodOn(f.x, f.y, true)) {
                boolean b = pnGame.Foods.get(indexOfFood(f.x, f.y, true)).eat(i);
                pnGame.Snakes.get(i).increaseScore(pnGame.Foods.get(indexOfFood(f.x, f.y, false)).getValue());
                System.out.println("Snake[" + i + "] score = " + pnGame.Snakes.get(i).getScore());
                    
                    /*System.out.println(indexOfFood(f.x, f.y, false) + " ::: " + f.x + " :: " + f.y + 
                            ", eaten = '" + pnGame.Foods.get(indexOfFood(f.x, f.y, false)).isEaten() + "'");*/
                    
                if(!b) {
                    if(isTimeForBonusFood()) {
                        Food nf;
                        do {
                            nf = Food.createBonusFood(GameWidth, GameHeight, GameLevel * 5);
                            System.out.println("BF: x = " + nf.getX() + ", y = " + nf.getY());
                        } while(isFoodOn(nf.getX(), nf.getY(), false) || isSnakeOn(nf.getX(), nf.getY()));
                        pnGame.Foods.add(nf);
                    }
                    Food nf;
                    do {
                        nf = Food.createFood(GameWidth, GameHeight, GameLevel);
                        System.out.println("F:  x = " + nf.getX() + ", y = " + nf.getY());
                    } while(isFoodOn(nf.getX(), nf.getY(), false) || isSnakeOn(nf.getX(), nf.getY()));
                    pnGame.Foods.add(nf);
                }
            }
            statusText += "Snake[" + i + "] score = " + pnGame.Snakes.get(i).getScore() + "   ";
                
            if(isSnakeOn(f.x, f.y, i) || isMazeOn(f.x, f.y)) {
                gameOver();
                return ;
            }
                
            Point l = pnGame.Snakes.get(i).movePSnake(GameWidth, GameHeight);
            if(isFoodOn(l.x, l.y, false)) {
                pnGame.Snakes.get(i).increasePSnake(l.x, l.y);
                pnGame.Foods.remove(indexOfFood(l.x, l.y, false));
            }
                
//            repaint();
//                pnGame.setElements(Snakes, Foods);
//                pnGame.repaint();
            }
        lbStatusText.setText(statusText);
            
        repaint();
            
//            try {
//                t.sleep(GameSpeed);
//            } catch (InterruptedException e) {}
//        }
    }
    
    private boolean isTimeForBonusFood() {
        if(Math.random() > 0.8) return true;
        return false;
    }
    
    private boolean loadMazes() {
        return true;
    }
    
    private class cKeyListener implements KeyListener {
        //@Override
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
            }
        }
            
        //@Override
        public void keyReleased(KeyEvent e) {
        }

        //@Override
        public void keyTyped(KeyEvent e) {
            
        }
    }
    
    private class cWindowAdapter extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            DB.closeConnection();
            System.exit(0);
        }
    }
}