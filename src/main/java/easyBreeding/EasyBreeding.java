package easyBreeding;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid="easyBreeding", name="Easy Breeding", version="4.0.1", acceptableRemoteVersions = "*", dependencies = "required-after:Forge@[12.16.0.1809,);")
public class EasyBreeding 
{
	@Mod.EventHandler
	public void load(FMLInitializationEvent evt)
	{
		MinecraftForge.EVENT_BUS.register(new droppedFoodEventHandler());
	}
}
