import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameEngine extends JPanel implements Runnable {
    private boolean terminal;
    private long prev_time;
    private long cur_time;
    private int obstacleCounter;
    private final int maxObstacles = 6;
    private final float obsGenMinDelay = 0.2f;
    private final float obsGenMaxDelay = 0.4f;
    private boolean paused;
    Random rand;

    JFrame gameFrame;
    GamePanel gamePanel;

    public GameEngine(int width, int height) {
        this.gameFrame = this.createGameCanvas();
        this.gamePanel = new GamePanel(width, height);
        this.prev_time = System.currentTimeMillis();
        this.terminal = false;
        this.paused = false;
        this.obstacleCounter = 0;
        this.rand = new Random();
    }

    public void spawnGameThread() {
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    private JFrame createGameCanvas() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("XmasDash!");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        return window;
    }

    // will probably have to change this to return some sort of obstacle super class
    private void createAndSpawnObstacle() {
        long delay = (long) (this.obsGenMinDelay + (this.obsGenMaxDelay - this.obsGenMinDelay) * rand.nextDouble());
        try {
            Thread.sleep(delay);
        } catch (Exception e) {

        } finally {
        }
        return;
    }

    @Override
    public void run() {
        this.gameFrame.add(this.gamePanel);
        this.gameFrame.pack();

        // Game Loop
        while (!terminal) {
            this.cur_time = System.currentTimeMillis();
            // 16.67 ms for 60Hz game loop
            // Everything else goes under this if
            if (this.cur_time - this.prev_time < 16.67 || this.paused)
                continue;

            // Spawns an obstacle with a certain variance when there is room
            if (this.obstacleCounter < this.maxObstacles) {
                this.createAndSpawnObstacle();
                this.obstacleCounter += 1;
            }

            this.prev_time = this.cur_time;
        }
    }
}
