package org.example.Entity.Guns;

import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet07;
import org.example.Entity.Bullets.Bullet08;
import org.example.Entity.Player;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;

public class Gun08 extends Gun {

    public Gun08(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 20;
        this.defaultBulletNumber = 60;
        this.currentBulletNumber = defaultBulletNumber;
        this.type = "gunNumberEight";
        getGunImages();
    }

    @Override
    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_08_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_08_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_08_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_08_resting_left.png"));
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

    public Bullet08 generateBullet(){
        return new Bullet08(this);
    }

}
