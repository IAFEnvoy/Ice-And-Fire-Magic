package iafmagic.entity.entity;

import iafmagic.client.EntitySpawnPacket;
import iafmagic.client.iafmagicClient;
import iafmagic.entity.registry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;

public class FireBallEntity extends ThrownItemEntity {

  public FireBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
    super(entityType, world);
  }

  public FireBallEntity(World world, LivingEntity owner) {
    super(registry.FireBall, owner, world);
  }

  public FireBallEntity(World world, double x, double y, double z) {
    super(registry.FireBall, x, y, z, world);
  }

  @Override
  protected Item getDefaultItem() {
    return registry.fireball;
  }

  protected void onEntityHit(EntityHitResult entityHitResult) { // called on entity hit.
    super.onEntityHit(entityHitResult);
    Entity entity = entityHitResult.getEntity(); // sets a new Entity instance as the EntityHitResult (victim)
    int i = entity instanceof BlazeEntity ? 0 : 3; // sets i to 3 if the Entity instance is an instance of BlazeEntity
    entity.damage(DamageSource.thrownProjectile(this, this.getOwner()), (float) i); // deals damage

    if (entity instanceof LivingEntity) {
      LivingEntity livingEntity = (LivingEntity) entity;
      livingEntity.setOnFireFor(10);
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
