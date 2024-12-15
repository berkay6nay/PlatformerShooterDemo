package org.example.Entity.Bullets;

import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet06 extends Bullet{

    public Bullet06(Gun gun){
        super(gun);
        width = 10;
        height = 3;
        this.force = 20;
        loadImage();
    }

    public void loadImage(){

        try{
            image = ImageIO.read(new File("res/Bullets/bullet_06.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
