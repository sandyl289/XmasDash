import java.awt.image.BufferedImage;

public class Player {
    private int score = 0;
    private int life = 3;
    private int posX;
    private int posY;
    private String pathDinoImg = "src/files/dino1.png";
    private BufferedImage dino1BufferedImage;
    private final int SIZE_DINO = 70;

    private static final double INITIAL_Y_POS = 20;

    Player(){
        ImageHelper imageHelper = new ImageHelper();
        this.dino1BufferedImage = imageHelper.getBufferedImg(pathDinoImg);
        this.posY = 400;  //todo: replace value by constant ground level value
    }
    public int getSizeDino() {
        return SIZE_DINO;
    }

    public BufferedImage getDino1BufferedImage() {
        return dino1BufferedImage;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getPathDinoImg() {
        return pathDinoImg;
    }

    public void setPathDinoImg(String pathDinoImg) {
        this.pathDinoImg = pathDinoImg;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}
