package org.example;

import org.example.Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Armor {

    public boolean isActive;
    public int x;
    public int y;
    public int height;
    public int width;
    public double activationTime;
    public BufferedImage image;
    public String type;

    public Armor(Player player , String type){
        this.x = player.x;
        this.y = player.y;
        this.width = player.gp.tileSize;
        this.height = player.gp.tileSize;
        this.isActive = true;
        this.activationTime = System.nanoTime();
        this.type = type;
        loadImage();
    }

    public void loadImage(){
        try{
            image = ImageIO.read(new File("res/Perks/armor.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Player player){
        x = player.x;
        y = player.y;
    }

    public void draw(Graphics2D g2){
        if(isActive){
            g2.drawImage(image , x , y , width , height , null);
        }
    }

}
