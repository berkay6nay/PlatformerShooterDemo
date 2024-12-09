package org.example.Entity;

import org.example.BulletKeyHandler;
import org.example.Entity.Guns.*;
import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class PlayerRed extends Player{

    public PlayerRed(GamePanel gp , KeyHandler kH , Gun gun, BulletKeyHandler bulletKeyHandler){
        this.gp = gp;
        this.keyH = kH;
        this.bulletKeyHandler = bulletKeyHandler;
        this.x = 600;
        this.y = 50;
        this.direction = "left";
        setDefaultValues();
        getPlayerImage();
        this.gun = gun;
    }


    @Override
    public void getPlayerImage(){
        try{
            left1 = ImageIO.read(new File("res/Player/boy_red_left_1.png"));
            left2 = ImageIO.read(new File("res/Player/boy_red_left_2.png"));
            right1 = ImageIO.read(new File("res/Player/boy_red_right_1.png"));
            right2 = ImageIO.read(new File("res/Player/boy_red_right_2.png"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


}
