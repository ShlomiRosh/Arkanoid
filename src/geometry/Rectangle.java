/* name: shalomi rosh
   id: 308154418
   oop
*/
package geometry;

import java.util.ArrayList;
/**
 * This Object will create rectangles to be displayed
 * on the screen.
 * @author shlomi rosh.
 */
public class Rectangle {
    private Point upperLeft;
    private double width, height;
    private Line upLine, bottomLine, leftLine, rightLine;

    /**
     * Creates a new Rectangle with its corner, width and height.
     * @param upperLeft corner Coordinate of the rectangle.
     * @param width of the rectangle.
     * @param height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        upLine = new Line(this.getUpperLeft(),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()));
        bottomLine = new Line(new Point(this.getUpperLeft().getX(), this.getUpperLeft().getY()
                + this.getHeight()),
                new Point(this.getUpperLeft().getX() + this.getWidth(), this.getUpperLeft().getY()
                        + this.getHeight()));
        leftLine = new Line(upLine.start(), bottomLine.start());
        rightLine = new Line(upLine.end(), bottomLine.end());
    }

    /**
     * Return a (possibly empty) List of intersection points
     * with a specific line.
     * @param line to be checked against.
     * @return the list of intersection Points.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        ArrayList<Point> pointsIntersection = new ArrayList<Point>();
        if (upLine.isIntersecting(line)) {
            pointsIntersection.add(upLine.intersectionWith(line));
        }
        if (bottomLine.isIntersecting(line)) {
            pointsIntersection.add(bottomLine.intersectionWith(line));
        }
        if (rightLine.isIntersecting(line)) {
            pointsIntersection.add(rightLine.intersectionWith(line));
        }
        if (leftLine.isIntersecting(line)) {
            pointsIntersection.add(leftLine.intersectionWith(line));
        }
        return pointsIntersection;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return width;
    }
    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return height;
    }

    /**
     * @return the upper Left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }
    /**
     * @return the Top of the line.
     */
    public Line getUpLine() {
        return upLine;
    }
    /**
     * @return the Bottom of the line.
     */
    public Line getBottomLine() {
        return bottomLine;
    }
    /**
     * @return the Right Line.
     */
    public Line getRightLine() {
        return rightLine;
    }
    /**
     * @return the  Left Line.
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     * @return the  Rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this;
    }
}