package org.millenaire.items;

import org.millenaire.gui.MillAchievement;

import net.minecraft.entity.player.Player;
import net.minecraft.item.UseAnim;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;

public class ItemMillFood extends Item
{
	private boolean isDrink;
	private int healAmount;
	private int drunkDuration;
	private int regDuration;

        ItemMillFood(int healIn, int regIn, int drunkIn, int hungerIn, float saturationIn, boolean drinkIn)
        {
                super();

		healAmount = healIn;
		regDuration = regIn;
		drunkDuration = drunkIn;
		
                isDrink = drinkIn;
	}

	@Override
        public UseAnim getUseAnimation(ItemStack stack) { return isDrink ? UseAnim.DRINK : UseAnim.EAT; }
	
        @Override
        public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entity)
    {
                if (entity instanceof Player)
                {
                        Player player = (Player) entity;
                        player.heal(healAmount);

                        if (isDrink) {
                                player.addStat(MillAchievement.cheers, 1);
                        }

                        if (regDuration > 0) {
                                player.addPotionEffect(new PotionEffect(Potion.regeneration.id, regDuration * 20, 0));
                        }

                        if (drunkDuration > 0) {
                                player.addPotionEffect(new PotionEffect(Potion.confusion.id, drunkDuration * 20, 0));
                        }
                }

                return super.finishUsingItem(stack, worldIn, entity);
    }
}
