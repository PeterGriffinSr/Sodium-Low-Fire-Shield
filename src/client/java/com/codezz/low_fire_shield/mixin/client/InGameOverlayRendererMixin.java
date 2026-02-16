package com.codezz.low_fire_shield.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Unique
    private static final double FIRE_OVERLAY_Y_OFFSET = -0.2;
    @Unique
    private static final double FIRE_OVERLAY_X_OFFSET = 0.0;
    @Unique
    private static final double FIRE_OVERLAY_Z_OFFSET = 0.0;

    @Inject(method = "renderFireOverlay",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
                    shift = At.Shift.AFTER), require = 0)
    private static void low_fire_shield$lowerFireOverlay1210(MinecraftClient client, @NotNull MatrixStack matrices, CallbackInfo ci) {
        matrices.translate(FIRE_OVERLAY_X_OFFSET, FIRE_OVERLAY_Y_OFFSET, FIRE_OVERLAY_Z_OFFSET);
    }

    @Inject(method = "renderFireOverlay",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;push()V",
                    shift = At.Shift.AFTER), require = 0)
    private static void low_fire_shield$lowerFireOverlay1215(MinecraftClient client, @NotNull MatrixStack matrices, CallbackInfo ci) {
        matrices.translate(FIRE_OVERLAY_X_OFFSET, FIRE_OVERLAY_Y_OFFSET, FIRE_OVERLAY_Z_OFFSET);
    }
}