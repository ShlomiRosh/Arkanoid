package io;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * Color parsing class. Used for parsing formatted string to a color object.
 */
public class ColorsParser {
    /**
     * Parse color Definition and return the specified Color.
     * @param s the string to be parsed.
     * @return the specified Color.
     */
    public static Color colorFromString(String s) {
        String[] stringArr = s.split("[(|)]");
        if (stringArr[0].equals("color") && stringArr[1].equals("RGB")) {
            String[] col = stringArr[2].split(",");
            return new Color(Integer.parseInt(col[0]), Integer.parseInt(col[1]), Integer.parseInt(col[2]));
        } else if (stringArr[0].equals("color") && !stringArr[1].equals("RGB")) {
            Color color;
            try {
                Field field = Class.forName("java.awt.Color").getField(stringArr[1]);
                color = (Color) field.get(null);
            } catch (Exception e) {
                color = null; // Not defined
            }
            return color;
        }
        return null;
    }
}
