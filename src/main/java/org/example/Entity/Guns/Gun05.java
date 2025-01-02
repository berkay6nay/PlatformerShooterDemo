package org.example.Entity.Guns;
import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet05;
import org.example.Entity.Player;
import org.example.GamePanel;
import javax.imageio.ImageIO;
import java.io.File;


public class Gun05 extends Gun{

    public Gun05(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "gunNumberFive";
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 10;
        this.gunHeight = 20;
        this.defaultBulletNumber = 3;
        this.currentBulletNumber = defaultBulletNumber;
        this.shootingInterval = 2000000000;
        getGunImages();
    }


    @Override
    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_05_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_05_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_05_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_05_resting_left.png"));
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

    public Bullet05 generateBullet(){
        gp.playSoundFX(0);
        return new Bullet05(this);
    }
}
