/**
 * This class manages some attributes and methods of animated sprites,
 * extends to sprite class.
 * @author virginia ojeda corona
 * @see videogame.sprites.Sprite
 */

package videogame.sprites;

public abstract class AnimatedSprite extends Sprite
{
    protected int TOTAL_MOVEMENTS;
    public static final int RIGHT = 0;
    public static final int LEFT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;
    public static final int FACE = 4;
    public static final int BACK = 5;
    public static final byte SPRITE_CHANGE = 5;

    protected int currentDirection;
    //protected byte currentSprite;
    protected byte currentSpriteChange;

    protected int[] spriteXCoordinate;
    protected int[] spriteYCoordinate;

    public AnimatedSprite(int width, int height)
    {
        super(width, height);
        currentDirection = RIGHT;
        //currentSprite = 0;
        currentSpriteChange = 0;
    }

    public int getCurrentDirection()
    {
        return currentDirection;
    }

    public void setCurrentDirection(int direction)
    {
        currentDirection = direction;
    }

    public void animate(int movement)
    {
        if (movement != currentDirection)
        {
            currentDirection = movement;
            currentSpriteChange = 0;
        }
        else
        {
            currentSpriteChange++;
            if (currentSpriteChange >= SPRITE_CHANGE)
            {
                currentSpriteChange = 0;
//                currentSprite = (byte)((currentSprite + 1) %
//                        spriteXCoordinate[currentDirection].length);
            }

        }
        updateSpriteCoordinate();
    }

    protected void updateSpriteCoordinate()
    {
        spriteX = spriteXCoordinate[currentDirection];
        spriteY = spriteYCoordinate[currentDirection];
    }
}
