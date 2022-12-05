import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable{
    static int WINDOW_WIDTH = 900; // Window Width
    static int WINDOW_HEIGHT = 700; // Window Height
    private final int OBSTACLE_SPAWN_POINT_Y = 400;
    ArrayList<Obstacle> activeObs = new ArrayList<>();
    ArrayList<Obstacle> obsToSpawn = new ArrayList<>();
    ArrayList<Obstacle> obsToRemove = new ArrayList<>();
    private final int objSpeed = 3;  //Speed of objects
    private long prev_time;
    private long cur_time;
    private double jumpStartTime = 0;
    private double curRelativeLocation = 0;
    
    private final Player player;
    private final Obstacle obstacle;
    private final Landscape landscape;
    private boolean paused;

    KeyHandler KH = new KeyHandler();
    private boolean gameover;

    public GamePanel(){

        this.player = new Player();
        this.obstacle = new Obstacle();
        this.landscape = new Landscape();
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(KH);
        this.setFocusable(true);
        this.obsToSpawn.add(this.obstacle);
        this.gameover = false;
        Thread gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Spawns new obstacles
        for (Obstacle o: this.obsToSpawn){
            this.obsToRemove.add(o);

            o.x = WINDOW_WIDTH;
            o.y = OBSTACLE_SPAWN_POINT_Y;
            g2.drawImage(o.treeBufferedImg, o.x, o.y, 50, 50, null);
            this.activeObs.add(o);
        }
        this.obsToSpawn.removeAll(this.obsToRemove);
        this.obsToRemove.removeAll(this.obsToRemove);
        
        for (Obstacle o: this.activeObs){
            g2.drawImage(o.treeBufferedImg, o.x, o.y, 50, 50, null);
        }

        //Dino
        g2.drawImage(this.player.getDino1BufferedImage(), this.player.getPosX(),this.player.getPosY(), Player.SIZE_DINO,Player.SIZE_DINO, null);
        
        //Landscape
        this.landscape.moveLandscape(objSpeed);
        this.landscape.paint(g2);

        //Score
        g2.setColor(Color.darkGray);
        g2.setFont(new Font("Courier New", Font.BOLD, 30));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int scorePosX = WINDOW_WIDTH-fontMetrics.stringWidth(this.player.getScoreStr())-10;
        g2.drawString(this.player.getScoreStr(), scorePosX, 25);

        if (this.gameover){
            g2.setFont(new Font("Courier New", Font.BOLD, 60));
            g2.drawString("GAME OVER!!!", WINDOW_WIDTH/2 - fontMetrics.stringWidth("GAME OVER!"), WINDOW_HEIGHT/2);
            g2.setFont(new Font("Courier New", Font.BOLD, 20));
            g2.drawString("Press R to restart", WINDOW_WIDTH/2 - fontMetrics.stringWidth("Press R to restart")/3, WINDOW_HEIGHT/2 + 40);
            MusicHelper.playSound(3);
        }

        g2.dispose();
    }

    @Override
    public void run() {
        long prev_time_obstacle = 0;
        long cur_time_obstacle = 0;
        // Game Loop
        prev_time_obstacle = System.currentTimeMillis();
        while (true) {
            if (KH.rPressed && gameover){
                repaint();
                reset();
            }
            if (!KH.pPressed && !gameover){ 
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
                
                cur_time_obstacle = System.currentTimeMillis();
                if (cur_time_obstacle - prev_time_obstacle >= (long) ThreadLocalRandom.current().nextInt(3000, 4500 + 1)){
                    this.obsToSpawn.add(new Obstacle());
                    prev_time_obstacle = cur_time_obstacle;
                }

                for (Obstacle o: this.activeObs){
                    o.x -= this.objSpeed;
                    if (o.x + 50 <= 0){
                        this.obsToRemove.add(o);
                        continue;
                    }
                    if (PhysicsEngine.detectCollision(
                        (float) this.player.getPosX() + (float) Player.SIZE_DINO - 15.0f, (float) this.player.getPosY(),
                      (float) this.player.getPosX() + (float) Player.SIZE_DINO - 15.0f, (float) this.player.getPosY() + (float) Player.SIZE_DINO,
                      (float) o.x + 15f, (float) o.y,
                      (float) o.x + 14.8f, (float) o.y + 50))
                      {
                        MusicHelper.playSound(2);
                        this.gameover = true;     
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
    }

    private void reset(){
        for (Obstacle o: this.activeObs){
            this.obsToRemove.add(o);
        }
        this.activeObs.removeAll(this.obsToRemove);
        this.obsToRemove.removeAll(this.obsToRemove);
        this.player.resetScore();
        this.gameover = false;
        this.KH.rPressed = false;
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