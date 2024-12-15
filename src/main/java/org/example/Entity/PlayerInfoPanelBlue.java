package org.example.Entity;
import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PlayerInfoPanelBlue {

    public int lives;
    public GamePanel gp;
    public int panelX;
    public int panelY;
    public int panelWidth;
    public int panelHeight;
    public Player player;

    BufferedImage heartImage;
    BufferedImage playerImage;



    public PlayerInfoPanelBlue(Player player , GamePanel gp){
        lives = player.lives;

        this.player = player;

        panelWidth = 2*gp.tileSize;
        panelHeight = 2*gp.tileSize;
        panelX = gp.tileSize;
        panelY = 13*gp.tileSize;
        this.gp = gp;
        getImages();
    }


    public void drawPlayerPanel(Graphics2D g2){
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(panelX , panelY , panelWidth , panelHeight , 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(panelX + 5 , panelY + 5, panelWidth - 10 , panelHeight-10 , 25 ,25);

        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(16F));
        String lives = Integer.toString(player.lives);
        String ammo = Integer.toString(player.gun.currentBulletNumber);
        BufferedImage gunImage = player.gun.right;



        int textX = panelX + 10;
        int textY = panelY + 10;

        g2.drawImage(playerImage , textX , textY , 24 , 24,  null);

        g2.drawImage(heartImage , textX , textY + 24 , 24 ,24 , null);

        int tailX = panelX + panelWidth - 26;

        textX = getXToAlignRight(lives , tailX , g2);
        g2.drawString(lives, textX , textY+40);

        textX = panelX + 10;

        g2.drawImage(gunImage , textX , textY + 48 , 24,18,null);

        textX = getXToAlignRight(ammo , tailX , g2);
        g2.drawString(ammo , textX , textY  + 64);

    }
    public int getXToAlignRight(String text , int tailX , Graphics2D g2){
        int length = (int)g2.getFontMetrics().getStringBounds(text , g2).getWidth();
        return tailX - length;
    }

    public void getImages(){
        try {
            heartImage = ImageIO.read(new File("res/Perks/heart.png"));
            playerImage = ImageIO.read(new File("res/Player/boy_right_1.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
