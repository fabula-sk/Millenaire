package org.millenaire.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.Item.Properties;
import org.millenaire.MillTabs;
import net.minecraft.world.World;

public class ItemMillTool 
{
    static IItemTier TOOLS_norman = MillItemTier.NORMAN;
    static IItemTier TOOLS_obsidian = MillItemTier.OBSIDIAN;

        static class ItemMillAxe extends AxeItem
        {
                ItemMillAxe(IItemTier material) {
                        super(material, 5.0F, -3.0F, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
        }

        static class ItemMillShovel extends ShovelItem
        {
                ItemMillShovel(IItemTier material) {
                        super(material, 1.5F, -3.0F, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
        }

        static class ItemMillPickaxe extends PickaxeItem
        {
                ItemMillPickaxe(IItemTier material) {
                        super(material, 1, -2.8F, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
        }

        static class ItemMillHoe extends HoeItem
        {
                ItemMillHoe(IItemTier material) {
                        super(material, 0, -3.0F, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
        }
	
        public static class ItemMillMace extends SwordItem
        {
                ItemMillMace(IItemTier material) {
                        super(material, 3, -2.4F, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
		
		@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	    {
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) == 0) {
				stack.addEnchantment(Enchantment.knockback, 2);
			}
	    }
	}
}