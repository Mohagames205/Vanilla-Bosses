package shreb.me.vanillabosses.main.configHelpers;

import net.md_5.bungee.api.ChatColor;

import java.io.IOException;
import java.io.Serializable;

public class RGBColors implements Serializable {

    public String r;
    public String g;
    public String b;

    public RGBColors(String r, String g, String b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * @return the Hex String of the RGBColors object
     */
    public String serializeColor() {
        return "#" + r + g + b;
    }

    /**
     * This method makes a RGBColors object out of a fitting String.
     * if you serialize an RGBColors object and use this method to deserialize it again you will get the original Object back.
     *
     * @param color String from config with 6 characters representing a hex value
     * @return an RGBColors Object with the values from the String
     * @throws IllegalArgumentException if the provided String is too short (less than 6 characters)
     */
    public static RGBColors deserializeColor(String color) throws IllegalArgumentException {

        if(color.length() < 7 || !color.startsWith("#")) throw new IllegalArgumentException("Provided String too short!");

       return new RGBColors(
                String.valueOf(color.charAt(1)) + color.charAt(2),
                String.valueOf(color.charAt(3)) + color.charAt(4),
                String.valueOf(color.charAt(5)) + color.charAt(6));
    }

    /**
     * @return a ChatColor Object from an RGBColors Object
     */
    public ChatColor chatColorFromRGB() {

        return net.md_5.bungee.api.ChatColor.of( "#" + r + g + b);

    }

}
