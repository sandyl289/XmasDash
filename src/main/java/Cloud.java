public class Cloud {
    private int x;
    private final int y;
    public static final int SIZE = 70;

    Cloud(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
}