package assix;

import game.Animation;

/**
 * Interface for the Menu.
 * @param <T> is the Generic type.
 */
public interface Menu<T> extends Animation {
    /**
     * Method that will add the Selection.
     * @param key to be pressed.
     * @param message to be displayed.
     * @param returnVal to be returned.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Get Status of keys pressed.
     * @return the status.
     */
    T getStatus();

    /**
     * Method that will add a sub-menu.
     * @param key to be pressed.
     * @param message to be displayed.
     * @param subMenu to be shown.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);
}
