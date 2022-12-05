import java.awt.image.BufferedImage;

public class Player {
    private int score = 0;
    private final int posX = 20;
    private int posY;
    private final BufferedImage dino1BufferedImage;
    public static final int SIZE_DINO = 70;
    public static final int INITIAL_Y_POS = 382;
    private boolean isJumping = false;

    Player(){
        ImageHelper imageHelper = new ImageHelper();
        String pathDinoImg = "src/files/dino1.png";
        this.dino1BufferedImage = imageHelper.getBufferedImg(pathDinoImg);
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
        if(this.score%500 == 0) MusicHelper.playSound(0);
    }
    public void resetScore(){
        this.score = 0;
    }

    public int getPosX() {
        return posX;
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
