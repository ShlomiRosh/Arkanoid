package game;

import assix.HighScoresAnimation;
import assix.HighScoresTable;
import assix.ScoreInfo;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import assix.KeyPressStoppableAnimation;
import sprite.LevelInformation;

import java.io.File;
import java.util.List;
/**
 * @author Shlomi Rosh.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor key;
    private Counter livesCounter = new Counter();
    private Counter scoreCounter = new Counter();
    private HighScoresTable highScoresTable;
    /**
     * Controls the Flow of the game.
     * @param ar animation Runner.
     * @param ks the keyboard sensor.
     * @param table the scores table.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, HighScoresTable table) {
        animationRunner = ar;
        key = ks;
        highScoresTable = table;
        livesCounter.increase(7);
    }
    /**
     * Method that will run through the levels.
     * @param levels current level.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, animationRunner, key, this.livesCounter, this.scoreCounter);
            level.initialize();
            while ((level.getBlockCounter().getValue() > 0) && (level.getLive().getValue() > 0)) {
                level.playOneTurn();
            }
        }
        if (highScoresTable.getRank(this.scoreCounter.getValue()) <= highScoresTable.size()) {
            DialogManager dialog = animationRunner.getGui().getDialogManager();
            String name = dialog.showQuestionDialog("Name",
                    "What is your name?", "Anonymous");
            ScoreInfo currentInfo = new ScoreInfo(name,
                    this.scoreCounter.getValue());
            highScoresTable.add(currentInfo);

            File highScoresTableFile = new File("highscores.txt");
            try {
                highScoresTable.save(highScoresTableFile);
            } catch (Exception e) {
                System.out.println("Problem saving to high scores file!");
            }
        }

        this.animationRunner.run(new KeyPressStoppableAnimation(key,
                KeyboardSensor.SPACE_KEY, new EndScreen(scoreCounter, livesCounter)));

        if (livesCounter.getValue() == 0) {
            this.livesCounter.increase(7);
        }
        this.scoreCounter = new Counter();

        this.animationRunner.run(new KeyPressStoppableAnimation(key,
                KeyboardSensor.SPACE_KEY, new HighScoresAnimation(highScoresTable)));
    }
}
