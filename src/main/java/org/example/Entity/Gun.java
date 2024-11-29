package org.example.Entity;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gun {
    public int x , y;
    public BufferedImage right , left , rightResting, leftResting;
    public String direction;
    public GamePanel gp;
    public final int  gunWidth = 27;
    public final int  gunHeight = 16;
    public final double gunScale = 2;
    Player player;

    public Gun(GamePanel gp , Player player){
        this.player = player;
        this.direction = player.direction;
        this.gp = player.gp;
        this.x = player.x;
        this.y = player.y;
        getGunImages();
    }

    public void update(){
        direction = player.direction;
        x = player.x + gp.tileSize/3;
        y = player.y + gp.tileSize/4 + 5;
    }


    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_01_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_01_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_resting_left.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        if(!player.keyH.playerMovingHorizontally) {
            switch (direction) {
                case "right":
                    image = rightResting;
                    break;
                case "left":
                    image = leftResting;
                    x = player.x - gunWidth + 5;
                    break;
            }
        }
        else{
            switch (direction) {
                case "right":
                    image = right;
                    break;
                case "left":
                    image = left;
                    x = player.x - gunWidth;
                    break;
            }
        }
        g2.drawImage(image , x , y , 52 , 24 , null);
    }

}
