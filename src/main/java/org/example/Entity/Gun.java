package org.example.Entity;
import org.example.BulletKeyHandler;
import org.example.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gun {
    public int gunX , gunY;
    public BufferedImage right , left , rightResting, leftResting;
    public String gunDirection;
    public GamePanel gp;
    public String type;
    public BulletKeyHandler keyH;

    public Gun(GamePanel gp , BulletKeyHandler keyH){
        this.gp = gp;
        this.type = "default";
        this.keyH = keyH;
        getGunImages();
    }

    public void update(String direction , Integer playerX , Integer playerY){
        gunDirection = direction;
        gunX = playerX + gp.tileSize/3;
        gunY = playerY + gp.tileSize/4 + 5;

        if(keyH.gunBeingShot){
            Bullet bullet = new Bullet(this);
            gp.bullets.add(bullet);
        }

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

    public void draw(Graphics2D g2 , boolean playerMovingHorizontally){
        BufferedImage image = null;
        if(!playerMovingHorizontally) {
            switch (gunDirection) {
                case "right":
                    image = rightResting;
                    gunX -= 8;
                    break;
                case "left":
                    image = leftResting;
                    gunX -= 25;
                    break;
            }
        }
        else{
            switch (gunDirection) {
                case "right":
                    image = right;
                    gunX -= 8;
                    break;
                case "left":
                    image = left;
                    gunX -= 25;
                    break;
            }
        }
        g2.drawImage(image , gunX , gunY , 52 , 24 , null);
    }

}
