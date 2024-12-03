package org.example.Entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Player extends Entity {
    public GamePanel gp;
    public KeyHandler keyH;
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
    boolean isInsideTheBorders;
    boolean isFalling;
    public Gun gun;
    public Player(GamePanel gp, KeyHandler keyH , Gun gun) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        this.gun = gun;
    }

    public void setDefaultValues() {
        x = 300;
        y = 50;
        speed = 5;
        fallSpeed = 0;
        maxFallSpeed = 10;
        gravityAcceleration = 1;
        subjectToGravity = true;
        direction = "right";
        jumpSpeed = 20;
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
        isFalling = false;
    }

    public void update() {

        boolean theAbyss = checkIfPlayerHasReachedTheAbyss();

        if(!theAbyss){
            subjectToGravity = !gp.collisionChecker.checkIfStandingOnGround(this);

            isStandingOnGround = gp.collisionChecker.checkIfStandingOnGround(this);
            horizontalCollision = gp.collisionChecker.checkCollisionHorizontally(this);
            isInsideTheBorders = gp.collisionChecker.isInsideTheBordersOfMap(this);

            isFalling = keyH.downPressed && isStandingOnGround;

            managePickingUpGunFromGround();

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



            if(isStandingOnGround || (!horizontalCollision && isInsideTheBorders)){
                manageLeftAndRightMovement();
            }
            manageFallWhenSubjectToGravity(subjectToGravity);
        }

        else{
            System.out.println("It is a dreadful thing to fall into the hands of the living god.");
            this.x = 500;
            this.y  = 0;
        }

        if(keyH.playerMovingHorizontally){
            manageSpriteAnimation();
        }

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
        g2.drawImage(image , x,y,gp.tileSize , gp.tileSize , null);
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
            if(keyH.rightPressed){
                direction = "right";
                x += speed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
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

    public void managePickingUpGunFromGround(){
        ArrayList<Drop> toRemove = new ArrayList<>();
        for(Drop drop : gp.dropManager.drops){
            boolean collidesWithDrop = gp.collisionChecker.checkCollisionBetweenPlayerAndDrop(this, drop);
            if(collidesWithDrop){
                switch (drop.dropType){
                    case "default":
                        this.gun = new GunDefault(gp,gp.bulletKeyHandler);
                        break;
                    case "gun02" :
                        this.gun = new Gun02(gp , gp.bulletKeyHandler);
                        break;
                    case "gun03" :
                        this.gun = new Gun03(gp , gp.bulletKeyHandler);
                        break;
                    case "gun04" :
                        this.gun = new Gun04(gp , gp.bulletKeyHandler);
                        break;
                }
                toRemove.add(drop);
            }
        }
        gp.dropManager.drops.removeAll(toRemove);
    }
    public void manageFallWhenSubjectToGravity(boolean subjectToGravity){
        if (subjectToGravity) {
            y += fallSpeed;
            if(fallSpeed <= maxFallSpeed){
                fallSpeed += gravityAcceleration;

            } else {
                fallSpeed = maxFallSpeed;
            }
        }
    }

}