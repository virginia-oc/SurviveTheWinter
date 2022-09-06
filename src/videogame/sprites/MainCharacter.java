/**
 * This class manages attributes and methods of the main character,
 * extends to animated sprite class.
 * @author virginia ojeda corona
 * @see videogame.sprites.AnimatedSprite
 */

package videogame.sprites;

import javafx.scene.image.Image;
import videogame.scenes.GeneralScene;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MainCharacter extends AnimatedSprite
{
    public static final int MAIN_CHARACTER_WIDTH = 98;
    public static final int MAIN_CHARACTER_HEIGHT = 66;
    public static final int MAX_AMMUNITION = 1;
    private static final String IMAGE_PATH = "assets/cloud.png";
    private static int STEP = 4;
    private static int currentPowerUpType;
    private int ammunition = 0;
    private static boolean ghost = false;

    public MainCharacter()
    {
        super(MAIN_CHARACTER_WIDTH, MAIN_CHARACTER_HEIGHT);
        widthScale = MAIN_CHARACTER_WIDTH / 2;
        heightScale = MAIN_CHARACTER_HEIGHT / 2;
        TOTAL_MOVEMENTS = 6;
        spriteXCoordinate = new int[TOTAL_MOVEMENTS];
        spriteYCoordinate = new int[TOTAL_MOVEMENTS];

        try
        {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setUpPicture();
        updateSpriteCoordinate();
    }

    private void setUpPicture() {
        spriteXCoordinate[RIGHT] = 98;
        spriteYCoordinate[RIGHT] = 0;
        spriteXCoordinate[LEFT] = 0;
        spriteYCoordinate[LEFT] = 0;
        spriteXCoordinate[UP] = 294;
        spriteYCoordinate[UP] = 0;
        spriteXCoordinate[DOWN] = 392;
        spriteYCoordinate[DOWN] = 0;
       }

    private void setUpPictureGhost() {
        spriteXCoordinate[RIGHT] = 686;
        spriteYCoordinate[RIGHT] = 0;
        spriteXCoordinate[LEFT] = 588;
        spriteYCoordinate[LEFT] = 0;
        spriteXCoordinate[UP] = 882;
        spriteYCoordinate[UP] = 0;
        spriteXCoordinate[DOWN] = 980;
        spriteYCoordinate[DOWN] = 0;}

    public int getAmmunition()
    {
        return ammunition;
    }

    public void setAmmunition(int ammunition)
    {
        this.ammunition = ammunition;
    }

    public int getCurrentPowerUpType()
    {
        return currentPowerUpType;
    }

    public void setCurrentPowerUpType(int powerUpType)
    {
        currentPowerUpType = powerUpType;
    }

    public int getCurrentDirection()
    {
        return this.currentDirection;
    }

    public boolean isGhost() { return ghost; }

    public void enableGhost() {
        ghost = true;
        setUpPictureGhost();
        updateSpriteCoordinate();
    }

    public void disableGhost()
    {
        ghost = false;
        setUpPicture();
        updateSpriteCoordinate();
    }

    public void move(int movement)
    {
        int newX = x;
        int newY = y;
        if (movement == LEFT)
            newX -= Math.min(STEP, x);
        else if (movement == RIGHT)
            newX += Math.min(STEP, GeneralScene.GAME_WIDTH - widthScale - x);
        else if (movement == UP)
            newY -= Math.min(STEP, y);
        else if (movement == DOWN)
            newY += Math.min(STEP, GeneralScene.GAME_HEIGHT - heightScale - y);
        moveTo(newX, newY);
        animate(movement);
    }

//    public void dash()
//    {
//        this.STEP = 12;
//        move(currentDirection);
//        this.STEP = 4;
//    }

    public void resetPosition()
    {
        enableGhost();
        moveTo(GeneralScene.X_CENTER,
                GeneralScene.Y_CENTER);
    }
}
