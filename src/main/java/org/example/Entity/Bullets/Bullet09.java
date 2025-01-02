package org.example.Entity.Bullets;

import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet09 extends Bullet{

    public Bullet09(Gun gun){
        super(gun);
        width = 12;
        height = 5;
        this.force = 20;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_09.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}