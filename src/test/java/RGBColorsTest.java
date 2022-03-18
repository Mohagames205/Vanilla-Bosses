import net.md_5.bungee.api.ChatColor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import shreb.me.vanillabosses.main.configHelpers.RGBColors;


public class RGBColorsTest {

    RGBColors color = new RGBColors("aa","12","11");

    @Test
    public void testSerialize(){

        Assertions.assertEquals("#aa1211", color.serializeColor());

    }

    @Test
    public void testDeserialize() throws IllegalArgumentException {

        Assertions.assertEquals(color.r, RGBColors.deserializeColor("#aa1211").r);
        Assertions.assertEquals(color.g, RGBColors.deserializeColor("#aa1211").g);
        Assertions.assertEquals(color.b, RGBColors.deserializeColor("#aa1211").b);

        Assertions.assertThrows(IllegalArgumentException.class, () -> RGBColors.deserializeColor("aa12333"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> RGBColors.deserializeColor("#1234567"));

    }

    @Test
    public void testChatColorFromRGB(){

        ChatColor chatColor = color.chatColorFromRGB();

        Assertions.assertEquals(chatColor, ChatColor.of("#aa1211"));

    }

}
