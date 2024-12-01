package org.example.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import org.example.BulletKeyHandler;
import org.example.GamePanel;
import javax.imageio.ImageIO;

public class GunDefault extends Gun{


    public GunDefault(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.lastFiringTime = 0;
        this.type = "default";
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 5;
        this.shootingInterval = 200000000;
        getGunImages();
    }


    @Override
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

    @Override
    public void draw(Graphics2D g2 , boolean playerMovingHorizontally){
        BufferedImage image = null;
        if(playerMovingHorizontally || keyH.gunBeingShot) {
            image = switch (gunDirection) {
                case "right" -> right;
                case "left" -> left;
                default -> image;
            };
        }
        else{
            image = switch (gunDirection) {
                case "right" -> rightResting;
                case "left" -> leftResting;
                default -> image;
            };
        }
        g2.drawImage(image , gunX , gunY , gunWidth , gunHeight , null);
    }

    @Override
    public void update(Player player){
        gunDirection = player.direction;
        switch (gunDirection){
            case "right":
                gunX = player.x + gunXDifferenceWhenFacingRight;
                break;
            case "left":
                gunX = player.x - gunXDifferenceWhenFacingLeft;
        }
        gunY = player.y + gunYDifference;

        if(keyH.gunBeingShot){
            shootBullet(shootingInterval , this::generateBullet);
        }
    }

    public DefaultBullet generateBullet(){
        return new DefaultBullet(this);
    }


}
