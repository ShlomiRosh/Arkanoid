package game;

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * Created by Shlomi Rosh.
 */
public class PauseScreen implements Animation {

    private boolean stop;

    /**
     * Constructor Method.
     */
    public PauseScreen() {

        this.stop = false;
    }
    /**
     * Method that will draw the surface at every frame.
     * @param d The surface to be drawn.
     * @param dt the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {

        d.setColor(Color.GRAY);
        d.fillCircle(380, 300, 90);
        d.setColor(Color.WHITE);
        d.fillCircle(380, 300, 88);
        d.setColor(Color.BLUE);
        d.fillCircle(380, 300, 80);
        d.setColor(Color.GRAY);
        d.fillCircle(380, 300, 76);
        d.setColor(Color.BLACK);
        d.fillCircle(380, 300, 74);
        d.setColor(Color.GRAY);
        d.fillRectangle(343, 260, 35, 85);
        d.setColor(Color.GRAY);
        d.fillRectangle(385, 260, 35, 85);
        d.setColor(Color.BLUE);
        d.fillRectangle(345, 262, 33, 83);
        d.setColor(Color.BLUE);
        d.fillRectangle(387, 262, 33, 83);
        d.setColor(Color.BLUE);
        d.drawText(208, 498, "Press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(210, 500, "Press space to continue", 32);

    }
    /**
     * Check if the screen needs to stop.
     * @return true or false.
     */
    public boolean shouldStop() { return this.stop; }
}
