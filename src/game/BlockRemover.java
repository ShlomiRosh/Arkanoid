package game;

import background.ExploPhotos;
import geometry.Point;
import geometry.Rectangle;
import sprite.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * Created by Shlomi Rosh.
 * a BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * Constructor Method.
     *
     * @param game is the Game.
     * @param removedBlocks Counter to remove Blocks.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    /**
     * Method that will notify on Hit Events.
     * @param beingHit is the object that is being hit.
     * @param hitter is the object that is doing the Hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {

            int rend = (int) (Math.random() * 10 + 5);
            for (int i = 0; i < rend; i++) {
                BlockExplosion blockExplosion = new BlockExplosion(new SpecialBlocks(new Rectangle(
                        new Point(beingHit.getCollisionRectangle().getUpperLeft().getX(),
                                beingHit.getCollisionRectangle().getUpperLeft().getY()),
                        beingHit.getCollisionRectangle().getWidth(),
                        beingHit.getCollisionRectangle().getHeight())
                        , beingHit.getColor(), 1, true, false, false, false));
                blockExplosion.addToGame(game);
                blockExplosion.timePassed(1);

            }



            beingHit.removeFromGame(game);
            remainingBlocks.decrease(1);
        }
    }
}