package sprite;

import game.Counter;
/**
 * @author shlomi rosh.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Listener for tracking the scores during the game.
     * @param scoreCounter the counter that will keep the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /**
     * Method that will notify at the event of an object being hit.
     * @param beingHit the object being hit.
     * @param hitter the ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
        this.currentScore.increase(5);
    }
}
