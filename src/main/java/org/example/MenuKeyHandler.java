package org.example;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MenuKeyHandler implements KeyListener {

    public GamePanel gp;

    public MenuKeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_P && gp.gameState == 1){
            gp.gameState = 0;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
