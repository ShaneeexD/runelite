package net.runelite.client.plugins.craftingtraining;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

import java.awt.*;


@ConfigGroup("craftingtraining")
    public interface craftingConfig extends Config
    {

        enum OptionEnum
        {
            LEATHERGLOVES,
            GOLDBRACELET,
            RUBYBRACELET,
            RUBYAMULET,
            DIABRACELET,
            GREENDHIDE,
            DIAAMULET,
            DSBRACELET,

        }
        @ConfigItem(
                position = 1,
                keyName = "methodConfig",
                name = "Training method",
                description = "Select a training method"
        )
        default OptionEnum SelectTrainingMethod() { return OptionEnum.GOLDBRACELET; }

        @ConfigItem(
                position = 2,
                keyName = "colorConfig",
                name = "Outline Color",
                description = "Color of the outlines"
        )
        default Color colorOutlines() { return Color.BLUE; }

        @ConfigItem
                (
                        position = 3,
                        keyName = "outlinesConfig",
                        name = "Toggle Outlines",
                        description = "Toggle on or off object outlines"
                )

        default boolean toggleOutlines() { return true; }
    }


