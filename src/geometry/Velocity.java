/* name: shalomi rosh
   id: 308154418
   oop
*/
package geometry;

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * @author Shlomi Rosh
 */
public class Velocity {
    private double dx, dy;
    /**
     * Constructor.
     *@param dx X point element
     *@param dy The Y element of the point
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Take a point with position (x,y) and return a new point
     * with position (x+dx, y+dy).
     * @param p  point with position
     * @return Point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * convenient to specify the velocity in terms and angle and a speed, so instead of
     * specifying dx=2, dy=0, you could specify .
     * @param angle  Creates the operating angle of the ball
     * @param speed Creates the speed of the ball
     * @return velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {

        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -1 * (speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * accessor.
     * @return dx
     */
    public double getDx() {
        return dx;
    }
    /**
     * accessor.
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * seting method.
     * @param x -the given distance.
     */
    public void setDx(double x) {
        this.dx = x;
    }
    /**
     * seting method.
     * @param y -the given distance.
     */
    public void setDy(double y) {
        this.dy = y;
    }

    /**
     * Method to scale the velocity.
     * @param dt the frames per second.
     * @return the new velocity.
     */
    public Velocity scale(double dt) {
        return new Velocity(this.dx * dt, this.dy * dt);
    }

}