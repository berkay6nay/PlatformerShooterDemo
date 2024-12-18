package org.example;

import org.example.Entity.*;
import org.example.Entity.Bullets.Bullet;
import org.example.Entity.Guns.Gun02;
import org.example.Entity.Guns.Gun04;
import org.example.Entity.Guns.GunDefault;
import org.example.Tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize*scale;

    public ArrayList<Bullet> bullets = new ArrayList<>();

    public final int maxScreenCol = 20;
    public final int maxScreenRow = 15;
    public final int screenWidth = tileSize*maxScreenCol;
    public final int screenHeight = tileSize*maxScreenRow;
    final int FPS = 60;
    Thread gameThread;

    KeyHandlerBlue keyHB = new KeyHandlerBlue();
    public BulletKeyHandlerBlue bulletKeyHandlerBlue = new BulletKeyHandlerBlue();
    GunDefault gun = new GunDefault(this ,bulletKeyHandlerBlue);
    PlayerBlue playerBlue = new PlayerBlue(this, keyHB , gun , bulletKeyHandlerBlue);

    public BulletKeyHandlerRed bulletKeyHandlerRed = new BulletKeyHandlerRed();
    public KeyHandlerRed keyHR = new KeyHandlerRed();
    Gun02 gun02 = new Gun02(this , bulletKeyHandlerRed);
    PlayerRed playerRed = new PlayerRed(this , keyHR , gun02 , bulletKeyHandlerRed);

    TileManager tileManager = new TileManager(this);
    public DropManager dropManager = new DropManager(this);
    public PerkManager perkManager = new PerkManager(this);

    PlayerInfoPanelBlue panelBlue = new PlayerInfoPanelBlue(playerBlue , this);
    PlayerInfoPanelRed panelRed = new PlayerInfoPanelRed(playerRed , this);

    MenuKeyHandler menuKeyHandler = new MenuKeyHandler(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    String victoriousPlayer;

    int playing = 0;
    int menu = 1;
    int victory = 2;
    int winner;

    int gameState = menu;

    Sound sound = new Sound();

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHR);
        this.addKeyListener(bulletKeyHandlerRed);
        this.addKeyListener(keyHB);
        this.addKeyListener(bulletKeyHandlerBlue);
        this.addKeyListener(menuKeyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while(gameThread != null){
            //UPDATE INFORMATION
            update();

            //DRAW THE SCREEN WITH UPDATED INFO
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void update(){
        if(gameState == playing){
            playerBlue.update();
            playerRed.update();
            playerBlue.gun.update(playerBlue);
            playerRed.gun.update(playerRed);
            BulletManager.update(this);
            dropManager.update();
            perkManager.update();
            checkIfAPlayerHasWon();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(new Font("TimesRoman" , Font.PLAIN , 32));
        tileManager.draw(g2);

        if(gameState == playing){
            playerBlue.draw(g2);
            playerRed.draw(g2);
            playerBlue.gun.draw(g2 , playerBlue.keyH.playerMovingHorizontally);
            playerRed.gun.draw(g2 , playerRed.keyH.playerMovingHorizontally);
            dropManager.draw(g2);
            perkManager.draw(g2);
            panelBlue.drawPlayerPanel(g2);
            panelRed.drawPlayerPanel(g2);
            BulletManager.draw(g2 , this);
        }

        if(gameState == menu){
            String menuMessage = "PRESS P TO START GAME";
            int middleXMenu = screenWidth/2 - ((int)g2.getFontMetrics().getStringBounds(menuMessage , g2).getWidth())/2;
            g2.drawString(menuMessage , middleXMenu , screenHeight/2);
        }

        if(gameState == victory){
            if(winner == 0){
                 victoriousPlayer = "Red";
            }
            if(winner == 1){
                 victoriousPlayer = "Blue";
            }
            String message = "Player " + victoriousPlayer + " has won!!!";
            int middleX = screenWidth/2 - ((int)g2.getFontMetrics().getStringBounds(message , g2).getWidth())/2;
            g2.drawString(message , middleX , screenHeight/2);
        }
        g2.dispose();

    }

    public void checkIfAPlayerHasWon(){
        if(playerBlue.lives == 0){
            winner = 0;
            gameState = victory;
        }
        if(playerRed.lives == 0){
            winner = 1;
            gameState = victory;
        }
    }

    public void playSoundFX(int i){
        sound.setFile(i);
        sound.play();
    }
}
