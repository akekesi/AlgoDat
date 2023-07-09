import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Crashtest {
    private static final double GFORCE = 9.81; //rough g force in m/s
    private Car testcar;
    private String testdummy;

    public Crashtest() {
        this.testcar = new Car();
        this.testdummy = "Carl";
    }

    /***********************************************
     ***************BRAKING TESTS*******************
     ***********************************************/

    @Test
    public void shouldHaveBrakingForceAbove20ms() {
        assertTrue(testcar.brakingForce > 20.0, testdummy + " refuses to enter such an unsafe car!");
    }
    @Test
    public void shouldComeToAFullStopWithin50m() {
        testcar.currentSpeed = testcar.maxGeschwindigkeit; // The initial speed for the test, in kp/h
        assertTrue(testcar.emergencyStop() < 50.0, testdummy + " looks on in shock as the car shoots over the virtual cliff!");
    }
    @Test
    public void shouldNotExceedPositiveGLimit() {
        assertTrue((testcar.brakingForce / GFORCE) < 4, testdummy + " starts blacking out and loses control of the vehicle.");
    }

    /***********************************************
     *************ACCELERATION TESTS****************
     ***********************************************/

    @Test
    public void shouldNotExceedNegativeGLimit() {
        assertTrue((testcar.acceleration / GFORCE) < 5, testdummy + " starts blacking out and loses control of the vehicle.");
    }
    @Test
    public void shouldAccelerateWithinOnrampLength() {
        testcar.currentSpeed = 30.0; // The initial speed for the test, in kp/h
        assertTrue(testcar.accelerateTo(120.0) < 80.0, testdummy + " wisely turns onto the side-rail, avoiding the much faster highway traffic.");
    }


    /***********************************************
     *****************CRASH TESTS*******************
     ***********************************************/
    @Test
    public void shouldNeverCrashIntoWall() {
        testcar.currentSpeed = 80.0;
        double speedDuringTest = testcar.currentSpeed / 3.6; // convert kp/h to m/s
        double timeToStandstill = speedDuringTest / testcar.brakingForce;
        for (int i = 6; i > 1; i--) {
            assertTrue(timeToStandstill < (double) i, testdummy + " gets ruffled as the car hits the wall with " + ((timeToStandstill - i) * speedDuringTest) + " m/s remaining during the " + i + " second test!");
        }
    }
    @Test
    public void shouldInterpretCollisionDataCorrectly(){
        double smallNegativeAcceleration = -3.0;
        double smallPositiveAcceleration = 5.0;
        double dangerousNegativeAcceleration = -12.0;
        double dangerousPositiveAcceleration = 14.0;
        double deadlyAcceleration = 35.0;
        assertFalse(testcar.readContactSensors(smallNegativeAcceleration),testdummy + " gets smacked by overreactive airbags.");
        assertFalse(testcar.readContactSensors(smallPositiveAcceleration),testdummy + " gets smacked by overreactive airbags.");
        assertTrue(testcar.readContactSensors(dangerousNegativeAcceleration),testdummy + " will need to visit the hospital-class for sure.");
        assertTrue(testcar.readContactSensors(dangerousPositiveAcceleration),testdummy + " will need to visit the hospital-class for sure.");
        assertTrue(testcar.readContactSensors(deadlyAcceleration),"Garbage Collection! " + testdummy + " needs to be cleaned up.");
    }

    @Test
    public void shouldDeployAirbagsWithin100Millis(){
        for(int i = 10; i>0;i--){
            testcar.currentSpeed = i * 9.0;
            boolean deployed = assertTimeout(Duration.ofMillis(100), testcar::deployAirbags,"Airbags were not deployed fast enough during " + (11-i) + ". test!");
            assertTrue(deployed,"Airbags are not correctly deploying at " + testcar.currentSpeed + " kp/h , said " + testdummy + "'s new Instance.");
        }
    }
}