import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Landscape {
    private final ArrayList<Ground> grounds;
    private final ArrayList<Cloud> clouds;
    private BufferedImage groundBufferedImg;
    private BufferedImage mudBufferedImg;
    private BufferedImage cloudBufferedImg;
    int lastGroundPosX;
    Landscape() {
        ImageHelper imageHelper = new ImageHelper();
        try{
            this.groundBufferedImg = ImageIO.read(Landscape.class.getResource("ground.png"));
            this.mudBufferedImg = ImageIO.read(Landscape.class.getResource("mud.png"));
            this.cloudBufferedImg = ImageIO.read(Landscape.class.getResource("cloud.png"));
        }
        catch(Exception e){
            System.out.println(e);
        }

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
        for(int i = 0; i < 3; i++){
            Cloud cloud = new Cloud(i*200, i*70);
            this.clouds.add(cloud);
        }
        Cloud cloud = new Cloud(600, 70);
        Cloud cloud2 = new Cloud(800, 100);
        this.clouds.add(cloud);
        this.clouds.add(cloud2);

    }

    public void paint(Graphics2D g2){
        g2.drawImage(this.mudBufferedImg, 0, Ground.Y + Ground.SIZE, 900, 200, null);
        for (Ground ground : this.grounds) {
            g2.drawImage(groundBufferedImg, ground.getX(), Ground.Y, Ground.SIZE, Ground.SIZE, null);
        }

        for (Cloud cloud : this.clouds) {
            g2.drawImage(cloudBufferedImg, cloud.getX(), cloud.getY(), Cloud.SIZE, Cloud.SIZE, null);
        }


    }

    public void moveLandscape(int speed){
        int x;

        for(Cloud cloud: this.clouds){
            x = cloud.getX() - speed;
            if(x <= - Cloud.SIZE){
                x = GamePanel.WINDOW_WIDTH;
            }
            cloud.setX(x);
        }
    }

    public ArrayList<Cloud> getClouds() {
        return clouds;
    }

}

