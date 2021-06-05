package net.runelite.client.plugins.craftingtraining;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import net.runelite.api.*;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;
import net.runelite.client.ui.overlay.infobox.Timer;


import javax.inject.Inject;
import java.awt.image.BufferedImage;


@PluginDescriptor(
        name = "Crafting Training",
        description = "Show training methods for crafting",
        tags = {"crafting", "", "", ""},
        loadWhenOutdated = true,
        enabledByDefault = false
)



public class craftingPlugin extends Plugin {

    int icon1;
    int icon2;
    int icon3;
    String methodtext;
    String itemtext1;
    String itemtext2;
    String itemtext3;
    String name1;
    String name2;
    String name3;
    String xpperhour;
    String location;
    int output;
    int craftingLevel;
    int profitability;
    @Inject
    private OverlayManager overlayManager;
    @Inject
    private craftingOverlay overlay;
    @Inject
    private clickboxOverlay clickboxoverlay;
    @Inject
    private craftingConfig config;
    @Inject
    private Client client;
    @Inject
    private InfoBoxManager infoBoxManager;
    @Inject
    private ItemManager itemManager;
    @Getter(AccessLevel.PACKAGE)
    @Setter
    private InfoBoxPriority priority;

    private craftedCounter counterBox;
    private craftedCounter counterBox2;
    private craftedCounter counterBox3;
    public static final int FURNACE = 16469;
    public static final int BANK = 10355;

    @Getter(AccessLevel.PACKAGE)
    private GameObject furnace;


    @Getter(AccessLevel.PACKAGE)
    private GameObject bank;

    @Provides
    craftingConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(craftingConfig.class);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.LEATHERGLOVES)
        {
            icon1 = 1734;
            name1 = "Thread";
            icon2 = 1741;
            name2 = "Leather";
            icon3 = 1733;
            name3 = "Needle";
            methodtext = "Leather Gloves";
            itemtext1 = "1x Thread (Per 4 Leather)";
            itemtext2 = "1x Leather";
            itemtext3 = "Needle";
            craftingLevel = 1;
            xpperhour = "25k";
            output = 1059;
            location = "Anywhere";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.GOLDBRACELET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 11065;
            name2 = "Bracelet Mould";
            methodtext = "Gold Bracelet";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "Bracelet Mould";
            craftingLevel = 7;
            xpperhour = "35k";
            output = 11068;
            location = "Edgeville";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.RUBYBRACELET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 1603;
            name2 = "Ruby";
            icon3 = 11065;
            name3 = "Bracelet Mould";
            methodtext = "Ruby Bracelet";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "1x Ruby";
            itemtext3 = "Bracelet Mould";
            craftingLevel = 42;
            xpperhour = "115k";
            output = 11085;
            location = "Edgeville";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.RUBYAMULET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 1603;
            name2 = "Ruby";
            icon3 = 1595;
            name3 = "Amulet Mould";
            methodtext = "Ruby Amulet (u)";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "1x Ruby";
            itemtext3 = "Amulet Mould";
            craftingLevel = 50;
            xpperhour = "125k";
            output = 1679;
            location = "Edgeville";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.DIABRACELET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 1601;
            name2 = "Diamond";
            icon3 = 11065;
            name3 = "Bracelet Mould";
            methodtext = "Diamond Bracelet";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "1x Diamond";
            itemtext3 = "Bracelet Mould";
            craftingLevel = 58;
            xpperhour = "140k";
            output = 11092;
            location = "Edgeville";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.GREENDHIDE)
        {
            icon1 = 1734;
            name1 = "Thread";
            icon2 = 1745;
            name2 = "Green Dragon Leather";
            icon3 = 1733;
            name3 = "Needle";
            methodtext = "Green D'Hide Body";
            itemtext1 = "1x Thread (Per 13 Hide)";
            itemtext2 = "1x Green Dragon Leather";
            itemtext3 = "Needle";
            craftingLevel = 63;
            xpperhour = "300k";
            output = 1135;
            location = "Anywhere";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.DIAAMULET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 1601;
            name2 = "Diamond";
            icon3 = 1595;
            name3 = "Amulet Mould";
            methodtext = "Diamond Amulet (u)";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "1x Diamond";
            itemtext3 = "Amulet Mould";
            craftingLevel = 70;
            xpperhour = "145k";
            output = 1681;
            location = "Edgeville";
        }
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.DSBRACELET)
        {
            icon1 = 2357;
            name1 = "Gold Bar";
            icon2 = 1615;
            name2 = "Dragonstone";
            icon3 = 11065;
            name3 = "Bracelet Mould";
            methodtext = "Dragonstone Bracelet";
            itemtext1 = "1x Gold Bar";
            itemtext2 = "1x Dragonstone";
            itemtext3 = "Bracelet Mould";
            craftingLevel = 74;
            xpperhour = "160k";
            output = 11115;
            location = "Edgeville";
        }


        if (location != "Edgeville")
        {
            overlayManager.remove(clickboxoverlay);
        }
        else
        {
            overlayManager.add(clickboxoverlay);
        }

        removeInfobox();
        updateInfobox();
    }


    @Subscribe
    public void onGameTick(GameTick event)
    {
        int itemcost1 = itemManager.getItemPriceWithSource(icon1, true);
        int itemcost2 = itemManager.getItemPriceWithSource(icon2, true);
        int itemsell = itemManager.getItemPriceWithSource(output, true);
        if (config.SelectTrainingMethod() == craftingConfig.OptionEnum.GOLDBRACELET) {
            profitability = itemsell - itemcost1;
        }
        else
            {
                profitability = itemsell - (itemcost1 + itemcost2);
            }




    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        GameObject gameObject = event.getGameObject();

            switch (gameObject.getId())
            {
                case FURNACE:
                    furnace = gameObject;
                    break;
                case BANK:
                    bank = gameObject;
                    break;
             }
    }

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned event)
    {
        GameObject gameObject = event.getGameObject();

        switch (gameObject.getId())
        {
            case FURNACE:
                furnace = null;
                break;

            case BANK:
                bank = null;
                break;
        }
    }


    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
        overlayManager.remove(clickboxoverlay);
        bank = null;
        furnace = null;
        removeInfobox();
    }

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
        overlayManager.add(clickboxoverlay);
        updateInfobox();
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOADING)
        {
            bank = null;
            furnace = null;
        }
    }

    private void updateInfobox()
    {
        final BufferedImage image1 = itemManager.getImage(icon1, 1, false);
        final BufferedImage image2 = itemManager.getImage(icon2, 1, false);
        if (config.SelectTrainingMethod() != craftingConfig.OptionEnum.GOLDBRACELET)
        {
            final BufferedImage image3 = itemManager.getImage(icon3, 1, false);
            counterBox3 = new craftedCounter(this, icon1, 1, name3, image3);
            counterBox2 = new craftedCounter(this, icon1, 1, name2, image2);
            infoBoxManager.addInfoBox(counterBox3);
        }
        else
            {
                counterBox2 = new craftedCounter(this, icon1, 1, name2, image2);
            }

        counterBox = new craftedCounter(this, icon1, 1, name1, image1);
        infoBoxManager.addInfoBox(counterBox);
        infoBoxManager.addInfoBox(counterBox2);
    }

    private void removeInfobox()
    {
        infoBoxManager.removeInfoBox(counterBox);
        infoBoxManager.removeInfoBox(counterBox2);
        infoBoxManager.removeInfoBox(counterBox3);
        counterBox = null;
    }

}