package org.example;

import org.example.Entity.Entity;

public class CollisionChecker {
    public GamePanel gp;
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    public boolean checkIfStandingOnGround(Entity entity){
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.width + entity.solidArea.x;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityBottomRow;

        int tileNum1 , tileNum2;

        entityBottomRow = (entityBottomWorldY - 1)/gp.tileSize;
        tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
        return gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision;
    }
}
