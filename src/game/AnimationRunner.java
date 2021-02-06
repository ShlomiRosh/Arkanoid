package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * Created by Shlomi Rosh.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     * Run the animation on the GUI.
     * @param gui the gui to be animated.
     */
    public AnimationRunner(GUI gui) {
        this.framesPerSecond = 60;
        this.gui = gui;
        this.sleeper = new Sleeper();
    }
    /**
     * set method.
     * setting the FramesPerSecond
     * @param framesPerSecon - the given value for FramesPerSecond
     */
    public void setFramesPerSecond(int framesPerSecon) {
        this.framesPerSecond = framesPerSecon;
    }
    /**
     * Run the Animation.
     * @param animation the animation to be run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 10000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d, (double) 1 / 60);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * access method.
     * @return - the gui that being drawing on.
     */
    public GUI getGui() {
        return gui;
    }

}