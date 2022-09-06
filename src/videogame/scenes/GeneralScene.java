/**
 * This class contains attributes and methods that all scenes
 * contains in common.
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */

package videogame.scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import java.io.*;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Set;

public abstract class GeneralScene extends Scene
{
    public static final int GAME_WIDTH = 816;
    public static final int GAME_HEIGHT = 480;
    protected final static int MAX_FPS = 60;
    public static final int X_CENTER = GAME_WIDTH / 2;
    public static final int Y_CENTER = GAME_HEIGHT / 2;
    private StackPane root = new StackPane();
    protected GraphicsContext gc;
    protected Set<KeyCode> activeKeys;
    protected Set<KeyCode> releasedKeys;
    protected MediaPlayer mediaPlayer;
    protected Media sound;
    protected Font arcadeFont;
    protected static final String FONT_PATH = "assets/fonts/ARCADECLASSIC.ttf";
    protected InputStream inputStream;

    public GeneralScene()
    {
        super(new StackPane(), GAME_WIDTH, GAME_HEIGHT);

        root = new StackPane();
        this.setRoot(root);

        Canvas canvas = new Canvas(GAME_WIDTH, GAME_HEIGHT);
        root.getChildren().add(canvas);
        gc = canvas.getGraphicsContext2D();

        activeKeys = new HashSet<>();
        releasedKeys = new HashSet<>();
        this.setOnKeyPressed(e -> {
            activeKeys.add(e.getCode());
        });
        this.setOnKeyReleased(e -> {
            activeKeys.remove(e.getCode());
            releasedKeys.add(e.getCode());
        });

        try
        {
            inputStream = new FileInputStream(FONT_PATH);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        arcadeFont = Font.loadFont(inputStream, 30);
    }

    public abstract  void draw();
}
