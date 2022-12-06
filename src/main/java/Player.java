import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;

public class Player {
    private int score = 0;
    private static final int POS_X = 20;
    private int posY;
    private BufferedImage dino1BufferedImage;
    public static final int SIZE_DINO = 70;
    public static final int INITIAL_Y_POS = 382;
    private boolean isJumping = false;

    Player(){
        URL pathDinoImg = Player.class.getResource("dino1.png");
        try{

            this.dino1BufferedImage = ImageIO.read(pathDinoImg);
        }
        catch(Exception e){
            System.out.println(e);
        }
        this.posY = INITIAL_Y_POS;
    }

    public BufferedImage getDino1BufferedImage() {
        return dino1BufferedImage;
    }

    public String getScoreStr(){
        return this.score + " pts";
    }
    public void increaseScore() {
        this.score++;
    }
    public void resetScore(){
        this.score = 0;
    }
    public int getScore(){
        return this.score;
    }

    public int getPosX() {
        return POS_X;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
    public boolean getIsJumping(){
        return this.isJumping;
    }
    public void setIsJumping(boolean jumping){
        this.isJumping = jumping;
    }
}
