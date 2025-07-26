package org.millenaire.entities.ai;

import org.millenaire.entities.EntityMillVillager;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;

public class EntityAIBuild extends Goal
{
        private LivingEntity theEntity;
	
        public EntityAIBuild(LivingEntity entityIn)
	{
		this.theEntity = entityIn;

        if (!(entityIn instanceof EntityMillVillager))
        {
            throw new IllegalArgumentException("Unsupported mob type for BuildGoal");
        }
        else if (!((EntityMillVillager)entityIn).getVillagerType().canBuild)
        {
        	throw new IllegalArgumentException("Villager does support BuildGoal");
        }
	}

    /**
     * Returns whether the goal should begin execution.
     */
        @Override
        public boolean canUse() { return false; }

	/**
     * Returns whether an in-progress goal should continue executing
     */
        @Override
    public boolean canContinueToUse() { return false; }
	
	/**
     * Execute a one shot task or start executing a continuous task
     */
    public void start()
    {
    	
    }
    
    /**
     * Updates the task
     */
    public void tick()
    {
    	
    }
    
    /**
     * Resets the task
     */
    @Override
    public void stop()
    {
    	
    }
}
