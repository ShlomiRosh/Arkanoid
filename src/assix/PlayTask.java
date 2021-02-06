package assix;

import game.GameFlow;
import sprite.LevelInformation;

import java.util.List;

/**
 * Created by Shlomi Rosh.
 * Class that will run the Tasks.
 */
public class PlayTask implements Task<Void> {
    private GameFlow gameFlow;
    private List<LevelInformation> levelInformations;

    /**
     * Constructor Method.
     * @param g the gameFlow.
     * @param l the LevelInformation.
     */
    public PlayTask(GameFlow g, List<LevelInformation> l) {
        this.gameFlow = g;
        this.levelInformations = l;
    }

    /**
     * Method that will run the levelInformation.
     * @return null.
     */
    @Override
    public Void run() {
        gameFlow.runLevels(levelInformations);
        return null;
    }
}
