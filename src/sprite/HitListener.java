package sprite;
/**
 * @author shlomi rosh.
 */
public interface HitListener {
    /**
     * @param beingHit the block bing hit.
     * @param hitter The hitter parameter is the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);

}