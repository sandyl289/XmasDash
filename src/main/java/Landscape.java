import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Iterator;

public class Landscape {
    private ArrayList<Ground> grounds;
    private ArrayList<Cloud> clouds;
    private static BufferedImage GROUND_BUFFERED_IMG;
    private static BufferedImage MUD_BUFFERED_IMG;
    private static BufferedImage CLOUD_BUFFERED_IMG;
    int lastGroundPosX;
    Landscape() {
        ImageHelper imageHelper = new ImageHelper();
        this.GROUND_BUFFERED_IMG = imageHelper.getBufferedImg("src/files/ground.png");
        this.MUD_BUFFERED_IMG = imageHelper.getBufferedImg("src/files/mud.png");
        this.CLOUD_BUFFERED_IMG = imageHelper.getBufferedImg("src/files/cloud.png");

       //Ground
        this.grounds = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            Ground ground = new Ground();
            ground.setX(i*Ground.SIZE);
            this.grounds.add(ground);
        }
        lastGroundPosX = 21*Ground.SIZE;

        //Cloud
        this.clouds = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Cloud cloud = new Cloud(i*150, i*50);
            this.clouds.add(cloud);
        }
    }

    public void paint(Graphics2D g2){
        g2.drawImage(this.MUD_BUFFERED_IMG, 0, Ground.Y + Ground.SIZE, 900, 200, null);
        for (Ground ground : this.grounds) {
            g2.drawImage(GROUND_BUFFERED_IMG, ground.getX(), Ground.Y, Ground.SIZE, Ground.SIZE, null);
        }

        for (Cloud cloud : this.clouds) {
            g2.drawImage(CLOUD_BUFFERED_IMG, cloud.getX(), cloud.getY(), Cloud.SIZE, Cloud.SIZE, null);
        }


    }

    public void moveLandscape(int speed){
        int x;
        for(Ground ground: grounds){
            x = ground.getX() - speed;
            if(x <= - Ground.SIZE){
                x = lastGroundPosX;
                lastGroundPosX = x;
            }
            ground.setX(x);
        }
    }
}

