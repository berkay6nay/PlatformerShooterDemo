package org.example.Entity.Bullets;
import org.example.Entity.BaseEntity;
import org.example.Entity.Guns.Gun;

public abstract class Bullet extends BaseEntity {

    public String direction;
    public int force;
    public int speed;
    public boolean isActive = true;

    public Bullet(Gun gun){

        force = 24;
        speed = 18;

        if(gun.gunDirection.equals("right")){
            x = gun.gunX + gun.gunWidth;
        }
        else{
            x = gun.gunX;
        }
        y = gun.gunY + gun.gunHeight / 2;
        direction = gun.gunDirection;
        gp = gun.gp;
        width = 8;
        height = 3;
    }

    public void loadImage() {
    }

}
