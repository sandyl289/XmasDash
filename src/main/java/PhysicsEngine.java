public class PhysicsEngine {

    private static final float AMPLITUDE = 7f;
    private static final float STRETCH = 1.2f;
    public static double moveWithForce(double radians, boolean jumping){
        if (!jumping) return 0;
        double yPos = AMPLITUDE * Math.cos(STRETCH * radians);
        return yPos;
    }

    public boolean detectCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
        //Check if any points from first object are in second object

        //Check if any points from second object are in first object
        return false;
    }

    public int[] nextPos(int x1, int y1, long cur_time, long prev_time){
        int x2 = 0;
        int y2 = 0;

        int[] nextPos = {x2, y2};
        return nextPos;
    }
}
