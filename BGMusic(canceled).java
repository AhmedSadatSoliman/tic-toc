package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

javax.sound.sampled.AudioSystem;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main frame = new Main();
                    frame.setVisible(true);
                    PlaySound();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void PlaySound() {

        InputStream in;
        try {
            in = new FileInputStream(new File("Jock_Jams_-_Are_You_Ready_For_This.wav"));
            AudioStream audios = new AudioStream(in);
            AudioPlayer.player.start(audios);
            }
        catch (Exception e)
            {
            JOptionPane.showMessageDialog(null, e);

            }

    }
