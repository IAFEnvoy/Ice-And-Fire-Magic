package iafmagic.registry;

import iafmagic.iafmagic;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class ores {
    private static final String MOD_ID = iafmagic.MOD_ID;
    // 矿石生成
    // 红宝石
    private static ConfiguredFeature<?, ?> ORE_Ruby_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    items.ruby_ore.getDefaultState(), 6)) // 矿脉大小
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, // 底部偏移量
                    0, // 最小生成高度
                    40))) // 最大生成高度
            .spreadHorizontally().repeat(1); // 每区块矿脉数

    // 蓝宝石
    private static ConfiguredFeature<?, ?> ORE_Sapphire_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    items.sapphire_ore.getDefaultState(), 6)) // 矿脉大小
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0, // 底部偏移量
                    0, // 最小生成高度
                    40))) // 最大生成高度
            .spreadHorizontally().repeat(1); // 每区块矿脉数

    @Deprecated
    public static void init() {
        // 注册矿石
        // 红宝石
        RegistryKey<ConfiguredFeature<?, ?>> oreRubyOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_ruby_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreRubyOverworld.getValue(), ORE_Ruby_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomes.FireWorld_KEY),
                net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES, oreRubyOverworld);

        // 蓝宝石
        RegistryKey<ConfiguredFeature<?, ?>> oreSapphireOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_sapphire_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreSapphireOverworld.getValue(),
                ORE_Sapphire_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(biomes.IceWorld_KEY),
                net.minecraft.world.gen.GenerationStep.Feature.UNDERGROUND_ORES, oreSapphireOverworld);
    }
}
