import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameEngine extends JPanel implements Runnable {
    private boolean terminal;
    private long prev_time;
    private long cur_time;

    JFrame gameFrame;
    GamePanel gamePanel;

    public GameEngine(int width, int height){
        this.gameFrame = this.createGameCanvas();
        this.gamePanel = new GamePanel(width, height);
        this.prev_time = System.currentTimeMillis();
    }

    public void spawnGameThread(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }


    private JFrame createGameCanvas(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("XmasDash!");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }

    @Override
    public void run() {
        this.gameFrame.add(this.gamePanel);
        this.gameFrame.pack();

        //Game Loop
        while (!terminal){
            this.cur_time = System.currentTimeMillis();
            if (this.cur_time - this.prev_time < 16.67) continue; // 16.67 ms for 60Hz game loop

            this.prev_time = this.cur_time;
        }
    }
}
