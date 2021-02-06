package sprite;

import biuoop.DrawSurface;
/**
 * This class will create the Sprite interface.
 * @author shlomi rosh.
 */
public interface Sprite {
    /**
     * This will call the draw Method for drawing
     * the sprite to the screen.
     * @param d the sprite to be drawn.
     */
    void drawOn(DrawSurface d);
    /**
     * Notify the Sprite that time has passed.
     * @param dt the frames per second.
     */
    void timePassed(double dt);
}