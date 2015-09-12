package easyBreeding;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.world.ChunkCoordIntPair;

public class EntityAIEatDroppedFood
  extends EntityAIBase
{
  private EntityAnimal animal;
  private Random rand = new Random();
  
  public EntityAIEatDroppedFood(EntityAnimal ent)
  {
    this.animal = ent;
  }
  
  public EntityItem whatFoodIsNear()
  {
    ChunkCoordIntPair world = this.animal.p;
    float searchDistance = 8.0F;
    EntityItem ei = new EntityItem(world);
    List nearbyEntities = world.a(ei.getClass(), this.animal.D.b(searchDistance, searchDistance, searchDistance));
    
    Iterator iterate = nearbyEntities.iterator();
    EntityItem check;
    do
    {
      if (!iterate.hasNext()) {
        return null;
      }
      check = (EntityItem)iterate.next();
    } while (!this.animal.c(check.age));
    return check;
  }
  
  public boolean shouldExecute()
  {
    EntityItem closeFood = whatFoodIsNear();
    if ((closeFood != null) && (this.animal.inLove <= 0) && (!this.animal.isChild()) && (this.animal.getGrowingAge() == 0)) {
      execute(this.animal, closeFood);
    }
    return false;
  }
  
  public boolean execute(EntityAnimal enta, EntityItem enti)
  {
    if (enta.getNavigator().tryMoveToXYZ(enti.posX, enti.posY, enti.posZ, 0.25F)) {
      if (enta.getDistanceToEntity(enti) < 1.0F)
      {
        enta.p.a("heart", enta.posX, enta.posY + 0.05D, enta.posZ, 0.0D, 0.0D, 0.0D);
        enta.inLove = 600;
        enti.setDead();
      }
    }
    return true;
  }
}
