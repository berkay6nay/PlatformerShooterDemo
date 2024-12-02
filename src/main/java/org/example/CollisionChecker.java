package org.example;
import org.example.Entity.Drop;
import org.example.Entity.Player;

public class CollisionChecker {
    public GamePanel gp;
    public int entityLeftWorldX;
    public int entityRightWorldX;
    public int entityTopWorldY;
    public int entityBottomWorldY;
    public int entityLeftCol;
    public int entityRightCol;
    public int entityTopRow;
    public int entityBottomRow;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void calculateCollisionValues(Player entity){
        entityLeftWorldX = entity.x + entity.solidArea.x;
        entityRightWorldX = entity.x + entity.solidArea.width + entity.solidArea.x;
        entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;
        entityTopWorldY = entity.y + entity.solidArea.y;
        entityLeftCol = entityLeftWorldX/gp.tileSize;
        entityRightCol = entityRightWorldX/gp.tileSize;
        entityTopRow = entityTopWorldY/gp.tileSize;
        entityBottomRow = entityBottomWorldY/gp.tileSize;
    }

    public boolean checkIfStandingOnGround(Player entity){

        calculateCollisionValues(entity);
        int entityBottomRow;
        int tileNum1 , tileNum2;

        entityBottomRow = (entityBottomWorldY - 1)/gp.tileSize;

        tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
        tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
        return gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision;

    }

    public boolean checkCollisionHorizontally(Player entity){
        calculateCollisionValues(entity);
        int tileNum1 , tileNum2;
            if(entity.direction.equals("left")){
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                return gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision;
            }
            else if(entity.direction.equals("right")){
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                return gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision;
            }
        return false;
    }

    public boolean isInsideTheBordersOfMap(Player player){
        if(player.direction.equals("right")) return player.x + player.gp.tileSize + player.speed < player.gp.screenWidth;
        else return player.x + player.speed > 0;
    }

    public boolean checkIfDropSubjectToGravity(Drop drop){
            int tileNum1 , tileNum2;
            entityLeftCol = drop.x / gp.tileSize;
            entityRightCol = (drop.x + drop.width) / gp.tileSize;
            entityBottomRow = (drop.y + drop.height - 1) / gp.tileSize;

            tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];

        return !(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision);
    }

    public boolean checkCollisionBetweenPlayerAndDrop(Player player , Drop drop){
        return player.x + player.solidArea.x < drop.x + drop.width && player.x + player.solidArea.x + player.solidArea.width > drop.x
                && player.y + player.solidArea.y < drop.y + drop.height && player.y + player.solidArea.y + player.solidArea.height > drop.y;
    }
}
