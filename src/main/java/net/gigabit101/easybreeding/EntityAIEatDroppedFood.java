package net.gigabit101.easybreeding;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIEatDroppedFood extends Goal
{
  private AnimalEntity animal;
  private World world = null;
  double searchDistance = 10;
  
  public EntityAIEatDroppedFood(AnimalEntity ent)
  {
    this.animal = ent;
    this.world = ent.world;
  }
  
  public ItemEntity whatFoodIsNear()
  {
    List<ItemEntity> items = getItems();
    //Turns the list into single Item Entity's
    for(ItemEntity item : items)
    {
      ItemEntity stack = item;
    	
    	if(items != null)
    	{
    		return stack;
    	}
    }
	return null;
  }
  
  // Gets all item entity's within one block of the animals pos, can be changed adds the to a list
  List<ItemEntity> getItems()
  {
      return world.getEntitiesWithinAABB(ItemEntity.class, new AxisAlignedBB(animal.posX - searchDistance, animal.posY - searchDistance, animal.posZ - searchDistance,
              animal.posX + searchDistance, animal.posY + searchDistance, animal.posZ + searchDistance));
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public boolean shouldExecute()
  {
    ItemEntity closeFood = whatFoodIsNear();
    
    if ((closeFood != null) && (!this.animal.isChild()) && (this.animal.getGrowingAge() == 0) && (!this.animal.isInLove()) && (this.animal.isBreedingItem(closeFood.getItem())))
    {
      execute(this.animal, closeFood);
    }
    return false;
  }
 
  @SuppressWarnings({"rawtypes", "unchecked"})
  public boolean execute(AnimalEntity enta, ItemEntity enti)
  {
    if (enta.getNavigator().tryMoveToXYZ(enti.posX, enti.posY, enti.posZ, 1.25F)) 
    {
      if (enta.getDistanceSq(enti) < 1.0F)
      {
        eatOne(enti);
        enta.setInLove(null);
      }
    }
    return true;
  }
  
  public void eatOne(ItemEntity enti)
  {
	  ItemStack stack = enti.getItem();
      stack.setCount(stack.getCount() -1);
		if(stack.getCount() == 0)
			enti.remove();
  }
}
