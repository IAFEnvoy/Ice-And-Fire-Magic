package iafmagic.entity.entity;

import iafmagic.client.EntitySpawnPacket;
import iafmagic.client.iafmagicClient;
import iafmagic.entity.registry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
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

  @Environment(EnvType.CLIENT)
  private ParticleEffect getParticleParameters() { // Not entirely sure, but probably has do to with the snowball's
                                                   // particles. (OPTIONAL)
    ItemStack itemStack = this.getItem();
    return (ParticleEffect) (itemStack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL
        : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack));
  }

  @Environment(EnvType.CLIENT)
  public void handleStatus(byte status) { // Also not entirely sure, but probably also has to do with the particles.
                                          // This method (as well as the previous one) are optional, so if you don't
                                          // understand, don't include this one.
    if (status == 3) {
      ParticleEffect particleEffect = this.getParticleParameters();

      for (int i = 0; i < 8; ++i) {
        this.world.addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
      }
    }

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

    world.createExplosion(null, entity.getX(),entity.getY(), entity.getZ(), 2.0F, DestructionType.BREAK);
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
