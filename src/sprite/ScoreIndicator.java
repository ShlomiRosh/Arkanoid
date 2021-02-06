package sprite;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;

import java.awt.Color;
/**
 * @author shlomi rosh.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;

    /**
     * the constructor.
     * @param count - the score counter
     */
    public ScoreIndicator(Counter count) {
        this.scoreCounter = count;
    }

    /**
     * access method.
     * @return - the counter.
     */
    private Counter getScoreCounter() {
        return this.scoreCounter;
    }

    /**
     * draw the score to the screen.
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(360, 15, "Score: " + Integer.toString(this.getScoreCounter().getValue()), 15);
        d.setColor(Color.red);
        d.drawText(362, 16, "Score: " + Integer.toString(this.getScoreCounter().getValue()), 15);
    }

    /**
     * notify the sprite that time has passed.
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
