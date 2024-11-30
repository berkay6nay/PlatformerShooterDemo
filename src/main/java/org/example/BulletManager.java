package org.example;

import org.example.Entity.Bullet;

import java.awt.*;

public class BulletManager {

    public static void draw(Graphics2D g2 , GamePanel gp){
        for(Bullet bullet : gp.bullets){
            g2.drawImage(bullet.image , bullet.x , bullet.y , bullet.width , bullet.height , null);
        }
    }

    public static void update(GamePanel gp){

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
