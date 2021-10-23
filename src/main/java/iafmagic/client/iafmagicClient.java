package iafmagic.client;

import java.util.UUID;

import iafmagic.iafmagic;
import iafmagic.entity.registry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

@Environment(EnvType.CLIENT)
public class iafmagicClient implements ClientModInitializer {

  public static final Identifier PacketID = new Identifier(iafmagic.MOD_ID, "spawn_packet");

  @Override
  public void onInitializeClient() {
    EntityRendererRegistry.INSTANCE.register(registry.FireBall,
        (manager, context) -> new FlyingItemEntityRenderer<>(manager, context.getItemRenderer()));
    EntityRendererRegistry.INSTANCE.register(registry.IceBall,
        (manager, context) -> new FlyingItemEntityRenderer<>(manager, context.getItemRenderer()));
    receiveEntityPacket();
  }

  @Deprecated
  public void receiveEntityPacket() {
    ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
      EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
      UUID uuid = byteBuf.readUuid();
      int entityId = byteBuf.readVarInt();
      Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
      float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
      float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
      ctx.getTaskQueue().execute(() -> {
        if (MinecraftClient.getInstance().world == null)
          throw new IllegalStateException("Tried to spawn entity in a null world!");
        Entity e = et.create(MinecraftClient.getInstance().world);
        if (e == null)
          throw new IllegalStateException(
              "Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
        e.updateTrackedPosition(pos);
        e.setPos(pos.x, pos.y, pos.z);
        e.pitch = pitch;
        e.yaw = yaw;
        e.setEntityId(entityId);
        e.setUuid(uuid);
        MinecraftClient.getInstance().world.addEntity(entityId, e);
      });
    });
  }
}
