package io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * read from set of levels file.
 * @author shlomi rosh.
 */
public class LevelSets {
    private List<SetLevel> setLevelList;

    /**
     * Constructor Method.
     */
    public LevelSets() {
        this.setLevelList = new ArrayList<SetLevel>();
    }

    /**
     * Method that will read the levelSets File.
     *
     * @param reader to read the file.
     * @return the SetLevels.
     */
    public static LevelSets fromReader(Reader reader) {
        LevelSets set = new LevelSets();

        SetLevel level = null;
        LineNumberReader lineNumberReader = null;
        String line = "";
        try {
            lineNumberReader = new LineNumberReader(reader);

            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    level.setLevelPath(line.trim());
                    set.addSetLevel(level);
                    level = null;
                } else {
                    level = new SetLevel();
                    String[] linePieces = line.trim().split(":");
                    level.setKey(linePieces[0]);
                    level.setTitle(linePieces[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (lineNumberReader != null) {
                try {
                    lineNumberReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return set;
    }

    /**
     * Adds the levels to the LevelSet.
     *
     * @param setLevel the level to set.
     */
    public void addSetLevel(SetLevel setLevel) {
        this.setLevelList.add(setLevel);
    }



    /**
     * Method to get the LevelList.
     *
     * @return the level List.
     */
    public List<SetLevel> getSetLevelList() {
        return this.setLevelList;
    }
}
