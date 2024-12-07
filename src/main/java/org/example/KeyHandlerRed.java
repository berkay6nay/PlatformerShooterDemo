package org.example;
import java.awt.event.KeyEvent;


public class KeyHandlerRed extends KeyHandler {

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;

            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
            playerMovingHorizontally = true;
            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
            playerMovingHorizontally = true;
            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_UP){
            upPressed = true;
            System.out.println("Red Handler Working");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            upReleased = true;
            upPressed = false;
            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
            downReleased = true;
            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
            playerMovingHorizontally = false;
            System.out.println("Red Handler Working");
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
            playerMovingHorizontally = false;
            System.out.println("Red Handler Working");
        }
    }
}
