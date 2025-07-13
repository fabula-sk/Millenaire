package org.millenaire;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class MillConfig {
    // Config values
    public static boolean learnLanguages;
    public static boolean villageAnnouncement;
    public static boolean displayNames;
    public static int nameDistance;
    public static int dialogueDistance;

    public static boolean generateVillages;
    public static boolean generateLoneBuildings;
    public static int minVillageDistance;
    public static int minLoneDistance;
    public static int minVillageLoneDistance;
    public static int spawnDistance;

    public static int loadedRadius;
    public static int minBuildingDistance;
    public static int maxChildren;
    public static boolean buildPaths;
    public static int villageRelationDistance;
    public static int banditRaidDistance;
    public static int raidPercentChance;
    public static String forbiddenBlocks;

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static ForgeConfigSpec.BooleanValue learnLanguagesVal;
    private static ForgeConfigSpec.BooleanValue villageAnnouncementVal;
    private static ForgeConfigSpec.BooleanValue displayNamesVal;
    private static ForgeConfigSpec.IntValue nameDistanceVal;
    private static ForgeConfigSpec.IntValue dialogueDistanceVal;

    private static ForgeConfigSpec.BooleanValue generateVillagesVal;
    private static ForgeConfigSpec.BooleanValue generateLoneBuildingsVal;
    private static ForgeConfigSpec.IntValue minVillageDistanceVal;
    private static ForgeConfigSpec.IntValue minLoneDistanceVal;
    private static ForgeConfigSpec.IntValue minVillageLoneDistanceVal;
    private static ForgeConfigSpec.IntValue spawnDistanceVal;

    private static ForgeConfigSpec.IntValue loadedRadiusVal;
    private static ForgeConfigSpec.IntValue minBuildingDistanceVal;
    private static ForgeConfigSpec.IntValue maxChildrenVal;
    private static ForgeConfigSpec.BooleanValue buildPathsVal;
    private static ForgeConfigSpec.IntValue villageRelationDistanceVal;
    private static ForgeConfigSpec.IntValue banditRaidDistanceVal;
    private static ForgeConfigSpec.IntValue raidPercentChanceVal;
    private static ForgeConfigSpec.ConfigValue<String> forbiddenBlocksVal;

    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.comment("UI Options").push("uioptions");
        learnLanguagesVal = BUILDER.define("learnLanguages", true);
        villageAnnouncementVal = BUILDER.define("villageAnnouncementRecipe", false);
        displayNamesVal = BUILDER.define("displayNames", true);
        nameDistanceVal = BUILDER.defineInRange("nameDistance", 20, 1, Integer.MAX_VALUE);
        dialogueDistanceVal = BUILDER.defineInRange("dialogueDistance", 5, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("World Generation Options").push("worldgen");
        generateVillagesVal = BUILDER.define("generateVillages", true);
        generateLoneBuildingsVal = BUILDER.define("generateLoneBuildings", true);
        minVillageDistanceVal = BUILDER.defineInRange("minVillageDistance", 600, 1, Integer.MAX_VALUE);
        minLoneDistanceVal = BUILDER.defineInRange("minLoneDistance", 600, 1, Integer.MAX_VALUE);
        minVillageLoneDistanceVal = BUILDER.defineInRange("minVillageLoneDistance", 300, 1, Integer.MAX_VALUE);
        spawnDistanceVal = BUILDER.defineInRange("spawnDistance", 200, 1, Integer.MAX_VALUE);
        BUILDER.pop();

        BUILDER.comment("Village Behaviour Options").push("villagebehavior");
        loadedRadiusVal = BUILDER.defineInRange("loadedRadius", 200, 1, Integer.MAX_VALUE);
        minBuildingDistanceVal = BUILDER.defineInRange("minBuildingDistance", 2, 1, Integer.MAX_VALUE);
        maxChildrenVal = BUILDER.defineInRange("maxChildren", 10, 0, Integer.MAX_VALUE);
        buildPathsVal = BUILDER.define("buildPaths", true);
        villageRelationDistanceVal = BUILDER.defineInRange("villageRelationDistance", 2000, 1, Integer.MAX_VALUE);
        banditRaidDistanceVal = BUILDER.defineInRange("banditRaidDistance", 1500, 1, Integer.MAX_VALUE);
        raidPercentChanceVal = BUILDER.defineInRange("raidPercentChance", 20, 0, 100);
        forbiddenBlocksVal = BUILDER.define("forbiddenBlocks", "forbidden: ");
        BUILDER.pop();

        SPEC = BUILDER.build();
    }

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SPEC);
        MinecraftForge.EVENT_BUS.register(MillConfig.class);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading event) {
        if (event.getConfig().getSpec() == SPEC) {
            bake();
        }
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading event) {
        if (event.getConfig().getSpec() == SPEC) {
            bake();
        }
    }

    private static void bake() {
        learnLanguages = learnLanguagesVal.get();
        villageAnnouncement = villageAnnouncementVal.get();
        displayNames = displayNamesVal.get();
        nameDistance = nameDistanceVal.get();
        dialogueDistance = dialogueDistanceVal.get();

        generateVillages = generateVillagesVal.get();
        generateLoneBuildings = generateLoneBuildingsVal.get();
        minVillageDistance = minVillageDistanceVal.get();
        minLoneDistance = minLoneDistanceVal.get();
        minVillageLoneDistance = minVillageLoneDistanceVal.get();
        spawnDistance = spawnDistanceVal.get();

        loadedRadius = loadedRadiusVal.get();
        minBuildingDistance = minBuildingDistanceVal.get();
        maxChildren = maxChildrenVal.get();
        buildPaths = buildPathsVal.get();
        villageRelationDistance = villageRelationDistanceVal.get();
        banditRaidDistance = banditRaidDistanceVal.get();
        raidPercentChance = raidPercentChanceVal.get();
        forbiddenBlocks = forbiddenBlocksVal.get();
    }
}

