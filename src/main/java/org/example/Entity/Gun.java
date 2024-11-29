package org.example.Entity;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gun {
    public int x , y;
    public BufferedImage right , left;
    public String direction;
    public GamePanel gp;
    public final int  gunWidth = 27;
    public final int  gunHeight = 16;
    public final int gunScale = 2;
    Player player;

    public Gun(String direction , GamePanel gp , Player player){
        this.player = player;
        this.direction = player.direction;
        this.gp = player.gp;
        this.x = player.x;
        this.y = player.y;
        getGunImages();
    }

    public void update(){
        direction = player.direction;
        x = player.x + gp.tileSize/2;
        y = player.y + gp.tileSize/2;
    }


    public void getGunImages(){
        try{
            right = ImageIO.read(new File("res/Guns/gun_01_right.png"));
            left = ImageIO.read(new File("res/Guns/gun_01_left.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "right":
                image = right;
                break;
            case "left":
                image = left;
                x = player.x - gunWidth;
                break;
        }
        g2.drawImage(image , x , y , gunWidth * gunScale , gunHeight * gunScale , null);
    }

}
