package io;

import background.Background;
import geometry.Velocity;
import sprite.Block;
import sprite.LevelInformation;
import sprite.Sprite;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the level specs file and makes a list of levels informations.
 * @author shlomi rosh.
 */
public class LevelSpecificationReader {

    /**
     * Reads the level specs file and makes a list of levels informations.
     * @param reader the level specs file reader
     * @return list of level informations
     * @throws IOException problem with the file
     */
    public static List<LevelInformation> fromReader(java.io.Reader reader) throws IOException {
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        BufferedReader info = new BufferedReader(reader);

        // Go over level sections and make levels
        String line = info.readLine();
        while (line != null) {
            if (skipOver(line)) {
                line = info.readLine();
                continue;
            }
            if (line.contentEquals("START_LEVEL")) {
                levels.add(creatLevelInfo(info));
                line = info.readLine();
            } else {
                throw new IOException("Filed in riding the file");
            }

        }
        return levels;
    }

    /**
     * Checks if the line is to be skipped.
     * @param specLine line to check
     * @return true/false
     */
    private static boolean skipOver(String specLine) {
        return specLine.startsWith("#") || specLine.trim().isEmpty();
    }

    /**
     * Reads a level from file and creates the level information object.
     * @param stream a reader to get level from
     * @return the prepared level information
     * @throws IOException problem reading file
     */
    private static LevelInformation creatLevelInfo(BufferedReader stream) throws IOException {
        String levelName = null;
        List<Velocity> velocities = null;
        Sprite background = null;
        Integer paddleSpeed = null;
        Integer paddleWidth = null;
        Integer blocksStartX = null;
        Integer blocksStartY = null;
        Integer rowHeight = null;
        Integer numBlocks = null;
        String blocksFile = null;
        List<Block> blocks = null;

        // Read lines until blocks section
        String line = stream.readLine();
        while (!line.contentEquals("START_BLOCKS")) {
            if (line == null) {
                throw new IOException("Illegal pattern");
            }
            if (skipOver(line)) {
                continue;
            }
            // Separate the tag and the data
            String[] lineSplit = splitParameter(line);
            String tag = lineSplit[0];
            String value = lineSplit[1];
            // Update data according to the tag
            if (tag.contentEquals("level_name")) {
                levelName = value;
            } else if (tag.contentEquals("ball_velocities")) {
                velocities = creatVelocity(value);
            } else if (tag.contentEquals("background")) {
                background = new Background(value);
            } else if (tag.contentEquals("paddle_speed")) {
                paddleSpeed = Integer.decode(value);
            } else if (tag.contentEquals("paddle_width")) {
                paddleWidth = Integer.decode(value);
            } else if (tag.contentEquals("block_definitions")) {
                blocksFile = value;
            } else if (tag.contentEquals("blocks_start_x")) {
                blocksStartX = Integer.decode(value);
            } else if (tag.contentEquals("blocks_start_y")) {
                blocksStartY = Integer.decode(value);
            } else if (tag.contentEquals("row_height")) {
                rowHeight = Integer.decode(value);
            } else if (tag.contentEquals("num_blocks")) {
                numBlocks = Integer.decode(value);
            } else {
                throw new IOException();
            }
            line = stream.readLine();
        }

        // Create list of blocks
         blocks = getBlocks(stream, blocksFile, blocksStartX, blocksStartY, rowHeight);

        // Read lines until end of level
        line = stream.readLine();
        while (!line.contentEquals("END_LEVEL")) {
            if (!skipOver(line)) {
                throw new IOException(); // lines after the end of BLOCKS section
            }
            line = stream.readLine();
        }

        // Create the new level from parsed data and return it
        return createNewLevel(velocities, paddleSpeed, paddleWidth,
                levelName, background, blocks, numBlocks);
    }

