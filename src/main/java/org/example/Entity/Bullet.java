package org.example.Entity;
import org.example.GamePanel;
import java.awt.image.BufferedImage;

public abstract class Bullet {

    public int x , y;
    public String direction;
    public int force;
    public GamePanel gp;
    public int speed;
    public BufferedImage image;
    public int width;
    public int height;

    public Bullet(Gun gun){

        if(gun.gunDirection.equals("right")){
            x = gun.gunX + gun.gunWidth;
        }
        else{
            x = gun.gunX;
        }
        y = gun.gunY + gun.gunHeight / 2;
        direction = gun.gunDirection;
        gp = gun.gp;
        width = 6;
        height = 2;
    }

    public void loadImage() {
    }

}
