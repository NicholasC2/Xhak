package com.nick.gui;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;

public class XhakBooleanOption extends XhakOption<Boolean> {
    public XhakBooleanOption(String name, boolean value) {
        super(name, value);
    }

    @Override
    public void render(GuiGraphicsExtractor graphics, XhakGui gui, int x, int y, int width, int height) {
        Font font = gui.getFont();

        graphics.text(
            font, 
            getName(), 
            x, 
            y + height / 2 - font.lineHeight / 2, 
            gui.textColor
        );

        int boxSize = height;
        int boxX = x + width - boxSize;
        int boxY = y;

        graphics.outline(boxX, boxY, boxSize, boxSize, gui.textColor);

        if (!getValue()) return;

        int padding = boxSize / 4;

        graphics.fill(
            boxX + padding,
            boxY + padding,
            boxX + boxSize - padding,
            boxY + boxSize - padding,
            gui.textColor
        );
    }

    @Override 
    public void onClick(MouseButtonEvent event) {
        if(event.button() == InputConstants.MOUSE_BUTTON_LEFT) {
            setValue(!getValue());
        }
    }
}
