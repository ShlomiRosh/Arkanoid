package sprite;

import biuoop.DrawSurface;
import geometry.Rectangle;
import java.awt.Color;
import java.util.Random;

/**
 * SpecialBlocks - borders and blocks that i creat.
 * @author shlomi rosh.
 */
public class SpecialBlocks extends Block {

    private Color color;
    private boolean filling = false, circleColor = false, glitter = false, lithColor = false;

    /**
     * The Constructor for the block.
     * @param r receives the rectangle.
     * @param n is the counter of the hits.
     * @param a filling.
     * @param b circleColor.
     * @param c glitter.
     * @param d lithColor.
     */
    public SpecialBlocks(Rectangle r,Color color, int n, boolean a, boolean b, boolean c, boolean d) {
        super(r, null, n, null, null);
        this.color = color;
        this.filling = a;
        this.circleColor = b;
        this.glitter = c;
        this.lithColor = d;
    }
    /**
     * This method will send the parameters of the block
     * to be drawn.
     * @param surface is the object received that will be drawn.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        if (filling) {
            surface.setColor(this.color);
            surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
        if (circleColor) {
            surface.setColor(Color.BLACK);
            surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
        if (glitter) {
            drawGlitter(surface);
        }
    }

    /**
     * Draws the litter blocks.
     * @param surface the Surface to be drawn.
     */
    private void drawGlitter(DrawSurface surface) {
        Random rand = new Random();
        if (filling) {
            if (lithColor) {
                Color[] colors = new Color[] {
                        Color.white, new Color(0xFFF2C3), new Color(0xF7FFB4)
                        , new Color(0xFAFFC4), new Color(0xDFFFB8)};
                Color randomColor = colors[(int) (Math.random() * 5)];
                surface.setColor(randomColor);
            } else {
                surface.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            }
            surface.fillRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
            if (!circleColor) {
                surface.setColor(color);
                surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
            } else {
                surface.setColor(Color.BLACK);
                surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                        (int) this.getCollisionRectangle().getUpperLeft().getY(),
                        (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
            }
        } else {
            surface.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            surface.drawRectangle((int) this.getCollisionRectangle().getUpperLeft().getX(),
                    (int) this.getCollisionRectangle().getUpperLeft().getY(),
                    (int) this.getCollisionRectangle().getWidth(), (int) this.getCollisionRectangle().getHeight());
        }
    }
    /**
     * access method.
     * @return the block's color
     */
    public Color getColor() {
        return this.color;
    }
}
