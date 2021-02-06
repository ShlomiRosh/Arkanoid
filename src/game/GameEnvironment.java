/* name: shalomi rosh
   id: 308154418
   oop
*/
package game;

import geometry.Line;
import geometry.Point;
import sprite.Callable;
import sprite.CollisionInfo;
import java.util.ArrayList;
import java.util.List;


/**
 * This class will create the Game Environment.
 * @author shlomi rosh.
 */
public class GameEnvironment {
    private ArrayList<Callable> collidableArrayList = new ArrayList<Callable>();

    /**
     * add the given collidable to the environment.
     * @param c the current collidable.
     */
    public void addCollidable(Callable c) {
        collidableArrayList.add(c);
    }

    /**
     * This method will check for the closest Collision.
     * If this object will not collide with any of the collidables
     * it will return null. Else, return the information
     * about the closest collision that is going to occur.
     * @param trajectory the trajectory to be checked against.
     * @return collision info.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableArrayList.isEmpty()) {
            return null;
        }
        Callable temperryCollidable;
        Point near;
        int i = 0;
        do {
            temperryCollidable = collidableArrayList.get(i);
            near = trajectory.closestIntersectionToStartOfLine(temperryCollidable.getCollisionRectangle());
            i++;
        } while ((near == null) && (i < collidableArrayList.size()));
        if (near == null) { return null; }
        Callable collidable = collidableArrayList.get(i - 1);
        for (int j = i; j < this.collidableArrayList.size(); j++) {
            temperryCollidable = collidableArrayList.get(i);
            Point pointTemp = trajectory.closestIntersectionToStartOfLine(temperryCollidable.getCollisionRectangle());
            if (pointTemp != null && trajectory.start().distance(near) > trajectory.start().distance(pointTemp)) {
                near = pointTemp;
                collidable = temperryCollidable;
            }
        }
        return new CollisionInfo(near, collidable);
    }
    /**
     * remove the given collidable to the environment.
     * @param c the current collidable.
     */
    public void removeColliadable(Callable c) {
        this.collidableArrayList.remove(c);
    }

    public List<Callable> getCollliadable() {
        return this.collidableArrayList;
    }
}