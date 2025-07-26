package org.millenaire;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.core.BlockPos;

/**
 * Simple container for raid data used by {@link RaidTracker}.
 */
public class RaidInfo {
    private BlockPos position;
    private long startTime;
    private boolean finished;

    public RaidInfo() {}

    public RaidInfo(BlockPos position, long startTime, boolean finished) {
        this.position = position;
        this.startTime = startTime;
        this.finished = finished;
    }

    public BlockPos getPosition() {
        return position;
    }

    public long getStartTime() {
        return startTime;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public CompoundNBT toNBT() {
        CompoundNBT tag = new CompoundNBT();
        if(position != null) {
            tag.putLong("Pos", position.toLong());
        }
        tag.putLong("start", startTime);
        tag.putBoolean("finished", finished);
        return tag;
    }

    public static RaidInfo fromNBT(CompoundNBT nbt) {
        BlockPos pos = BlockPos.fromLong(nbt.getLong("Pos"));
        long start = nbt.getLong("start");
        boolean fin = nbt.getBoolean("finished");
        return new RaidInfo(pos, start, fin);
    }
}
