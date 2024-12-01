package org.example.Entity;
import org.example.BulletKeyHandler;
import org.example.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class Gun {
    public int gunX , gunY;
    public BufferedImage right , left , rightResting, leftResting;
    public String gunDirection;
    public GamePanel gp;
    public String type;
    public BulletKeyHandler keyH;
    public double lastFiringTime;
    public int gunXDifferenceWhenFacingRight;
    public int gunXDifferenceWhenFacingLeft;
    public int gunWidth = 52;
    public int gunHeight = 24;
    public int gunYDifference;
    public double shootingInterval;

    public void update(Player player){

    }

    public void getGunImages(){

    }

    public void draw(Graphics2D g2 , boolean playerMovingHorizontally){

    }

    public double checkIfCertainAmountOfTimeHasPassedToFire(Double now){
        if(lastFiringTime == 0){
            lastFiringTime = now;
            return 0;
        }
        return now - lastFiringTime;
    }


    public void shootBullet(Double shootingInterval , BulletGenerator<? extends  Bullet> bulletGenerator){
        double now = System.nanoTime();
        double amountOfTimePassedSinceLastFiring = checkIfCertainAmountOfTimeHasPassedToFire(now);
        if(amountOfTimePassedSinceLastFiring == 0){
            Bullet bullet = bulletGenerator.generate();
            gp.bullets.add(bullet);
            lastFiringTime = now;
        }
        else if(amountOfTimePassedSinceLastFiring > shootingInterval){
            Bullet bullet = bulletGenerator.generate();
            gp.bullets.add(bullet);
            lastFiringTime = now;
        }
    }

}
