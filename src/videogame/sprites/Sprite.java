/**
 * This class manages basic attributes and methods inherent to all sprites.
 * @author virginia ojeda corona
 */

package videogame.sprites;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Sprite
{
    protected int width, height;
    protected int widthScale, heightScale;
    protected int x, y;
    protected  int spriteX, spriteY;
    protected Image spriteImage;

    public Sprite(int width, int height)
    {
        this.width = width;
        this.height = height;
        widthScale = width;
        heightScale = height;
    }

    public void moveTo(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void draw(GraphicsContext gc)
    {
        gc.drawImage(spriteImage, spriteX, spriteY,
                width, height, x, y, widthScale, heightScale);
    }

    public boolean collidesWith(Sprite sp)
    {
        if (sp instanceof PowerUp)
        {
            return sp.collidesWith(this);
        }
        else
        {
            return (x + widthScale > sp.x && x < sp.x + sp.widthScale &&
                    y + heightScale > sp.y && y < sp.y + sp.heightScale);
        }
    }
}