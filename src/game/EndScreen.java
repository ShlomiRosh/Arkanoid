package game;

import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Shlomi Rosh.
 * the end screen animation
 */
public class EndScreen implements Animation {
    private Counter lives;
    private boolean stop;
    private Counter score;
    /**
     * Constructor Method.
     *
     * @param scoreCounter the Counter.
     * @param live the Counter.
     */
    public EndScreen(Counter scoreCounter, Counter live) {
        this.stop = false;
        this.score = scoreCounter;
        this.lives = live;
        sound();
    }

    private void sound() {
        Sound sound;
        if (lives.getValue() == 0) {
            sound = new Sound("wah.wav", 0);
            sound.playSound();
        } else {
            sound = new Sound("3.wav", 0);
            sound.playSound();
        }
    }

    /**
     * Method that will draw the screen to the Gui.
     * @param d The surface to be drawn.
     * @param dt the frames per second.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        Integer smiley = 0x1F60D;
        if (lives.getValue() == 0) {
            smiley = 0x1F621;
            d.setColor(Color.red);
            d.drawText(230, 120, "You Lost", 80);
            d.setColor(Color.black);
            d.drawText(227, 117, "You Lost", 80);
        } else {
            d.setColor(Color.red);
            d.drawText(230, 120, "You Win!", 80);
            d.setColor(Color.black);
            d.drawText(227, 117, "You Win!", 80);
        }
        d.setColor(Color.YELLOW);
        d.fillCircle(380, 290, 130);
        d.setColor(Color.BLACK);
        d.drawText(230, 400, String.valueOf(Character.toChars(smiley)), 300);
        d.drawText(30, 570, "Final score: " + Integer.toString(score.getValue()), 20);
        d.setColor(Color.BLUE);
        d.drawText(208, 498, "Press space to continue", 32);
        d.setColor(Color.BLACK);
        d.drawText(210, 500, "Press space to continue", 32);

    }
    /**
     * Method that will check if the program needs to be terminated.
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
