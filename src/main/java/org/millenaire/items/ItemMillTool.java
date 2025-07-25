package org.millenaire.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class ItemMillTool 
{
    static IItemTier TOOLS_norman = MillItemTier.NORMAN;
    static IItemTier TOOLS_obsidian = MillItemTier.OBSIDIAN;

        static class ItemMillAxe extends ItemAxe
        {
                ItemMillAxe(IItemTier material) { super(material); }
        }

        static class ItemMillShovel extends ItemSpade
        {
                ItemMillShovel(IItemTier material) { super(material); }
        }

        static class ItemMillPickaxe extends ItemPickaxe
        {
                ItemMillPickaxe(IItemTier material) { super(material); }
        }

        static class ItemMillHoe extends ItemHoe
        {
                ItemMillHoe(IItemTier material) { super(material); }
        }
	
        public static class ItemMillMace extends ItemSword
        {
                ItemMillMace(IItemTier material) { super(material); }
		
		@Override
		public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
	    {
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.knockback.effectId, stack) == 0) {
				stack.addEnchantment(Enchantment.knockback, 2);
			}
	    }
	}
}