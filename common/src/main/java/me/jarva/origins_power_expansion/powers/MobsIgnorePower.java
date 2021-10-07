package me.jarva.origins_power_expansion.powers;

import io.github.apace100.origins.power.Power;
import io.github.apace100.origins.power.PowerType;
import io.github.apace100.origins.power.factory.PowerFactory;
import io.github.apace100.origins.util.SerializableData;
import io.github.apace100.origins.util.SerializableDataType;
import me.jarva.origins_power_expansion.OriginsPowerExpansion;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;
import java.util.function.Predicate;

@SuppressWarnings({"UnstableApiUsage", "deprecation"})
public class MobsIgnorePower extends Power {
    private final HashSet<EntityType<?>> mobTypes = new HashSet<>();
    private final Predicate<LivingEntity> mobCondition;

    public MobsIgnorePower(PowerType<?> type, PlayerEntity player, Predicate<LivingEntity> mobCondition) {
        super(type, player);
        this.mobCondition = mobCondition;
    }

    public boolean shouldIgnore(LivingEntity mob) {
        return this.mobCondition.test(mob);
    }

    public static PowerFactory<?> getFactory() {
        return new PowerFactory<MobsIgnorePower>(
                OriginsPowerExpansion.identifier("mobs_ignore"),
                new SerializableData()
                        .add("mob_condition", SerializableDataType.ENTITY_CONDITION),
                data -> (type, player) -> new MobsIgnorePower(type, player, data.get("mob_condition")))
                .allowCondition();
    }
}
