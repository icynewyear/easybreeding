package easyBreeding;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class droppedFoodEventHandler 
{
	@SubscribeEvent
	public void addFoodtoAnimals(LivingEvent.LivingUpdateEvent evt) 
	{
		if ((evt.entityLiving.ticksExisted < 5) && (!evt.entityLiving.isChild())) 
		{
			EntityAnimal mob;

			if (evt.entityLiving instanceof EntityCow) 
			{
				mob = (EntityCow) evt.entityLiving;
			} else if (evt.entityLiving instanceof EntityPig) 
			{
				mob = (EntityPig) evt.entityLiving;
			} else if (evt.entityLiving instanceof EntityChicken) 
			{
				mob = (EntityChicken) evt.entityLiving;
			} else if (evt.entityLiving instanceof EntitySheep) 
			{
				mob = (EntitySheep) evt.entityLiving;
			} else {
				mob = null;
			}
			if (mob != null) 
			{
				mob.tasks.addTask(2, new EntityAIEatDroppedFood(mob));
			}
		}
	}
}
