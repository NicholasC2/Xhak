package com.nick.gui;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class XhakScreen extends Screen {
    private final XhakGui gui;

    public XhakScreen(XhakGui gui) {
        super(Component.literal("Xhak Menu"));
        this.gui = gui;
    }
    
    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);
        
        gui.render(graphics);
    }

    @Override
    public boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        for (XhakMenu menu : gui.menus) {
            if (menu.mouseClicked(gui, event)) {
                return true;
            }
        }
        return super.mouseClicked(event, doubleClick);
    }

    @Override
    public boolean mouseReleased(@NonNull MouseButtonEvent event) {
        for(XhakMenu menu : gui.menus) {
            menu.mouseReleased(gui, event);
        }
        return super.mouseReleased(event);
    }

    @Override
    public boolean mouseDragged(@NonNull MouseButtonEvent event, double dragX, double dragY) {
        for(XhakMenu menu : gui.menus) {
            menu.mouseDragged(gui, event);
        }
        return super.mouseDragged(event, dragX, dragY);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
