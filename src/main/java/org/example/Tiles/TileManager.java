package org.example.Tiles;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;

public class TileManager {
    public GamePanel gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        getTileImage();
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];

        loadMap();


    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/Tiles/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/Tiles/tree.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/Tiles/water.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/Tiles/sand.png"));
            tile[4] = new Tile();
            tile[4].collision = true;
            tile[4].image = ImageIO.read(new File("res/Tiles/wall.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("res/Tiles/earth.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadMap(){
        try{
            File map = new File("res/Maps/map01.txt");
            Scanner reader = new Scanner(map);
            int row = 0;
            int col = 0;
            while(reader.hasNextLine() && col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = reader.nextLine();
                while(col < gp.maxScreenCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            reader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        int row = 0; int col = 0; int x = 0; int y = 0;
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image , x , y, gp.tileSize , gp.tileSize , null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
