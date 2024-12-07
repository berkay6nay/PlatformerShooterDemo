package org.example;
import java.awt.event.KeyEvent;

public class BulletKeyHandlerRed extends BulletKeyHandler {

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_K){
            gunBeingShot = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_K){
            gunBeingShot = false;
        }
    }
}
