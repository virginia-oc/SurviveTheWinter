/**
 * This class manages storm clouds' attributes.
 * @author virginia ojeda corona
 * @see videogame.sprites.AnimatedSprite
 */

package videogame.sprites.enemies;

import javafx.scene.image.Image;
import videogame.scenes.GameScene;
import videogame.scenes.GeneralScene;
import videogame.sprites.AnimatedSprite;
import videogame.sprites.MainCharacter;
import videogame.sprites.PowerUp;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class StormCloud extends AnimatedSprite
{
    public static final int ENEMY_WIDTH = 152;
    public static final int ENEMY_HEIGHT = 66;
    private static final String IMAGE_PATH = "assets/enemyCloud.png";
    private static int STEP = 4;
    public static final int STORMCLOUD_TYPE = PowerUp.THUNDER;

    public StormCloud() {
        super(ENEMY_WIDTH, ENEMY_HEIGHT);
        widthScale = ENEMY_WIDTH / 2;
        heightScale = ENEMY_HEIGHT / 2;
        TOTAL_MOVEMENTS = 4;
        spriteXCoordinate = new int[TOTAL_MOVEMENTS];
        spriteYCoordinate = new int[TOTAL_MOVEMENTS];

        try {
            spriteImage = new Image(Files.newInputStream(Paths.get(IMAGE_PATH)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        spriteXCoordinate[RIGHT] = 304;
        spriteYCoordinate[RIGHT] = 0;
        spriteXCoordinate[LEFT] = 456;
        spriteYCoordinate[LEFT] = 0;
        spriteXCoordinate[UP] = 152;
        spriteYCoordinate[UP] = 0;
        spriteXCoordinate[DOWN] = 0;
        spriteYCoordinate[DOWN] = 0;

        updateSpriteCoordinate();
    }

    public void moveTo()
    {
        Random r = new Random();
        int xCoordinate = GameScene.X_CENTER;
        int yCoordinate = GameScene.Y_CENTER;
        while (xCoordinate > GameScene.X_CENTER - MainCharacter.MAIN_CHARACTER_WIDTH * 2 &&
                xCoordinate < GameScene.X_CENTER + MainCharacter.MAIN_CHARACTER_WIDTH * 2  &&
                yCoordinate > GameScene.Y_CENTER - MainCharacter.MAIN_CHARACTER_HEIGHT * 2 &&
                yCoordinate < GameScene.Y_CENTER + MainCharacter.MAIN_CHARACTER_HEIGHT * 2)
        {
            yCoordinate = r.nextInt(GeneralScene.GAME_HEIGHT - ENEMY_HEIGHT);
            xCoordinate = r.nextInt(GeneralScene.GAME_WIDTH - ENEMY_WIDTH);
        }

        if (yCoordinate <= GameScene.Y_CENTER - MainCharacter.MAIN_CHARACTER_HEIGHT)
            setCurrentDirection(DOWN);
        else if (xCoordinate <= GameScene.X_CENTER - MainCharacter.MAIN_CHARACTER_WIDTH)
            setCurrentDirection(RIGHT);
        else if (yCoordinate >= GameScene.Y_CENTER + MainCharacter.MAIN_CHARACTER_HEIGHT)
            setCurrentDirection(UP);
        else if (xCoordinate >= GameScene.X_CENTER + MainCharacter.MAIN_CHARACTER_WIDTH)
            setCurrentDirection(LEFT);

        updateSpriteCoordinate();
        super.moveTo(xCoordinate, yCoordinate);
    }
}
