package assix;

import biuoop.DrawSurface;
import game.Animation;
import java.awt.Color;

/**
 * Created by Shlomi Rosh.
 * This Class Will display the High Scores in the Gui.
 */
public class HighScoresAnimation implements Animation {

    private Boolean stop;
    private HighScoresTable highScoresTable;

    /**
     * Constructor Method.
     * @param scores scores table.
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.stop = false;
        this.highScoresTable = scores;
    }

    /**
     * Method that will draw the surface at every frame.
     * @param d The surface to be drawn.
     * @param dt the time frame speed.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.RED);
        d.drawText(19, 41, "High Scores", 40);

        d.setColor(Color.BLUE);
        d.drawText(20, 40, "High Scores", 40);

        d.drawText(100, 120, "Player Name", 30);
        d.drawText(500, 120, "Score", 30);

        d.setColor(Color.RED);
        d.drawText(98, 118, "Player Name", 30);
        d.drawText(498, 118, "Score", 30);

        d.setColor(Color.BLUE);
        d.drawLine(100, 131, 700, 131);

        d.setColor(Color.YELLOW);
        for (int i = 0; i < highScoresTable.getHighScores().size(); i++) {
            ScoreInfo scoreInfo = highScoresTable.getHighScores().get(i);
            d.drawText(110, (i * 40) + 155, scoreInfo.getName(), 20);
            d.drawText(510, (i * 40) + 155, "" + scoreInfo.getScore() + "", 20);
        }


        d.setColor(Color.GREEN);
        d.drawText(202, 550, "Press space to continue", 32);
        d.setColor(Color.GRAY);
        d.drawText(200, 550, "Press space to continue", 32);

    }


    /**
     * Check if the screen needs to stop.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}

