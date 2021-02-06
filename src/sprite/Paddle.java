/* name: shalomi rosh
   id: 308154418
   oop
*/
package sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameLevel;
import game.Sound;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;
/**
 * This class will create the Paddle object.
 * @author shlomi rosh.
 */
public class Paddle implements Sprite, Callable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private int speed;
    private int width;

    /**
     * Constructor of the Paddle.
     * @param keyboardSensor will receive the keyboard movements
     * from the user.
     * @param block will be used to build the rectangle
     * which is the paddle.
     */
    public Paddle(KeyboardSensor keyboardSensor, Rectangle block) {
        this.keyboard = keyboardSensor;
        this.paddle = block;
    }
    /**
     * Constructor of the Paddle.
     * @param keyboardSensor will receive the keyboard movements
     * from the user.
     * @param block will be used to build the rectangle
     * which is the paddle.
     * @param speed Getting the speed required for the Paddle
     * @param width Gets the length of the paddle
     */
    public Paddle(KeyboardSensor keyboardSensor, Rectangle block, int speed, int width) {
        this.keyboard = keyboardSensor;
        this.paddle = block;
        this.speed = speed;
        this.width = width;
    }
    /**
     * This method will move the paddle to the left.
     * @param dt the frames per second.
     */
    private void moveLeft(double dt) {
        if (this.getCollisionRectangle().getUpperLeft().getX() > 20) {
            paddle = new Rectangle(new Point(paddle.getCollisionRectangle().getUpperLeft().getX() - this.speed * dt,
                    paddle.getCollisionRectangle().getUpperLeft().getY()),
                    paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
        } else {
            paddle = new Rectangle(new Point(20, paddle.getCollisionRectangle().getUpperLeft().getY()),
                    paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
        }

    }
    /**
     * This method will move the paddle to the right.
     * @param dt the frames per second.
     */
    private void moveRight(double dt) {
        if (this.getCollisionRectangle().getUpperLeft().getX() < 780 - width) {
            paddle = new Rectangle(new Point(paddle.getCollisionRectangle().getUpperLeft().getX() + this.speed * dt,
                    paddle.getCollisionRectangle().getUpperLeft().getY()),
                    paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
        } else {
            paddle = new Rectangle(new Point(780 - width , paddle.getCollisionRectangle().getUpperLeft().getY()),
                    paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
        }
    }

    private void moveUp(double dt) {
        paddle = new Rectangle(new Point(paddle.getCollisionRectangle().getUpperLeft().getX(),
                paddle.getCollisionRectangle().getUpperLeft().getY() - this.speed * dt),
                paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
    }
    private void moveDown(double dt) {
        paddle = new Rectangle(new Point(paddle.getCollisionRectangle().getUpperLeft().getX(),
                paddle.getCollisionRectangle().getUpperLeft().getY() + this.speed * dt),
                paddle.getCollisionRectangle().getWidth(), paddle.getCollisionRectangle().getHeight());
    }

    /**
     * This will continuously check to see when a left or right
     * arrow is pressed by the user.
     * @param dt the frames per second.
     */
    public void timePassed(double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }

        if (this.keyboard.isPressed(KeyboardSensor.UP_KEY)) {
            this.moveUp(dt);
        }
        if (this.keyboard.isPressed(KeyboardSensor.DOWN_KEY)) {
            this.moveDown(dt);
        }

    }
    /**
     * This method will draw the paddle to the screen.
     * @param d will send the required info to the Draw method.
     */
    public void drawOn(DrawSurface d) {
        Rectangle rect = this.paddle.getCollisionRectangle();
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * acssor.
     * @return this paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * This method will change the velocity of the ball
     * depending on where on the paddle the ball hits.
     * @param collisionPoint will receive the point of the
     * the collidable to be checked.
     * @param currentVelocity will receive the current
     * velocity to be checked.
     * @return the new velocity.
     * @param hitter ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        new Sound("metalbat.wav", 0).playSound();
        double newSpeed = Math.sqrt((currentVelocity.getDx() * currentVelocity.getDx())
                + (currentVelocity.getDy() * currentVelocity.getDy()));
        int x, y;
        if (checkIfHitOnSide(collisionPoint)) {
            x = 1;
            y = -1;
        } else {
            x = 1;
            y = 1;
        }
        if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
                + this.getCollisionRectangle().getWidth() / 5) {
            currentVelocity = Velocity.fromAngleAndSpeed(300, newSpeed);
        } else if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
                + 2 * (this.getCollisionRectangle().getWidth() / 5)) {
            currentVelocity = Velocity.fromAngleAndSpeed(330, newSpeed);
        } else if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
                + 3 * (this.getCollisionRectangle().getWidth() / 5)) {
            return new Velocity(currentVelocity.getDx() * 1, currentVelocity.getDy() * -1);
        } else if (collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
                + 4 * (this.getCollisionRectangle().getWidth() / 5)) {
            currentVelocity = Velocity.fromAngleAndSpeed(30, newSpeed);
        } else {
            //(collisionPoint.getX() < this.getCollisionRectangle().getUpperLeft().getX()
            // + (this.getCollisionRectangle().getWidth()/5) * 5)
            currentVelocity = Velocity.fromAngleAndSpeed(60, newSpeed);
        }
        return new Velocity(currentVelocity.getDx() * x, currentVelocity.getDy() * y);
    }

    /**
     * see if the side of the block was hit.
     * @param collisionPoint receives the point.
     * @return either true or false.
     */
    public boolean checkIfHitOnSide(Point collisionPoint) {
        if ((Line.checkPointOnLine(getCollisionRectangle().getUpLine(), collisionPoint))
                || (Line.checkPointOnLine(getCollisionRectangle().getBottomLine(), collisionPoint))) {
            return false;
        }
        return true;
    }
    /**
     * Will add the paddle to Collidable and Sprite.
     * @param g is the game that will receive the paddle.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
    /**
     * Will remove the paddle from Collidable and Sprite.
     * @param g is the game that will receive the paddle.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }
}