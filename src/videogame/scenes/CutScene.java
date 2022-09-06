/**
 * This class manages the four different cut scenes that
 * we can see between levels
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */

package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import videogame.SurviveTheWinter;

public class CutScene extends GeneralScene
{
    private int currentLevel;

    public CutScene(int level)
    {
        super();
        currentLevel = level;
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

                showCurrentLevelMessage();

                if (activeKeys.contains(KeyCode.SPACE))
                {
                    this.stop();
                    SurviveTheWinter.setScene(SurviveTheWinter.GAME_SCENE[currentLevel]);
                }
                else if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    SurviveTheWinter.exit();
                }
            }
        }.start();
    }

    private void showCurrentLevelMessage()
    {
        switch (currentLevel)
        {
            case SurviveTheWinter.LEVEL_1:
                showLevel1Message();
                break;
            case SurviveTheWinter.LEVEL_2:
                showLevel2Message();
                break;
            case SurviveTheWinter.LEVEL_3:
                showLevel3Message();
                break;
            case SurviveTheWinter.LEVEL_4:
                showLevel4Message();
                break;
        }
    }

    private void showLevel1Message()
    {

        gc.setFont(arcadeFont);
        gc.setFill(Color.WHITE);
        gc.fillText("LEVEL  1", X_CENTER - 60, 100);

        gc.fillText("Collect   lightning   to   shoot  and \n " +
                "eliminate   all   storm   clouds", 150, 175);

        gc.fillText("Move   the   white   cloud   with   arrow   keys  \n  " +
                "and   space   bar   for   shot", 150, 250);

        gc.fillText("Press   space   bar   to   continue", 150, 400);
    }

    private void showLevel2Message()
    {

    }

    private void showLevel3Message()
    {

    }

    private void showLevel4Message()
    {

    }
}
