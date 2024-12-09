package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class KeyHandler implements KeyListener {

    public boolean   downPressed , leftPressed , rightPressed , upPressed , playerMovingHorizontally , upReleased;
    public boolean downReleased = true;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
