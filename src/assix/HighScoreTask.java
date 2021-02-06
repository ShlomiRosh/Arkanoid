package assix;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;

import java.io.File;

/**
 * Method that will run the HighScore Task.
 */
public class HighScoreTask implements Task<Void> {
    private AnimationRunner animationR;
    private GUI gui;
    private File highScoresTableFile;

    /**
     * Constructor Method.
     * @param r the animation Runner.
     * @param g the GUI.
     * @param f the File to be read.
     */
    public HighScoreTask(AnimationRunner r, GUI g, File f) {
        this.highScoresTableFile = f;
        this.gui = g;
        this.animationR = r;
    }

    /**
     * Method that will run the HighScores Screen.
     * @return null.
     */
    public Void run() {
        HighScoresTable loadedTable = HighScoresTable.
                loadFromFile(highScoresTableFile);
        animationR.run(new assix.KeyPressStoppableAnimation(gui.getKeyboardSensor(),
                KeyboardSensor.SPACE_KEY,
                new HighScoresAnimation(loadedTable)));
        return null;
    }
}
