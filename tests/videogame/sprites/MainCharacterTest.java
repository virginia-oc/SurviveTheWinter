/**
 * This class contains unit tests for the MainCharacter class.
 * @author virginia ojeda corona
 */

package videogame.sprites;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import videogame.scenes.GeneralScene;
import videogame.sprites.enemies.StormCloud;

class MainCharacterTest {
    MainCharacter cloudTest;
    StormCloud stormCloudTest;

    @BeforeEach
    void setUp()
    {
        cloudTest = new MainCharacter();
        stormCloudTest = new StormCloud();
    }

    @Test
    void getCurrentDirection()
    {
        //gCD0
        assertNotNull(cloudTest.getCurrentDirection());
        //gCD1
        assertEquals(AnimatedSprite.RIGHT, cloudTest.getCurrentDirection());
        //gCD2
        assertNotEquals(AnimatedSprite.LEFT, cloudTest.getCurrentDirection());
        //gCD3
        assertNotEquals(AnimatedSprite.UP, cloudTest.getCurrentDirection());
        //gCD4
        assertNotEquals(AnimatedSprite.DOWN, cloudTest.getCurrentDirection());
    }

    @Test
    void setCurrentDirection()
    {
        //sCD1
        cloudTest.setCurrentDirection(AnimatedSprite.RIGHT);
        assertEquals(AnimatedSprite.RIGHT, cloudTest.getCurrentDirection());
        //sCD2
        cloudTest.setCurrentDirection(AnimatedSprite.LEFT);
        assertEquals(AnimatedSprite.LEFT, cloudTest.getCurrentDirection());
        //sCD3
        cloudTest.setCurrentDirection(AnimatedSprite.UP);
        assertEquals(AnimatedSprite.UP, cloudTest.getCurrentDirection());
        //sCD4
        cloudTest.setCurrentDirection(AnimatedSprite.DOWN);
        assertEquals(AnimatedSprite.DOWN, cloudTest.getCurrentDirection());
    }

    @Test
    void getX() {
        //gX0
        assertNotNull(cloudTest.getX());
        //gX1
        assertEquals(0, cloudTest.getX());
    }

    @Test
    void getY() {
        //gY0
        assertNotNull(cloudTest.getY());
        //gY1
        assertEquals(0, cloudTest.getY());
    }

    @Test
    void moveTo()
    {
        //mT0
        cloudTest.moveTo(0, 0);
        assertEquals(0, cloudTest.getX());
        assertEquals(0, cloudTest.getY());
        //mT1
        cloudTest.moveTo(GeneralScene.GAME_WIDTH, GeneralScene.GAME_HEIGHT);
        assertEquals(816, cloudTest.getX());
        assertEquals(480, cloudTest.getY());
    }

    @Test
    void collidesWith()
    {
        //cW1
        cloudTest.moveTo(0, 0);
        stormCloudTest.moveTo(0, 0);
        assertTrue(cloudTest.collidesWith(stormCloudTest));
        //cW2
        cloudTest.moveTo(GeneralScene.X_CENTER, GeneralScene.Y_CENTER);
        assertFalse(cloudTest.collidesWith(stormCloudTest));
    }

    @Test
    void getCurrentPowerUpType()
    {
        //gCPUT0
        assertNotNull(cloudTest.getCurrentPowerUpType());
        //gCPUT1
        cloudTest.setCurrentPowerUpType(PowerUp.THUNDER);
        assertEquals(0, cloudTest.getCurrentPowerUpType());
    }

    @Test
    void setCurrentPowerUpType()
    {
        //sCPUT1
        cloudTest.setCurrentPowerUpType(PowerUp.SNOW);
        assertEquals(1, cloudTest.getCurrentPowerUpType());
    }

    @Test
    void move()
    {
        //m0 (NOT VALID TEST)
        //cloudTest.moveTo(0, 0);
        //cloudTest.move(-1);
        //assertTrue(cloudTest.getX() == 0 && cloudTest.getY() == 0);
        //m1
        cloudTest.moveTo(GeneralScene.GAME_WIDTH, 0);
        cloudTest.move(MainCharacter.RIGHT);
        assertTrue(cloudTest.getX() == 767 && cloudTest.getY() == 0);
        //m2
        cloudTest.moveTo(0, 0);
        cloudTest.move(MainCharacter.LEFT);
        assertTrue(cloudTest.getX() == 0 && cloudTest.getY() == 0);
        //m3
        cloudTest.moveTo(0, 0);
        cloudTest.move(MainCharacter.UP);
        assertTrue(cloudTest.getX() == 0 && cloudTest.getY() == 0);
        //m4
        cloudTest.moveTo(0, GeneralScene.GAME_HEIGHT);
        cloudTest.move(MainCharacter.DOWN);
        assertTrue(cloudTest.getX() == 0 && cloudTest.getY() == 447);
        //m5
        cloudTest.moveTo(GeneralScene.X_CENTER, GeneralScene.Y_CENTER);
        cloudTest.move(MainCharacter.RIGHT);
        assertTrue(cloudTest.getX() == 412 && cloudTest.getY() == 240);
        //m6
        cloudTest.moveTo(GeneralScene.X_CENTER, GeneralScene.Y_CENTER);
        cloudTest.move(MainCharacter.LEFT);
        assertTrue(cloudTest.getX() == 404 && cloudTest.getY() == 240);
        //m7
        cloudTest.moveTo(GeneralScene.X_CENTER, GeneralScene.Y_CENTER);
        cloudTest.move(MainCharacter.UP);
        assertTrue(cloudTest.getX() == 408 && cloudTest.getY() == 236);
        //m8
        cloudTest.moveTo(GeneralScene.X_CENTER, GeneralScene.Y_CENTER);
        cloudTest.move(MainCharacter.DOWN);
        assertTrue(cloudTest.getX() == 408 && cloudTest.getY() == 244);
    }

    @Test
    void resetPosition()
    {
        cloudTest.resetPosition();
        assertEquals(408, cloudTest.getX());
        assertEquals(240, cloudTest.getY());
    }
}