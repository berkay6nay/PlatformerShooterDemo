package org.example;
import org.example.Entity.Perk;
import org.example.Entity.Player;
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

    Double[] probabilitiesList = {0.2,0.6,1.0};

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
            String randomType = pickRandomPerkFromGunList();
            String randomRowName = perkGenerationRowPlaces.get(random.nextInt(perkGenerationRowPlaces.size()));

            switch (randomRowName) {
                case "leftBottom" -> {
                    int maxX = 6 * gp.tileSize;
                    int minX = 3 * gp.tileSize;
                    int randomY = gp.tileSize * 9;
                    int randomX = random.nextInt(maxX - minX + 1) + minX;
                    BufferedImage perkImage = getImageForRandomPerkType(randomType);
                    Perk perk = new Perk(gp, randomType, randomX, randomY, perkImage, width, height);
                    perks.add(perk);
                    lastPerkTime = now;
                }
                case "rightBottom" -> {
                    int maxX = 16 * gp.tileSize;
                    int minX = 12 * gp.tileSize;
                    int randomY = gp.tileSize * 9;
                    int randomX = random.nextInt(maxX - minX + 1) + minX;
                    BufferedImage perkImage = getImageForRandomPerkType(randomType);
                    Perk perk = new Perk(gp, randomType, randomX, randomY, perkImage, width, height);
                    perks.add(perk);
                    lastPerkTime = now;
                }
                case "middle" -> {
                    int maxX = 12 * gp.tileSize;
                    int minX = 7 * gp.tileSize;
                    int randomY = gp.tileSize * 7;
                    int randomX = random.nextInt(maxX - minX + 1) + minX;
                    BufferedImage perkImage = getImageForRandomPerkType(randomType);
                    Perk perk = new Perk(gp, randomType, randomX, randomY, perkImage, width, height);
                    perks.add(perk);
                    lastPerkTime = now;
                }
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
        return switch (randomType) {
            case "lifeUp" -> lifeUpImage;
            case "speedUp" -> speedUpImage;
            case "armor" -> armorImage;
            default -> null;
        };
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

    public void managePickingUpPerk(Player player){
        ArrayList<Perk> toRemove = new ArrayList<>();
        for(Perk perk : perks){
            boolean collidesWithPerk = gp.collisionChecker.checkCollisionBetweenPlayerAndEntity(player,perk);
            if(collidesWithPerk){
                switch (perk.type){
                    case "lifeUp" :
                        player.lives += 1;
                        gp.playSoundFX(1);
                        break;
                    case "speedUp" :
                        if(!player.isSpeedBoostActive){
                            player.lastSpeedBoostTime = System.nanoTime();
                            player.isSpeedBoostActive = true;
                        }
                        break;
                    case "armor" :
                        player.armor = new Armor(player, "perkArmor");
                        break;
                }
                toRemove.add(perk);
            }
        }
        perks.removeAll(toRemove);
    }

    public String pickRandomPerkFromGunList(){
        Random rand = new Random();
        double prob = rand.nextDouble();

        for(int i = 0 ; i < perkTypes.size() ; ++i){
            if(probabilitiesList[i] > prob) return perkTypes.get(i);
        }
        return null;
    }


}
