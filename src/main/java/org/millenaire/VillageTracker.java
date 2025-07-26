package org.millenaire;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.millenaire.village.Village;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraft.server.level.ServerLevel;

public class VillageTracker extends SavedData
{
	private final static String IDENTITY = "Millenaire.VillageInfo";

	private Map<UUID, Village> villages = new HashMap<>();
	
       public VillageTracker() { super(IDENTITY); }

       private VillageTracker(String id) { super(id); }

       @Override
       public void load(CompoundNBT nbt)
        {
                System.out.println("Village Tracker reading from NBT");
               for(String s : nbt.getAllKeys()) {
                       if(nbt.get(s) instanceof CompoundNBT) {
                               villages.put(UUID.fromString(s), readVillageFromCompound(nbt.getCompound(s)));
                        }
                }
        }

       private Village readVillageFromCompound(CompoundNBT nbt) {
               Village vil = new Village();
               vil.setPos(BlockPos.fromLong(nbt.getLong("pos")));
               return vil;
       }

       @Override
       public CompoundNBT save(CompoundNBT nbt)
       {
               System.out.println("Village Tracker Writing to NBT");
               for(Entry<UUID, Village> e : villages.entrySet()) {
                       CompoundNBT villageTag = new CompoundNBT();

                       villageTag.setLong("Pos", e.getValue().getPos().toLong());

                       nbt.put(e.getKey().toString(), villageTag);
               }
               return nbt;
       }
	
	/**
	 * @return All Villages within a radius from a Block
	 */
	public List<Village> getNearVillages(BlockPos pos, int maxDist) {
		List<Village> nearby = new ArrayList<Village>();
		
		for(Village v : villages.values()) {
			if(v.getPos().distanceSq(pos) <= maxDist*maxDist) {
				nearby.add(v);
			}
		}
		
		return nearby;
	}
	
	public void registerVillage(UUID id, Village vil) { villages.put(id, vil); }
	
	public void unregisterVillage(UUID id) { villages.remove(id); }
	
       public static VillageTracker get(Level world)
       {
               if (world instanceof ServerLevel)
               {
                       DimensionDataStorage storage = ((ServerLevel)world).getDataStorage();
                       return storage.computeIfAbsent(() -> new VillageTracker(IDENTITY), IDENTITY);
               }
               return new VillageTracker(IDENTITY);
       }
}
