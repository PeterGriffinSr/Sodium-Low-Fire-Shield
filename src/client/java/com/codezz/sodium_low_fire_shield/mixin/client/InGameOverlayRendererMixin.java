package com.codezz.sodium_low_fire_shield.mixin.client;

import com.codezz.sodium_low_fire_shield.config.SodiumLowFireShieldConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void cancelFireOverlay(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (!SodiumLowFireShieldConfig.isLowFireEnabled()) {
            return;
        }
        matrices.push();
        matrices.translate(0.0, -0.2, 0.0);
    }
    @Inject(method = "renderFireOverlay", at = @At("RETURN"))
    private static void popMatrix(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (!SodiumLowFireShieldConfig.isLowFireEnabled()) {
            return;
        }
        matrices.pop();
    }
}
