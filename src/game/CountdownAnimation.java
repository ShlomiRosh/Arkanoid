package game;

import biuoop.DrawSurface;
import sprite.SpriteCollection;
import java.awt.Color;

/**
 * @author Shlomi Rosh.
 * Class that will activate the COuntdown animation.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int countDown;
    private boolean stop = false;
    private SpriteCollection gameScreen;
    private double initialTime = System.currentTimeMillis();
    /**
     * Constructor Method.
     * @param numOfSeconds between each animation.
     * @param countFrom a starting number.
     * @param gameScreen that the animation will be displayed on.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.countDown = countFrom;
    }
    /**
     * Does the animation at each frame.
     * @param d what to draw.
     * @param dt the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gameScreen.drawAllOn(d);
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(360 + 2, 300 + 100, "" + getNumber(), 48);
        d.drawText(360 - 2, 300 + 100, "" + getNumber(), 48);
        d.drawText(360, 300 + 102, "" + getNumber(), 48);
        d.drawText(360, 300 + 98, "" + getNumber(), 48);
        d.setColor(Color.WHITE);
        d.drawText(360, 300 + 100, "" + getNumber(), 48);
        if (countDown < 1) {
            stop = true;
        }
    }
    /**
     * Checks if the animation should stop or not.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
    /**
     * Get the number of the count down.
     * @return the number.
     */
    private String getNumber() {
        double currentTime = System.currentTimeMillis();
        if ((currentTime - initialTime) > (numOfSeconds / (countFrom + 1)) * 1500) {
            countDown--;
            initialTime = System.currentTimeMillis();
        }
        if (countDown == 0) {
            return ("GO");
        }
        return ("  " + countDown + "");
    }

}
