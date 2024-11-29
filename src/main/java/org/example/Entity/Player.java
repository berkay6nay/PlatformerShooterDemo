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
    boolean hasJumpedOnce;
    boolean horizontalCollision;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        x = 300;
        y = 50;
        speed = 6;
        fallSpeed = 0;
        maxFallSpeed = 10;
        gravityAcceleration = 1;
        subjectToGravity = true;
        direction = "right";
        jumpSpeed = 30;
        jumpDetrimention= 5;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        isStandingOnGround = false;
        isJumping = false;
        hasJumpedOnce = false;
        horizontalCollision = false;
    }

    public void update() {

        boolean theAbyss = checkIfPlayerHasReachedTheAbyss();

        if(!theAbyss){
            System.out.println(this.x +" " +  this.y);
            subjectToGravity = !gp.collisionChecker.checkIfStandingOnGround(this);
            isStandingOnGround = gp.collisionChecker.checkIfStandingOnGround(this);
            horizontalCollision = gp.collisionChecker.checkCollisionHorizontally(this);
            if(isStandingOnGround){
                fallSpeed = 0;

            }
            startTheProcessOfJumping();

            if (isJumping) {
                subjectToGravity = false;
                manageLeftAndRightMovement();
                y -= jumpSpeed;
                jumpSpeed -= jumpDetrimention;
                if(jumpSpeed == 0){
                    subjectToGravity = true;
                    isJumping = false;
                    jumpSpeed = 30;
                }
            }
            else if (keyH.rightPressed) {
                direction = "right";
            } else if (keyH.leftPressed) {
                direction = "left";
            }
            if (subjectToGravity) {
                y += fallSpeed;
                if(fallSpeed <= maxFallSpeed){
                    fallSpeed += gravityAcceleration;

                } else {
                    fallSpeed = maxFallSpeed;
                }
            }

            if(!isStandingOnGround && !horizontalCollision){
                manageLeftAndRightMovement();
            } else if (isStandingOnGround) {
                manageLeftAndRightMovement();
            }
        }
        else{
            System.out.println("fallen into the abyss");
            this.x = 500;
            this.y  = 0;
        }

        manageSpriteAnimation();

    }
    public void draw (Graphics2D g2){
        BufferedImage image = null;
        switch (direction){
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

    public void manageLeftAndRightMovement(){
            if(keyH.rightPressed) x += speed;
            else if(keyH.leftPressed) x -= speed;
    }

    public void manageSpriteAnimation(){
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1) spriteNum = 2;
            else if(spriteNum == 2)spriteNum = 1;
            spriteCounter = 0;
        }
    }

    public void startTheProcessOfJumping(){
        if(!hasJumpedOnce && keyH.upPressed && isStandingOnGround){
            isJumping = true; hasJumpedOnce = true;
        }
        else if(hasJumpedOnce && keyH.upReleased && keyH.upPressed && isStandingOnGround){
            isJumping = true;
            keyH.upPressed = false;
            keyH.upReleased = false;
        }
    }
    public boolean checkIfPlayerHasReachedTheAbyss(){
        return this.y + this.gp.tileSize >= this.gp.screenHeight;
    }

}