import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LandscapeTest {
    private Landscape landscape;
    private ArrayList<Cloud> cloudsInitial;
    private ArrayList<Cloud> cloudsExpected;
    @BeforeEach
    void setup() {
        this.landscape = new Landscape();
        this.cloudsInitial = landscape.getClouds();
        this.cloudsExpected = new ArrayList<>(cloudsInitial.size());
    }

    @AfterEach
    void teardown() {
        this.landscape = null;
    }

    @Test
    void testMoveLandscape(){
        int speed = 3;
        for(Cloud cloudInitial: cloudsInitial){   //need to create deep copy
            Cloud cloud = new Cloud(cloudInitial.getX()-speed, 100);
            cloudsExpected.add(cloud);
        }
        //when
        this.landscape.moveLandscape(speed);
        ArrayList<Cloud> actualClouds = landscape.getClouds();

        //assert
        for(int i = 0; i< cloudsInitial.size(); i++){
            assertEquals(cloudsExpected.get(i).getX(), actualClouds.get(i).getX());
        }
    }
    @Test
    void testMoveLandscapeCloudOutOfWindow(){
        int speed = Cloud.SIZE;  //Set speed to be cloud size to test if statement
        for(Cloud cloudInitial: cloudsInitial){   //need to create deep copy
            Cloud cloud = new Cloud(cloudInitial.getX()-speed, 100);
            cloudsExpected.add(cloud);
        }
        cloudsExpected.get(0).setX(GamePanel.WINDOW_WIDTH); //1st cloud should be out of screen and set to window width

        //when
        this.landscape.moveLandscape(speed);
        ArrayList<Cloud> actualClouds = landscape.getClouds();

        //assert
        for(int i = 0; i< cloudsInitial.size(); i++){
            assertEquals(cloudsExpected.get(i).getX(), actualClouds.get(i).getX());
        }
    }
}
