package com.example.actionpack;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/** Staff that calls a line of three lightning bolts in front of you. */
public class ThunderStaffItem extends Item {
    public ThunderStaffItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (level instanceof ServerLevel serverLevel) {
            Vec3 look = player.getLookAngle();
            Vec3 dir = new Vec3(look.x, 0, look.z).normalize();
            for (int i = 1; i <= 3; i++) {
                Vec3 pos = player.position().add(dir.scale(3.0 + i * 3.0));
                LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(serverLevel, EntitySpawnReason.TRIGGERED);
                if (bolt != null) {
                    bolt.setPos(pos.x, pos.y, pos.z);
                    serverLevel.addFreshEntity(bolt);
                }
            }
        }
        player.getCooldowns().addCooldown(stack, 100);
        return InteractionResult.SUCCESS;
    }
}
