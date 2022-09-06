/**
 * This class manages visualization of ranking scores.
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */

package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import videogame.Player;
import videogame.SurviveTheWinter;
import videogame.Ranking;

public class RankingScene extends GeneralScene
{
    public Ranking ranking;
    //private static final String BACKGROUND_IMAGE = "assets/tempBackground.png";
    private Image background;

    public RankingScene()
    {
        super();
        ranking = new Ranking();
    }

    private void showRanking()
    {
        int line = 160;
//        try
//        {
//            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }

        gc.setFont(arcadeFont);
        gc.setFill(Color.WHITE);
        gc.fillText("Ranking", 60, 80);

        int position = 1;
        for (Player player : ranking.getPlayerList())
        {
            gc.fillText(position + "        " + player.getName(), 60, line);
            gc.fillText("" + player.getScore(), X_CENTER, line);
            line+=30;
            position++;
        }
        gc.fillText("Press  ESC  key  to  return", 460, 470);
    }

    @Override
    public void draw()
    {
        activeKeys.clear();

        gc.setFill(Color.BLACK);
        gc.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);

        showRanking();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    SurviveTheWinter.setScene(
                            SurviveTheWinter.WELCOME_SCENE);
                }
            }
        }.start();
    }
}
