package iafmagic.registry;

import iafmagic.iafmagic;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class biomes {
    private static final String MOD_ID = iafmagic.MOD_ID;
    // 生成群系
    // 火世界
    // SurfaceBuilder defines how the surface of your biome looks.
    // We use custom surface builder for our biome to cover surface with obsidians.
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> FireWorld_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(items.firegrassblock.getDefaultState(), Blocks.DIRT.getDefaultState(),
                    Blocks.GRAVEL.getDefaultState()));

    private static final net.minecraft.world.biome.Biome fireworld = createFireWorld();

    private static net.minecraft.world.biome.Biome createFireWorld() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(FireWorld_SURFACE_BUILDER);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return (new net.minecraft.world.biome.Biome.Builder())
                .precipitation(net.minecraft.world.biome.Biome.Precipitation.RAIN)
                .category(net.minecraft.world.biome.Biome.Category.NONE).depth(0.125F).scale(0.05F).temperature(4F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    public static final RegistryKey<net.minecraft.world.biome.Biome> FireWorld_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(MOD_ID, "_fireworld"));

    // 冰世界
    // SurfaceBuilder defines how the surface of your biome looks.
    // We use custom surface builder for our biome to cover surface with obsidians.
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> IceWorld_SURFACE_BUILDER = SurfaceBuilder.DEFAULT
            .withConfig(new TernarySurfaceConfig(items.icegrassblock.getDefaultState(), Blocks.DIRT.getDefaultState(),
                    Blocks.GRAVEL.getDefaultState()));

    private static final net.minecraft.world.biome.Biome iceworld = createIceWorld();

    private static net.minecraft.world.biome.Biome createIceWorld() {
        // We specify what entities spawn and what features generate in the biome.
        // Aside from some structures, trees, rocks, plants and
        // custom entities, these are mostly the same for each biome.
        // Vanilla configured features for biomes are defined in DefaultBiomeFeatures.

        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(spawnSettings);
        DefaultBiomeFeatures.addMonsters(spawnSettings, 95, 5, 100);

        GenerationSettings.Builder generationSettings = new GenerationSettings.Builder();
        generationSettings.surfaceBuilder(IceWorld_SURFACE_BUILDER);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(generationSettings);
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addDefaultLakes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);

        return (new net.minecraft.world.biome.Biome.Builder())
                .precipitation(net.minecraft.world.biome.Biome.Precipitation.RAIN)
                .category(net.minecraft.world.biome.Biome.Category.NONE).depth(0.125F).scale(0.05F).temperature(0.1F)
                .downfall(0.4F)
                .effects((new BiomeEffects.Builder()).waterColor(0x3f76e4).waterFogColor(0x050533).fogColor(0xc0d8ff)
                        .skyColor(0x77adff).build())
                .spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    public static final RegistryKey<net.minecraft.world.biome.Biome> IceWorld_KEY = RegistryKey.of(Registry.BIOME_KEY,
            new Identifier(MOD_ID, "_iceworld"));

    @Deprecated
    public static void init() {
        // 注册群系
        // 火世界
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MOD_ID, "_fireworld"),
                FireWorld_SURFACE_BUILDER);

        Registry.register(BuiltinRegistries.BIOME, FireWorld_KEY.getValue(), fireworld);
        // BuiltinBiomesAccessor.getRawIdMap().put(BuiltinRegistries.BIOME.getRawId(fireworld),
        // FireWorld_KEY);
        OverworldBiomes.addContinentalBiome(FireWorld_KEY, OverworldClimate.DRY, 0.75D);

        // 冰世界
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(MOD_ID, "_iceworld"),
                IceWorld_SURFACE_BUILDER);

        Registry.register(BuiltinRegistries.BIOME, IceWorld_KEY.getValue(), iceworld);
        // BuiltinBiomesAccessor.getRawIdMap().put(BuiltinRegistries.BIOME.getRawId(fireworld),
        // FireWorld_KEY);
        OverworldBiomes.addContinentalBiome(IceWorld_KEY, OverworldClimate.SNOWY, 0.75D);
    }

}
