/**
 * This class requests and manages the player's name to store it in the ranking
 * when the player lose his lives during the game.
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */

package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import videogame.Ranking;
import videogame.SurviveTheWinter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PreGameOverScene extends GeneralScene {
    private static final String BACKGROUND_IMAGE =
            "assets/gameOverSceneInputName.png";
    private Image background;
    private String playerName;
    private Ranking ranking;

    public PreGameOverScene() {
        super();

        try {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw() {
        activeKeys.clear();
        playerName = "";

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                //Black background:
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
                gc.drawImage(background, 0, 0);

                if (activeKeys.contains(KeyCode.BACK_SPACE))
                {
                    if (playerName.length() != 0)
                    {
                        playerName = playerName.substring(
                                0, playerName.length() - 1);
                    }
                }
                else if (activeKeys.contains(KeyCode.SPACE))
                {
                    playerName += " ";
                }
                else if (activeKeys.contains(KeyCode.ENTER))
                {
                    ranking = new Ranking();
                    ranking.addScore(playerName.trim(), SurviveTheWinter.totalPoints);
                    this.stop();
                    SurviveTheWinter.setScene(
                            SurviveTheWinter.GAME_OVER_SCENE);
                }
                else if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    SurviveTheWinter.setScene(SurviveTheWinter.WELCOME_SCENE);
                }
                else if (activeKeys.size() != 0 &&
                        (activeKeys.iterator().next().isLetterKey() ||
                         activeKeys.iterator().next().isDigitKey()))
                {
                    playerName += activeKeys.iterator().next().getName();
                }

                gc.setFont(arcadeFont);
                gc.setFill(Color.WHITE);
                gc.fillText(playerName, X_CENTER - 100, Y_CENTER + 100);

                activeKeys.clear();
            }
        }.start();
    }
}
