package org.firedragon91245.bounty_enchantment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class BountyEnchantmentConfig {
    public static final String MOD_VERSION = "1.0.0";
    private static final Gson GSON =
            new GsonBuilder().setPrettyPrinting().create();

    public double[] levelMultipliers;
    public int maxXpCap;
    public boolean allowOffhand;
    public boolean debug;

    public static BountyEnchantmentConfig loadOrCreate() {
        Path cfgDir = FabricLoader.getInstance().getConfigDir();
        Path cfgPath = cfgDir.resolve("xpboost.json");

        if (!Files.exists(cfgPath)) {
            BountyEnchantmentConfig cfg = defaults();
            try {
                Files.createDirectories(cfgDir);
                try (BufferedWriter w = Files.newBufferedWriter(
                        cfgPath, StandardCharsets.UTF_8)) {
                    GSON.toJson(cfg, w);
                }
                BountyEnchantmentMod.LOG.info(
                        "[{}] Wrote default config at {}", BountyEnchantmentMod.MODID, cfgPath);
            } catch (IOException e) {
                BountyEnchantmentMod.LOG.error(
                        "[{}] Failed to write default config", BountyEnchantmentMod.MODID, e);
            }
            return cfg;
        }

        try (BufferedReader r = Files.newBufferedReader(
                cfgPath, StandardCharsets.UTF_8)) {
            BountyEnchantmentConfig cfg = GSON.fromJson(r, BountyEnchantmentConfig.class);
            return sanitize(cfg);
        } catch (Exception e) {
            BountyEnchantmentMod.LOG.error(
                    "[{}] Failed to read config; using defaults", BountyEnchantmentMod.MODID, e);
            return defaults();
        }
    }

    private static BountyEnchantmentConfig defaults() {
        BountyEnchantmentConfig c = new BountyEnchantmentConfig();
        c.levelMultipliers = new double[]{1.25, 1.5, 1.75, 2.0, 2.5};
        c.maxXpCap = 32767;
        c.allowOffhand = false;
        c.debug = false;
        return c;
    }

    private static BountyEnchantmentConfig sanitize(BountyEnchantmentConfig c) {
        if (c == null) return defaults();

        if (c.levelMultipliers == null || c.levelMultipliers.length != 5) {
            BountyEnchantmentMod.LOG.warn(
                    "[{}] levelMultipliers invalid; resetting to defaults", BountyEnchantmentMod.MODID);
            c.levelMultipliers = defaults().levelMultipliers;
        }
        for (int i = 0; i < c.levelMultipliers.length; i++) {
            if (Double.isNaN(c.levelMultipliers[i])
                    || Double.isInfinite(c.levelMultipliers[i])) {
                c.levelMultipliers[i] = 1.0;
            }
            if (c.levelMultipliers[i] < 0.0) c.levelMultipliers[i] = 0.0;
        }
        if (c.maxXpCap < 0) c.maxXpCap = defaults().maxXpCap;
        return c;
    }

    public String prettyMultipliers() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < levelMultipliers.length; i++) {
            sb.append(levelMultipliers[i]);
            if (i + 1 < levelMultipliers.length) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
