package sprite;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

/**
 * This class will create the Collidable Object.
 * @author shlomi rosh.
 */
public interface Callable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method will notify the object that we collided with it
     * at the Collision Point with a given Velocity.
     * @param collisionPoint is the point to be checked.
     * @param currentVelocity is the velocity to be checked against.
     * @param hitter ball.
     * @return the new velocity after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}