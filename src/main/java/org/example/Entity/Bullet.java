package org.example.Entity;
import org.example.GamePanel;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

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
        x = gun.gunX;
        y = gun.gunY;
        direction = gun.gunDirection;
        gp = gun.gp;
        width = 6;
        height = 2;
    }

    public void loadImage() {
    }

}
