public abstract class PhysicsEngine {


    public boolean detectCollision(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2){
        return false;
    }

    public int[] nextPos(int x1, int y1, long cur_time, long prev_time){
        int x2 = 0;
        int y2 = 0;
        
        int[] nextPos = {x2, y2};
        return nextPos;
    }
}