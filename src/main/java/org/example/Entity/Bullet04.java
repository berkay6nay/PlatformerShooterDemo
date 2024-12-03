package org.example.Entity;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet04 extends Bullet{

    public Bullet04(Gun gun){
        super(gun);
        speed = 20;
        force = 60;
        width = 11;
        height = 4;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Bullets/bullet_04.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
