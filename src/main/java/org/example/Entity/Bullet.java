package org.example.Entity;
import org.example.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Bullet {
    public int x , y;
    public String direction;
    public int force;
    public String bulletType;
    public GamePanel gp;
    public int speed;
    public BufferedImage image;
    public int width , height;

    public Bullet(Gun gun){
        x = gun.gunX;
        y = gun.gunY;
        direction = gun.gunDirection;
        force = 30;
        bulletType = gun.type + "Bullet";
        gp = gun.gp;
        speed = 8;
        width = 6;
        height = 2;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_default.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
