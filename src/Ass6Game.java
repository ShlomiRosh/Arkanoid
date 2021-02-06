
import assix.*;
import biuoop.GUI;
import game.AnimationRunner;
import game.GameFlow;
import game.GameLoadingAnimation;
import game.Sound;
import io.LevelSets;
import io.LevelSpecificationReader;
import io.SetLevel;
import sprite.LevelInformation;
import java.io.*;
import java.util.List;


/**
 * @author shlomi rosh.
 */
public class Ass6Game {

    private static int width = 800;
    private static int height = 600;

    /**
     * the maim of ass5.
     * @param args Receives arguments from the user.
     */
    public static void main(String[] args) {

        /*Creates the new .txt File to hold the high Scores.*/
        String scoresFile = "highscores.txt";
        final File highScoresTableFile = new File(scoresFile);

        HighScoresTable temp = null;
        try {
            if (!highScoresTableFile.exists()) {
                highScoresTableFile.createNewFile();
                temp = new HighScoresTable(3);
                temp.save(highScoresTableFile);
            } else {
                temp = HighScoresTable.loadFromFile(highScoresTableFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final HighScoresTable table = temp;
        Sound sound = new Sound("startGame.wav", 0);
        GUI gui = new GUI("arkoind", width, height);
        sound.playSound();
        AnimationRunner animationRunner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(animationRunner, gui.getKeyboardSensor(), table);
        animationRunner.run(new GameLoadingAnimation());
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Main Menu", gui.getKeyboardSensor(), animationRunner);
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(
                "Choose Level", gui.getKeyboardSensor(), animationRunner);

        LevelSets levelSets = null;
        Reader reader = null;
        if (args.length > 0) {
            try {
                reader = new FileReader(args[0]);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            levelSets = LevelSets.fromReader(reader);
        } else {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
            reader = new InputStreamReader(is);
            levelSets = LevelSets.fromReader(reader);
        }
        for (SetLevel level : levelSets.getSetLevelList()) {
            Reader reader2 = null;
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(level.getLevelPath());
            reader2 = new InputStreamReader(is);
            List<LevelInformation> info = null;
            try {
                info = LevelSpecificationReader.fromReader(reader2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(level.getKey());
            subMenu.addSelection(level.getKey(), level.getTitle(), new PlayTask(gameFlow, info));
        }
        menu.addSubMenu("s", "Start Game", subMenu);
        Task<Void> quit = new Task<Void>() {
            @Override
            public Void run() {
                System.exit(1);
                return null;
            }
        };
        menu.addSelection("h", "high scores", new HighScoreTask(animationRunner, gui, highScoresTableFile));
        menu.addSelection("q", "quit", quit);

        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            sound.stopSound();
            task.run();
            sound.playSound();
        }
    }
}