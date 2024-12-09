package org.example.Entity;

import org.example.BulletKeyHandler;
import org.example.Entity.Guns.*;
import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class PlayerBlue extends Player{


    public PlayerBlue(GamePanel gp, KeyHandler keyH , Gun gun , BulletKeyHandler bulletKeyHandler) {
        this.gp = gp;
        this.bulletKeyHandler = bulletKeyHandler;
        this.keyH = keyH;
        this.x = 300;
        this.y = 50;
        setDefaultValues();
        getPlayerImage();
        this.gun = gun;
    }


    @Override
    public void getPlayerImage() {
        try{
            left1 = ImageIO.read(new File("res/Player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/Player/boy_left_2.png"));
            right1 = ImageIO.read(new File("res/Player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/Player/boy_right_2.png"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
