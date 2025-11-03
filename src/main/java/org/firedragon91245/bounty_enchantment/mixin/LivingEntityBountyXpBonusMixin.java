package org.firedragon91245.bounty_enchantment.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.firedragon91245.bounty_enchantment.BountyEnchantmentMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class LivingEntityBountyXpBonusMixin {

    @Redirect(
            method = "dropXp",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/entity/ExperienceOrbEntity;spawn(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/Vec3d;I)V"
            )
    )
    private void bounty$spawnWithBonus(ServerWorld world, net.minecraft.util.math.Vec3d pos, int vanillaAmount) {
        int resultAmount = vanillaAmount;

        LivingEntity self = (LivingEntity) (Object) this;
        if (world == null || vanillaAmount <= 0) {
            ExperienceOrbEntity.spawn(world, pos, resultAmount);
            return;
        }

        LivingEntity attacker = self.getAttacker();
        if (attacker instanceof PlayerEntity killer) {

            // Resolve our enchant registry entry from the world's dynamic registries
            Registry<Enchantment> enchantReg =
                    world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);

            Optional<RegistryEntry.Reference<Enchantment>> bountyOpt =
                    enchantReg.getEntry(BountyEnchantmentMod.BOUNTY.getValue());

            if (bountyOpt.isPresent()) {
                RegistryEntry<Enchantment> bounty = bountyOpt.get();

                int level;
                if (BountyEnchantmentMod.CONFIG.allowOffhand) {
                    level = EnchantmentHelper.getEquipmentLevel(bounty, killer);
                } else {
                    ItemStack main = killer.getMainHandStack();
                    level = EnchantmentHelper.getLevel(bounty, main);
                }

                if (level > 0) {
                    double[] multipliers = BountyEnchantmentMod.CONFIG.levelMultipliers;

                    if (level > multipliers.length) level = multipliers.length - 1;

                    double multiplier = multipliers[level - 1];

                    long newAmount = getNewAmount(vanillaAmount, multiplier);
                    resultAmount = (int) newAmount;

                    if (BountyEnchantmentMod.CONFIG.debug) {
                        BountyEnchantmentMod.LOG.info(
                                "[XPBoost] {} base={} level={} multiplier={} result={}",
                                self.getName().getString(),
                                vanillaAmount,
                                level,
                                multiplier,
                                resultAmount
                        );
                    }
                }
            }
        }

        ExperienceOrbEntity.spawn(world, pos, resultAmount);
    }

    @Unique
    private static long getNewAmount(int vanillaAmount, double multiplier) {
        long capped = Math.round(vanillaAmount * (multiplier - 1.0));
        if (BountyEnchantmentMod.CONFIG.maxXpCap > 0 && capped > BountyEnchantmentMod.CONFIG.maxXpCap) {
            capped = BountyEnchantmentMod.CONFIG.maxXpCap;
        }
        long newAmount = vanillaAmount + Math.max(0, capped);
        // clamp to int range for safety
        if (newAmount > Integer.MAX_VALUE) newAmount = Integer.MAX_VALUE;
        return newAmount;
    }
}
