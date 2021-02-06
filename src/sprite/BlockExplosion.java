package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;
/**
 * This class will create the expiosion Block.
 * @author shlomi rosh.
 */
public class BlockExplosion implements Sprite {

    private double x, y, dx, dy, gravity;
    private Color color;
    private int size;
    private SpecialBlocks piss;
    /**
     * The Constructor for the block.
     * @param block receives the block Which explodes.
     */
    public BlockExplosion(SpecialBlocks block) {
        this.x = block.getCollisionRectangle().getUpperLeft().getX() + block.getCollisionRectangle().getWidth() / 2;
        this.y = block.getCollisionRectangle().getUpperLeft().getY() + block.getCollisionRectangle().getHeight() / 2;
        this.dx = (Math.random() * 30 - 15);
        this.dy = (Math.random() * 30 - 15);
        this.size = (int) (Math.random() * 15 + 2);
        this.color = block.getColor();
        this.gravity = 0.8;
        piss = new SpecialBlocks(new Rectangle(new Point(x, y), size, size), color, 2, false, false, false, false);
    }
    /**
     * Particles of the exploding block.
     */
    public void explosion() {
        x += dx;
        y += dy;
        dy += gravity;
        piss = new SpecialBlocks(new Rectangle(new Point(x, y), size, size), color, 2, false, false, false, false);
    }

    /**
     * This will call the draw Method for drawing
     * the sprite to the screen.
     * @param d the sprite to be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(color);
            d.fillRectangle((int) piss.getCollisionRectangle().getUpperLeft().getX(),
                    (int) piss.getCollisionRectangle().getUpperLeft().getY(),
                    size, size);
        }
    }
    /**
     * Notify the Sprite that time has passed.
     * @param dt the frames per second.
     */
    @Override
    public void timePassed(double dt) {
        explosion();
    }
    /**
     * Will add the Particles of the exploding block to the List of Sprites
     * and the List of Collidables.
     * @param g is the game that the block will be sent to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
    /**
     * Will remove the Particles of the exploding block from the List of Sprites
     * and the List of Collidables.
     * @param g is the game that the block will be sent to.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}
