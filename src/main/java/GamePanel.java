import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    int width;
    int height;
    public int x;
    public int y;
    public int[] obstacleSpawnPoint = {600, 400};

    public final int obstacleSpeed = 3; //todo: change obstacleSpeed name to objSpeed
    private final float obsGenMinDelay = 0.2f;
    private final float obsGenMaxDelay = 0.4f;
    private boolean terminal;
    private long prev_time;
    private long cur_time;
    private int obstacleCounter;
    private final int maxObstacles = 6;
    private double jumpStartTime = 0;
    private double prevRelativeLocation = 0;
    private double curRelativeLocation = 0;


    private Player player;
    private Obstacle obstacle;
    private Landscape landscape;
    Random rand = new Random();
    private boolean paused;

    KeyHandler KH = new KeyHandler();

    public GamePanel(int w, int h){
        this.width = w;
        this.height = h;
        this.x = obstacleSpawnPoint[0];
        this.y = obstacleSpawnPoint[1];
        this.player = new Player();
        this.obstacle = new Obstacle();
        this.landscape = new Landscape();
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (this.x < 10){
            this.x = this.obstacleSpawnPoint[0];
            this.y = this.obstacleSpawnPoint[1];
        }

        g2.drawImage(this.obstacle.treeBufferedImg, this.x, this.y, 50, 50, null);

        Graphics2D playerGraphic2D = (Graphics2D)g;
        playerGraphic2D.drawImage(this.player.getDino1BufferedImage(), this.player.getPosX(),this.player.getPosY(), this.player.getSizeDino(),this.player.getSizeDino(), null);

        Graphics2D scoreGraphics2D = (Graphics2D)g;
        scoreGraphics2D.setColor(Color.darkGray);
        scoreGraphics2D.setFont(new Font("Courier New", Font.BOLD, 30));
        FontMetrics fontMetrics = scoreGraphics2D.getFontMetrics();
        int scorePosX = this.width-fontMetrics.stringWidth(this.player.getScoreStr())-10;
        scoreGraphics2D.drawString(this.player.getScoreStr(), scorePosX, 25);
        this.landscape.paint(g2);
        g2.dispose();
        playerGraphic2D.dispose();
        scoreGraphics2D.dispose();
    }
    
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
        // Game Loop
        while (!terminal) {
            // 16.67 ms for 60Hz game loop
            // Everything else goes under this if
            this.cur_time = System.currentTimeMillis();
            if (this.cur_time - this.prev_time < 16.67 || this.paused)
                continue;
            this.player.increaseScore();
            // Spawns an obstacle with a certain variance when there is room
            // if (this.obstacleCounter < this.maxObstacles) {
            //     this.createAndSpawnObstacle();
            //     this.obstacleCounter += 1;
            // }
            if (KH.spacePressed && KH.spacereleased && !this.player.getIsJumping()){
                this.jumpStartTime = 0;
                this.player.setIsJumping(true);
                MusicHelper.playSound(1);
            }
            if (this.player.getIsJumping()) jump();
                
            x -= this.obstacleSpeed;
            repaint();
            
            this.prev_time = this.cur_time;
        }
    }

    private void jump(){  
        this.jumpStartTime += 0.05;
        this.curRelativeLocation = PhysicsEngine.moveWithForce(jumpStartTime, this.player.getIsJumping());

        int temp = (this.player.getPosY() + -1 * (int) Math.ceil(this.curRelativeLocation));
        if (temp > Player.INITIAL_Y_POS-2){
            this.player.setPosY(Player.INITIAL_Y_POS);
            this.player.setIsJumping(false);
        }
        else{
            this.player.setPosY(temp);
        }

        this.prevRelativeLocation = this.curRelativeLocation;
    }

}

class Obstacle{
   public int x;
   public BufferedImage treeBufferedImg;
   Obstacle(){
       ImageHelper imageHelper = new ImageHelper();
       this.treeBufferedImg = imageHelper.getBufferedImg("src/files/tree.png");
   }
}