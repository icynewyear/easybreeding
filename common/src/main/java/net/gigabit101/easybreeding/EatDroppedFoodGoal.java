package net.gigabit101.easybreeding;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.stream.Collectors;

public class EatDroppedFoodGoal extends Goal
{
    private final Animal animal;

    public EatDroppedFoodGoal(Animal animal)
    {
        this.animal = animal;
    }

    @Override
    public boolean canUse()
    {
        if(animal.isBaby()) return false;
        if(animal.isInLove()) return false;
        if(!animal.canFallInLove()) return false;
        if(animal.canBreed()) return false;
        if(animal.getAge() != 0) return false;

        List<ItemEntity> list = this.animal.level.getEntitiesOfClass(ItemEntity.class, this.animal.getBoundingBox().inflate(4.0D, 4.0D, 4.0D));
        if(list.isEmpty()) return false;

        List<ItemEntity> breedAbleItemEntity = list.stream().filter(itemEntity -> animal.isFood(itemEntity.getItem())).collect(Collectors.toList());
        if(breedAbleItemEntity.isEmpty()) return false;
        ItemEntity breedAbleStack = breedAbleItemEntity.get(0);
        if(breedAbleStack == null) return false;

        animal.getNavigation().moveTo(breedAbleStack, 1.25F);
        if(!animal.level.isClientSide() && animal.distanceToSqr(breedAbleStack) < 1.0F)
        {
            breedAbleStack.getItem().shrink(1);
            animal.setInLove(null);
            return true;
        }

        return true;
    }
}
