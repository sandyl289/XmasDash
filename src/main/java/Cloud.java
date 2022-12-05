public class Cloud {
    private int x;
    private final int y;
    public static final int SIZE = 70;
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
}