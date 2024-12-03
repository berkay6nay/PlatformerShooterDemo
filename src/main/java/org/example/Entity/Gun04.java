package org.example.Entity;

import org.example.BulletKeyHandler;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Gun04 extends Gun{

    public Gun04(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "gunNumberFour";
        this.shootingInterval = 2000000000;
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 20;
        getGunImages();
    }

    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_04_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_04_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_04_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_04_resting_left.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Player player){
        manageGunPositionAndDirection(player);
        if(keyH.gunBeingShot){
            shootBullet(shootingInterval , this::generateBullet);
        }
    }

    public Bullet04 generateBullet(){
        return new Bullet04(this);
    }

}
