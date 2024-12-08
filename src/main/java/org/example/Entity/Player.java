package org.example.Entity;
import org.example.Entity.Bullets.Bullet;
import org.example.Entity.Guns.*;
import org.example.GamePanel;
import org.example.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Player extends Entity {
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
    int fallingStartPixel;
    boolean hasFallenOnce;
    int lives;
    public int forceCausedByTheImpactWithBullet;
    int bulletForceDetriment;
    boolean affectedByTheForceOfABullet;
    String directionOfTheForceFromTheBullet;
    int speedWhenJumping;
    public int currentSpeed;


    public void setDefaultValues() {
        baseSpeed = 4;
        fallSpeed = 0;
        maxFallSpeed = 8;
        gravityAcceleration = 1;
        subjectToGravity = true;
        direction = "right";
        jumpSpeed = 20;
        jumpDetrimention= 5;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 48;
        isStandingOnGround = false;
        isJumping = false;
        hasJumpedOnce = false;
        horizontalCollision = false;
        isFalling = false;
        hasFallenOnce = false;
        lives = 50;
        bulletForceDetriment = 4;
        affectedByTheForceOfABullet = false;
        speedWhenJumping = 2;
        currentSpeed = baseSpeed;
    }

    public void update() {


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

    }

    public void manageLeftAndRightMovement(){

            if(keyH.rightPressed){
                direction = "right";
                x += currentSpeed;
            }
            else if(keyH.leftPressed){
                direction = "left";
                x -= currentSpeed;
            }
    }

    public void manageSpriteAnimation(){
        spriteCounter++;
        if(spriteCounter > 8){
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

    }

    public void manageFallWhenSubjectToGravity(boolean subjectToGravity , boolean isFalling){
        if (subjectToGravity || isFalling) {
            y += fallSpeed;
            if(fallSpeed <= maxFallSpeed){
                fallSpeed += gravityAcceleration;

            } else {
                fallSpeed = maxFallSpeed;
            }
        }
    }

    public void manageCollisionWithBullets(){
        for(Bullet bullet : gp.bullets){
            boolean isCollision = gp.collisionChecker.checkCollisionBetweenPlayerAndBullet(this, bullet);
            if(isCollision){
                forceCausedByTheImpactWithBullet = bullet.force;
                directionOfTheForceFromTheBullet = bullet.direction;
                bullet.isActive = false;
                System.out.println("I AM HIT AGGGGHHHHHHHHHHHHH");
                affectedByTheForceOfABullet = true;
            }
        }
    }

    public void manageXPositionWhenAffectedByTheForceOfABullet(){
        if(affectedByTheForceOfABullet){
            boolean isInsideTheBordersAfterImpactWithBullet = gp.collisionChecker.checkIsInsideBordersWhenAffectedByBullet(this);
            if(isInsideTheBordersAfterImpactWithBullet){
                if(directionOfTheForceFromTheBullet.equals("right")) x += forceCausedByTheImpactWithBullet;
                else if(directionOfTheForceFromTheBullet.equals("left")) x -= forceCausedByTheImpactWithBullet;
            }
            forceCausedByTheImpactWithBullet -= bulletForceDetriment;
            if(forceCausedByTheImpactWithBullet == 0) affectedByTheForceOfABullet = false;
        }
    }
}