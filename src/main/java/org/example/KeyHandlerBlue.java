package org.example;

import java.awt.event.KeyEvent;

public class KeyHandlerBlue extends KeyHandler{

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_S){
            downPressed = true;

            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
            playerMovingHorizontally = true;
            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            playerMovingHorizontally = true;
            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_W){
            upPressed = true;
            System.out.println("Blue Handler Working");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upReleased = true;
            upPressed = false;
            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
            downReleased = true;
            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
            playerMovingHorizontally = false;
            System.out.println("Blue Handler Working");
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
            playerMovingHorizontally = false;
            System.out.println("Blue Handler Working");
        }
    }

}
