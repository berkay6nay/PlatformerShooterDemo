package org.example.Entity.Bullets;

import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet05 extends Bullet{

    public Bullet05(Gun gun){
        super(gun);
        width = 12;
        height = 5;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_05.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
