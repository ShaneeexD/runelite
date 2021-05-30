package net.runelite.client.plugins.craftingtraining;

import java.awt.image.BufferedImage;
import lombok.Getter;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.Counter;
import net.runelite.client.util.QuantityFormatter;

class craftedCounter extends Counter
{
    @Getter
    private final int itemID;
    private final String name;

    craftedCounter(Plugin plugin, int itemID, int count, String name, BufferedImage image)
    {
        super(image, plugin, count);
        this.itemID = itemID;
        this.name = name;
    }

    @Override
    public String getText()
    {
        return QuantityFormatter.quantityToRSDecimalStack(getCount());
    }

    @Override
    public String getTooltip()
    {
        return name;
    }
}
