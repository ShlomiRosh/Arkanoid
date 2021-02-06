package sprite;

import geometry.Velocity;
import java.util.List;
/**
 * @author shlomi rosh.
 */
public interface LevelInformation {
    /**
     * access method.
     * @return - the number of ball that should be on the game.
     */
    int numberOfBalls();
    // The initial velocity of each ball
    // Note that initialBallVelocities().size() == numberOfBalls()
    /**
     * creating list that old velocity for the game balls.
     * @return - the velocity list.
     */
    List<Velocity> initialBallVelocities();
    /**
     * access method.
     * @return - the paddle speed.
     */
    int paddleSpeed();
    /**
     * access method.
     * @return - the paddle width.
     */
    int paddleWidth();
    /**
     * access method.
     * @return - the paddle Start.
     */
   // int paddleStart();
    // the level name will be displayed at the top of the screen.
    /**
     * access method.
     * @return - the levelName.
     */
    String levelName();
    // Returns a sprite with the background of the level
    /**
     * access method.
     * @return - the Background.
     */
    Sprite getBackground();
    // The Blocks that make up this level, each block contains
    // its size, color and location.
    /**
     * access method.
     * @return - the blockList.
     */
    List<Block> blocks();
    // Number of levels that should be removed
    // before the level is considered to be "cleared".
    // This number should be <= blocks.size();
    /**
     * access method.
     * @return - the numberOfBlocksToRemove.
     */
    int numberOfBlocksToRemove();
    /**
     * access method.
     * @return the backColor.
     */
    //Color colorBack();
}