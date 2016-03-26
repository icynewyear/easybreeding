package easyBreeding;

import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class droppedFoodEventHandler 
{
	@SubscribeEvent
	public void addFoodtoAnimals(LivingEvent.LivingUpdateEvent evt) 
	{
		if ((evt.getEntityLiving().ticksExisted < 5) && (!evt.getEntityLiving().isChild())) 
		{
			EntityAnimal mob;

			if (evt.getEntityLiving() instanceof EntityCow) 
			{
				mob = (EntityCow) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof EntityPig) 
			{
				mob = (EntityPig) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof EntityChicken) 
			{
				mob = (EntityChicken) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof EntitySheep) 
			{
				mob = (EntitySheep) evt.getEntityLiving();
			} else if (evt.getEntityLiving() instanceof EntityRabbit)
			{
			        mob = (EntityRabbit) evt.getEntityLiving();
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
