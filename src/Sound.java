
import java.io.File;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author tatad6701
 */
public class Sound {
    
    private Clip sound;
    
    public Sound(String name) {
        try {
            
            AudioInputStream in = AudioSystem.getAudioInputStream(new File(name));
            sound = AudioSystem.getClip();
            sound.open(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void play() {
        new Thread(new Runnable() {
            public void run() {
                sound.setFramePosition(0);
                sound.start();
            }
        }).start();
    }


    
    
    public void stop(){
        sound.stop();
        sound.setFramePosition(0);
    }
    
    public void loop(){
        new Thread(new Runnable() {
            public void run() {
                sound.setFramePosition(0);
                sound.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }).start();
    }
}
