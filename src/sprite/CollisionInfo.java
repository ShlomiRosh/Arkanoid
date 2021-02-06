/* name: shalomi rosh
   id: 308154418
   oop
*/
package sprite;

import geometry.Point;

/**
 * This Class will be a referance for all the
 * collisions.
 * @author shlomi rosh.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Callable collisionObject;
    /**
     * @return the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * Receives the collidable object.
     * @return the collidabel Object.
     */
    public Callable collisionObject() {
        return this.collisionObject;
    }
    /**
     * the constructor- setting the members.
     * @param point  - the collision point
     * @param c - the collision object
     */
    public CollisionInfo(Point point, Callable c) {
        this.collisionPoint = point;
        this.collisionObject = c;
    }
}
