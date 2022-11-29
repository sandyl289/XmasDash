import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Landscape {
    private ArrayList<Ground> grounds;
    private String pathGroundImg = "src/files/ground.png";
    private static BufferedImage GROUND_BUFFERED_IMG;

    Landscape() {
        ImageHelper imageHelper = new ImageHelper();
        this.GROUND_BUFFERED_IMG = imageHelper.getBufferedImg(pathGroundImg);

       //Ground
        this.grounds = new ArrayList<>();
        int posX = 0;
        for (int i = 0; i < 18; i++) {
            Ground ground = new Ground();
            ground.setX(posX);
            this.grounds.add(ground);
            posX += 50;
        }
    }

    public void paint(Graphics2D g2){
        for (Ground ground : this.grounds) {
            g2.drawImage(GROUND_BUFFERED_IMG, ground.getX(), Ground.Y, 50, 50, null);
        }
    }
}

