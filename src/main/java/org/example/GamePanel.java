package org.example;

import org.example.Entity.*;
import org.example.Entity.Bullets.Bullet;
import org.example.Entity.Guns.Gun04;
import org.example.Entity.Guns.Gun05;
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
    GunDefault gunDefault = new GunDefault(this , bulletKeyHandlerRed);
    PlayerRed playerRed = new PlayerRed(this , keyHR , gunDefault , bulletKeyHandlerRed);

    TileManager tileManager = new TileManager(this);
    public DropManager dropManager = new DropManager(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHR);
        this.addKeyListener(bulletKeyHandlerRed);
        this.addKeyListener(keyHB);
        this.addKeyListener(bulletKeyHandlerBlue);
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
        playerBlue.update();
        playerRed.update();
        playerBlue.gun.update(playerBlue);
        playerRed.gun.update(playerRed);
        BulletManager.update(this);
        dropManager.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        playerBlue.draw(g2);
        playerRed.draw(g2);
        playerBlue.gun.draw(g2 , playerBlue.keyH.playerMovingHorizontally);
        playerRed.gun.draw(g2 , playerRed.keyH.playerMovingHorizontally);
        dropManager.draw(g2);
        BulletManager.draw(g2 , this);
        g2.dispose();
    }
}
