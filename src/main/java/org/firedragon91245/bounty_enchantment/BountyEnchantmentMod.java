package org.firedragon91245.bounty_enchantment;

import net.fabricmc.api.ModInitializer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BountyEnchantmentMod implements ModInitializer {

    public static final String MODID = "bounty_enchantment";
    public static final Logger LOG = LoggerFactory.getLogger(MODID);

    public static final RegistryKey<Enchantment> BOUNTY =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MODID, "bounty"));

    public static BountyEnchantmentConfig CONFIG;

    @Override
    public void onInitialize() {
        CONFIG = BountyEnchantmentConfig.loadOrCreate();
        LOG.info(
                "[{}] Initialized v{} / multipliers: {}",
                MODID,
                BountyEnchantmentConfig.MOD_VERSION,
                CONFIG.prettyMultipliers()
        );
    }
}
