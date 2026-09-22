package com.nick.gui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;

public abstract class XhakOption<T> {
    private String name;
    private T value;
    
    public XhakOption(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public abstract void render(GuiGraphicsExtractor graphics, XhakGui gui, int x, int y, int width, int height);

    public abstract void onClick(MouseButtonEvent event);
}