    /**
     * Creates a level information object with the specified params.
     * @param velocities class parameter
     * @param paddleSpeed class parameter
     * @param paddleWidth class parameter
     * @param levelName class parameter
     * @param background class parameter
     * @param blocks class parameter
     * @param numBlocks class parameter
     * @return new level information object
     */
    private static LevelInformation createNewLevel(final List<Velocity> velocities, final int paddleSpeed,
                                                   final int paddleWidth, final String levelName,
                                                   final Sprite background, final List<Block> blocks,
                                                   final int numBlocks) {
        return new LevelInformation() {
            public int numberOfBalls() {
                return initialBallVelocities().size();
            }

            public List<Velocity> initialBallVelocities() {
                return velocities;
            }

            public int paddleSpeed() {
                return paddleSpeed;
            }

            public int paddleWidth() {
                return paddleWidth;
            }

            public String levelName() {
                return levelName;
            }

            public Sprite getBackground() {
                return background;
            }

            public List<Block> blocks() {
                return blocks;
            }

            public int numberOfBlocksToRemove() {
                return numBlocks;
            }
        };
    }

    /**
     * Parses the velocities data as one string to a list of velocities.
     * @param stringToParse the velocities as a whole string
     * @return list of velocities
     */
    private static List<Velocity> creatVelocity(String stringToParse) {
        List<Velocity> ballVelocities = new ArrayList<Velocity>();
        // Get array of strings, each one representing one velocity
        String[] strVelocities = stringToParse.split(" ");
        // For each velocity - parse and add to list
        for (String strVelocity : strVelocities) {
            String[] separatedAngleAndSpeed = strVelocity.split(",");
            ballVelocities.add(Velocity.fromAngleAndSpeed(Double.parseDouble(separatedAngleAndSpeed[0]),
                    Double.parseDouble(separatedAngleAndSpeed[1])));
        }
        return ballVelocities;
    }

    /**
     * Splits a single parameter string to strings array.
     * @param parameter a parameter to split
     * @return array of 2 strings - parameter name and value
     * @throws IOException if thier is a problem.
     */
    private static String[] splitParameter(String parameter) throws IOException {
        String[] paramSplit = parameter.split(":");
        if (paramSplit.length != 2) {
            throw new IOException();
        }
        return paramSplit;
    }

    /**
     * Creates a list of blocks of the level.
     * @param stream the file to create blocks from
     * @param blocksFile the file of blocks definitions
     * @param blocksStartX start x position
     * @param blocksStartY start y position
     * @param rowHeight height of one block row
     * @return the list of blocks
     * @throws IOException problem reading from file
     */
    private static List<Block> getBlocks(BufferedReader stream, String blocksFile,
                                         int blocksStartX, int blocksStartY,
                                         int rowHeight) throws IOException {
        List<String> blockString = new ArrayList<String>();
        List<Block> blocks = new ArrayList<Block>();
        String line;
        int xPos = blocksStartX;
        int yPos = blocksStartY;
        int count = 0;

        // Read lines until end of blocks section
        line = stream.readLine();
        while (!line.contentEquals("END_BLOCKS")) {
            if (skipOver(line)) {
                line = stream.readLine();
                continue;

            }
            blockString.add(count, line);
            count++;
            line = stream.readLine();
        }
        Reader reader = new FileReader("re/" + blocksFile);
        BlocksFromSymbolsFactory factory;
        factory = BlocksDefinitionReader.fromReader(reader);

        for (int i = 0; i < blockString.size(); i++) {
            char[] lineBlocks = blockString.get(i).toCharArray();
            for (int j = 0; j < lineBlocks.length; j++) {
                if (factory.isBlockSymbol(Character.toString(lineBlocks[j]))) {
                    Block tempBlock = factory.getBlock(Character.
                            toString(lineBlocks[j]), xPos, yPos);
                    blocks.add(tempBlock);
                    xPos += tempBlock.getCollisionRectangle().getWidth();

                } else if (factory.isSpaceSymbol(Character.toString(lineBlocks[j]))) {
                    xPos += factory.getSpaceWidth(Character.toString(lineBlocks[j]));
                }
            }
            xPos = blocksStartX;
            yPos += rowHeight;
        }
        return blocks;
    }

}
