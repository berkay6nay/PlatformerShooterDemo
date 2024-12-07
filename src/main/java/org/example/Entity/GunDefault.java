package org.example.Entity;
import java.io.File;
import org.example.BulletKeyHandler;
import org.example.GamePanel;
import javax.imageio.ImageIO;

public class GunDefault extends Gun{

    public boolean isReloading;
    public double creationTime;

    public GunDefault(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.type = "default";
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 8;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 5;
        this.shootingInterval = 200000000;
        this.defaultBulletNumber = 20;
        this.currentBulletNumber = defaultBulletNumber;
        this.isReloading = true;
        this.creationTime = System.nanoTime();
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
    public void update(Player player){
        manageGunPositionAndDirection(player);
        double timeBetweenNowAndGunCreation = calculateTimeBetweenNowAndGunCreation();
        if(timeBetweenNowAndGunCreation < 1500000000F) System.out.println("reloading");
        else isReloading = false;

        if(keyH.gunBeingShot && !isReloading){
            shootBullet(shootingInterval , this::generateBullet);
        }
    }

    public DefaultBullet generateBullet(){
        return new DefaultBullet(this);
    }

    public double calculateTimeBetweenNowAndGunCreation(){
        double now = System.nanoTime();
        return now - creationTime;
    }

}
