package org.example;

import org.example.Entity.Bullet;
import org.example.Entity.Gun;
import org.example.Entity.Player;
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

    KeyHandler keyH = new KeyHandler();
    BulletKeyHandler bulletKeyHandler = new BulletKeyHandler();
    Gun gun = new Gun(this , bulletKeyHandler);
    Player player = new Player(this, keyH , gun);
    TileManager tileManager = new TileManager(this);

    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth , screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.addKeyListener(bulletKeyHandler);
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
        player.update();
        player.gun.update(player.direction , player.x , player.y);
        BulletManager.update(this);

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        player.gun.draw(g2 , player.keyH.playerMovingHorizontally);
        BulletManager.draw(g2 , this);
        g2.dispose();
    }
}
