package org.example.Entity.Guns;
import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet06;
import org.example.Entity.Bullets.Bullet07;
import org.example.Entity.Player;
import org.example.GamePanel;
import javax.imageio.ImageIO;
import java.io.File;

public class Gun07 extends  Gun{

    public Gun07(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "gunNumberSeven";
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
            right = ImageIO.read(new File("res/Guns/gun_07_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_07_left.png"));
            rightResting = ImageIO.read(new File("res/Guns/gun_07_resting_right.png"));
            leftResting = ImageIO.read(new File("res/Guns/gun_07_resting_left.png"));
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

    public Bullet07 generateBullet(){
        gp.playSoundFX(8);
        return new Bullet07(this);
    }
}
