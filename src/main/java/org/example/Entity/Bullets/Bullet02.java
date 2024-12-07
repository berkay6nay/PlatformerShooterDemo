package org.example.Entity.Bullets;

import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet02 extends Bullet{

    public Bullet02(Gun gun) {
        super(gun);
        speed = 15;
        force = 25;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_02.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
