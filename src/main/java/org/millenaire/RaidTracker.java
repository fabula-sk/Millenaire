package org.millenaire;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.server.level.ServerLevel;

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

       public static RaidTracker get(Level world)
       {
               if (world instanceof ServerLevel)
               {
                       DimensionDataStorage storage = ((ServerLevel)world).getDataStorage();
                       return storage.computeIfAbsent(RaidTracker::new, IDENTITY);
               }
               return new RaidTracker();
       }
}
