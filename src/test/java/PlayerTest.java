import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setup() {
        this.player = new Player();
    }

    @AfterEach
    void teardown() {
        this.player = null;
    }

    @Test
    void testGetScoreStr(){
        String expectedScoreStr = "0 pts"; //score is initialized to 0 by default
        assertEquals(expectedScoreStr, player.getScoreStr());
    }

    @Test
    void testIncreaseScore(){
        int initial = player.getScore();

        //when
        player.increaseScore();

        //assert
        int actual = player.getScore();
        assertEquals(initial + 1, actual);
    }

    @Test
    void resetScore(){
        //before
        player.increaseScore(); //increaseScore tested above

        //when
        player.resetScore();

        //assert
        int actual = player.getScore();
        assertEquals(0, actual);
    }
    @Test
    void testGetPosX(){
        int expected = 20; //pos x is always 20
        assertEquals(expected, player.getPosX());
    }

    @Test
    void testSetPosY(){
        int expected = 200;

        //when
        player.setPosY(expected);

        //assert
        int actual = player.getPosY();
        assertEquals(expected, actual);
    }

    @Test
    void testGetIsJumping(){
        //isJumping is initialized to false by default
        assertFalse(player.getIsJumping());
    }

    @Test
    void testSetIsJumping(){
        //isJumping is initialized to false by default

        //when
        player.setIsJumping(true);

        //assert
        assertTrue(player.getIsJumping());
    }
}