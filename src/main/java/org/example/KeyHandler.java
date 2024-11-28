package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upReleased , downPressed , leftPressed , rightPressed , upPressed , playerMovingHorizontally;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
            playerMovingHorizontally = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
            playerMovingHorizontally = true;
        }
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upReleased = true;
            upPressed = false;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
            playerMovingHorizontally = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
            playerMovingHorizontally = false;
        }
    }
}
