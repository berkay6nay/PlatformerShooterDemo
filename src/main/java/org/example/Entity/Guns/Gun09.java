package org.example.Entity.Guns;

import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet08;
import org.example.Entity.Bullets.Bullet09;
import org.example.Entity.Player;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Gun09 extends Gun{

    public Gun09(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 20;
        this.defaultBulletNumber = 50;
        this.currentBulletNumber = defaultBulletNumber;
        this.type = "gunNumberNine";
        getGunImages();
    }

    @Override
    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_09_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_09_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_09_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_09_resting_left.png"));
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

    public Bullet09 generateBullet(){
        return new Bullet09(this);
    }

}
