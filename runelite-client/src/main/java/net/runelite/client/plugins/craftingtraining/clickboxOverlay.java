package net.runelite.client.plugins.craftingtraining;

import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.Point;
import net.runelite.api.coords.LocalPoint;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

public class clickboxOverlay extends Overlay
{

    private final Client client;
    private static final int MAX_DISTANCE = 2350;
    @Inject
    private craftingConfig config;
    @Inject
    private craftingPlugin plugin;

    @Inject
    private clickboxOverlay(Client client, craftingConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        this.client = client;
        this.config = config;

    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        if (config.toggleOutlines() == true)
        {
                    if (plugin.getFurnace() != null && plugin.getBank() != null)
                    {
                        Color color = config.colorOutlines();
                        renderObject(plugin.getFurnace(), graphics, color);
                        renderObject(plugin.getBank(), graphics, color);
                    }else{}
        }

        return null;
    }

    private void renderObject(GameObject object, Graphics2D graphics, Color color)
    {
        LocalPoint localLocation = client.getLocalPlayer().getLocalLocation();
        Point mousePosition = client.getMouseCanvasPosition();

        LocalPoint location = object.getLocalLocation();

        if (localLocation.distanceTo(location) <= MAX_DISTANCE)
        {
            Shape objectClickbox = object.getClickbox();
            if (objectClickbox != null)
            {
                if (objectClickbox.contains(mousePosition.getX(), mousePosition.getY()))
                {
                    graphics.setColor(color.darker());
                }
                else
                {
                    graphics.setColor(color);
                }
                graphics.draw(objectClickbox);
                graphics.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 20));
                graphics.fill(objectClickbox);
            }
        }
    }
}
