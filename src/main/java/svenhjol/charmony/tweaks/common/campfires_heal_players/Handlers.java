package svenhjol.charmony.tweaks.common.campfires_heal_players;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import svenhjol.charmony.core.base.Setup;

public final class Handlers extends Setup<CampfiresHealPlayers> {
    public final int INTERVAL_CHECK = 200;
    public final int LENGTH_OF_REGENERATION = 2;
    public final int DISTANCE_TO_FIRE = 6;

    public Handlers(CampfiresHealPlayers feature) {
        super(feature);
    }

    public void tryRegeneratePlayersAroundFire(Level level, BlockPos pos) {
        if (level.getGameTime() % INTERVAL_CHECK == 0) {
            var bb = (new AABB(pos)).inflate(DISTANCE_TO_FIRE);
            var players = level.getEntitiesOfClass(Player.class, bb);
            var hostiles = level.getEntitiesOfClass(Monster.class, bb);
            if (!hostiles.isEmpty()) return;

            players.forEach(player -> {
                if (playerHasLineOfSight(player, pos) || playerHasLineOfSight(player, pos.above())) {
                    player.addEffect(new MobEffectInstance(MobEffects.REGENERATION, LENGTH_OF_REGENERATION * 20, 1));

                    if (!level.isClientSide()) {
                        feature().advancements.healedNearCampfire((ServerPlayer) player);
                    }
                }
            });
        }
    }

    private boolean playerHasLineOfSight(Player player, BlockPos target) {
        var playerVec = new Vec3(player.getX(), player.getEyeY(), player.getZ());
        var targetVec = new Vec3(target.getX(), target.getY(), target.getZ());
        var collision = player.level().clip(new ClipContext(playerVec, targetVec, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player)).getType();
        return collision == HitResult.Type.MISS;
    }
}
