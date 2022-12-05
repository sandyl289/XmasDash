import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PhysicsEngineTest {
    


    @Test
    void testDetectCollisionValid(){
        assertEquals(true, PhysicsEngine.detectCollision(0, 0, 1, 1, 1, 0, 0, 1));
    }
    @Test
    void testDetectCollisionInvalid(){
        assertEquals(false, PhysicsEngine.detectCollision(0, 0, 1, 1, 1, 0, 2, 1));
    }
    @Test
    void testMoveWithForceWhileJumping(){
        assertEquals((double) 0, PhysicsEngine.moveWithForce(0.2, false));
    }
    @Test
    void testMoveWithForce(){
        double radians = 0.2;
        assertEquals(7.0f * Math.cos(1.2f * radians), PhysicsEngine.moveWithForce(radians, true));
    }

}
