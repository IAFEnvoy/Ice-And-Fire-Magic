package iafmagic.registry;

import iafmagic.iafmagic;
import iafmagic.arms.fire_materials;
import iafmagic.arms.fire_stick_materials;
import iafmagic.arms.ice_materials;
import iafmagic.arms.ice_stick_materials;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class items {
  public static final String MOD_ID = iafmagic.MOD_ID;

  // 物品声明
  public static final Item blood = new Item(new Item.Settings());
  public static final Item fire_blood = new Item(new Item.Settings());
  public static final Item ice_blood = new Item(new Item.Settings());
  public static final Item ruby = new Item(new Item.Settings());
  public static final Item sapphire = new Item(new Item.Settings());
  public static final Item original_gemstone = new Item(new Item.Settings());
  public static final Item fire_stick = new Item(new Item.Settings());
  public static final Item ice_stick = new Item(new Item.Settings());
  public static final Item lapis_stick = new Item(new Item.Settings());
  public static final Item redstone_stick = new Item(new Item.Settings());
  public static final Item snow_1 = new Item(new Item.Settings());
  public static final Item snow_2 = new Item(new Item.Settings());
  public static final Item snow_3 = new Item(new Item.Settings());
  public static final Item fire_dragon_breath = new Item(new Item.Settings());
  public static final Item ice_dragon_breath = new Item(new Item.Settings());

  public static ToolItem fire_sword = new SwordItem(fire_stick_materials.INSTANCE, 1, 3, new Item.Settings());
  public static ToolItem ice_sword = new SwordItem(ice_stick_materials.INSTANCE, 1, 3, new Item.Settings());

  public static ToolItem fire_knief = new SwordItem(fire_stick_materials.INSTANCE, 15, 6, new Item.Settings());
  public static ToolItem ice_knief = new SwordItem(ice_stick_materials.INSTANCE, 15, 6, new Item.Settings());

  // 方块声明
  // 声明和初始化我们的自定义方块实例。
  // 我们将方块材料设置为METAL（金属），需要镐来高效挖掘。
  // 硬度（hardness）代表破坏需要多久。石头的硬度为1.5f，黑曜石的硬度为50.0f。
  // 核心
  public static final Block sapphire_block = new Block(
      FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));
  public static final Block ruby_block = new Block(
      FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));

  // 群系物品物品
  public static final Block ruby_ore = new Block(
      FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));
  public static final Block sapphire_ore = new Block(
      FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));
  public static final Block original_ore = new Block(
      FabricBlockSettings.of(Material.METAL).hardness(5.0f).breakByTool(FabricToolTags.PICKAXES, 3));
  public static final Block originalgrassblock = new Block(
      FabricBlockSettings.of(Material.SOIL).hardness(0.1f).breakByHand(true));

  public static final ArmorMaterial Fire_Materials = new fire_materials();
  public static final Item Fire_Materials_HELMET = new ArmorItem(Fire_Materials, EquipmentSlot.HEAD,
      new Item.Settings());
  public static final Item Fire_Materials_CHESTPLATE = new ArmorItem(Fire_Materials, EquipmentSlot.CHEST,
      new Item.Settings());
  public static final Item Fire_Materials_LEGGINGS = new ArmorItem(Fire_Materials, EquipmentSlot.LEGS,
      new Item.Settings());
  public static final Item Fire_Materials_BOOTS = new ArmorItem(Fire_Materials, EquipmentSlot.FEET,
      new Item.Settings());

  public static final ArmorMaterial Ice_Materials = new ice_materials();
  public static final Item Ice_Materials_HELMET = new ArmorItem(Ice_Materials, EquipmentSlot.HEAD, new Item.Settings());
  public static final Item Ice_Materials_CHESTPLATE = new ArmorItem(Ice_Materials, EquipmentSlot.CHEST,
      new Item.Settings());
  public static final Item Ice_Materials_LEGGINGS = new ArmorItem(Ice_Materials, EquipmentSlot.LEGS,
      new Item.Settings());
  public static final Item Ice_Materials_BOOTS = new ArmorItem(Ice_Materials, EquipmentSlot.FEET, new Item.Settings());

  public static final Block firegrassblock = new Block(
      FabricBlockSettings.of(Material.GLASS).hardness(1.0f).breakByTool(FabricToolTags.SHOVELS, 3));
  public static final Block icegrassblock = new Block(
      FabricBlockSettings.of(Material.GLASS).hardness(1.0f).breakByTool(FabricToolTags.SHOVELS, 3));

  @Deprecated
  public static void inititems() {
    // 注册物品
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby"), ruby);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sapphire"), sapphire);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_stick"), fire_stick);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_stick"), ice_stick);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "lapis_stick"), lapis_stick);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "redstone_stick"), redstone_stick);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "snow_1"), snow_1);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "snow_2"), snow_2);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "snow_3"), snow_3);

    // 注册方块
    // 核心
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "sapphire_block"), sapphire_block);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sapphire_block"),
        new BlockItem(sapphire_block, new Item.Settings()));
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "ruby_block"), ruby_block);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby_block"),
        new BlockItem(ruby_block, new Item.Settings()));

    // 群系
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "ruby_ore"), ruby_ore);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ruby_ore"), new BlockItem(ruby_ore, new Item.Settings()));
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "sapphire_ore"), sapphire_ore);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "sapphire_ore"),
        new BlockItem(sapphire_ore, new Item.Settings()));

    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "blood"), blood);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_blood"), fire_blood);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_blood"), ice_blood);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_dragon_breath"), fire_dragon_breath);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_dragon_breath"), ice_dragon_breath);

    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_sword"), fire_sword);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_sword"), ice_sword);

    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_knief"), fire_knief);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_knief"), ice_knief);

    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_materials_helmet"), Fire_Materials_HELMET);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_materials_chestplate"), Fire_Materials_CHESTPLATE);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_materials_leggings"), Fire_Materials_LEGGINGS);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "fire_materials_boots"), Fire_Materials_BOOTS);

    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_materials_helmet"), Ice_Materials_HELMET);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_materials_chestplate"), Ice_Materials_CHESTPLATE);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_materials_leggings"), Ice_Materials_LEGGINGS);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "ice_materials_boots"), Ice_Materials_BOOTS);

    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "firegrassblock"), firegrassblock);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "firegrassblock"),
        new BlockItem(firegrassblock, new Item.Settings()));
    Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "icegrassblock"), icegrassblock);
    Registry.register(Registry.ITEM, new Identifier(MOD_ID, "icegrassblock"),
        new BlockItem(icegrassblock, new Item.Settings()));

    biomes.init();
    ores.init();
  }

  public static final ItemGroup _Weapon = FabricItemGroupBuilder.create(new Identifier(MOD_ID, "iafeweapon"))
      .icon(() -> new ItemStack(items.ruby)).appendItems(stacks -> {
        stacks.add(new ItemStack(items.ruby));
        stacks.add(new ItemStack(items.sapphire));
        stacks.add(new ItemStack(items.ruby_block));
        stacks.add(new ItemStack(items.sapphire_block));
        stacks.add(new ItemStack(items.ruby_ore));
        stacks.add(new ItemStack(items.sapphire_ore));
        stacks.add(new ItemStack(items.fire_stick));
        stacks.add(new ItemStack(items.ice_stick));
        stacks.add(new ItemStack(items.redstone_stick));
        stacks.add(new ItemStack(items.lapis_stick));
        stacks.add(new ItemStack(items.snow_1));
        stacks.add(new ItemStack(items.snow_2));
        stacks.add(new ItemStack(items.snow_3));

        stacks.add(new ItemStack(items.fire_sword));
        stacks.add(new ItemStack(items.ice_sword));
        stacks.add(new ItemStack(items.fire_knief));
        stacks.add(new ItemStack(items.ice_knief));

        stacks.add(new ItemStack(items.blood));
        stacks.add(new ItemStack(items.fire_blood));
        stacks.add(new ItemStack(items.ice_blood));
        stacks.add(new ItemStack(items.fire_dragon_breath));
        stacks.add(new ItemStack(items.ice_dragon_breath));

        stacks.add(new ItemStack(items.Fire_Materials_HELMET));
        stacks.add(new ItemStack(items.Fire_Materials_CHESTPLATE));
        stacks.add(new ItemStack(items.Fire_Materials_LEGGINGS));
        stacks.add(new ItemStack(items.Fire_Materials_BOOTS));
        stacks.add(new ItemStack(items.Ice_Materials_HELMET));
        stacks.add(new ItemStack(items.Ice_Materials_CHESTPLATE));
        stacks.add(new ItemStack(items.Ice_Materials_LEGGINGS));
        stacks.add(new ItemStack(items.Ice_Materials_BOOTS));

        stacks.add(new ItemStack(items.firegrassblock));
        stacks.add(new ItemStack(items.icegrassblock));
      }).build();
}
