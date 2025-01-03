package org.example.Entity.Guns;
import java.io.File;
import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.DefaultBullet;
import org.example.Entity.Player;
import org.example.GamePanel;
import javax.imageio.ImageIO;

public class GunDefault extends Gun{

    public boolean isReloading;
    public double timeOfBeingEmptied;
    public double reloadingTime = 1000000000F;
    public GunDefault(GamePanel gp , BulletKeyHandler kH){
        this.gp = gp;
        this.keyH = kH;
        this.gunXDifferenceWhenFacingRight = gp.tileSize/3 - 4;
        this.gunXDifferenceWhenFacingLeft = gp.tileSize / 4 + 5;
        this.gunYDifference = gp.tileSize/4 + 7;
        this.shootingInterval = 500000000;
        this.defaultBulletNumber = 15;
        this.currentBulletNumber = defaultBulletNumber;
        this.isReloading = false;
        this.type = "default";
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
        double now = System.nanoTime();

        if(currentBulletNumber == 0 && !isReloading){
            System.out.println("reloading");
            timeOfBeingEmptied = System.nanoTime();
            isReloading = true;
        }

        if(isReloading && now - timeOfBeingEmptied > reloadingTime){
            currentBulletNumber = defaultBulletNumber;
            timeOfBeingEmptied = 0;
            isReloading = false;
        }

        if(keyH.gunBeingShot && !isReloading){
            System.out.println(currentBulletNumber);
            shootBullet(shootingInterval , this::generateBullet);
        }
    }

    public DefaultBullet generateBullet(){
        gp.playSoundFX(6);
        return new DefaultBullet(this);
    }

}
