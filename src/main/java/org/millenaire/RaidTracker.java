package org.millenaire;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.server.level.ServerLevel;

public class RaidTracker extends SavedData
{
        private final static String IDENTITY = "Millenaire.RaidInfo";

        private final Map<UUID, RaidInfo> raids = new HashMap<>();

       private RaidTracker() { super(IDENTITY); }

       public void registerRaid(UUID id, RaidInfo info) {
               raids.put(id, info);
               setDirty();
       }

       public void removeRaid(UUID id) {
               raids.remove(id);
               setDirty();
       }

       public Map<UUID, RaidInfo> getRaids() {
               return raids;
       }

       @Override
       public void load(CompoundNBT nbt)
       {
               for(String key : nbt.getAllKeys()) {
                       if(nbt.get(key) instanceof CompoundNBT) {
                               raids.put(UUID.fromString(key), RaidInfo.fromNBT(nbt.getCompound(key)));
                       }
               }
       }

       @Override
       public CompoundNBT save(CompoundNBT nbt)
       {
               for(Map.Entry<UUID, RaidInfo> e : raids.entrySet()) {
                       nbt.put(e.getKey().toString(), e.getValue().toNBT());
               }
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
