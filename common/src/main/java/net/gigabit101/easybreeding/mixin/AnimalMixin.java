package net.gigabit101.easybreeding.mixin;

import net.gigabit101.easybreeding.EatDroppedFoodGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Animal.class)
public class AnimalMixin extends Mob
{
    protected AnimalMixin(EntityType<? extends Mob> entityType, Level level) { super(entityType, level); }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(EntityType<? extends Animal> entityType, Level level, CallbackInfo ci)
    {
        goalSelector.addGoal(1, new EatDroppedFoodGoal((Animal) (Object) this));
    }
}
