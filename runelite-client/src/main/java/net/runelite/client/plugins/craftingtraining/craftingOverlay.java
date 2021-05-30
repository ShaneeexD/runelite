package net.runelite.client.plugins.craftingtraining;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.*;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.*;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

import javax.inject.Inject;
import java.awt.*;


public class craftingOverlay extends Overlay
{

    private final Client client;
    private final craftingConfig config;
    private final PanelComponent panelComponent = new PanelComponent();
    @Inject
    private craftingPlugin plugin;

    @Inject
    private craftingOverlay(Client client, craftingConfig config)
    {
        setPosition(OverlayPosition.TOP_LEFT);
        this.client = client;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {

        panelComponent.getChildren().clear();
        String overlayTitle = "Crafting";
        panelComponent.getChildren().add(TitleComponent.builder()
                .text(overlayTitle)
                .color(Color.GREEN)
                .build());
        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(overlayTitle) + 100,
                0));
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Method: " + plugin.methodtext)
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Crafting level: " + plugin.craftingLevel)
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("                   ")
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Items Needed:")
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left(plugin.itemtext1)
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left(plugin.itemtext2)
                .build());
        if (config.SelectTrainingMethod() != craftingConfig.OptionEnum.GBracelet)
        {
            panelComponent.getChildren().add(LineComponent.builder()
                    .left(plugin.itemtext3)
                    .build());
        }else
        {}
        panelComponent.getChildren().add(LineComponent.builder()
                .left("                   ")
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Xp/hr: " + plugin.xpperhour)
                .build());
        panelComponent.getChildren().add(LineComponent.builder()
                .left("Profit per item: " + plugin.profitability)
                .build());

        return panelComponent.render(graphics);

    }

}
