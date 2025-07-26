package org.millenaire.pathing;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BlockFenceGate;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.PathPoint;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.pathfinder.WalkNodeProcessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.BlockGetter;

public class MillWalkNodeProcessor extends WalkNodeProcessor
{
	@Override
        public int findPathOptions(PathPoint[] pathOptions, Mob entityIn, PathPoint currentPoint, PathPoint targetPoint, float maxDistance)
    {
        int i = 0;
        int j = 0;
        if (this.getMillVerticalOffset(entityIn, currentPoint.xCoord, currentPoint.yCoord + 1, currentPoint.zCoord) == PathType.WALKABLE)
        {
            j = 1;
        }

        PathPoint pathpoint = this.getMillSafePoint(entityIn, currentPoint.xCoord, currentPoint.yCoord, currentPoint.zCoord + 1, j);
        PathPoint pathpoint1 = this.getMillSafePoint(entityIn, currentPoint.xCoord - 1, currentPoint.yCoord, currentPoint.zCoord, j);
        PathPoint pathpoint2 = this.getMillSafePoint(entityIn, currentPoint.xCoord + 1, currentPoint.yCoord, currentPoint.zCoord, j);
        PathPoint pathpoint3 = this.getMillSafePoint(entityIn, currentPoint.xCoord, currentPoint.yCoord, currentPoint.zCoord - 1, j);

        if (pathpoint != null && !pathpoint.visited && pathpoint.distanceTo(targetPoint) < maxDistance)
        {
            pathOptions[i++] = pathpoint;
        }

        if (pathpoint1 != null && !pathpoint1.visited && pathpoint1.distanceTo(targetPoint) < maxDistance)
        {
            pathOptions[i++] = pathpoint1;
        }

        if (pathpoint2 != null && !pathpoint2.visited && pathpoint2.distanceTo(targetPoint) < maxDistance)
        {
            pathOptions[i++] = pathpoint2;
        }

        if (pathpoint3 != null && !pathpoint3.visited && pathpoint3.distanceTo(targetPoint) < maxDistance)
        {
            pathOptions[i++] = pathpoint3;
        }

        return i;
    }

    /**
     * Returns a point that the entity can safely move to
     */
    private PathPoint getMillSafePoint(Mob entityIn, int x, int y, int z, int dy)
    {
        PathPoint pathpoint = null;
        PathType i = this.getMillVerticalOffset(entityIn, x, y, z);

        if (i == PathType.WALKABLE)
        {
            return this.openPoint(x, y, z);
        }
        else
        {
            if (i == PathType.OPEN)
            {
                pathpoint = this.openPoint(x, y, z);
            }

            if (pathpoint == null && dy > 0 && i != PathType.FENCE && i != PathType.DOOR_WOOD_CLOSED && this.getMillVerticalOffset(entityIn, x, y + dy, z) == PathType.WALKABLE)
            {
                pathpoint = this.openPoint(x, y + dy, z);
                y += dy;
            }

            if (pathpoint != null)
            {
                int j = 0;
                PathType k;

                for (k = 0; y > 0; pathpoint = this.openPoint(x, y, z))
                {
                    k = this.getMillVerticalOffset(entityIn, x, y - 1, z);

                    if (this.getAvoidsWater() && k == PathType.WATER)
                    {
                        return null;
                    }

                    if (k != PathType.WALKABLE)
                    {
                        break;
                    }

                    if (j++ >= entityIn.getMaxFallHeight())
                    {
                        return null;
                    }

                    --y;

                    if (y <= 0)
                    {
                        return null;
                    }
                }

                if (k == PathType.LAVA)
                {
                    return null;
                }
            }

            return pathpoint;
        }
    }

    /**
     * Checks if an entity collides with blocks at a position.
     * Returns 1 if clear, 0 for colliding with any solid block, -1 for water(if avoids water),
     * -2 for lava, -3 for fence and wall, -4 for closed trapdoor, 2 if otherwise clear except for open trapdoor or
     * water(if not avoiding)
     */
    private PathType getMillVerticalOffset(Mob entityIn, int x, int y, int z)
    {
        BlockPos pos = new BlockPos(x, y, z);
        BlockState state = this.blockaccess.getBlockState(pos);
        if (state.getBlock() instanceof BlockFenceGate && this.blockaccess.isEmptyBlock(pos.above()))
            return PathType.WALKABLE;
        PathType type = state.getBlock().getAiPathNodeType(state, (BlockGetter)this.blockaccess, pos, entityIn);
        return type == null ? PathType.BLOCKED : type;
    }
}
