package io;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Reads and parses the blocks definitions file.
 */
public class BlocksDefinitionReader {
    /**
     * Constructor Method.
     * @param reader to read the file.
     * @return the factory.
     * @throws IOException if their is a problem.
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) throws IOException {
        BlocksFromSymbolsFactory factory = new BlocksFromSymbolsFactory();
        BufferedReader lineReader = new BufferedReader(reader);
        String line = "";
        HashMap<String, String> defaultMap = new HashMap<String, String>();
        try {
            while ((line = lineReader.readLine()) != null) {
                String[] lineArray = line.split(" ");
                if (lineArray[0].equals("default")) {
                    for (int i = 1;  i < lineArray.length; i++) {
                        String[] stringLine = lineArray[i].split(":");
                        defaultMap.put(stringLine[0], stringLine[1]);
                    }
                } else if (lineArray[0].equals("bdef")) {
                    HashMap<String, String> blockMap = new HashMap<String, String>(defaultMap);
                    for (int i = 1;  i < lineArray.length; i++) {
                        String[] stringLine = lineArray[i].split(":");
                        blockMap.put(stringLine[0], stringLine[1]);
                    }
                    int height = Integer.parseInt(blockMap.get("height"));
                    int width = Integer.parseInt(blockMap.get("width"));
                    int hitPoints =
                            Integer.parseInt(blockMap.get("hit_points"));
                    String fill = blockMap.get("fill");
                    Color stroke = null;
                    if (blockMap.containsKey("stroke")) {
                        stroke = ColorsParser.
                                colorFromString(blockMap.get("stroke"));
                    }
                    HashMap<Integer, String> fillHash = new
                            HashMap<Integer, String>();

                    for (int j = 1; j < 25; j++) {
                        if (blockMap.containsKey("fill-" + j)) {
                            fillHash.put(j, blockMap.get("fill-" + j));
                        }
                    }
                    BlockObject tempBlock;
                    tempBlock = new BlockObject(height,
                            width, hitPoints, fill, stroke, fillHash);
                    factory.addBlockCreator(blockMap.get("symbol"), tempBlock);
                } else if (lineArray[0].equals("sdef")) {
                    HashMap<String, String> spaceMap = new HashMap<String, String>(defaultMap);
                    for (int i = 1; i < lineArray.length; i++) {
                        String [] stringLine = lineArray[i].split(":");
                        spaceMap.put(stringLine[0], stringLine[1]);
                    }
                    int width = Integer.parseInt(spaceMap.get("width"));
                    factory.addSpacer(spaceMap.get("symbol"), width);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return factory;
    }
}
