import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroundTest {

    private Ground ground;

    @BeforeEach
    void setup() {
        this.ground = new Ground();
    }

    @AfterEach
    void teardown() {
        this.ground = null;
    }

    @Test
    void testGetX(){
        assertEquals(0, ground.getX());
    }

    @Test
    void testSetX(){
        int expectedX = 50;
        ground.setX(expectedX); //when
        assertEquals(expectedX, ground.getX());
    }
}