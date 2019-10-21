package net.gigabit101.easybreeding;

import net.minecraft.entity.passive.*;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DroppedFoodEventHandler
{
	@SubscribeEvent
	public void addFoodtoAnimals(LivingEvent.LivingUpdateEvent evt) 
	{
		if ((evt.getEntityLiving().ticksExisted < 5) && (!evt.getEntityLiving().isChild()))
		{
			AnimalEntity mob;

			if (evt.getEntityLiving() instanceof CowEntity)
			{
				mob = (CowEntity) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof PigEntity)
			{
				mob = (PigEntity) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof ChickenEntity)
			{
				mob = (ChickenEntity) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof SheepEntity)
			{
				mob = (SheepEntity) evt.getEntityLiving();
			} else {
				mob = null;
			}
			if (mob != null) 
			{
				mob.goalSelector.addGoal(1, new EntityAIEatDroppedFood(mob));
			}
		}
	}
}
