package background;

import biuoop.DrawSurface;
import io.ColorsParser;
import sprite.Sprite;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.net.URL;

/**
 * The background of a level.
 * @author shlomi rosh.
 */
public class Background implements Sprite {
    private String str;

    /**
     * Constructor Method.
     * @param s the String.
     */
    public Background(String s) {
        this.str = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        String s = str;
        String [] stringsArray  = s.split("\\(");
        if (stringsArray[0].equals("image")) {
            String string = stringsArray[1].substring(0, stringsArray[1].length() - 1);
            Image img;
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            URL resource1 = classLoader1.getResource(string);
            ImageIcon imageIcon = new ImageIcon(resource1);
            img = imageIcon.getImage();
            d.drawImage(0, 0, img);
        } else {
            d.setColor(ColorsParser.colorFromString(s));
            d.fillRectangle(0, 0, 800, 600);
        }

    }

    @Override
    public void timePassed(double dt) {
    }
}


