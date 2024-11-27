package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    boolean subjectToGravity;
    int fallSpeed;
    int maxFallSpeed;
    int gravityAcceleration;
    int jumpSpeed;
    int jumpDetrimention;
    boolean isStandingOnGround;
    boolean isJumping;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        isStandingOnGround = false;
        isJumping = false;
    }

    public void setDefaultValues() {
        x = 300;
        y = 100;
        speed = 4;
        fallSpeed = 0;
        maxFallSpeed = 7;
        gravityAcceleration = 1;
        subjectToGravity = true;
        direction = "down";
        jumpSpeed = 35;
        jumpDetrimention= 5;
    }

    public void update() {

        subjectToGravity = !gp.collisionChecker.checkIfStandingOnGround(this);
        isStandingOnGround = gp.collisionChecker.checkIfStandingOnGround(this);

        if(isStandingOnGround){
            fallSpeed = 0;
        }

        if ((keyH.upPressed && isStandingOnGround) || isJumping) {
            System.out.println("I jump");
            isJumping = true;
            subjectToGravity = false;
            keyH.upPressed = false;
            direction = "up";
            y -= jumpSpeed;
            jumpSpeed -= jumpDetrimention;
            System.out.println(keyH.upReleased);
            if(jumpSpeed == 0){
                subjectToGravity = true;
                isJumping = false;
                jumpSpeed = 35;
            }
        }
         else if (keyH.rightPressed) {
            direction = "right";
            x += speed;
        } else if (keyH.leftPressed) {
            direction = "left";
            x -= speed;
        }
         if (subjectToGravity) {
            y += fallSpeed;
            if(fallSpeed <= maxFallSpeed){
                fallSpeed += gravityAcceleration;

            } else {
                fallSpeed = maxFallSpeed;
            }
         }
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2)spriteNum = 1;
            spriteCounter = 0;
        }
    }
    public void draw (Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
            case "up":
                if(spriteNum == 1) image = up1;
                if(spriteNum == 2) image = up2;
                break;
            case "down":
                if(spriteNum == 1) image = down1;
                if(spriteNum == 2) image = down2;
                break;
            case "left":
                if(spriteNum == 1) image = left1;
                if(spriteNum == 2) image = left2;
                break;
            case "right":
                if(spriteNum == 1) image = right1;
                if(spriteNum == 2) image = right2;
                break;
        }
        g2.drawImage(image , x,y,gp.tileSize , gp.tileSize, null);
    }
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(new File("res/Player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/Player/boy_up_2.png"));
            left1 = ImageIO.read(new File("res/Player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/Player/boy_left_2.png"));
            down1 = ImageIO.read(new File("res/Player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/Player/boy_down_2.png"));
            right1 = ImageIO.read(new File("res/Player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/Player/boy_right_2.png"));

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}