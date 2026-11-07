package com.example.actionpack;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

/** Sword that launches you forward when you right-click. */
public class DashBladeItem extends Item {
    public DashBladeItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Vec3 look = player.getLookAngle();

        player.setDeltaMovement(look.x * 2.2, 0.25 + Math.max(0.0, look.y) * 1.5, look.z * 2.2);
        player.hurtMarked = true; // sync the new velocity to the client

        if (!level.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.RESISTANCE, 15, 4));
            player.addEffect(new MobEffectInstance(MobEffects.SPEED, 60, 1));
            level.playSound(null, player.blockPosition(), SoundEvents.PLAYER_ATTACK_SWEEP,
                    SoundSource.PLAYERS, 1.0f, 1.0f);
        }
        player.getCooldowns().addCooldown(stack, 40);
        return InteractionResult.SUCCESS;
    }
}
