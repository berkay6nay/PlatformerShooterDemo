package org.example.Entity.Guns;

import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet03;
import org.example.Entity.Player;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Gun03 extends Gun{

    public Gun03(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "gunNumberTree";
        this.shootingInterval = 200000000;
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 20;
        this.defaultBulletNumber = 45;
        this.currentBulletNumber = defaultBulletNumber;
        getGunImages();
    }

    @Override
    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_03_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_03_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_03_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_03_resting_left.png"));
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

    public Bullet03 generateBullet(){
        return new Bullet03(this);
    }
}
