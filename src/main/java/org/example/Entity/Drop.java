package org.example.Entity;
import org.example.GamePanel;

import java.awt.image.BufferedImage;


public class Drop {
    public BufferedImage image;
    public int x;
    public int y;
    public String dropType;
    public GamePanel gp;
    public int width;
    public int height;

    public Drop(GamePanel gp , Integer x , String dropType , BufferedImage image , Integer width , Integer height){
        this.gp = gp;
        this.x = x;
        this.dropType = dropType;
        this.image = image;
        this.y = 0;
        this.width = width;
        this.height = height;
    }

}
