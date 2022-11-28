
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class ShootingGame extends JFrame {
    private Image bufferImage;
    private Graphics screenGraphic;

    private final Image mainScreen = new ImageIcon("src/images/main_screen.png").getImage();
    private final Image mainBtn = new ImageIcon("src/images/main_btn.png").getImage();
    private final Image loadingScreen = new ImageIcon("src/images/loading_screen.png").getImage();
    private final Image loadingBar = new ImageIcon("src/images/loding.png").getImage();
    private final Image loadingBarSingle = new ImageIcon("src/images/loading_bar.png").getImage();
    private final Image gameScreen = new ImageIcon("src/images/game_screen.png").getImage();


    //화면 전환을 위한 bool값
    public static boolean isMainScreen;
    private static boolean isLoadingScreen;
    private static boolean isGameScreen;
    boolean lodingbar = false;
    boolean lodingbar2 = false;

    private final Game game = new Game();

    private Audio backgroundMusic;


    public ShootingGame() {


        setTitle("Shooting Game");
        setUndecorated(true);
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        init();
    }


    private void init() {


        isMainScreen = true;
        isLoadingScreen = false;
        isGameScreen = false;

        backgroundMusic = new Audio("src/audio/menuBGM.wav", true);
        backgroundMusic.start();

        addKeyListener(new KeyListener());
    }

    private void gameStart() {
        isMainScreen = false;
        isLoadingScreen = true;
        Timer loadingTimer = new Timer();
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                lodingbar = true;
            }
        };
        loadingTimer.schedule(task, 800);


        TimerTask task2 = new TimerTask() {

            @Override
            public void run() {
                lodingbar2 = true;
            }
        };
        loadingTimer.schedule(task2, 1200);


        TimerTask loadingTask = new TimerTask() {

            @Override
            public void run() {
                lodingbar = true;
                //backgroundMusic.stop();
                isLoadingScreen = false;
                isGameScreen = true;
                game.start();
            }
        };
        loadingTimer.schedule(loadingTask, 3000);
    }

    public void paint(Graphics g) {
        bufferImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);

        g.drawImage(bufferImage, 0, 0, null);


    }

    public void screenDraw(Graphics g) {
        if (isMainScreen) {

            g.drawImage(mainScreen, 0, 0, null);
            g.drawImage(mainBtn, 260, 570, null);


        }
        if (isLoadingScreen) {
            g.drawImage(loadingScreen, 0, 0, null);
            g.drawImage(loadingBar, 100, 385, null);
            if (lodingbar) {

                g.drawImage(loadingBarSingle, 595, 403, null);
            }
            if (lodingbar2) {
                System.out.println("dsfdfdfdfd");
                g.drawImage(loadingBarSingle, 632, 403, null);
            }


        }
        if (isGameScreen) {
            g.drawImage(gameScreen, 0, 0, null);
            game.gameDraw(g);
        }

//        if(isScoreScreen){
//            //g.drawImage(scoreScreen, 0, 0, null);
//        }
        this.repaint();
    }


    class KeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    game.setDown(true);
                    break;
                case KeyEvent.VK_UP:
                    game.setUp(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.setRight(true);
                    break;
                case KeyEvent.VK_LEFT:
                    game.setLeft(true);
                    break;
                case KeyEvent.VK_W:
                    game.setUp(true);
                    break;
                case KeyEvent.VK_S:
                    game.setDown(true);
                    break;
                case KeyEvent.VK_A:
                    game.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    game.setRight(true);
                    break;
                case KeyEvent.VK_R:
                    if (game.isOver()) game.reset();
                    break;
                case KeyEvent.VK_SPACE:
                    game.setShooting(true);
                    break;
                case KeyEvent.VK_ENTER:
                    if (isMainScreen) gameStart();
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
            }
        }

        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_DOWN:
                    game.setDown(false);
                    break;
                case KeyEvent.VK_UP:
                    game.setUp(false);
                    break;
                case KeyEvent.VK_RIGHT:
                    game.setRight(false);
                    break;
                case KeyEvent.VK_LEFT:
                    game.setLeft(false);
                    break;
                case KeyEvent.VK_W:
                    game.setUp(false);
                    break;
                case KeyEvent.VK_S:
                    game.setDown(false);
                    break;
                case KeyEvent.VK_A:
                    game.setLeft(false);
                    break;
                case KeyEvent.VK_D:
                    game.setRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    game.setShooting(false);
                    break;
            }
        }
    }
}