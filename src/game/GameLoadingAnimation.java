package game;

import biuoop.DrawSurface;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

/**
 * The tag game animation.
 * @author shlomi rosh.
 */
public class GameLoadingAnimation implements Animation {
    private double numOfSeconds;
    private int countDown;
    private boolean stop = false;
    private double initialTime = System.currentTimeMillis();
    /**
     * Constructor Method.
     */
    public GameLoadingAnimation() {
        this.numOfSeconds = 3;
        this.countDown = (int) numOfSeconds;

    }
    /**
     * Does the animation at each frame.
     * @param d what to draw.
     * @param dt the frames per second.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        Image image;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL resource = classLoader.getResource("giphy.gif");
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\IdeaProjects\\ass6H1\\re\\giphy.gif");
        image = imageIcon.getImage();
        d.drawImage(0, 0, image);
        double currentTime = System.currentTimeMillis();
        if ((currentTime - initialTime) > (numOfSeconds) * 980) {
            countDown--;
            initialTime = System.currentTimeMillis();
        }

        if (countDown == 1) {
            Image image1;
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            URL resource1 = classLoader1.getResource("arkanoid_logo.png");
            ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\User\\IdeaProjects\\ass6H1\\re\\arkanoid_logo.png");
            image1 = imageIcon1.getImage();
            d.drawImage(250, 250, image1);
        }

        if (countDown == 0) {
            stop = true;
        }
    }
    /**
     * Checks if the animation should stop or not.
     * @return true or false.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
