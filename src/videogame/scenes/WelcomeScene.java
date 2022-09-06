/**
 * This class manages user's decisions about game modes and other
 * options.
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */

package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import videogame.SurviveTheWinter;

import java.nio.file.Files;
import java.nio.file.Paths;

public class WelcomeScene extends GeneralScene
{
    private static final String BACKGROUND_IMAGE = "assets/welcomeSceneBackground.png";
    private static final String[] CLOUD_IMAGE =
            {"assets/cloudMainFace.png","assets/cloudMainFaceMovement.png"};
    private Image background;
    private Image[] cloud = new Image[2];
    private final int SHAPE_UP = 0;
    private final int SHAPE_DOWN = 1;
    private int currentShape = SHAPE_DOWN;
    private int counterFrames = 0;

    public WelcomeScene()
    {
        super();
        try
        {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
            cloud[SHAPE_UP] = new Image(Files.newInputStream(Paths.get(CLOUD_IMAGE[SHAPE_UP])));
            cloud[SHAPE_DOWN] = new Image(Files.newInputStream(Paths.get(CLOUD_IMAGE[SHAPE_DOWN])));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void draw()
    {
        activeKeys.clear();
        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                //Black background:
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
                gc.drawImage(background, 0, 0);
                gc.drawImage(cloud[currentShape],
                        X_CENTER - X_CENTER /8, Y_CENTER - Y_CENTER /9 );

                if (activeKeys.contains(KeyCode.LEFT))
                {
                    this.stop();
                    SurviveTheWinter.setScene(
                            SurviveTheWinter.CUT_SCENE[SurviveTheWinter.LEVEL_1]);
                }
                else if (activeKeys.contains(KeyCode.RIGHT))
                {
                    //this.stop();
                    //SurviveTheWinter.setScene(SurviveTheWinter.GAME_SCENE);
                }
                else if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    SurviveTheWinter.exit();
                }
                else if (activeKeys.contains(KeyCode.DOWN))
                {
                    this.stop();
                    SurviveTheWinter.setScene(SurviveTheWinter.RANKING_SCENE);
                }

                counterFrames++;
                if (counterFrames % MAX_FPS < MAX_FPS / 2)
                    currentShape = SHAPE_UP;
                else
                    currentShape = SHAPE_DOWN;

                if (counterFrames == MAX_FPS)
                    counterFrames = 0;
            }
        }.start();
    }
}
