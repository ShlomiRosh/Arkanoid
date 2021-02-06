package io;

import sprite.Block;

/**
 * Created by shlomi rosh.
 */
public interface BlockCreator {
    /**
     * Create the block at the specified location.
     * @param xpos x coordinate
     * @param ypos y coordinate
     * @return new block
     */
    Block create(int xpos, int ypos);
}
