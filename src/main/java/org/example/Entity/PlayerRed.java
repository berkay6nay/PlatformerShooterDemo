package org.example.Entity;

import org.example.Entity.Guns.*;
import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class PlayerRed extends Player{

    public PlayerRed(GamePanel gp , KeyHandler kH , Gun gun){
        this.gp = gp;
        this.keyH = kH;
        this.x = 600;
        this.y = 50;
        this.direction = "left";
        setDefaultValues();
        getPlayerImage();
        this.gun = gun;
    }

    @Override
    public void update(){
        boolean theAbyss = checkIfPlayerHasReachedTheAbyss();

        if(!theAbyss){
            isStandingOnGround = gp.collisionChecker.checkIfStandingOnGround(this);

            isFalling = (keyH.downPressed && isStandingOnGround) || (keyH.downReleased && isStandingOnGround);

            if(isStandingOnGround & !isFalling) fallingStartPixel =  y + gp.tileSize;

            subjectToGravity = !gp.collisionChecker.checkIfStandingOnGround(this);

            horizontalCollision = gp.collisionChecker.checkCollisionHorizontally(this);
            isInsideTheBorders = gp.collisionChecker.isInsideTheBordersOfMap(this);

            managePickingUpGunFromGround();

            if(isStandingOnGround & !isFalling){
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
            manageFallWhenSubjectToGravity(subjectToGravity , isFalling);

            if(isFalling){
                if(y + gp.tileSize  - fallingStartPixel >= 50){
                    isFalling = false;
                    keyH.downReleased = false;

                    hasFallenOnce = true;
                }
            }
            manageCollisionWithBullets();
        }

        else{
            System.out.println("It is a dreadful thing to fall into the hands of the living god.");

            lives -= 1;

            if(y >= 3000){
                x = 360;
                y = 0;
                keyH.downReleased = false;
            }
            else{
                this.y  += 50;
            }
        }

        if(gun.currentBulletNumber == 0){
            this.gun = new GunDefault(gp , gp.bulletKeyHandlerRed);
        }

        if(keyH.playerMovingHorizontally){
            manageSpriteAnimation();
        }
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

    @Override
    public void managePickingUpGunFromGround(){
        ArrayList<Drop> toRemove = new ArrayList<>();
        for(Drop drop : gp.dropManager.drops){
            boolean collidesWithDrop = gp.collisionChecker.checkCollisionBetweenPlayerAndDrop(this, drop);
            if(collidesWithDrop){
                switch (drop.dropType){
                    case "default":
                        this.gun = new GunDefault(gp,gp.bulletKeyHandlerRed);
                        break;
                    case "gun02" :
                        this.gun = new Gun02(gp , gp.bulletKeyHandlerRed);
                        break;
                    case "gun03" :
                        this.gun = new Gun03(gp , gp.bulletKeyHandlerRed);
                        break;
                    case "gun04" :
                        this.gun = new Gun04(gp , gp.bulletKeyHandlerRed);
                        break;
                }
                toRemove.add(drop);
            }
        }
        gp.dropManager.drops.removeAll(toRemove);
    }
}
