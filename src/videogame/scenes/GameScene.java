/**
 * This class manages sprites that appear in game, their interactions and
 * user-game interaction.
 * @author virginia ojeda corona
 * @see videogame.scenes.GeneralScene
 */
package videogame.scenes;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import videogame.SurviveTheWinter;
import videogame.sprites.MainCharacter;
import videogame.sprites.PowerUp;
import videogame.sprites.Shot;
import videogame.sprites.enemies.StormCloud;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameScene extends GeneralScene
{
    private static final String[] BACKGROUND_IMAGE = {
            "assets/tempBackground.png", "assets/tempBackground.png",
            "assets/tempBackground.png", "assets/tempBackground.png"};
    private int currentLevel;

    private Image background;
    private MainCharacter cloud;
    private PowerUp powerUp = null;
    private Shot cloudShot = null;
    private List<StormCloud> stormClouds = new ArrayList<>();
    private List<Shot> enemyShots = new ArrayList<>();
    private MediaPlayer mediaPlayerEffects;
    private Media effect;
    private int lives = 3;
    private int frame = 0;
    private int frameGhost = 0;
    private final int MAX_SECONDS_LIMIT = 300;
    private long secondsRemain = MAX_SECONDS_LIMIT;

    /**
     * Constructor with parameters
     * @param currentLevel An integer between 1 and 4 indicating the level to be constructed
     */
    public GameScene(int currentLevel)
    {
        super();
        this.currentLevel = currentLevel;
        try
        {
            background = new Image(Files.newInputStream(Paths.get(BACKGROUND_IMAGE[currentLevel])));
            cloud = new MainCharacter();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Returns the seconds available to finish the level
     * @return Seconds remaining in this level
     */
    public long getSecondsRemain()
    {
        return secondsRemain;
    }

    /**
     * Method inherited from the super class GeneralScene that draws
     * the level on the screen.
     */
    @Override
    public void draw()
    {
        reset();

        //sound = new Media(new File(BACKGROUND_SONG).toURI().toString());
        //mediaPlayer = new MediaPlayer(sound);
        //mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayer.play();

        spawnStormClouds();

        spawnEnemyShots();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gc.setFill(Color.BLACK);
                gc.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);

                drawAndMoveObjects();

                updateHUD();

                if (activeKeys.contains(KeyCode.ESCAPE))
                {
                    this.stop();
                    SurviveTheWinter.setScene(
                            SurviveTheWinter.WELCOME_SCENE);
                }
                else if (activeKeys.contains(KeyCode.LEFT))
                {
                    cloud.move(MainCharacter.LEFT);
                }
                else if (activeKeys.contains(KeyCode.RIGHT))
                {
                    cloud.move(MainCharacter.RIGHT);
                }
                else if (activeKeys.contains(KeyCode.UP))
                {
                    cloud.move(MainCharacter.UP);
                }
                else if (activeKeys.contains(KeyCode.DOWN))
                {
                    cloud.move(MainCharacter.DOWN);
                }

                //Check collisions and respawn objects:

                spawnMainCharacterShot();
                respawnPowerUp();
                checkMainCloudCollisions();
                checkEnemyShotsCollisions();
                checkCloudShotCollisions();
                checkGhost();


                //Check game over:
                if (lives == 0)
                {
                    this.stop();
                    //mediaPlayer.stop();
                    SurviveTheWinter.setScene(SurviveTheWinter.PRE_GAME_OVER_SCENE);
                }
                else if (!areRemainingEnemies())
                {
                    this.stop();
                    SurviveTheWinter.totalPoints += secondsRemain;
                    if (currentLevel == SurviveTheWinter.LEVEL_4)
                    {
                        SurviveTheWinter.setScene(SurviveTheWinter.CREDITS_SCENE);
                    }
                    else
                        SurviveTheWinter.setScene(SurviveTheWinter.GAME_SCENE[currentLevel + 1]);
                }

               calculateRatingPoints();


            }
        }.start();
    }

    /**
     * Returns attributes of the class to their initial values.
     */
    private void reset()
    {
        activeKeys.clear();
        cloud.resetPosition();
        lives = 3;
        stormClouds.clear();
        enemyShots.clear();
        powerUp = null;
        cloudShot = null;
        frame = 0;
        secondsRemain = MAX_SECONDS_LIMIT;

        if (currentLevel == SurviveTheWinter.LEVEL_1)
            SurviveTheWinter.totalPoints = 0;
    }

    /**
     * Reproduces the sound effects of the level.
     * @param path Path to sound file
     */
    private void playEffect(String path)
    {
//        effect = new Media(new File(path).toURI().toString());
//        mediaPlayerEffects = new MediaPlayer(effect);
//        mediaPlayerEffects.play();
    }

    /**
     * Updates the on-screen HUD
     */
    private void updateHUD()
    {
//        Font myFont = Font.font("Arial", FontWeight.NORMAL, 18);
//        gc.setFont(myFont);
//        gc.setFill(Color.BLUE);
//        gc.fillText("Score: " + points, 20, GeneralScene.GAME_HEIGHT - 15);
//
//        gc.setFill(Color.YELLOW);
//        gc.fillText("Lives: " + lives, GeneralScene.GAME_WIDTH - 100, GeneralScene.GAME_HEIGHT - 15);
    }

    /**
     * Spawn the enemy clouds on screen, avoiding
     * overlaps between them and with the main cloud.
     * @see StormCloud
     */
    private void spawnStormClouds()
    {
        for (int numberOfEnemies = 0;
             numberOfEnemies < (currentLevel + 1) * SurviveTheWinter.gameDifficult; numberOfEnemies++)
        {
            stormClouds.add(new StormCloud());
        }

        //Set storm clouds in scene avoiding overlapping between them:
        for (StormCloud currentCloud: stormClouds)
        {
            for (int i = 0; i < stormClouds.indexOf(currentCloud); i++)
            {
                if (currentCloud.collidesWith(stormClouds.get(i)))
                {
                    currentCloud.moveTo();
                    i = 0;
                }
            }
        }
    }

    /**
     * Spawn shots from enemies with random speed.
     * @see Shot
     * @see StormCloud
     */
    private void spawnEnemyShots()
    {
        for (int i = 0; i < stormClouds.size(); i++)
        {
            enemyShots.add(new Shot(StormCloud.STORMCLOUD_TYPE,
                    stormClouds.get(i).getCurrentDirection()));
            enemyShots.get(i).setStepIncrement(1 + (Math.random() * 3));
            enemyShots.get(i).moveTo(stormClouds.get(i));
        }
    }

    /**
     * Checks whether the conditions for drawing the objects
     * in the scene are met, draws them and moves them to the next position.
     */
    private void drawAndMoveObjects()
    {
        gc.drawImage(background, 0, 0);
        cloud.draw(gc);

        for (StormCloud stormCloud: stormClouds)
        {
            if (stormCloud != null)
                stormCloud.draw(gc);
        }

        if (powerUp != null)
            powerUp.draw(gc);

        if (cloudShot != null)
        {
            cloudShot.draw(gc);
            cloudShot.move();
        }

        for (Shot enemyShot: enemyShots)
        {
            enemyShot.draw(gc);
            enemyShot.move();
        }
    }

    /**
     * Generates the PowerUp object when certain conditions are met.
     * @see PowerUp
     * @see MainCharacter
     */
    private void respawnPowerUp()
    {
        if (powerUp == null && cloud.getAmmunition() == 0)
        {
            powerUp = new PowerUp(currentLevel);
            powerUp.moveTo();

            for (int indexCloudList = 0; indexCloudList < stormClouds.size(); indexCloudList++)
            {
                if (stormClouds.get(indexCloudList) != null &&
                        powerUp.collidesWith(stormClouds.get(indexCloudList)))
                {
                    powerUp.moveTo();
                    indexCloudList = 0;
                }
            }
        }
    }

    /**
     * Check for collisions between enemy shots and the main character.
     * @see videogame.sprites.Sprite
     * @see Shot
     */
    private void checkEnemyShotsCollisions()
    {
        //Collisions with main character cloud:
        for (Shot enemyShot :enemyShots) {
            if (!cloud.isGhost() && enemyShot != null && enemyShot.collidesWith(cloud)) {
                cloud.resetPosition();
                enemyShot.moveTo(stormClouds.get(enemyShots.indexOf(enemyShot)));
                lives--;
            }
            //Collisions with edges:
            else if ((enemyShot.getX() < 0 || enemyShot.getX() > GAME_WIDTH ||
                    enemyShot.getY() < 0 || enemyShot.getY() > GAME_HEIGHT) &&
                    stormClouds.get(enemyShots.indexOf(enemyShot)) != null) {
                enemyShot.moveTo(stormClouds.get(enemyShots.indexOf(enemyShot)));
            }
        }
    }

    /**
     * Check the collisions between the main character's shots and the enemies.
     * @see videogame.sprites.Sprite
     * @see Shot
     */
    private void checkCloudShotCollisions()
    {
        //Collisions with an enemy cloud:
        for (StormCloud enemyCloud: stormClouds) {
            if (cloudShot != null && enemyCloud != null && enemyCloud.collidesWith(cloudShot))
            {
                stormClouds.set(stormClouds.indexOf(enemyCloud), null);
                cloudShot = null;
            }
        }
        //Collisions with edges:
        if (cloudShot!= null && (
            cloudShot.getX() < 0 || cloudShot.getX() > GAME_WIDTH ||
            cloudShot.getY() < 0 || cloudShot.getY() > GAME_HEIGHT))
        {
            cloudShot = null;
        }
    }

    /**
     * Checks collisions of the main character with enemies and PowerUps
     * @see videogame.sprites.Sprite
     * @see MainCharacter
     */
    private void checkMainCloudCollisions()
    {
        //Collisions with a power up:
        if (powerUp != null && cloud.collidesWith(powerUp))
        {
            cloud.setCurrentPowerUpType(powerUp.getType());
            cloud.setAmmunition(cloud.MAX_AMMUNITION);
            //playEffect(SOUND_EFFECT);
            powerUp = null;
        }
        //Collisions with a storm cloud:
        for (StormCloud enemyCloud: stormClouds)
        {
            if (!cloud.isGhost() && enemyCloud != null && cloud.collidesWith(enemyCloud))
            {
                cloud.resetPosition();
                lives--;
            }
        }
    }

    /**
     * Spawn the main character's shot if the player
     * presses the SPACE key and certain conditions are met.
     * @see Shot
     * @see MainCharacter
     */
    private void spawnMainCharacterShot()
    {
        if (activeKeys.contains(KeyCode.SPACE))
        {
            if (cloud.getAmmunition() != 0)
            {
                cloudShot = new Shot(cloud.getCurrentPowerUpType(), cloud.getCurrentDirection());
                cloudShot.moveTo(cloud);
                cloud.setAmmunition(cloud.getAmmunition() - 1);
            }
//                   else
//                        cloud.dash();
        }
    }

    private void checkGhost()
    {
        if (cloud.isGhost())
        {
            frameGhost++;
            if (frameGhost == MAX_FPS*2)
            {
                frameGhost = 0;
                cloud.disableGhost();
            }
        }
    }

    /**
     * Calculates and updates the remaining seconds to finish the level.
     */
    private void calculateRatingPoints()
    {
        if (frame < MAX_FPS)
            frame++;
        else
        {
            if (secondsRemain != 0)
                secondsRemain--;
            frame = 0;
        }
    }

    /**
     * Checks if there are any enemies left on the screen.
     * @return True if at least one enemy remains, or false if none remains.
     */
    private boolean areRemainingEnemies()
    {
        for (StormCloud enemyCloud: stormClouds)
        {
            if (enemyCloud != null)
                return true;
        }
        return false;
    }
}
