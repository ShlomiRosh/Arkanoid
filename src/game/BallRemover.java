package game;

import sprite.Ball;
import sprite.Block;
import sprite.HitListener;
/**
 * Created by Shlomi Rosh.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter removedBalls;

    /**
     * Constructor Method for the Ball remover.
     * @param game the current game.
     * @param removedBalls the Ball counter.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.removedBalls = removedBalls;

    }

    /**
     * Check to see if an object gets it.
     * @param beingHit a block object being hit.
     * @param hitter A ball doing the hitting.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        removedBalls.decrease(1);
    }

}
