package org.example.Entity.Bullets;

import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet03 extends Bullet{

    public Bullet03(Gun gun){
        super(gun);
        speed = 15;
        force = 25;
        width = 12;
        height = 5;
        loadImage();
    }
    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_03.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
