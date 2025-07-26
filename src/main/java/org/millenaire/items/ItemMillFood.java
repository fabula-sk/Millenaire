package org.millenaire.items;

import org.millenaire.gui.MillAchievement;

import net.minecraft.entity.player.Player;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraft.entity.LivingEntity;

public class ItemMillFood extends ItemFood
{
	private boolean isDrink;
	private int healAmount;
	private int drunkDuration;
	private int regDuration;

	ItemMillFood(int healIn, int regIn, int drunkIn, int hungerIn, float saturationIn, boolean drinkIn)
	{	
		super(hungerIn, saturationIn, false);

		healAmount = healIn;
		regDuration = regIn;
		drunkDuration = drunkIn;
		
		isDrink = drinkIn;
		if(isDrink)
		{
			this.setAlwaysEdible();
		}
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack) { return isDrink ? EnumAction.DRINK : EnumAction.EAT; }
	
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
