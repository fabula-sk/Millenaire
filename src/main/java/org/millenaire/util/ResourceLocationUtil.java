package org.millenaire.util;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

public class ResourceLocationUtil {

	public static ResourceLocation getRL(String rl) { return new ResourceLocation(rl); }
	
	public static String getString(ResourceLocation rl) { return rl.getResourceDomain() + ":" + rl.getResourcePath(); }
	
        public static Item getItem(ResourceLocation rl) { return ForgeRegistries.ITEMS.getValue(rl); }
}
