import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable{

    int width;
    int height;
    public int playerSpawnX;
    public int playerSpawnY;
    public int[] obstacleSpawnPoint = {600, 400};
    ArrayList<Obstacle> activeObs = new ArrayList<>();
    ArrayList<Obstacle> obsToSpawn = new ArrayList<>();
    ArrayList<Obstacle> obsToRemove = new ArrayList<>();
    private final int obstacleSpeed = 3;
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
    private long prev_time_obstacle = 0;
    private long cur_time_obstacle = 0;

    public GamePanel(int w, int h){
        this.width = w;
        this.height = h;
        this.player = new Player();
        this.obstacle = new Obstacle();
        this.landscape = new Landscape();
        this.setPreferredSize(new Dimension(this.width, this.height));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
        this.obsToSpawn.add(this.obstacle);
        this.paused = false;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        //Spawns new obstacles
        for (Obstacle o: this.obsToSpawn){
            this.obsToRemove.add(o);
            o.x = this.obstacleSpawnPoint[0];
            o.y = this.obstacleSpawnPoint[1];
            g2.drawImage(o.treeBufferedImg, o.x, o.y, 50, 50, null);
            this.activeObs.add(o);
        }
        this.obsToSpawn.removeAll(this.obsToRemove);
        this.obsToRemove.removeAll(this.obsToRemove);
        
        for (Obstacle o: this.activeObs){
            g2.drawImage(o.treeBufferedImg, o.x, o.y, 50, 50, null);
        }

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
        this.prev_time_obstacle = System.currentTimeMillis();
        while (!terminal) {
            if (!this.paused){ 
                // 16.67 ms for 60Hz game loop
                // Everything else goes under this if
                this.cur_time = System.currentTimeMillis();
                if (this.cur_time - this.prev_time < 16.67 || this.paused)
                    continue;
                this.player.increaseScore();
    
                if (KH.spacePressed && KH.spacereleased && !this.player.getIsJumping()){
                    this.jumpStartTime = 0;
                    this.player.setIsJumping(true);
                    MusicHelper.playSound(1);
                }
                if (this.player.getIsJumping()) jump();
                
                this.cur_time_obstacle = System.currentTimeMillis();
                if (this.cur_time_obstacle - this.prev_time_obstacle >= (long) ThreadLocalRandom.current().nextInt(3000, 4500 + 1)){
                    this.obsToSpawn.add(new Obstacle());
                    this.prev_time_obstacle = this.cur_time_obstacle;
                }

                for (Obstacle o: this.activeObs){
                    o.x -= this.obstacleSpeed;
                    if (o.x + 50 <= 0){
                        this.obsToRemove.add(o);
                        continue;
                    }
                    if (PhysicsEngine.detectCollision(
                        (float) this.player.getPosX() + (float) this.player.getSizeDino() - 15.0f, (float) this.player.getPosY(),
                      (float) this.player.getPosX() + (float) this.player.getSizeDino() - 15.0f, (float) this.player.getPosY() + (float) this.player.getSizeDino(),
                      (float) o.x + 15f, (float) o.y,
                      (float) o.x + 14.8f, (float) o.y + 50)){
                        MusicHelper.playSound(2);
                        this.paused = true;               
                    }
                }
                this.activeObs.removeAll(this.obsToRemove);
                this.obsToRemove.removeAll(obsToRemove);
    
                repaint();
                
                this.prev_time = this.cur_time;
            }
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
   public int y;
   public BufferedImage treeBufferedImg;
   Obstacle(){
       ImageHelper imageHelper = new ImageHelper();
       this.treeBufferedImg = imageHelper.getBufferedImg("src/files/tree.png");
   }
}