package io;


/**
 * Sets the key from the file.
 * @author shlomi rosh.
 */
public class SetLevel {
    private String key;
    private String title;
    private String levelPath;

    /**
     * Sets the key from the file.
     * @param k the key to set.
     */
    public void setKey(String k) {
        this.key = k;
    }

    /**
     * Sets the title.
     * @param t the title to set.
     */
    public void setTitle(String t) {
        this.title = t;
    }

    /**
     * Sets the Path to read the level description.
     * @param path to be read from.
     */
    public void setLevelPath(String path) {
        this.levelPath = path;
    }

    /**
     * Gets the key.
     * @return the key.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gets the title.
     * @return the title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the Level Path.
     * @return the levelPath.
     */
    public String getLevelPath() {
        return this.levelPath;
    }
}
