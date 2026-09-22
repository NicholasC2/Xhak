package com.nick;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mojang.blaze3d.platform.InputConstants;
import com.nick.gui.XhakGui;
import com.nick.gui.XhakScreen;
import com.nick.gui.options.XhakBooleanOption;
import com.nick.gui.XhakMenu;
import com.nick.gui.XhakOption;

public class Xhak implements ClientModInitializer {
	public static final String MOD_ID = "xhak";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static KeyMapping menuKey;

	private static XhakGui gui;

	@Override
	public void onInitializeClient() {
		KeyMapping.Category menuCategory = KeyMapping.Category.register(
			Identifier.fromNamespaceAndPath(MOD_ID, "menu")
		);

		menuKey = new KeyMapping(
			"key.xhak.menu.open", 
			InputConstants.Type.KEYBOARD, 
			InputConstants.KEY_RCONTROL, 
			menuCategory
		);

		KeyMappingHelper.registerKeyMapping(menuKey);

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (menuKey.consumeClick()) {
				client.setScreenAndShow(new XhakScreen(getGui()));
			}
		});

		LOGGER.info("Xhak Loaded");
	}

	public static XhakGui getGui() {
        if (gui == null) {
			ArrayList<XhakMenu> menus = new ArrayList<>();
			ArrayList<XhakOption<?>> options = new ArrayList<>();

			options.add(new XhakBooleanOption("test", false));

			menus.add(new XhakMenu(options, "test"));
			
			gui = new XhakGui(menus);
        }
        return gui;
    }
}
