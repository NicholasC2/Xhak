package com.nick.gui;

import java.util.ArrayList;

import org.jspecify.annotations.NonNull;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.ARGB;

public class XhakGui {
    private Font font;
    public ArrayList<XhakMenu> menus;

    public int optionMargin = 4;
    public int optionHeight = 20;

    public int tabHeight = 10;

    public int tabColor = ARGB.color(200, 50, 50, 75);
    public int backgroundColor = ARGB.color(200, 50, 50, 60);
    public int textColor = ARGB.color(200, 255, 255, 255);

    public XhakGui(ArrayList<XhakMenu> menus, Font font) {
        this.menus = menus;
        this.font = font;
    }

    public XhakGui(ArrayList<XhakMenu> menus) {
        this(menus, null);
    }

    public @NonNull Font getFont() {
        if(font == null) {
            return Minecraft.getInstance().font;
        }
        return font;
    }

    public void render(GuiGraphicsExtractor graphics) {
        for (XhakMenu menu : menus) {
            menu.render(graphics, this);
        }
    }
}