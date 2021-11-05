package iafmagic.entity.entity;

import iafmagic.client.EntitySpawnPacket;
import iafmagic.client.iafmagicClient;
import iafmagic.entity.registry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class IceBallEntity extends ThrownItemEntity {

  public IceBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
    super(entityType, world);
  }

  public IceBallEntity(World world, LivingEntity owner) {
    super(registry.IceBall, owner, world);
  }

  public IceBallEntity(World world, double x, double y, double z) {
    super(registry.IceBall, x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return registry.iceball;
  }

  protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
    super.onEntityHit(entityHitResult);
    Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
    entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), 3); // deals damage

    if (entity instanceof LivingEntity) {
      LivingEntity livingEntity = (LivingEntity) entity;
      world.setBlockState(livingEntity.getBlockPos(), Blocks.ICE.getDefaultState());
      world.setBlockState(livingEntity.getBlockPos().add(0, 1, 0), Blocks.ICE.getDefaultState());
      world.setBlockState(livingEntity.getBlockPos().add(0, 2, 0), Blocks.ICE.getDefaultState());
      livingEntity.addStatusEffect((new StatusEffectInstance(StatusEffects.SLOWNESS, 20 * 3, 2)));
    }
  }

  protected void onCollision(HitResult hitResult) { // called on collision with a block
    super.onCollision(hitResult);
    if (!this.world.isClient) {
      world.createExplosion(null, hitResult.getPos().x,hitResult.getPos().y, hitResult.getPos().z, 2.0F, DestructionType.BREAK);
      this.world.sendEntityStatus(this, (byte) 3); // particle?
      this.kill(); // kills the projectile
    }
  }

  @Override
  public Packet<?> createSpawnPacket() {
    return EntitySpawnPacket.create(this, iafmagicClient.PacketID);
  }
}
