package org.example.Entity;

import javax.imageio.ImageIO;
import java.io.File;

public class Bullet02 extends Bullet{

    public Bullet02(Gun gun) {
        super(gun);
        speed = 10;
        force = 20;
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
