package org.example;

import java.awt.event.KeyEvent;

public class KeyHandlerBlue extends KeyHandler{

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_A){
            leftPressed = true;
            playerMovingHorizontally = true;
        }

        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
            playerMovingHorizontally = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_S){
            downReleased = true;
            downPressed = false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = false;
            playerMovingHorizontally = false;
        }


        if(code == KeyEvent.VK_W){
            upReleased = true;
            upPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
            playerMovingHorizontally = false;
        }
    }

}
