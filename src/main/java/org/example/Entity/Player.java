package org.example.Entity;
import org.example.Armor;
import org.example.BulletKeyHandler;
import org.example.Entity.Bullets.Bullet;
import org.example.Entity.Guns.*;
import org.example.GamePanel;
import org.example.KeyHandler;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Player extends Entity {
    public GamePanel gp;
    public KeyHandler keyH;
    boolean subjectToGravity;
    int fallSpeed;
    int maxFallSpeed;
    int gravityAcceleration;
    public int jumpSpeed;
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
    BulletKeyHandler bulletKeyHandler;
    boolean insideTheBordersAfterUpwardMovement;
    int spawningX;
    Armor armor;
    int baseSpeedWhenSpeedBoost;
    boolean isSpeedBoostActive;
    double lastSpeedBoostTime;
    int baseSpeedWhenNotSpeedBoost;

    public void setDefaultValues() {
        baseSpeed = 4;
        baseSpeedWhenNotSpeedBoost = 4;
        baseSpeedWhenSpeedBoost = 7;
        fallSpeed = 0;
        maxFallSpeed = 8;
        gravityAcceleration = 1;
        subjectToGravity = true;
        jumpSpeed = 20;
        jumpDetrimention= 5;
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 0;
        solidArea.width = 32;
        solidArea.height = 48;
        lives = 50;
        bulletForceDetriment = 2;
        speedWhenJumping = 2;
        currentSpeed = baseSpeed;
        armor = new Armor(this , "spawnArmor");
    }

    public void update() {
        boolean theAbyss = checkIfPlayerHasReachedTheAbyss();

        if(!theAbyss){
            isStandingOnGround = gp.collisionChecker.checkIfStandingOnGround(this);

            if(isSpeedBoostActive) baseSpeed = baseSpeedWhenSpeedBoost;
            else baseSpeed = baseSpeedWhenNotSpeedBoost;

            startTheProcessOfFalling();

            if(isStandingOnGround & !isFalling) fallingStartPixel =  y + gp.tileSize;

            subjectToGravity = !gp.collisionChecker.checkIfStandingOnGround(this);

            horizontalCollision = gp.collisionChecker.checkCollisionHorizontally(this);


            managePickingUpGunFromGround();
            manageCollisionWithPerks();


            if(isStandingOnGround & !isFalling){
                fallSpeed = 0;
            }

            startTheProcessOfJumping();

            if (isJumping) {

                subjectToGravity = false;

                currentSpeed = speedWhenJumping;

                manageLeftAndRightMovement();

                insideTheBordersAfterUpwardMovement = gp.collisionChecker.isInsideTheMapAfterUpwardMovement(this);

                if(insideTheBordersAfterUpwardMovement){
                    manageLeftAndRightMovement();
                    y -= jumpSpeed;
                    jumpSpeed -= jumpDetrimention;
                }

                if(jumpSpeed == 0 || !insideTheBordersAfterUpwardMovement){
                    subjectToGravity = true;
                    isJumping = false;
                    jumpSpeed = 30;
                }

            } else currentSpeed = baseSpeed;

            if(keyH.rightPressed){
                direction = "right";
            }
            else if(keyH.leftPressed){
                direction = "left";
            }

            isInsideTheBorders = gp.collisionChecker.isInsideTheBordersOfMap(this);

            if(!isStandingOnGround && !horizontalCollision && isInsideTheBorders){
                manageLeftAndRightMovement();
            } else if (isStandingOnGround) {
                manageLeftAndRightMovement();
            }

            manageFallWhenSubjectToGravity(subjectToGravity , isFalling);

            if(isFalling){
                if(y + gp.tileSize  - fallingStartPixel >= gp.tileSize){
                    isFalling = false;
                }
            }

            manageArmor();
            manageSpeedBoost();
            manageCollisionWithBullets();
            manageXPositionWhenAffectedByTheForceOfABullet();
        }

        else{
            forceCausedByTheImpactWithBullet = 0;
            if(y >= 8000){
                lives -= 1;
                x = spawningX;
                this.armor = new Armor(this , "spawnArmor");
                y = 0;
                this.gun = new GunDefault(gp ,bulletKeyHandler);
                keyH.downReleased = false;
            }
            else{
                this.y  += 50;
            }
        }

        if(gun.currentBulletNumber == 0 && !gun.type.equals("default")){
            this.gun = new GunDefault(gp , bulletKeyHandler);
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
        armor.draw(g2);
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

    public void startTheProcessOfFalling(){
        if(!hasFallenOnce && keyH.downPressed && isStandingOnGround){
            isFalling = true; hasFallenOnce = true;
        }
        else if(hasFallenOnce && keyH.downReleased && keyH.downPressed && isStandingOnGround){
            isFalling = true;
            keyH.downPressed = false;
            keyH.downReleased = false;
        }
    }

    public boolean checkIfPlayerHasReachedTheAbyss(){
        return this.y + this.gp.tileSize >= this.gp.screenHeight;
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
            if(isCollision && !armor.isActive){
                forceCausedByTheImpactWithBullet = bullet.force;
                directionOfTheForceFromTheBullet = bullet.direction;
                bullet.isActive = false;
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

    public void managePickingUpGunFromGround(){
        ArrayList<Drop> toRemove = new ArrayList<>();
        for(Drop drop : gp.dropManager.drops){
            boolean collidesWithDrop = gp.collisionChecker.checkCollisionBetweenPlayerAndDrop(this, drop);
            if(collidesWithDrop){
                switch (drop.dropType){
                    case "gun02" :
                        this.gun = new Gun02(gp , this.bulletKeyHandler);
                        break;
                    case "gun03" :
                        this.gun = new Gun03(gp , this.bulletKeyHandler);
                        break;
                    case "gun04" :
                        this.gun = new Gun04(gp , this.bulletKeyHandler);
                        break;
                    case "gun05" :
                        this.gun = new Gun05(gp , this.bulletKeyHandler);
                        break;
                    case "gun06" :
                        this.gun = new Gun06(gp , this.bulletKeyHandler);
                        break;
                    case "gun07" :
                        this.gun = new Gun07(gp , this.bulletKeyHandler);
                        break;
                }
                toRemove.add(drop);
            }
        }
        gp.dropManager.drops.removeAll(toRemove);
    }

    public void manageCollisionWithPerks(){
        ArrayList<Perk> toRemove = new ArrayList<>();
        for(Perk perk : gp.perkManager.perks){
            boolean collidesWithPerk = gp.collisionChecker.checkCollisionBetweenPlayerAndPerk(this,perk);
            if(collidesWithPerk){
                switch (perk.type){
                    case "lifeUp" :
                        lives += 1;
                        break;
                    case "speedUp" :
                        if(!isSpeedBoostActive){
                            lastSpeedBoostTime = System.nanoTime();
                            isSpeedBoostActive = true;
                        }
                        break;
                    case "armor" :
                        armor = new Armor(this, "perkArmor");
                        break;
                }
                toRemove.add(perk);
            }
        }
        gp.perkManager.perks.removeAll(toRemove);
    }

    public void manageArmor(){
        double now = System.nanoTime();
        armor.update(this);
        if(now - armor.activationTime >= 2000000000L && armor.type.equals("spawnArmor")){
            armor.isActive = false;
        }
        if(now - armor.activationTime >= 7000000000L && armor.type.equals("perkArmor")){
            armor.isActive = false;
        }
    }

    public void manageSpeedBoost(){
        double now = System.nanoTime();
        if(isSpeedBoostActive && now - lastSpeedBoostTime >= 5000000000L){
            isSpeedBoostActive = false;
        }
    }
}