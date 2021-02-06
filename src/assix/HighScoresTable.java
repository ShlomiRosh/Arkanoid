package assix;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shlomi rosh.
 */
public class HighScoresTable {

    private int size;
    private List<ScoreInfo> highScores;

    /**
     * Method that will create a new high Scores Table.
     * @param size of the HighScore table.
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.highScores = new ArrayList<ScoreInfo>();
    }

    /**
     * This method will add information to the TextFile.
     * @param score that will be added to the file.
     */
    public void add(ScoreInfo score) {
        int indexToInsert = this.highScores.isEmpty() ? 0 : -1;
        for (int i = 0; i < this.size; i++) {
            if (i < this.highScores.size()) {
                ScoreInfo current = this.highScores.get(i);
                if (current.getScore() < score.getScore()) {
                    indexToInsert = i;
                    break;
                }
            } else {
                indexToInsert = i;
                break;
            }
        }
        if (indexToInsert != -1) {
            this.highScores.add(indexToInsert, score);
        }

        while (this.highScores.size() > this.size) {
            this.highScores.remove(this.highScores.size() - 1);
        }
    }

    /**
     * Returns the table size.
     * @return table size.
     */
    public int size() {
        return this.size;
    }


    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     * @return the high Scores.
     */
    public List<ScoreInfo> getHighScores() {
        return highScores;
    }

    /**
     * Return the rank of the current score.
     * @param score the score to be checked.
     * @return the rank of the score.
     */
    public int getRank(int score) {
        for (int i = 0; i < this.size; i++) {
            if (i < this.highScores.size()) {
                ScoreInfo si = this.getHighScores().get(i);
                if (si.getScore() < score) {
                    return i + 1;
                }
            } else {
                return i + 1;
            }
        }
        return this.highScores.size() + 1;
    }
    /**
     * Method that will Clears the table.
     */
    public void clear() {
            highScores.clear();
    }

    /**
     * Method that will load the file.
     * @param filename of the file to be loaded.
     * @throws java.io.IOException if there is an error.
     */
    public void load(File filename) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            this.size = Integer.parseInt(in.readLine());
            String scoresCSV = in.readLine();
            if (scoresCSV != null && !scoresCSV.isEmpty()) {
                String[] scores = scoresCSV.split(";");
                for (String scoreInfoAsString : scores) {
                    if (scoreInfoAsString != null && !scoreInfoAsString.isEmpty()) {
                        String[] info = scoreInfoAsString.split(",");
                        this.highScores.add(new ScoreInfo(info[0],
                                Integer.parseInt(info[1])));
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }


    /**
     * Save info to the file.
     * @param filename of the file to be opened.
     * @throws java.io.IOException if there is an error.
     */
    public void save(File filename) throws IOException {
        StringBuilder scoresCSV = new StringBuilder();
        for (ScoreInfo score : this.highScores) {
            scoresCSV.append(score.getName())
                    .append(",")
                    .append(score.getScore())
                    .append(";");
        }

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(new FileOutputStream(filename));
            writer.write(String.valueOf(this.size));
            writer.write("\n");
            writer.write(scoresCSV.toString());
        } catch (IOException ioe) {
            throw ioe;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }

        writer.close();
    }


    /**
     * Method that will load a specific file and start a buffer
     * to start reading.
     * @param filename to be opened.
     * @return the highscores table.
     */
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable table = new HighScoresTable(0);
        try {
            table.load(filename);
            return table;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
