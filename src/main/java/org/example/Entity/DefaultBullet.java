package org.example.Entity;
import javax.imageio.ImageIO;
import java.io.File;

public class DefaultBullet extends Bullet{

    public DefaultBullet(Gun gun){
        super(gun);
        speed = 20;
        force = 30;
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
