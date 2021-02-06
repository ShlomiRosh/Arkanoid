package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import java.awt.Color;
/**
 * @author shlomi rosh.
 */
public class LevelIndicator implements Sprite {
    private String name;
    /**
     * the constructor.
     * setting the fields.
     * @param s - the level name
     */
    public LevelIndicator(String s) {
        this.name = s;
    }

    /**
     * access method.
     * @return - the level name
     */
    public String getName() {
        return name;
    }

    /**
     * drawing the level's name to the screen.
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.red);
        d.drawText(500, 15, "LevelName: " + this.getName(), 15);
        d.setColor(Color.BLUE);
        d.drawText(501, 15, "LevelName: " + this.getName(), 15);
    }
    /**
     * implement of Sprite.
     * @param dt the frames per second.
     */
    public void timePassed(double dt) {

    }

    /**
     * adding this class to a given game.
     * @param game - the given game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}
