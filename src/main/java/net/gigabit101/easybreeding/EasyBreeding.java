package net.gigabit101.easybreeding;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("easybreeding")
public class EasyBreeding 
{
	public static EasyBreeding INSTANCE;
	
	public EasyBreeding()
	{
		INSTANCE = this;
		MinecraftForge.EVENT_BUS.register(new DroppedFoodEventHandler());
	}
}
