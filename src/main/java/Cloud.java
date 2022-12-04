public class Cloud {
    private int x = 0;
    private int y = 450;
    public static int SIZE = 70;
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }

    Cloud(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}