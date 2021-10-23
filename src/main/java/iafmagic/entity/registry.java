package iafmagic.entity;

import iafmagic.iafmagic;
import iafmagic.entity.entity.FireBallEntity;
import iafmagic.entity.items.FireBallItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class registry {
  public static final EntityType<FireBallEntity> FireBall = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(iafmagic.MOD_ID, "fire_ball"),
			FabricEntityTypeBuilder.<FireBallEntity>create(SpawnGroup.MISC, FireBallEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);
  public static final Item fireball=new FireBallItem(new Item.Settings().maxDamage(200));

  public static final EntityType<FireBallEntity> IceBall = Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(iafmagic.MOD_ID, "ice_ball"),
			FabricEntityTypeBuilder.<FireBallEntity>create(SpawnGroup.MISC, FireBallEntity::new)
					.dimensions(EntityDimensions.fixed(0.25F, 0.25F)) // dimensions in Minecraft units of the projectile
					.trackRangeBlocks(4).trackedUpdateRate(10) // necessary for all thrown projectiles (as it prevents it from breaking, lol)
					.build() // VERY IMPORTANT DONT DELETE FOR THE LOVE OF GOD PSLSSSSSS
	);
  public static final Item iceball=new FireBallItem(new Item.Settings().maxDamage(200));

  public static void init(){
    Registry.register(Registry.ITEM, new Identifier(iafmagic.MOD_ID, "fireball"), fireball);
    Registry.register(Registry.ITEM, new Identifier(iafmagic.MOD_ID, "iceball"), iceball);
  }
}
