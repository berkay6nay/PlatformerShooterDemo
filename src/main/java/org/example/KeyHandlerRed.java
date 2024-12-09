package org.example;
import java.awt.event.KeyEvent;


public class KeyHandlerRed extends KeyHandler {

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = true;
            playerMovingHorizontally = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = true;
            playerMovingHorizontally = true;
        }
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            upReleased = true;
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downReleased = true;
            downPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPressed = false;
            playerMovingHorizontally = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPressed = false;
            playerMovingHorizontally = false;
        }
    }
}
