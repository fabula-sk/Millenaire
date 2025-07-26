package org.millenaire.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item.Properties;
import net.minecraft.inventory.EquipmentSlotType;
import org.millenaire.MillTabs;
import net.minecraft.world.World;

public class ItemMillArmor 
{
    static IArmorMaterial ARMOR_norman = MillArmorMaterial.NORMAN;
    static IArmorMaterial ARMOR_japaneseWarriorRed = MillArmorMaterial.JAPANESE_WARRIOR_RED;
    static IArmorMaterial ARMOR_japaneseWarriorBlue = MillArmorMaterial.JAPANESE_WARRIOR_BLUE;
    static IArmorMaterial ARMOR_japaneseGuard = MillArmorMaterial.JAPANESE_GUARD;
    static IArmorMaterial ARMOR_byzantine = MillArmorMaterial.BYZANTINE;
    static IArmorMaterial ARMOR_mayanQuest = MillArmorMaterial.MAYAN_QUEST;
	
        public static class mayanQuestCrown extends ArmorItem
        {
                public mayanQuestCrown(IArmorMaterial material)
                {
                        super(material, EquipmentSlotType.HEAD, new Properties().tab(MillTabs.MILLENAIRE_TAB));
                }
		
                @Override
                public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
            {
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.respiration.effectId, stack) == 0)
			{
				stack.addEnchantment(Enchantment.respiration, 3);
			}
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.aquaAffinity.effectId, stack) == 0)
			{
				stack.addEnchantment(Enchantment.aquaAffinity, 1);
			}
			
			if (EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) == 0)
			{
				stack.addEnchantment(Enchantment.protection, 4);
			}
	    }
	}
}