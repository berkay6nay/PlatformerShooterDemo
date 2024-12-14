package org.example.Entity.Guns;
import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet;
import org.example.Entity.Bullets.BulletGenerator;
import org.example.Entity.Player;
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
    public double lastFiringTime = 0;
    public int gunXDifferenceWhenFacingRight;
    public int gunXDifferenceWhenFacingLeft;
    public int gunWidth = 52;
    public int gunHeight = 24;
    public int gunYDifference;
    public double shootingInterval = 100000000;
    public int defaultBulletNumber;
    public int currentBulletNumber;

    public void update(Player player){

    }

    public void getGunImages(){

    }

    public void draw(Graphics2D g2 , boolean playerMovingHorizontally){
        BufferedImage image = null;
        if(playerMovingHorizontally || keyH.gunBeingShot) {
            image = switch (gunDirection) {
                case "right" -> right;
                case "left" -> left;
                default -> image;
            };
        }
        else{
            image = switch (gunDirection) {
                case "right" -> rightResting;
                case "left" -> leftResting;
                default -> image;
            };
        }
        g2.drawImage(image , gunX , gunY , gunWidth , gunHeight , null);
    }

    public double checkIfCertainAmountOfTimeHasPassedToFire(Double now){
        if(lastFiringTime == 0){
            lastFiringTime = now;
            return 0;
        }
        return now - lastFiringTime;
    }


    public void shootBullet(Double shootingInterval , BulletGenerator<? extends Bullet> bulletGenerator){
        double now = System.nanoTime();
        double amountOfTimePassedSinceLastFiring = checkIfCertainAmountOfTimeHasPassedToFire(now);
        if(amountOfTimePassedSinceLastFiring == 0 || amountOfTimePassedSinceLastFiring > shootingInterval ){
            Bullet bullet = bulletGenerator.generate();
            gp.bullets.add(bullet);
            lastFiringTime = now;
            this.currentBulletNumber -= 1;
            System.out.println(currentBulletNumber);
        }
    }

    public void manageGunPositionAndDirection(Player player){
        gunDirection = player.direction;

        switch (gunDirection){
            case "right":
                gunX = player.x + gunXDifferenceWhenFacingRight;
                break;
            case "left":
                gunX = player.x - gunXDifferenceWhenFacingLeft;
        }
        gunY = player.y + gunYDifference;
    }
}
