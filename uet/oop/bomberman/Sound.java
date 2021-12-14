package uet.oop.bomberman;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public static void playSound(String fileName) {


        try {
            String path = ("src/uet/oop/bomberman/res/sound/" + fileName + ".wav");
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    uet/oop/bomberman/res/sound/dropPodLeaving.wav
    public static void loopSound(String fileName) {
        String path = ("src/uet/oop/bomberman/res/sound/" + fileName + ".wav");

        try {
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);

            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//    public static void no(){//nổ
//        playSound("Mortar_Explode_Incendiary_1a-resources.assets-3848");
//    }

    public static void playerDead() {
        playSound("Spawn-resources.assets-2428");
    }

    public static void getItem() {
        playSound("beepSmall");
    }

//    public static void enemyDead1() {
//        playSound("Pawn_Megascarab_Wounded_1c-resources.assets-3996");
//    }

    public static void ThemeSound() {

        loopSound("burningMemory");
    }
}
