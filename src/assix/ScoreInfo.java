package assix;

/**
 * info of a player.
 * @author shlomi rosh.
 */
public class ScoreInfo {

    private String name;
    private int score;

    /**
     * Constructor Method.
     * @param name name of the player.
     * @param score score of the player.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Method that will get the players name.
     * @return the players name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the players score.
     * @return the players score.
     */
    public int getScore() {
        return this.score;
    }
}

