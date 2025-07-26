package org.millenaire.pathing;

import net.minecraft.entity.Mob;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.level.Level;

public class MillPathNavigate extends GroundPathNavigation
{
        public MillPathNavigate(Mob entitylivingIn, Level worldIn)
        {
                super(entitylivingIn, worldIn);
                this.setCanOpenDoors(true);
                this.setCanFloat(true);
                this.setCanPassDoors(true);
        }
	
	@Override
        protected PathFinder createPathFinder(int range)
    {
        this.nodeProcessor = new MillWalkNodeProcessor();
        this.nodeProcessor.setCanEnterDoors(true);
        this.nodeProcessor.setCanOpenDoors(true);
        this.nodeProcessor.setCanFloat(true);
        return new PathFinder(this.nodeProcessor, range);
    }
}
