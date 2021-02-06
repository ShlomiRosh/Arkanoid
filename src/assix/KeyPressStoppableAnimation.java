package assix;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Animation;

/**
 * Decorator pattern.
 * @author Shlomi Rosh.
 */
public class KeyPressStoppableAnimation implements Animation {

    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean done;
    private boolean isAlreadyPressed;

    /**
     * Constructor Method.
     * @param sensor the keyboard sensor.
     * @param key to be pressed.
     * @param animation to be displayed.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.animation = animation;
        this.key = key;
        this.isAlreadyPressed = true;
    }
    /**
     * Do the actions for each frame.
     * @param d The surface to be drawn.
     * @param dt the time per frame.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.sensor.isPressed(this.key) && !this.isAlreadyPressed) {
            this.done = true;
        }

        if (!this.sensor.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Will constantly check when it should be stopped.
     * @return true or false.
     */
    @Override
    public boolean shouldStop() {
        return this.done;
    }

}

