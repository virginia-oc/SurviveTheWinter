/**
 * This class manages the different types of shots,
 * either the main character or enemies.
 * @author virginia ojeda corona
 * @see videogame.sprites.AnimatedSprite
 */

package videogame.sprites;

import javafx.scene.image.Image;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Shot extends AnimatedSprite
{
    private static final String[] IMAGE_PATH =
            {"assets/thunderShot.png", "assets/snowShot.png",
             "assets/hailShot.png", "assets/rainShot.png"};
    public static int SHOT_WIDTH = 22;
    public static int SHOT_HEIGHT = 22;
    private double stepIncrement = 5;

    public Shot(int shotType, int currentDirectionCloud)
    {
        super(SHOT_WIDTH, SHOT_HEIGHT);
        currentDirection = currentDirectionCloud;
        TOTAL_MOVEMENTS = 4;
        spriteXCoordinate = new int[TOTAL_MOVEMENTS];
        spriteYCoordinate = new int[TOTAL_MOVEMENTS];

        try
        {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH[shotType])));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        spriteXCoordinate[RIGHT] = 44;
        spriteYCoordinate[RIGHT] = 0;
        spriteXCoordinate[LEFT] = 66;
        spriteYCoordinate[LEFT] = 0;
        spriteXCoordinate[UP] = 22;
        spriteYCoordinate[UP] = 0;
        spriteXCoordinate[DOWN] = 0;
        spriteYCoordinate[DOWN] = 0;

        updateSpriteCoordinate();
    }

    public void setStepIncrement(double stepIncrement)
    {
        this.stepIncrement = stepIncrement;
    }

    public void move()
    {
        switch (currentDirection)
        {
            case RIGHT:
                x += (int) (1 + stepIncrement);
                break;
            case LEFT:
                x -= (int) (1 + stepIncrement);
                break;
            case UP:
                y -= (int) (1 + stepIncrement);
                break;
            case DOWN:
                y += (int) (1 + stepIncrement);
                break;
        }
    }

    public void moveTo(AnimatedSprite cloud)
    {
        if (currentDirection == RIGHT)
        {
            x = cloud.getX() + cloud.widthScale;
            y = cloud.getY() + cloud.heightScale /4 ;
        }
        else if (currentDirection == LEFT)
        {
            x = cloud.getX() - widthScale;
            y = cloud.getY() + cloud.heightScale /4;
        }
        else if (currentDirection == UP)
        {
            x = cloud.getX() + cloud.widthScale / 4;
            y = cloud.getY() - heightScale;
        }
        else if (currentDirection == DOWN)
        {
            x = cloud.getX() + cloud.widthScale / 4;
            y = cloud.getY() + cloud.heightScale;
        }
    }

//    @Override
//    public int compareTo(Object o)
//    {
//        //Si son disparos del mismo tipo (no el mismo disparo) se anulan¿?
//        //así implemento esta interfaz...
//        return 0;
//    }
}
