package com.nick.gui;

import java.util.ArrayList;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.Window;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;

public class XhakMenu {
    public int x = 0;
    public int y = 0;
    public int width = 100;
    public int height = 150;

    public String name;

    public boolean open = true;

    public ArrayList<XhakOption<?>> options;

    public boolean dragging = false;
    public int dragOffsetX;
    public int dragOffsetY;

    public XhakMenu(ArrayList<XhakOption<?>> options, String name, int x, int y, int width, int height, boolean open) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.open = open;
        this.options = options;
        this.name = name;
    }

    public XhakMenu(ArrayList<XhakOption<?>> options, String name, int x, int y, int width, int height) {
        this(options, name, x, y, width, height, true);
    }

    public XhakMenu(ArrayList<XhakOption<?>> options, String name, int x, int y) {
        this(options, name, x, y, 100, 150);
    }

    public XhakMenu(ArrayList<XhakOption<?>> options2, String name) {
        this(options2, name, 0, 0, 100, 150);
    }

    public void render(GuiGraphicsExtractor graphics, XhakGui gui) {
        Font font = gui.getFont();

        // tab
        graphics.fill(
            x, 
            y, 
            x + width, 
            y + gui.tabHeight, 
            gui.tabColor
        );

        graphics.text(
            font, 
            name, 
            x + gui.tabHeight / 2 - font.lineHeight / 2, 
            y + gui.tabHeight / 2 - font.lineHeight / 2, 
            gui.textColor
        );

        if(!open) return;

        // background
        graphics.fill(
            x,
            y + gui.tabHeight,
            x + width,
            y + height,
            gui.backgroundColor
        );

        graphics.enableScissor(
            x, 
            y + gui.tabHeight, 
            x + width, 
            y + height
        );

        int currentYOffset = gui.tabHeight + gui.optionMargin;

        for (XhakOption<?> option : options) {
            option.render(
                graphics,
                gui,
                x + gui.optionMargin, 
                y + currentYOffset, 
                width - (gui.optionMargin * 2), 
                gui.optionHeight - (gui.optionMargin * 2)
            );

            currentYOffset += gui.optionHeight + gui.optionMargin;
        }

        graphics.disableScissor();
    }

    public boolean mouseClicked(XhakGui gui, MouseButtonEvent event) {
        if(event.x() >= x && event.x() <= x + width &&
           event.y() >= y && event.y() <= y + gui.tabHeight && 
           event.button() == InputConstants.MOUSE_BUTTON_LEFT) {

            dragOffsetX = (int) event.x() - x;
            dragOffsetY = (int) event.y() - y;
            dragging = true;
            return true;
        }

        if(!open) return false;

        int currentYOffset = gui.tabHeight;
        for (XhakOption<?> option : options) {
            if (event.x() >= x && event.x() <= x + width &&
                event.y() >= y + currentYOffset && event.y() <= y + currentYOffset + gui.optionHeight) {
                
                option.onClick(event);
                return true;
            }
            currentYOffset += gui.optionHeight;
        }
        return false;
    }

    public void mouseDragged(XhakGui gui, MouseButtonEvent event) {
        if (dragging) {
            Window window = Minecraft.getInstance().getWindow();

            x = (int) (event.x() - dragOffsetX);
            y = (int) (event.y() - dragOffsetY);

            if(x < -width / 2) x = -width / 2;
            if(y < 0) y = 0;

            if(x > window.getGuiScaledWidth() - width / 2) x = window.getGuiScaledWidth() - width / 2;
            if(y > window.getGuiScaledHeight() - gui.tabHeight) y = window.getGuiScaledHeight() - gui.tabHeight;
        }
    }

    public void mouseReleased(XhakGui gui, MouseButtonEvent event) {
        if (event.button() == InputConstants.MOUSE_BUTTON_LEFT) {
            dragging = false;
        }
    }
}
