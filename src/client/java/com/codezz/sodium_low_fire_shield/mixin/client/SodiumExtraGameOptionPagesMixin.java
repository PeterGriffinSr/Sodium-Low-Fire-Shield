package com.codezz.sodium_low_fire_shield.mixin.client;

import com.codezz.sodium_low_fire_shield.sodium.SodiumExtraIntegration;
import com.google.common.collect.ImmutableList;
import me.flashyreese.mods.sodiumextra.client.gui.SodiumExtraGameOptionPages;
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionPage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(SodiumExtraGameOptionPages.class)
public class SodiumExtraGameOptionPagesMixin {
    @Inject(method = "render", at = @At("RETURN"), cancellable = true, remap = false)
    private static void addClearViewOptions(CallbackInfoReturnable<OptionPage> cir) {
        OptionPage originalPage = cir.getReturnValue();
        List<OptionGroup> groups = new ArrayList<>(originalPage.getGroups());
        groups.add(SodiumExtraIntegration.createLowRenderGroup());
        OptionPage newPage = new OptionPage(originalPage.getName(), ImmutableList.copyOf(groups));
        cir.setReturnValue(newPage);

    }
}
