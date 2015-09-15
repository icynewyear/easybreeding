package easyBreeding;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIEatDroppedFood extends EntityAIBase
{
  private EntityAnimal animal;
  private Random rand = new Random();
  private World world = null;
  double searchDistance = 10;
  
  public EntityAIEatDroppedFood(EntityAnimal ent)
  {
    this.animal = ent;
    this.world = ent.worldObj;
  }
  
  public EntityItem whatFoodIsNear()
  {
    List<EntityItem> items = getItems();
    //Turns the list into single Item Entity's
    for(EntityItem item : items) 
    {
    	EntityItem stack = item;  
    	
    	if(items != null)
    	{
    		return stack;
    	}
    }
	return null;
  }
  // Gets all item entity's within one block of the animals pos, can be changed adds the to a list
  List<EntityItem> getItems() 
  {
	return world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(animal.posX - searchDistance, animal.posY - searchDistance, animal.posZ - searchDistance,
			animal.posX + searchDistance, animal.posY + searchDistance, animal.posZ + searchDistance));
  }	
  
  @SuppressWarnings({"rawtypes", "unchecked"})
  public boolean shouldExecute()
  {
    EntityItem closeFood = whatFoodIsNear();
    
    if ((closeFood != null) 
    		//Don't know what this is???
    		//&& (this.animal.inLove <= 0) 
    		&& (!this.animal.isChild()) && (this.animal.getGrowingAge() == 0) && (!this.animal.isInLove()) && (this.animal.isBreedingItem(closeFood.getEntityItem()))) 
    {
      execute(this.animal, closeFood);
    }
    return false;
  }
 
  @SuppressWarnings({"rawtypes", "unchecked"})
  public boolean execute(EntityAnimal enta, EntityItem enti)
  {
    if (enta.getNavigator().tryMoveToXYZ(enti.posX, enti.posY, enti.posZ, 1.25F)) 
    {
      if (enta.getDistanceToEntity(enti) < 1.0F)
      {
        eatOne(enti);
        enta.func_146082_f(null);
      }
    }
    return true;
  }
  
  public void eatOne(EntityItem enti)
  {
	  ItemStack stack = enti.getEntityItem();
	  
	  stack.stackSize--;
		if(stack.stackSize == 0)
			enti.setDead();
  }
}
