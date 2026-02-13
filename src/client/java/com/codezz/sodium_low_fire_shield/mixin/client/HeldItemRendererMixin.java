package com.codezz.sodium_low_fire_shield.mixin.client;

import com.codezz.sodium_low_fire_shield.config.SodiumLowFireShieldConfig;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void lowerShield(AbstractClientPlayerEntity player, float tickDelta, float pitch,
                             Hand hand, float swingProgress, ItemStack item, float equipProgress,
                             MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!SodiumLowFireShieldConfig.isLowShieldEnabled()) {
            return;
        }
        if (item.getItem() instanceof ShieldItem) {
            if (player.isUsingItem() && player.getActiveHand() == hand) {
                matrices.translate(0.0, -0.2, 0.0);
            } else {
                matrices.translate(0.0, -0.15, 0.0);
            }
        }
    }
}
