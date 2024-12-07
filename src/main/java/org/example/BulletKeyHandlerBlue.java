package org.example;

import java.awt.event.KeyEvent;

public class BulletKeyHandlerBlue extends BulletKeyHandler{

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            gunBeingShot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            gunBeingShot = false;
        }
    }

}
