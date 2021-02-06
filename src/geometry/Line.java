/* name: shalomi rosh
   id: 308154418
   oop
*/
package geometry;
import biuoop.DrawSurface;
import sprite.Sprite;
import java.awt.Color;
import java.awt.geom.Line2D;

/**
 * Program to represent line values, line Represented by 2 points.
 * @author Shlomi Rosh
 */
public class Line implements Sprite {
    private Point start, end;
    private Color color;
    private boolean glitter = false;
    /**
     * Constructor.
     * @param start , point that start thr line
     * @param end , point that end the line
     */
    public Line(Point start , Point end) {
        this.start = start;
        this.end = end;
    }
    /**
     * Constructor creates start point and end point.
     * @param x1 ,X element of the starting point
     * @param y1 ,Y element of the starting point
     * @param x2 ,X element of the ending point
     * @param y2 ,Y element of the ebding point
     */
    public Line(double x1 , double y1 , double x2 , double y2) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
    }
    /**
     * Constructor creates start point and end point.
     * @param x1 ,X element of the starting point
     * @param y1 ,Y element of the starting point
     * @param x2 ,X element of the ending point
     * @param y2 ,Y element of the ebding point
     * @param color , The desired color for the line
     * @param b , A variable that says whether the color should sparkle
     */
    public Line(double x1, double y1, double x2, double y2, Color color, boolean b) {
        start = new Point(x1, y1);
        end = new Point(x2, y2);
        this.color = color;
        this.glitter = b;
    }

    /**
     * Return the length of the line.
     * @return length
     */
    public double length() {
        return (double) Math.sqrt(Math.pow(this.start.getX() - this.end.getX(), 2.0)
                + Math.pow(this.end.getY() - this.end.getY(), 2.0));
    }
    /**
     * Returns the middle point of the line.
     * @return Point
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }
    /**
     * Returns the start point of the line.
     * @return Point
     */
    public Point start() {
        return start;
    }
    /**
     * Returns the end point of the line.
     * @return Point
     */
    public Point end() {
            return end;
    }
    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other , point that start thr line
     * @return true/ false
     */
    public boolean isIntersecting(Line other) {
        // Return false if either of the lines have zero length
        if ((this.start.getX() == this.end.getX() && this.start.getY() == this.end.getY())
                || (other.start.getX() == other.end.getX() && other.start.getY() == other.end.getY())) {
            return false;
        }
        // Fastest method, based on Franklin Antonio's "Faster Line Segment Intersection"
        double ax = this.end.getX() - this.start.getX();
        double ay = this.end.getY() - this.start.getY();
        double bx = other.start.getX() - other.end.getX();
        double by = other.start.getY() - other.end.getY();
        double cx = this.start.getX() - other.start.getX();
        double cy = this.start.getY() - other.start.getY();
        double alphaNumerator = by * cx - bx * cy;
        double commonDenominator = ay * bx - ax * by;
        if (commonDenominator > 0) {
            if (alphaNumerator < 0 || alphaNumerator > commonDenominator) {
                return false;
            }
        } else if (commonDenominator < 0) {
            if (alphaNumerator > 0 || alphaNumerator < commonDenominator) {
                return false;
            }
        }
        double betaNumerator = ax * cy - ay * cx;
        if (commonDenominator > 0) {
            if (betaNumerator < 0 || betaNumerator > commonDenominator) {
                return false;
            }
        } else if (commonDenominator < 0) {
            if (betaNumerator > 0 || betaNumerator < commonDenominator) {
                return false;
            }
        }
        if (commonDenominator == 0) {
            // The lines are parallel.
            // Check if they're collinear.
            double y3LessY1 = other.start.getY() - this.start.getY();
            double collinearityTestForP3 = this.start.getX() * (this.end.getY() - other.start.getY())
                    + this.end.getX() * (y3LessY1) + other.start.getX() * (this.start.getY()
                    - this.end.getY());
            // If p3 is collinear with p1 and p2 then p4 will also be collinear,
            // since p1-p2 is parallel with p3-p4
            if (collinearityTestForP3 == 0) {
                // The lines are collinear
                        return true;
            }
            return false;
        }
        return true;
    }
    /**
     * Returns the intersection point if the lines intersect,
     * and null otherwise.
     * @param other , point that start thr line
     * @return Point
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            double det1And2 = det(this.start.getX(), this.start.getY(), this.end.getX(), this.end.getY());
            double det3And4 = det(other.start.getX(), other.start.getY(), other.end.getX(), other.end.getY());
            double x1LessX2 = this.start.getX() - this.end.getX();
            double y1LessY2 = this.start.getY() - this.end.getY();
            double x3LessX4 = other.start.getX() - other.end.getX();
            double y3LessY4 = other.start.getY() - other.end.getY();
            double det1Less2And3Less4 = det(x1LessX2, y1LessY2, x3LessX4, y3LessY4);
            if (det1Less2And3Less4 == 0) {
                // the denominator is zero so the lines are parallel and there's either
                // no solution (or multiple solutions if the lines overlap) so return null.
                return null;
            }
            double x = (det(det1And2, x1LessX2, det3And4, x3LessX4) / det1Less2And3Less4);
            double y = (det(det1And2, y1LessY2, det3And4, y3LessY4) / det1Less2And3Less4);
            return new Point(x, y);
        }
       return null;
    }
    /**
     * An auxiliary function for calculating the determinant according
     * to the mathematical formula.
     * @param a  a b c d are the Components of determinana.
     * @param b  a b c d are the Components of determinana.
     * @param c  a b c d are the Components of determinana.
     * @param d  a b c d are the Components of determinana.
     * @return det
     */
    private double det(double a , double b , double c , double d) {
        return a * d - b * c;
    }
    /**
     * equals -- return true is the lines are equal, false otherwise.
     * @param other , point that start thr line
     * @return true/ false
     */
    public boolean equals(Line other) {
        if (start.equals(other.start) && end.equals(other.end)) {
            return true;
        }
        return false;
    }
    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     * @param rect , rectangle received
     * @return point, closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        } else {
            Point closestIntersectionPoint = rect.intersectionPoints(this).get(0);
            for (int i = 0; i < rect.intersectionPoints(this).size(); i++) {
                if (this.start().distance(rect.intersectionPoints(this).get(i))
                        < this.start().distance(closestIntersectionPoint)) {
                    closestIntersectionPoint = rect.intersectionPoints(this).get(i);
                }
            }
            return closestIntersectionPoint;
        }
    }
    /**
     * This Method will check if the points are on the line.
     *
     * @param line - Gets the line to be checked.
     * @param point - Gets the Point to be checked.
     * @return true if the point is on the line
     * and false otherwise.
     */
    public static boolean checkPointOnLine(Line line, Point point) {

        double x1 = line.start().getX();
        double x2 = line.end().getX();
        double y1 = line.start().getY();
        double y2 = line.end().getY();
        //ptSegDist(double x1, double y1, double x2, double y2, double px, double py)
        //Returns the distance from a point to a line segment.
        if (Line2D.ptSegDist(x1, y1, x2, y2,
                point.getX(), point.getY()) < 0.001) {
            return true;
        }
        return false;
    }
    /**
     * This will call the draw Method for drawing
     * the sprite to the screen.
     * @param d the sprite to be drawn.
     */
    @Override
    public void drawOn(DrawSurface d) {
        if (!glitter) {
            d.setColor(color);
            d.drawLine((int) start().getX(), (int) start().getY(), (int) end().getX(), (int) end().getY());
        } else {
            Color[] colors = new Color[] {
                    Color.YELLOW, Color.ORANGE, new Color(0xFFCF25), new Color(0xFFEE87) };
            Color randomColor = colors[(int) (Math.random() * 4)];
            d.setColor(randomColor);
            d.drawLine((int) start().getX(), (int) start().getY(), (int) end().getX(), (int) end().getY());
        }
    }
    /**
     * Notify the Sprite that time has passed.
     * @param dt the frames per second.
     */
    @Override
    public void timePassed(double dt) {
    }

}