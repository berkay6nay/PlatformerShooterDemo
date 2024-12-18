package org.example;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sound {
    Clip clip;
    File[] soundURL = new File[30];

    public Sound(){
        soundURL[0] = new File("res/SoundFX/sniper_attack.wav");
        soundURL[1] = new File("res/SoundFX/lifeUp.wav");
        soundURL[2] = new File("res/SoundFX/hitEffect.wav");
        soundURL[3] = new File("res/SoundFX/bullet02.wav");
        soundURL[4] = new File("res/SoundFX/bullet03.wav");
        soundURL[5] = new File("res/SoundFX/bullet05.wav");
        soundURL[6] = new File("res/SoundFX/defaultBullet.wav");
        soundURL[7] = new File("res/SoundFX/bullet06.wav");
        soundURL[8] = new File("res/SoundFX/bullet07.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

}
