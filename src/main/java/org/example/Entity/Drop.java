package org.example.Entity;
import org.example.GamePanel;

import java.awt.image.BufferedImage;


public class Drop extends BaseEntity {

    public String dropType;
    public double creationTime;

    public Drop(GamePanel gp , Integer x , String dropType , BufferedImage image , Integer width , Integer height){
        this.gp = gp;
        this.x = x;
        this.dropType = dropType;
        this.image = image;
        this.y = 0;
        this.width = width;
        this.height = height;
        this.creationTime = System.nanoTime();
    }

}
