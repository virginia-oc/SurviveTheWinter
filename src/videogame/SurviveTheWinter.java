/**
 * This class manages scenes' flow. It contains the main method.
 * @author virginia ojeda corona
 */

package videogame;

import javafx.application.Application;
import javafx.stage.Stage;
import videogame.scenes.*;

public class SurviveTheWinter extends Application
{
    public static final int MAX_SCENES = 13;
    public static final int WELCOME_SCENE = 0;
    public static final int GAME_OVER_SCENE = 1;
    public static final int[] CUT_SCENE = {2, 3, 4, 5};
    public static final int[] GAME_SCENE = {6, 7, 8, 9};
    public static final int RANKING_SCENE = 10;
    public static final int PRE_GAME_OVER_SCENE = 11;
    public static final int CREDITS_SCENE = 12;
    public static final int LEVEL_1 = 0;
    public static final int LEVEL_2 = 1;
    public static final int LEVEL_3 = 2;
    public static final int LEVEL_4 = 3;
    public static final int gameDifficult = 8;
    public static long totalPoints = 0;

    public static final GeneralScene[] scenes =
            new GeneralScene[MAX_SCENES];
    //    public static final String BACKGROUND_SONG;
    //    public static final String SOUND_EFFECT;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception
    {
        this.stage = stage;

        scenes[WELCOME_SCENE] = new WelcomeScene();
        scenes[PRE_GAME_OVER_SCENE] = new PreGameOverScene();
        scenes[GAME_OVER_SCENE] = new GameOverScene();
        scenes[RANKING_SCENE] = new RankingScene();
        scenes[CREDITS_SCENE] = new CreditsScene();
        scenes[CUT_SCENE[LEVEL_1]] = new CutScene(LEVEL_1);
        scenes[CUT_SCENE[LEVEL_2]] = new CutScene(LEVEL_2);
//        scenes[CUT_SCENE[LEVEL_3]] = new CutScene(LEVEL_3);
//        scenes[CUT_SCENE[LEVEL_4]] = new CutScene(LEVEL_4);
        scenes[GAME_SCENE[LEVEL_1]] = new GameScene(LEVEL_1);
        scenes[GAME_SCENE[LEVEL_2]] = new GameScene(LEVEL_2);
//        scenes[GAME_SCENE[LEVEL_3]] = new GameScene(LEVEL_3);
//        scenes[GAME_SCENE[LEVEL_4]] = new GameScene(LEVEL_4);

        setScene(WELCOME_SCENE);
        stage.show();
    }

    public static void setScene(int numScene)
    {
        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void exit()
    {
        stage.hide();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
