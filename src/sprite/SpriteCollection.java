/* name: shalomi rosh
   id: 308154418
   oop
*/
package sprite;

import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * This class will create the Sprite Collection object.
 * @author shlomi rosh.
 */
public class SpriteCollection {
    private ArrayList<Sprite> listOfSprite = new ArrayList<Sprite>();
    /**
     * Add sprite to the Array List.
     * @param s the sprite to add to the list.
     */
    public void addSprite(Sprite s) {
        listOfSprite.add(s);
    }

    /**
     * Call the timePassed method on all the Sprites.
     * @param dt the frames per second.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.listOfSprite.size(); i++) {
            Sprite call = this.listOfSprite.get(i);
            call.timePassed(dt);
        }
    }

    /**
     * Calls the drawOn method for all the sprites.
     * @param d the sprites to be draw to the screen.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < listOfSprite.size(); i++) {
            listOfSprite.get(i).drawOn(d);
        }
    }
    /**
     * remove sprite from the Array List.
     * @param s the sprite to remove to the list.
     */
    public void removeSprite(Sprite s) {
        this.listOfSprite.remove(s);
    }
}