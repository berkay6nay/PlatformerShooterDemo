package org.example.Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int baseSpeed;

    public BufferedImage left1 , left2 , right1 , right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;


}
