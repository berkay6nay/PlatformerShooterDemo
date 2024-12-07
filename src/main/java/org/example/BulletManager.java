package org.example;

import org.example.Entity.Bullets.Bullet;

import java.awt.*;
import java.util.ArrayList;

public class BulletManager {

    public static void draw(Graphics2D g2 , GamePanel gp){
        for(Bullet bullet : gp.bullets){
            g2.drawImage(bullet.image , bullet.x , bullet.y , bullet.width , bullet.height , null);
        }
    }

    public static void update(GamePanel gp){
        ArrayList<Bullet> bulletsToRemove = new ArrayList<>();
        for(Bullet bullet : gp.bullets){
            if(!bullet.isActive || bullet.x + bullet.width <= 0 || bullet.x >= gp.screenWidth){
                bulletsToRemove.add(bullet);
            }
        }
        gp.bullets.removeAll(bulletsToRemove);
        for(Bullet bullet : gp.bullets){
            switch (bullet.direction){
                case "right":
                    bullet.x += bullet.speed;
                    break;
                case "left":
                    bullet.x -= bullet.speed;
            }
        }
    }

}
