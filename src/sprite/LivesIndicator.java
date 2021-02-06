package sprite;

import biuoop.DrawSurface;
import game.Counter;
import game.GameLevel;
import java.awt.Color;
/**
 * @author shlomi rosh.
 */
public class LivesIndicator implements Sprite {
    private Counter live;

    /**
     * the constructor.
     * setting the fields.
     * @param liveCounter - theLive tracking counter
     */
    public LivesIndicator(Counter liveCounter) {
        this.live = liveCounter;
    }

    /**
     * draw the sprite to the screen.
     * @param d - the DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        for (int i = 0; i < this.live.getValue(); i++) {
            d.setColor(Color.BLUE);
            d.drawText(150, 15, "lives:", 15);
            d.setColor(Color.RED);
            d.drawText(152, 16, "lives:", 15);
            if (this.live.getValue() == 1) {
                Color[] colors = new Color[] {
                        Color.red, new Color(0xFF5B17), new Color(0xFF5B20)
                        , new Color(0xFF4366), new Color(0xFF6C4A), Color.BLACK};
                Color randomColor = colors[(int) (Math.random() * 6)];
                d.setColor(randomColor);
            } else {
                d.setColor(Color.RED);
            }
            d.drawText(200 + (20 * i), 17, String.valueOf(Character.toChars(0x2764)), 20);
        }
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the frames per second.
     */
    public void timePassed(double dt) {

    }

    /**
     * adding this class to a given game.
     * @param game - the given game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
