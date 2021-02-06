package assix;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;
import game.AnimationRunner;
import geometry.Point;
import sprite.Ball;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;

/**
 * Creates the menu which can run a sub menu from it.
 * @author Shlomi Rosh.
 */
public class MenuAnimation<T> implements Animation, Menu<T> {
    private T status = null;
    private Map<String, T> menuOptions = new TreeMap<String, T>();
    private List<String> messagesList = new ArrayList<String>();
    private List<String> keyList = new ArrayList<String>();
    private KeyboardSensor keyboardSensor;
    private AnimationRunner runner;
    private boolean stop = false;
    private Map<String, Menu<T>> subMenus = new TreeMap<String, Menu<T>>();
    private String title;

    /**
     * Creates the menu which can run a sub menu from it.
     * @param sensor the key sensor
     * @param runner animation runner
     * @param title title of menu.
     */
    public MenuAnimation(String title, KeyboardSensor sensor, AnimationRunner runner) {
        this.keyboardSensor = sensor;
        this.runner = runner;
        this.title = title;

    }

    /**
     * Adds a select option to the menu.
     * @param key key to be pressed
     * @param message message to print on screen
     * @param returnVal the return value of the option
     */
    public void addSelection(String key, String message, T returnVal) {
        this.menuOptions.put(key, returnVal);
        this.messagesList.add(message);
        keyList.add(key);
    }

    /**
     * Adds sub menu to the current menu.
     * @param key key to be pressed to enter the sub menu
     * @param message message to print on the screen
     * @param subMenu the new sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        subMenus.put(key, subMenu);
        messagesList.add(message);
        keyList.add(key);
    }

    /**
     * Returns the menu status (the chosen option).
     * @return the chosen menu option
     */
    public T getStatus() {
        return status;
    }

    /**
     * One animation step.
     * @param d  is the surface
     * @param dt is the time of the move
     */
    public void doOneFrame(DrawSurface d, double dt) {
        drawBackground(d);
        displayOptions(d);
        checkUserAction();
    }

    /**
     * Indicates whether the animation should stop or not.
     * @return true / false
     */
    public boolean shouldStop() {
        boolean isToStop = this.stop;    // check if needs to stop
        this.stop = false;    // update the flag for future run
        return isToStop;
    }

    /**
     * Draws the menu background.
     * @param d draw surface
     */
    private void drawBackground(DrawSurface d) {
        d.setColor(Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Drawing the balls
        for (int i = 0; i < 1500; i++) {
            int rend = (int) (Math.random() * 900 + -100);
            int rend1 = (int) (Math.random() * 600 + 300);
            int rend2 = (int) (Math.random() * 15 + 10);
            Random rand = new Random();
            Ball ball = new Ball(new Point(rend, rend1), rend2,
                    new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            ball.drawOn(d);
        }
    }

    /**
     * Displays all the menu options.
     * @param d draw surface
     */
    private void displayOptions(DrawSurface d) {
        d.setColor(Color.RED);
        d.drawText(300, 40, title, 30);
        d.setColor(Color.BLUE);
        d.drawText(301, 40, title, 30);
        d.setColor(Color.WHITE);
        d.drawText(20, 85, "Please press one of the Following keys:", 20);

        d.setColor(Color.YELLOW);
        for (int i = 0; i < keyList.size(); i++) {
            d.drawText(150, (i * 50) + 130, '(' + keyList.get(i) + ')', 25);
            d.drawText(190, (i * 50) + 130, messagesList.get(i), 25);
        }
        d.setColor(Color.RED);
        for (int i = 0; i < keyList.size(); i++) {
            d.drawText(151, (i * 50) + 130, '(' + keyList.get(i) + ')', 25);
            d.drawText(191, (i * 50) + 130, messagesList.get(i), 25);
        }
    }

    /**
     * If user chose an option - update status and stop.
     */
    private void checkUserAction() {
        // Go over menu options and return if needed
        for (String key : menuOptions.keySet()) {
            // Check if user chose this option
            if (keyboardSensor.isPressed(key)) {
                status = menuOptions.get(key);
                stop = true;
                break;
            }
        }

        // Go over sub menus and run if needed
        for (final String key : subMenus.keySet()) {
            // Check if user chose this option
            if (keyboardSensor.isPressed(key)) {
                status = runSubMenu(subMenus.get(key));
                stop = true;
                break;
            }
        }
    }

    /**
     * Runs the new sub-menu of this menu.
     * @param subMenu the new sub-menu tu run
     * @return the status of the sub-menu
     */
    private T runSubMenu(Menu<T> subMenu) {
        runner.run(subMenu); // run the menu
        return subMenu.getStatus(); // wait for user selection
    }
}

