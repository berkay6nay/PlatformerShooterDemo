package org.example;
import org.example.Entity.Drop;
import org.example.Entity.Guns.*;
import org.example.Entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class DropManager {
    ArrayList<String> dropTypes;
    GamePanel gp;
    BufferedImage dropImage;
    double lastDropTime;
    public ArrayList<Drop> drops;
    Integer width;
    Integer height;
    Integer fallSpeed;

    Double[] probabilityList  = {0.15 , 0.3 , 0.4 , 0.5 , 0.62 , 0.73 , 0.85 , 1.0};

    public DropManager(GamePanel gp){
        this.gp = gp;
        dropTypes = new ArrayList<>();
        width = 40;
        height = 40;
        fallSpeed = 10;
        drops = new ArrayList<>();
        loadDropTypes();
        loadDropImages();
    }

    public void generateDrop(){
        double now = System.nanoTime();
        double timePassedSinceLastDropGeneration = checkIfCertainAmountOfTimeHasPassedToGenerateDrop(now);

        if(timePassedSinceLastDropGeneration > 10000000000L && drops.size() <= 2){
            Random random = new Random();
            String randomType = pickRandomGunFromGunList();
            Integer randomX = random.nextInt(gp.screenWidth - 4*width - 3*gp.tileSize + 1) + gp.tileSize * 3;
            Drop drop = new Drop(gp,randomX,randomType,dropImage , width , height);
            drops.add(drop);
            lastDropTime = now;
        }
    }

    public void loadDropImages(){
        try{
            dropImage = ImageIO.read(new File("res/Drops/crate.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        generateDrop();
        removeDropAfterCertainAmountOfTime();
        for(Drop drop : drops){
            if(gp.collisionChecker.checkIfDropSubjectToGravity(drop)){
                drop.y += fallSpeed;
            }
        }
    }

    public void draw(Graphics2D g2){
        if(!drops.isEmpty()){
            for(Drop drop : drops){
                g2.drawImage(drop.image , drop.x , drop.y , width , height , null);
            }
        }
    }

    public void loadDropTypes(){
        dropTypes.add("gun02");
        dropTypes.add("gun03");
        dropTypes.add("gun04");
        dropTypes.add("gun05");
        dropTypes.add("gun06");
        dropTypes.add("gun07");
        dropTypes.add("gun08");
        dropTypes.add("gun09");

    }

    public double checkIfCertainAmountOfTimeHasPassedToGenerateDrop(Double now){
        if(lastDropTime == 0){
            lastDropTime = now;
            return 0;
        }
        return now - lastDropTime;
    }

    public void removeDropAfterCertainAmountOfTime(){
        double now = System.nanoTime();
        ArrayList<Drop> dropsToRemove = new ArrayList<>();
        for(Drop drop : drops){
            if(now - drop.creationTime >= 10000000000L){
                dropsToRemove.add(drop);
            }
        }
        drops.removeAll(dropsToRemove);
    }

    public String pickRandomGunFromGunList(){
        Random rand = new Random();
        double prob = rand.nextDouble();

        for(int i = 0 ; i < dropTypes.size() ; ++i){
            if(probabilityList[i] > prob) return dropTypes.get(i);
        }
        return null;
    }

    public void managePickingUpDropFromGround(Player player){
        ArrayList<Drop> toRemove = new ArrayList<>();
        for(Drop drop : drops){
            boolean collidesWithDrop = gp.collisionChecker.checkCollisionBetweenPlayerAndEntity(player, drop);
            if(collidesWithDrop){
                switch (drop.dropType){
                    case "gun02" :
                        player.gun = new Gun02(gp , player.bulletKeyHandler);
                        break;
                    case "gun03" :
                        player.gun = new Gun03(gp , player.bulletKeyHandler);
                        break;
                    case "gun04" :
                        player.gun = new Gun04(gp , player.bulletKeyHandler);
                        break;
                    case "gun05" :
                        player.gun = new Gun05(gp , player.bulletKeyHandler);
                        break;
                    case "gun06" :
                        player.gun = new Gun06(gp , player.bulletKeyHandler);
                        break;
                    case "gun07" :
                        player.gun = new Gun07(gp , player.bulletKeyHandler);
                        break;
                    case "gun08" :
                        player.gun = new Gun08(gp , player.bulletKeyHandler);
                        break;
                    case "gun09" :
                        player.gun = new Gun09(gp , player.bulletKeyHandler);
                        break;
                }
                toRemove.add(drop);
            }
        }
        drops.removeAll(toRemove);
    }

}
