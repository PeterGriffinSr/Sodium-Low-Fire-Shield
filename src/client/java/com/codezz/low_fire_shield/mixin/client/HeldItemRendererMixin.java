package com.codezz.low_fire_shield.mixin.client;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.util.Hand;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {
    @Unique
    private static final double SHIELD_BLOCKING_MAINHAND_Y_OFFSET = -0.25;
    @Unique
    private static final double SHIELD_BLOCKING_OFFHAND_Y_OFFSET = -0.2;
    @Unique
    private static final double SHIELD_IDLE_Y_OFFSET = -0.15;
    @Unique
    private static final double SHIELD_X_OFFSET = 0.0;
    @Unique
    private static final double SHIELD_Z_OFFSET = 0.0;

    @Unique
    private boolean low_fire_shield$shieldMatrixPushed = false;

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), require = 0)
    private void low_fire_shield$lowerShield1210(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, @NotNull ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!(item.getItem() instanceof ShieldItem)) {
            return;
        }

        try {
            matrices.push();
            low_fire_shield$shieldMatrixPushed = true;

            double yOffset = low_fire_shield$calculateShieldYOffset(player, hand);
            matrices.translate(SHIELD_X_OFFSET, yOffset, SHIELD_Z_OFFSET);
        } catch (Exception e) {
            if (low_fire_shield$shieldMatrixPushed) {
                matrices.pop();
                low_fire_shield$shieldMatrixPushed = false;
            }
            throw e;
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), require = 0)
    private void low_fire_shield$lowerShield1215(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, @NotNull ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!(item.getItem() instanceof ShieldItem)) {
            return;
        }

        try {
            matrices.push();
            low_fire_shield$shieldMatrixPushed = true;

            double yOffset = low_fire_shield$calculateShieldYOffset(player, hand);
            matrices.translate(SHIELD_X_OFFSET, yOffset, SHIELD_Z_OFFSET);
        } catch (Exception e) {
            if (low_fire_shield$shieldMatrixPushed) {
                matrices.pop();
                low_fire_shield$shieldMatrixPushed = false;
            }
            throw e;
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At("RETURN"), require = 0)
    private void low_fire_shield$restoreShieldMatrix(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (low_fire_shield$shieldMatrixPushed) {
            matrices.pop();
            low_fire_shield$shieldMatrixPushed = false;
        }
    }

    @Unique
    private double low_fire_shield$calculateShieldYOffset(@NotNull AbstractClientPlayerEntity player, Hand hand) {
        if (player.isUsingItem() && player.getActiveHand() == hand) {
            return hand == Hand.MAIN_HAND
                    ? SHIELD_BLOCKING_MAINHAND_Y_OFFSET
                    : SHIELD_BLOCKING_OFFHAND_Y_OFFSET;
        }
        return SHIELD_IDLE_Y_OFFSET;
    }
}