import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;
import java.util.concurrent.ThreadLocalRandom;

public class GamePanel extends JPanel implements Runnable{
    public static final String COURIER_NEW = "Courier New";
    static final int WINDOW_WIDTH = 900; // Window Width
    static final int WINDOW_HEIGHT = 700; // Window Height
    private static final int OBSTACLE_SPAWN_POINT_Y = 400;
    ArrayList<Obstacle> activeObs = new ArrayList<>();
    ArrayList<Obstacle> obsToSpawn = new ArrayList<>();
    ArrayList<Obstacle> obsToRemove = new ArrayList<>();
    private static final int OBJECT_SPEED = 3;  //Speed of objects
    private long prevTime;
    private double jumpStartTime = 0;

    private final Player player;
    private final Landscape landscape;

    KeyHandler kh = new KeyHandler();
    private boolean gameover;
    private int level;
    private int lowerSpawnRate;
    private int upperSpawnRate;

    public GamePanel(){
        this.player = new Player();
        Obstacle obstacle = new Obstacle();
        this.landscape = new Landscape();
        this.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
        this.obsToSpawn.add(obstacle);
        this.gameover = false;
        this.level = 1;
        this.lowerSpawnRate = 2000;
        this.upperSpawnRate = 3500;
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
        this.landscape.moveLandscape(OBJECT_SPEED);
        this.landscape.paint(g2);

        //Score
        g2.setColor(Color.darkGray);
        g2.setFont(new Font(COURIER_NEW, Font.BOLD, 30));
        FontMetrics fontMetrics = g2.getFontMetrics();
        int scorePosX = WINDOW_WIDTH-fontMetrics.stringWidth(this.player.getScoreStr())-10;
        g2.drawString(this.player.getScoreStr(), scorePosX, 25);
        g2.setFont(new Font(COURIER_NEW, Font.BOLD, 20));
        g2.drawString("Space to Jump", scorePosX - 40, 45);
        g2.drawString("P to pause", scorePosX - 40, 65);

        if (this.gameover){
            g2.setFont(new Font(COURIER_NEW, Font.BOLD, 60));
            g2.drawString("GAME OVER!", WINDOW_WIDTH/2 - fontMetrics.stringWidth("GAME OVER!"), WINDOW_HEIGHT/2);
            g2.setFont(new Font(COURIER_NEW, Font.BOLD, 20));
            g2.drawString("Press R to restart", WINDOW_WIDTH/2 - fontMetrics.stringWidth("Press R to restart")/3, WINDOW_HEIGHT/2 + 40);
            MusicHelper.playSound(3);
        }

        g2.dispose();
    }

    @Override
    public void run() {
        long prevTimeObstacle;
        long curTimeObstacle;
        // Game Loop

        prevTimeObstacle = System.currentTimeMillis();
        while (true) {
            if (kh.rPressed && gameover){
                repaint();
                reset();
            }
            if (!kh.pPressed && !gameover){
                // 16.67 ms for 60Hz game loop
                // Everything else goes under this if
                long curTime = System.currentTimeMillis();
                if (curTime - this.prevTime < 16.67)
                    continue;
                this.player.increaseScore();
                if(this.player.getScore() % 1000 == 0){
                    MusicHelper.playSound(0);
                    this.level++;
                }
                else if(this.player.getScore() % 500 == 0){
                    MusicHelper.playSound(0);
                    if (this.lowerSpawnRate >= 600 && this.upperSpawnRate > this.lowerSpawnRate){
                        this.lowerSpawnRate -= 400;
                        this.upperSpawnRate -= 500;
                    }
                }
    
                if (kh.spacePressed && kh.spacereleased && !this.player.getIsJumping()){
                    this.jumpStartTime = 0;
                    this.player.setIsJumping(true);
                    MusicHelper.playSound(1);
                }
                if (this.player.getIsJumping()) jump();
                
                curTimeObstacle = System.currentTimeMillis();
                if (curTimeObstacle - prevTimeObstacle >= ThreadLocalRandom.current().nextInt(this.lowerSpawnRate, this.upperSpawnRate + 1)){
                    this.obsToSpawn.add(new Obstacle());
                    prevTimeObstacle = curTimeObstacle;
                }

                for (Obstacle o: this.activeObs){
                    o.x -= this.OBJECT_SPEED * this.level;
                    if (o.x + 50 <= 0){
                        this.obsToRemove.add(o);
                        continue;
                    }
                    checkCollisions(o);
                }
                this.activeObs.removeAll(this.obsToRemove);
                this.obsToRemove.removeAll(obsToRemove);
                repaint();
                this.prevTime = curTime;
            }
        }
    }

    private void jump(){  
        this.jumpStartTime += 0.05;
        double curRelativeLocation = PhysicsEngine.moveWithForce(jumpStartTime, this.player.getIsJumping());

        int temp = (this.player.getPosY() + -1 * (int) Math.ceil(curRelativeLocation));
        if (temp > Player.INITIAL_Y_POS-2){
            this.player.setPosY(Player.INITIAL_Y_POS);
            this.player.setIsJumping(false);
        } else{
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
        this.kh.rPressed = false;
        this.level = 1;
        this.lowerSpawnRate = 2000;
        this.upperSpawnRate = 3500;
    }

    private void checkCollisions(Obstacle o){
        final boolean detectCollision1 = PhysicsEngine.detectCollision(
                (float) this.player.getPosX() + (float) Player.SIZE_DINO - 15.0f, this.player.getPosY(),
                (float) this.player.getPosX() + (float) Player.SIZE_DINO - 15.0f, (float) this.player.getPosY() + (float) Player.SIZE_DINO,
                o.x + 15f, o.y,
                o.x + 14.8f, (float) o.y + 50);

        final boolean detectCollision2 = PhysicsEngine.detectCollision(
                this.player.getPosX() + 15f, (float) this.player.getPosY() + (float) Player.SIZE_DINO,
                this.player.getPosX() - 15f + Player.SIZE_DINO, (float) this.player.getPosY() + (float) Player.SIZE_DINO - 0.5f,
                o.x + 15f, o.y,
                o.x + 14.8f, (float) o.y + 50);
        if (detectCollision1 || detectCollision2){
            MusicHelper.playSound(2);
            this.gameover = true;     
        }
    }
}


class Obstacle{
   public int x;
   public int y;
   public BufferedImage treeBufferedImg;
   Obstacle(){
       ImageHelper imageHelper = new ImageHelper();
       this.treeBufferedImg = imageHelper.getBufferedImg("tree.png");
   }
}