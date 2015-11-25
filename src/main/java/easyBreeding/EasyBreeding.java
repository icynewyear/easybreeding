package easyBreeding;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid="easyBreeding", name="Easy Breeding", version="3.0.3", acceptableRemoteVersions = "*")
public class EasyBreeding 
{
	@Mod.EventHandler
	public void load(FMLInitializationEvent evt)
	{
		MinecraftForge.EVENT_BUS.register(new droppedFoodEventHandler());
	}
}
