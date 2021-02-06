package background;

import biuoop.DrawSurface;
import game.GameLevel;
import geometry.Point;
import sprite.Sprite;

import java.awt.*;

public class ExploPhotos implements Sprite {

    private Image image;
    private Point Location;

    public  ExploPhotos(Image image, geometry.Point point) {
        this.image = image;
        this.Location = point;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage((int) this.Location.getX(), (int) this.Location.getY(), this.image);
    }

    @Override
    public void timePassed(double dt) {

    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }


}

