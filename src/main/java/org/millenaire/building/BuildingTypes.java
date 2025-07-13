package org.millenaire.building;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.millenaire.MillCulture;
import org.millenaire.util.ItemRateWrapper;
import org.millenaire.util.ResourceLocationUtil;

import com.google.gson.Gson;

import net.minecraft.util.ResourceLocation;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

public class BuildingTypes {
	
	private static Map<ResourceLocation, BuildingType> buildingCache = new HashMap<ResourceLocation, BuildingType>();

        public static void cacheBuildingTypes(MillCulture culture) {
                IResourceManager rm = ServerLifecycleHooks.getCurrentServer().getResourceManager();
                try {
                        Collection<ResourceLocation> lists = rm.getAllResourceLocations("cultures/" + culture.cultureName.toLowerCase() + "/buildings", s -> s.endsWith("buildings.json"));

                        for (ResourceLocation list : lists) {
                                try (InputStream is = rm.getResource(list).getInputStream()) {
                                        String[] buildings = new Gson().fromJson(new InputStreamReader(is), String[].class);

                                        for (String building : buildings) {
                                                ResourceLocation loc = new ResourceLocation(building);
                                                ResourceLocation fileLoc = new ResourceLocation(list.getNamespace(), "cultures/" + loc.getNamespace() + "/buildings/" + loc.getPath() + ".json");
                                                try (InputStream file = rm.getResource(fileLoc).getInputStream()) {
                                                        BuildingType type = new Gson().fromJson(new InputStreamReader(file), BuildingType.class);
                                                        buildingCache.put(loc, type);
                                                }
                                        }
                                }
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
	
	public static BuildingType getTypeByID(ResourceLocation rl) { return buildingCache.get(rl); }
	
	public static BuildingType getTypeFromProject(BuildingProject proj) {
		return buildingCache.get(ResourceLocationUtil.getRL(proj.ID));
	}
	
	public static Map<ResourceLocation, BuildingType> getCache() { return buildingCache; }
	
	public static class BuildingType {
		
		private String identifier;
		protected List<ItemRateWrapper> itemrates = new ArrayList<ItemRateWrapper>();
		
		public BuildingType() {}
		
		public BuildingType(ResourceLocation cultureandname) { identifier = ResourceLocationUtil.getString(cultureandname); }
		
		public BuildingPlan loadBuilding() {
			ResourceLocation s = ResourceLocationUtil.getRL(identifier);
			try {
				return PlanIO.loadSchematic(PlanIO.getBuildingTag(s.getResourcePath(), MillCulture.getCulture(s.getResourceDomain()), true), MillCulture.getCulture(s.getResourceDomain()), 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
