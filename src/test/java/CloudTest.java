import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CloudTest {
    private Cloud cloud;

    @BeforeEach
    void setup() {
        int x = 3;
        int y = 50;
        this.cloud = new Cloud(x,y);
    }

    @AfterEach
    void teardown() {
        this.cloud = null;
    }

    @Test
    void testGetX(){
        assertEquals(3, cloud.getX());
    }

    @Test
    void testSetX(){
        //before
        assertEquals(3, cloud.getX());

        //when
        int expectedX = 40;
        cloud.setX(expectedX);

        //assert
        int actualX = cloud.getX();
        assertEquals(expectedX, actualX);
    }
    @Test
    void testGetY(){
        assertEquals(50, cloud.getY());
    }
}
