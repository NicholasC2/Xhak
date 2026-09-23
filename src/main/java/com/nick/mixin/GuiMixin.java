package com.nick.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.nick.Xhak;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

@Mixin(Gui.class)
public class GuiMixin {

    @Inject(
        method = "extractRenderState",
        at = @At("TAIL")
    )
    private void afterExtract(
        DeltaTracker deltaTracker,
        boolean renderLevel,
        boolean gameLoadFinished,
        CallbackInfo ci
    ) {
        Xhak.getGui().render();
    }
}