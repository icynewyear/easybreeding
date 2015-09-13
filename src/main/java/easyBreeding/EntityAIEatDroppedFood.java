package easyBreeding;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.item.ItemFood;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityAIEatDroppedFood extends EntityAIBase
{
  private EntityAnimal animal;
  private Random rand = new Random();
  private World world = null;
  
  public EntityAIEatDroppedFood(EntityAnimal ent)
  {
    this.animal = ent;
    this.world = ent.worldObj;
  }
  
  public EntityItem whatFoodIsNear()
  {
    float searchDistance = 8.0F;
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
		return world.getEntitiesWithinAABB(EntityItem.class, AxisAlignedBB.getBoundingBox(animal.posX, animal.posY, animal.posZ, animal.posX + 1, animal.posY + 1, animal.posZ + 1));
  }
  
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
  
  public boolean execute(EntityAnimal enta, EntityItem enti)
  {
    if (enta.getNavigator().tryMoveToXYZ(enti.posX, enti.posY, enti.posZ, 0.25F)) {
      if (enta.getDistanceToEntity(enti) < 1.0F)
      {
        enti.setDead();
     //  enta.isInLove(true);
        
      }
    }
    return true;
  }
}
