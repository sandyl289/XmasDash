import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class MusicHelper {
    private static final String BGM_PATH = "src/files/music_zapsplat_christmas_funk.wav"; //Sound from Zapsplat.com
    private static final String SCORE_SOUND_PATH = "src/files/zapsplat_multimedia_beep_bright_87460.wav"; //Sound from Zapsplat.com
    private static final String JUMP_SOUND_PATH = "src/files/zapsplat_multimedia_game_sound_short_beep_earn_point_pick_up_item_002_78374.wav"; //Sound from Zapsplat.com
    private static final String DEAD_SOUND_PATH = "src/files/zapsplat_cartoon_voice_high_pitched_says_ouch_001_15792.wav"; //Sound from Zapsplat.com
    private static final String GAME_OVER_PATH = "src/files/zapsplat_human_male_voice_says_game_over_001_15726.wav"; //Sound from Zapsplat.com

    private MusicHelper() {  //Add private constructor to hide the implicit public one.
        throw new IllegalStateException("Utility class");
    }

    static void playBackgroundMusic() {
        File file = new File(MusicHelper.BGM_PATH);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Plays sounds.
     * @param option: int (0 = SCORE sound; 1 = JUMP sound; 2 = DEAD sound; other = GAME OVER sound
     */
    static void playSound(int option){
        File file;

        if(option == 0){
            file = new File(SCORE_SOUND_PATH);
        } else if (option == 1){
            file = new File(JUMP_SOUND_PATH);
        } else if (option == 2){
            file = new File(DEAD_SOUND_PATH);
        } else {
            file = new File(GAME_OVER_PATH);
        }

        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(+6.0206f); // Reduce volume by 10 decibels.
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
