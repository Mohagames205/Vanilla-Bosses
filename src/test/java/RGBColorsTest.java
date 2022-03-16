import net.md_5.bungee.api.ChatColor;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import shreb.me.vanillabosses.main.configHelpers.RGBColors;


public class RGBColorsTest {

    RGBColors color = new RGBColors("aa","12","11");

    @Test
    public void testSerialize(){

        Assert.assertEquals("#aa1211", color.serializeColor());

    }

    @Test
    public void testDeserialize() throws IllegalArgumentException {

        Assert.assertEquals(color.r, RGBColors.deserializeColor("#aa1211").r);
        Assert.assertEquals(color.g, RGBColors.deserializeColor("#aa1211").g);
        Assert.assertEquals(color.b, RGBColors.deserializeColor("#aa1211").b);

    }

    @Test
    public void testChatColorFromRGB(){

        ChatColor chatColor = color.chatColorFromRGB();

        Assert.assertEquals(chatColor, ChatColor.of("#aa1211"));

    }

}
