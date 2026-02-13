package com.codezz.sodium_low_fire_shield.sodium;

import com.codezz.sodium_low_fire_shield.config.SodiumLowFireShieldConfig;
import me.flashyreese.mods.sodiumextra.client.gui.options.storage.SodiumExtraOptionsStorage;
import net.caffeinemc.mods.sodium.client.gui.options.OptionGroup;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpact;
import net.caffeinemc.mods.sodium.client.gui.options.OptionImpl;
import net.caffeinemc.mods.sodium.client.gui.options.control.TickBoxControl;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SodiumExtraIntegration {
    public static OptionGroup createLowRenderGroup() {
        List<OptionImpl<?, ?>> options = new ArrayList<>();
        OptionImpl<?, ?> lowFireOption = OptionImpl.createBuilder(boolean.class, new SodiumExtraOptionsStorage())
                .setName(Text.translatable("sodium_low_fire_shield.option.low_fire"))
                .setTooltip(Text.translatable("sodium_low_fire_shield.option.low_fire.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (opts, value) -> SodiumLowFireShieldConfig.setLowFireEnabled(value),
                        (opts) -> SodiumLowFireShieldConfig.isLowFireEnabled()
                )
                .setImpact(OptionImpact.LOW)
                .build();
        OptionImpl<?, ?> lowShieldOption = OptionImpl.createBuilder(boolean.class, new SodiumExtraOptionsStorage())
                .setName(Text.translatable("sodium_low_fire_shield.option.low_shield"))
                .setTooltip(Text.translatable("sodium_low_fire_shield.option.low_shield.tooltip"))
                .setControl(TickBoxControl::new)
                .setBinding(
                        (opts, value) -> SodiumLowFireShieldConfig.setLowShieldEnabled(value),
                        (opts) -> SodiumLowFireShieldConfig.isLowShieldEnabled()
                )
                .setImpact(OptionImpact.LOW)
                .build();
        return OptionGroup.createBuilder()
                .add(lowFireOption)
                .add(lowShieldOption)
                .build();
    }
}
