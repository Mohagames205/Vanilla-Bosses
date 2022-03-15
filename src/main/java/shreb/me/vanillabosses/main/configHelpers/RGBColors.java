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

    public String serializeColor(RGBColors color) {

        return color.r + color.g + color.b;

    }

    /**
     *
     * @param color String from config with 6 characters representing a hex value
     * @return an RGBColors Object with the values from the String
     * @throws IOException if the provided String is too short (less than 6 characters)
     */
    public RGBColors deserializeColor(String color) throws IOException {

        if(color.length() < 6) throw new IOException("Provided String too short!");

       return new RGBColors(
                String.valueOf(color.charAt(0) + color.charAt(1)),
                String.valueOf(color.charAt(2) + color.charAt(3)),
                String.valueOf(color.charAt(4) + color.charAt(5)));
    }

    public ChatColor chatColorFromRGB(RGBColors color) {

        return net.md_5.bungee.api.ChatColor.of(color.r + color.g + color.b);

    }

}
