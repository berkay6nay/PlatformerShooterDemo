package org.example;

import org.example.Entity.Drop;
import org.example.Entity.Perk;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class PerkManager {

    public BufferedImage lifeUpImage;
    public BufferedImage speedUpImage;
    public BufferedImage armorImage;
    public ArrayList<String> perkTypes;
    public GamePanel gp;
    public ArrayList<Perk> perks;
    public int height;
    public int width;
    public double lastPerkTime;
    public ArrayList<String> perkGenerationRowPlaces;

    public PerkManager(GamePanel gp){
        this.gp = gp;
        perkTypes = new ArrayList<>();
        perkGenerationRowPlaces = new ArrayList<>();
        width = 36;
        height = 36;
        perks = new ArrayList<>();
        loadPerkImages();
        loadPerkTypes();
        loadPerkGenerationRowNames();
    }

    public void generateDrop(){

        double now = System.nanoTime();
        double timePassedSinceLastDropGeneration = checkIfCertainAmountOfTimeHasPassedToGenerateDrop(now);

        if(timePassedSinceLastDropGeneration > 20000000000L && perks.size() <= 2){
            Random random = new Random();
            String randomType = perkTypes.get(random.nextInt(perkTypes.size()));
            String randomRowName = perkGenerationRowPlaces.get(random.nextInt(perkGenerationRowPlaces.size()));

            if(randomRowName.equals("leftBottom")){
                int maxX = 7*gp.tileSize;
                int minX = 2*gp.tileSize;
                int randomY = gp.tileSize*9;
                int randomX = random.nextInt(maxX - minX + 1) + minX;
                BufferedImage perkImage = getImageForRandomPerkType(randomType);
                Perk perk = new Perk(gp , randomType,randomX,randomY,perkImage,width,height);
                perks.add(perk);
                lastPerkTime = now;
            }
            else if(randomRowName.equals("rightBottom")){
                int maxX = 17*gp.tileSize;
                int minX = 12*gp.tileSize;
                int randomY = gp.tileSize*9;
                int randomX = random.nextInt(maxX - minX + 1) + minX;
                BufferedImage perkImage = getImageForRandomPerkType(randomType);
                Perk perk = new Perk(gp , randomType,randomX,randomY,perkImage,width,height);
                perks.add(perk);
                lastPerkTime = now;
            }
            else if(randomRowName.equals("middle")){
                int maxX = 12*gp.tileSize;
                int minX = 7*gp.tileSize;
                int randomY = gp.tileSize*7;
                int randomX = random.nextInt(maxX - minX + 1) + minX;
                BufferedImage perkImage = getImageForRandomPerkType(randomType);
                Perk perk = new Perk(gp , randomType,randomX,randomY,perkImage,width,height);
                perks.add(perk);
                lastPerkTime = now;
            }
        }
    }

    public void loadPerkImages(){
        try{
            lifeUpImage = ImageIO.read(new File("res/Perks/heart.png"));
            speedUpImage = ImageIO.read(new File("res/Perks/speedUp.png"));
            armorImage = ImageIO.read(new File("res/Perks/armor.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadPerkTypes(){
        perkTypes.add("lifeUp");
        perkTypes.add("speedUp");
        perkTypes.add("armor");
    }

    public void loadPerkGenerationRowNames(){
        perkGenerationRowPlaces.add("leftBottom");
        perkGenerationRowPlaces.add("rightBottom");
        perkGenerationRowPlaces.add("middle");
    }

    public double checkIfCertainAmountOfTimeHasPassedToGenerateDrop(Double now){
        if(lastPerkTime == 0){
            lastPerkTime = now;
            return 0;
        }
        return now - lastPerkTime;
    }

    public void update(){
        generateDrop();
        removePerkAfterCertainAmountOfTime();
    }

    public void draw(Graphics2D g2){
        for(Perk perk : perks){
                g2.drawImage(perk.image , perk.x , perk.y , width , height , null);
        }
    }

    public BufferedImage getImageForRandomPerkType(String randomType){
        if(randomType.equals("lifeUp")) return lifeUpImage;
        if(randomType.equals("speedUp")) return speedUpImage;
        if(randomType.equals("armor")) return armorImage;
        return null;
    }

    public void removePerkAfterCertainAmountOfTime(){
        double now = System.nanoTime();
        ArrayList<Perk> perksToRemove = new ArrayList<>();
        for(Perk perk : perks){
            if(now - perk.creationTime >= 10000000000L){
                perksToRemove.add(perk);
            }
        }
        perks.removeAll(perksToRemove);
    }

}
