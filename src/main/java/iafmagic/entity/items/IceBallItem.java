package iafmagic.entity.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import iafmagic.entity.entity.IceBallEntity;

public class IceBallItem extends Item {

  public IceBallItem(Settings settings) {
    super(settings);
  }

  public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
    ItemStack itemStack = user.getStackInHand(hand); // creates a new ItemStack instance of the user's itemStack in-hand
    world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW,
        SoundCategory.NEUTRAL, 0.5F, 1F); // plays a globalSoundEvent
    user.getItemCooldownManager().set(this, 20);
    if (!world.isClient) {
      IceBallEntity fireballEntity = new IceBallEntity(world, user);
      fireballEntity.setItem(itemStack);
      fireballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 0F);
      fireballEntity.setNoGravity(true);
      world.spawnEntity(fireballEntity); // spawns entity
    }

    user.incrementStat(Stats.USED.getOrCreateStat(this));
    if (!user.abilities.creativeMode) {
      itemStack.setDamage(itemStack.getDamage() + 1); // decrements itemStack if user is not in creative mode
      if (itemStack.getDamage() == itemStack.getMaxDamage())
        itemStack.decrement(1);
    }

    return TypedActionResult.success(itemStack, world.isClient());
  }
}
