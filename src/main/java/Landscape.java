import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Landscape {
    private ArrayList<Ground> grounds;
    private static BufferedImage GROUND_BUFFERED_IMG;
    private static BufferedImage MUD_BUFFERED_IMG;
    
    Landscape() {
        ImageHelper imageHelper = new ImageHelper();
        this.GROUND_BUFFERED_IMG = imageHelper.getBufferedImg("src/files/ground.png");
        this.MUD_BUFFERED_IMG = imageHelper.getBufferedImg("src/files/mud.png");

       //Ground
        this.grounds = new ArrayList<>();
        int posX = 0;
        for (int i = 0; i < 19; i++) {
            Ground ground = new Ground();
            ground.setX(i*Ground.SIZE);
            this.grounds.add(ground);
        }
    }

    public void paint(Graphics2D g2){
        g2.drawImage(this.MUD_BUFFERED_IMG, 0, Ground.Y +Ground.SIZE, 900, 200, null);
        for (Ground ground : this.grounds) {
            g2.drawImage(GROUND_BUFFERED_IMG, ground.getX(), Ground.Y, Ground.SIZE, Ground.SIZE, null);
        }
    }

}

