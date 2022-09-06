/**
 * This class manages the different types of power up sprites.
 * @author virginia ojeda corona
 * @see videogame.sprites.Sprite
 */

package videogame.sprites;

import javafx.scene.image.Image;
import videogame.scenes.GeneralScene;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PowerUp extends Sprite
{
    private static final String IMAGE_PATH = "assets/powerUps.png";
    public static final int POWERUP_WIDTH = 60;
    public static final int POWERUP_HEIGHT = 60;
    public static final int THUNDER = 0;
    public static final int SNOW = 1;
    public static final int HAIL = 2;
    public static final int RAIN = 3;
    public static final int ONE_UP = 4;
    private int type;

    public PowerUp(int type)
    {
        super(POWERUP_WIDTH, POWERUP_HEIGHT);
        widthScale = POWERUP_WIDTH / 2;
        heightScale = POWERUP_HEIGHT / 2;
        this.type = type;

        try
        {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        this.spriteY = 0;
        switch (type)
        {
            case THUNDER:
                this.spriteX = 0;
                break;
            case SNOW:
                this.spriteX = 60;
                break;
            case HAIL:
                this.spriteX = 120;
                break;
            case RAIN:
                this.spriteX = 180;
                break;
            case ONE_UP:
                this.spriteX = 240;
                break;
        }
    }

    public int getType()
    {
        return type;
    }

    @Override
    //Special type of collision with rounded objects (for power up sprites).
    public boolean collidesWith(Sprite sp)
    {
        double radius = widthScale / 2;
        double XCenter = x + radius;
        double YCenter = y + radius;
        int[] yFixPoints = {sp.y, sp.y + sp.heightScale};
        int[] xFixPoints = {sp.x, sp.x + sp.widthScale};
        for (int yFix: yFixPoints)
        {
            for (int xCoordinate = sp.x; xCoordinate < sp.x + sp.widthScale; xCoordinate++)
            {
                if (Math.pow(xCoordinate - XCenter, 2) + Math.pow(yFix - YCenter, 2) <= Math.pow(radius, 2))
                    return true;
            }
        }
        for (int xFix: xFixPoints)
        {
            for (int yCoordinate = sp.y; yCoordinate < sp.y + sp.heightScale; yCoordinate++)
            {
                if (Math.pow(xFix - XCenter, 2) + Math.pow(yCoordinate - YCenter, 2) <= Math.pow(radius, 2))
                    return true;
            }
        }
        return false;
    }

    public void moveTo()
    {
        super.moveTo(
                (int)(Math.random() *
                        (GeneralScene.GAME_WIDTH- PowerUp.POWERUP_WIDTH)),
                (int)(Math.random() *
                        (GeneralScene.GAME_HEIGHT- PowerUp.POWERUP_HEIGHT)));
    }
}
