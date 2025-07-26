package org.millenaire;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.world.server.ServerWorld;

public class RaidTracker extends SavedData
{
	private final static String IDENTITY = "Millenaire.RaidInfo";
	
	private RaidTracker() { super(IDENTITY); }

       @Override
       public void load(CompoundNBT nbt)
       {

       }

       @Override
       public CompoundNBT save(CompoundNBT nbt)
       {
               return nbt;
       }

       public static RaidTracker get(World world)
       {
               if (world instanceof ServerWorld)
               {
                       DimensionDataStorage storage = ((ServerWorld)world).getDataStorage();
                       return storage.computeIfAbsent(RaidTracker::new, IDENTITY);
               }
               return new RaidTracker();
       }
}
