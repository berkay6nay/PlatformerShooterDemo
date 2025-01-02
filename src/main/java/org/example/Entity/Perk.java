package org.example.Entity;

import org.example.GamePanel;

import java.awt.image.BufferedImage;

public class Perk extends BaseEntity {

    public String type;
    public double creationTime;

    public Perk(GamePanel gp , String type , int x , int y , BufferedImage image, int width , int height){
        this.gp = gp;
        this.type = type;
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
        this.creationTime = System.nanoTime();
    }


}
