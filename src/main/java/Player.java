import java.awt.image.BufferedImage;

public class Player {
    private int score = 0;
    private int posX = 20;
    private int posY;
    private String pathDinoImg = "src/files/dino1.png";
    private BufferedImage dino1BufferedImage;
    private final int SIZE_DINO = 70;
    public static final int INITIAL_Y_POS = 382;
    private boolean isJumping = false;
    private MusicHelper musicHelper;

    Player(){
        ImageHelper imageHelper = new ImageHelper();
        this.dino1BufferedImage = imageHelper.getBufferedImg(pathDinoImg);
        this.posY = INITIAL_Y_POS;
        this.musicHelper = new MusicHelper();
    }
    public int getSizeDino() {
        return SIZE_DINO;
    }

    public BufferedImage getDino1BufferedImage() {
        return dino1BufferedImage;
    }

    public String getScoreStr(){
        String scoreStr = this.score + " pts";
        return scoreStr;
    }
    public void increaseScore() {
        this.score++;
        if(this.score%500 == 0) musicHelper.playSound(0);
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
