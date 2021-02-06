/* name: shalomi rosh
   id: 308154418
   oop
*/
package geometry;

 /**
 * Program to represent point values.
 * @author Shlomi Rosh
 */
public class Point {
    private double x, y;
    /**
     * Constructor.
     *@param x X point element
     *@param y The Y element of the point
     */
    public Point(double x , double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * distance -- return the distance of this point to the other point.
     * @param other point
     * @return distance
     */
    public double distance(Point other) {
        return (double) Math.sqrt(Math.pow(this.x - other.x, 2.0) + Math.pow(this.y - other.y, 2.0));
    }
    /**
     *  equals -- return true is the points are equal, false otherwise.
     * @param other point
     * @return true/false
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof Point)) {
            return false;
        }
        return (this.x == ((Point) other).x && this.y == ((Point) other).y);
    }
    /**
     * Return the x value of this point.
     * @return double
     */
    public double getX() {
        return x;
    }
    /**
     * Return the y value of this point.
     * @return double
     */
    public double getY() {
        return y;
    }

     /**
      * Method that will find the mid point of the line.
      * @param p is the point.
      * @return the mid point.
      */
     public Point midpoint(Point p) {
         double ex = (p.getX() + x) / 2;
         double wy = (p.getY() + y) / 2;
         return new Point(ex, wy);
     }
}



