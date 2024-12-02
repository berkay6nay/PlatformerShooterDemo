package org.example;
import org.example.Entity.Drop;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class DropManager {
    ArrayList<String> dropTypes;
    GamePanel gp;
    BufferedImage imageDefaultGun , imageGun02;
    double lastDropTime;
    public ArrayList<Drop> drops;
    Integer width;
    Integer height;
    Integer fallSpeed;

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
            String randomType = dropTypes.get(random.nextInt(dropTypes.size()));
            Integer randomX = random.nextInt(gp.screenWidth - 3*width - 2*gp.tileSize + 1) + gp.tileSize * 2;
            switch (randomType){
                case "default":
                    Drop drop = new Drop(gp,randomX,randomType,imageDefaultGun , width , height);
                    drops.add(drop);
                    break;
                case "gun02":
                    Drop drop1 = new Drop(gp , randomX , randomType , imageGun02 , width , height);
                    drops.add(drop1);
                    break;
            }
            lastDropTime = now;
        }
    }

    public void loadDropImages(){
        try{
            imageDefaultGun = ImageIO.read(new File("res/Drops/gun_default_drop.png"));
            imageGun02 = ImageIO.read(new File("res/Drops/gun_02_drop.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        generateDrop();
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
        dropTypes.add("default");
        dropTypes.add("gun02");
    }

    public double checkIfCertainAmountOfTimeHasPassedToGenerateDrop(Double now){
        if(lastDropTime == 0){
            lastDropTime = now;
            return 0;
        }
        return now - lastDropTime;
    }
}
