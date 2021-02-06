/* name: shalomi rosh
   id: 308154418
   oop
*/
package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import game.Sound;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import io.ColorsParser;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.Image;
import java.awt.Color;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class will create the object Block.
 * @author shlomi rosh.
 */
public class Block implements Callable, Sprite, HitNotifier {

    private Rectangle rectangle;
    private String colorOrImageString;
    private Color strokeColor;
    private Integer hits;
    private List<HitListener> hitListeners;
    private HashMap<Integer, String> hashFill;
    private HashMap<Integer, Image> hashImage = new HashMap<Integer, Image>();
    private HashMap<Integer, Color> hashColor = new HashMap<Integer, Color>();
    private Image defaultImage = null;
    private Color defaultColor = null;

    /**
     * The Constructor for the block.

     * @param r receives the rectangle.
     * @param c receives the color for the block.
     * @param n is the counter of the hits.
     * @param s The color.
     * @param h The HashMap.
     */
    public Block(Rectangle r, String c, int n, Color s,
                 HashMap<Integer, String> h) {
        hashFill = h;
        strokeColor = s;
        rectangle = r;
        colorOrImageString = c;
        hits = n;
        this.hitListeners = new ArrayList<HitListener>();
        intializeHashMaps();
        initalizeDefaultColor();
    }
    /**
     * The copy Constructor for the block.
     * @param block receives the block for copy.
     */
    public Block(Block block) {
        hashFill = block.hashFill;
        strokeColor = block.strokeColor;
        rectangle = block.rectangle;
        colorOrImageString = block.colorOrImageString;
        hits = block.hits;
        this.hitListeners = new ArrayList<HitListener>();
        intializeHashMaps();
        initalizeDefaultColor();
    }


    /**
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @return the new velocity.
     * @param collisionPoint is the point of Collision.
     * @param currentVelocity is the current Velocity.
     * @param hitter ball.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        sound();
        if (hits > 0) {
            hits--;
        }
        int dx, dy;
        if (checkIfHitOnSide(collisionPoint)) {
            dx = -1;
            dy = 1;
        } else {
            dx = 1;
            dy = -1;
        }
        this.notifyHit(hitter);
        return new Velocity(currentVelocity.getDx() * dx, currentVelocity.getDy() * dy);

    }

    private void sound() {
        if (hits > 1 || this.rectangle.getCollisionRectangle().getWidth() > 750
                || this.rectangle.getCollisionRectangle().getHeight() > 550
                || this.rectangle.getCollisionRectangle().getUpperLeft().getY() > 590 ) {
            if (this.rectangle.getCollisionRectangle().getUpperLeft().getY() > 590) {

            } else if(hits > 1 || this.rectangle.getCollisionRectangle().getWidth() > 750
                    || this.rectangle.getCollisionRectangle().getHeight() > 550) {
                new Sound("WoodWhack.wav", 0).playSound();
            }
        } else if (hits == 1) {
            new Sound("1.wav", 0).playSound();
        }

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
     * This method will send the parameters of the block
     * to be drawn.
     * @param surface is the object received that will be drawn.
     */
    public void drawOn(DrawSurface surface) {

        if (hashFill != null) {
            if (hashImage.containsKey(hits)) {
                surface.drawImage((int) rectangle.getUpperLeft().getX(),
                        (int) rectangle.getUpperLeft().getY(),
                        hashImage.get(hits));
            } else if (hashFill.containsKey(hits)) {
                surface.setColor(hashColor.get(hits));
                surface.fillRectangle((int) this
                                .getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().
                                getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(),
                        (int) this.getCollisionRectangle().getHeight());
            } else {
                drawDefault(surface);
            }
        } else {
            drawDefault(surface);
        }
        if (strokeColor != null) {
            surface.setColor(strokeColor);
            surface.drawRectangle((int) this
                            .getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * Draws the Surface to the Gui.
     * @param surface the Surface to be drawn.
     */
    private void drawDefault(DrawSurface surface) {
        if (defaultImage != null) {
            surface.drawImage((int) rectangle.getUpperLeft().getX(),
                    (int) rectangle.getUpperLeft().getY(), defaultImage);
        } else {
            surface.setColor(defaultColor);
            surface.fillRectangle((int) this
                            .getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(),
                    (int) this.getCollisionRectangle().getHeight());
        }
    }

    /**
     * The timing method.
     * @param dt the frames per second.
     */
    @Override
    public void timePassed(double dt) {

    }
    /**
     * Will add the block to the List of Sprites
     * and the List of Collidables.
     * @param g is the game that the block will be sent to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Will remove the block from the List of Sprites
     * and from the List of Collidables.
     * @param game is the game that the block will be sent to.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * getting the hitter ball.
     * then notify to the block listeners about the hit.
     * @param hitter - the hiiting ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
    /**
     * removing a listener from the block listeners list.
     * @param hl - the listener to remove
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * set method.
     * @param hl remove a hit listener field.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * access method.
     * @return the block's hits
     */
    public Integer getHitPoints() {
        return hits;
    }
    /**
     * access method.
     * @return the block's color
     */
    public Color getColor() {
        if (defaultColor == null) {
            return hashColor.get(hashColor.size());
        }
        return defaultColor;
    }
    /**
     * Initialize the HashMaps.
     */
    private void intializeHashMaps() {
        if (hashFill != null) {
            for (int i = 1; i < 25; i++) {
                if (hashFill.containsKey(i)) {
                    String[] stringsArray = hashFill.get(i).split("\\(");
                    if (stringsArray[0].equals("image")) {
                        Image img = null;
                        String str = stringsArray[1].substring(0,
                                stringsArray[1].length() - 1);
                        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                        URL resource = classLoader.getResource(str);
                        ImageIcon imageIcon = new ImageIcon(resource);
                        img = imageIcon.getImage();
                        //img = ImageIO.read(new File("resources/" + str));
                        hashImage.put(i, img);
                    } else {
                        hashColor.put(i,
                                ColorsParser.colorFromString(hashFill.get(i)));
                    }
                }
            }
        }
    }
    /**
     * Method to initialize the Default color.
     */
    private void initalizeDefaultColor() {
        if (colorOrImageString != null) {
            String[] stringsArray = colorOrImageString.split("\\(");
            if (stringsArray[0].equals("image")) {
                Image img = null;
                String str = stringsArray[1].substring(0,
                        stringsArray[1].length() - 1);
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
                URL resource = classLoader.getResource(str);
                ImageIcon imageIcon = new ImageIcon(resource);
                img = imageIcon.getImage();
                //img = ImageIO.read(new File("resources/" + str));
                defaultImage = img;
            } else {
                defaultColor = ColorsParser.
                        colorFromString(colorOrImageString);
            }
        }
    }

}
