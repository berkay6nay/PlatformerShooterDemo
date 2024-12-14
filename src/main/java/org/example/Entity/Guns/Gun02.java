package org.example.Entity.Guns;

import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet02;
import org.example.Entity.Player;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Gun02 extends Gun{

    public Gun02(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "gunNumberTwo";
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 17;
        this.defaultBulletNumber = 45;
        this.currentBulletNumber = defaultBulletNumber;
        getGunImages();

    }

    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_02_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_02_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_02_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_02_resting_left.png"));
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


    public Bullet02 generateBullet(){
        return new Bullet02(this);
    }
}
