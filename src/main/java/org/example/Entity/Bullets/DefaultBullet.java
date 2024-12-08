package org.example.Entity.Bullets;
import org.example.Entity.Guns.Gun;

import javax.imageio.ImageIO;
import java.io.File;

public class DefaultBullet extends Bullet{

    public DefaultBullet(Gun gun){
        super(gun);
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
