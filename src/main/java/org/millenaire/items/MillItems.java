package org.millenaire.items;

import org.millenaire.Millenaire;
import org.millenaire.MillTabs;
import org.millenaire.blocks.BlockMillCrops;
import org.millenaire.blocks.MillBlocks;
import org.millenaire.items.ItemMillArmor.mayanQuestCrown;
import org.millenaire.items.ItemMillTool.ItemMillAxe;
import org.millenaire.items.ItemMillTool.ItemMillHoe;
import org.millenaire.items.ItemMillTool.ItemMillMace;
import org.millenaire.items.ItemMillTool.ItemMillPickaxe;
import org.millenaire.items.ItemMillTool.ItemMillShovel;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ItemTier;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MillItems
{
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Millenaire.MODID);
        //Class to hold basic items

        public static final RegistryObject<Item> denier = ITEMS.register("denier", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> denierOr = ITEMS.register("denier_or", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> denierArgent = ITEMS.register("denier_argent", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> silk = ITEMS.register("silk", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> obsidianFlake = ITEMS.register("obsidian_flake", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> unknownPowder = ITEMS.register("unknown_powder", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> woolClothes = ITEMS.register("wool_clothes", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> silkClothes = ITEMS.register("silk_clothes", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> galianiteDust = ITEMS.register("galianite_dust", () -> new Item(new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        //Crops
        public static final RegistryObject<Item> turmeric = ITEMS.register("turmeric", () -> new ItemMillSeeds(MillBlocks.cropTurmeric.get()));
        public static final RegistryObject<Item> rice = ITEMS.register("rice", () -> new ItemMillSeeds(MillBlocks.cropRice.get()));
        public static final RegistryObject<Item> maize = ITEMS.register("maize", () -> new ItemMillSeeds(MillBlocks.cropMaize.get()));
        public static final RegistryObject<Item> grapes = ITEMS.register("grapes", () -> new ItemMillSeeds(MillBlocks.cropGrapeVine.get()));

        public static final RegistryObject<Item> ciderApple = ITEMS.register("cider_apple", () -> new ItemMillFood(0, 0, 0, 1, 0.05F, false));
        public static final RegistryObject<Item> cider = ITEMS.register("cider", () -> new ItemMillFood(4, 15, 5, 0, 0.0F, true));
        public static final RegistryObject<Item> calva = ITEMS.register("calva", () -> new ItemMillFood(8, 30, 10, 0, 0.0F, true));
        public static final RegistryObject<Item> tripes = ITEMS.register("tripes", () -> new ItemMillFood(0, 0, 0, 10, 1.0F, false));
        public static final RegistryObject<Item> boudinNoir = ITEMS.register("boudin_noir", () -> new ItemMillFood(0, 0, 0, 10, 1.0F, false));

        public static final RegistryObject<Item> vegCurry = ITEMS.register("veg_curry", () -> new ItemMillFood(0, 0, 0, 6, 0.6F, false));
        public static final RegistryObject<Item> murghCurry = ITEMS.register("murgh_curry", () -> new ItemMillFood(0, 0, 0, 8, 0.8F, false));
        public static final RegistryObject<Item> rasgulla = ITEMS.register("rasgulla", () -> new ItemMillFood(2, 30, 0, 0, 0.0F, false));

        public static final RegistryObject<Item> cacauhaa = ITEMS.register("cacauhaa", () -> new ItemMillFood(6, 30, 3, 0, 0.0F, true));
        public static final RegistryObject<Item> masa = ITEMS.register("masa", () -> new ItemMillFood(0, 0, 0, 6, 0.6F, false));
        public static final RegistryObject<Item> wah = ITEMS.register("wah", () -> new ItemMillFood(0, 0, 0, 10, 1.0F, false));

        public static final RegistryObject<Item> wine = ITEMS.register("wine", () -> new ItemMillFood(3, 15, 5, 0, 0.0F, true));
        public static final RegistryObject<Item> malvasiaWine = ITEMS.register("malvasia_wine", () -> new ItemMillFood(8, 30, 5, 0, 0.0F, true));
        public static final RegistryObject<Item> feta = ITEMS.register("feta", () -> new ItemMillFood(3, 10, 0, 0, 0.0F, false));
        public static final RegistryObject<Item> souvlaki = ITEMS.register("souvlaki", () -> new ItemMillFood(0, 0, 0, 10, 1.0F, false));

        public static final RegistryObject<Item> sake = ITEMS.register("sake", () -> new ItemMillFood(8, 30, 10, 0, 0.0F, true));
        public static final RegistryObject<Item> udon = ITEMS.register("udon", () -> new ItemMillFood(0, 0, 0, 8, 0.8F, false));
        public static final RegistryObject<Item> ikayaki = ITEMS.register("ikayaki", () -> new ItemMillFood(0, 0, 0, 10, 1.0F, false));

        //Armour
        public static final RegistryObject<Item> normanHelmet = ITEMS.register("norman_helmet", () -> new ArmorItem(ItemMillArmor.ARMOR_norman, EquipmentSlotType.HEAD, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> normanChestplate = ITEMS.register("norman_chestplate", () -> new ArmorItem(ItemMillArmor.ARMOR_norman, EquipmentSlotType.CHEST, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> normanLeggings = ITEMS.register("norman_leggings", () -> new ArmorItem(ItemMillArmor.ARMOR_norman, EquipmentSlotType.LEGS, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> normanBoots = ITEMS.register("norman_boots", () -> new ArmorItem(ItemMillArmor.ARMOR_norman, EquipmentSlotType.FEET, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> byzantineHelmet = ITEMS.register("byzantine_helmet", () -> new ArmorItem(ItemMillArmor.ARMOR_byzantine, EquipmentSlotType.HEAD, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> byzantineChestplate = ITEMS.register("byzantine_chestplate", () -> new ArmorItem(ItemMillArmor.ARMOR_byzantine, EquipmentSlotType.CHEST, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> byzantineLeggings = ITEMS.register("byzantine_leggings", () -> new ArmorItem(ItemMillArmor.ARMOR_byzantine, EquipmentSlotType.LEGS, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> byzantineBoots = ITEMS.register("byzantine_boots", () -> new ArmorItem(ItemMillArmor.ARMOR_byzantine, EquipmentSlotType.FEET, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> japaneseGuardHelmet = ITEMS.register("japanese_guard_helmet", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseGuard, EquipmentSlotType.HEAD, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseGuardChestplate = ITEMS.register("japanese_guard_chestplate", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseGuard, EquipmentSlotType.CHEST, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseGuardLeggings = ITEMS.register("japanese_guard_leggings", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseGuard, EquipmentSlotType.LEGS, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseGuardBoots = ITEMS.register("japanese_guard_boots", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseGuard, EquipmentSlotType.FEET, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> japaneseBlueHelmet = ITEMS.register("japanese_blue_helmet", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorBlue, EquipmentSlotType.HEAD, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseBlueChestplate = ITEMS.register("japanese_blue_chestplate", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorBlue, EquipmentSlotType.CHEST, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseBlueLeggings = ITEMS.register("japanese_blue_leggings", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorBlue, EquipmentSlotType.LEGS, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseBlueBoots = ITEMS.register("japanese_blue_boots", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorBlue, EquipmentSlotType.FEET, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> japaneseRedHelmet = ITEMS.register("japanese_red_helmet", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorRed, EquipmentSlotType.HEAD, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseRedChestplate = ITEMS.register("japanese_red_chestplate", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorRed, EquipmentSlotType.CHEST, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseRedLeggings = ITEMS.register("japanese_red_leggings", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorRed, EquipmentSlotType.LEGS, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseRedBoots = ITEMS.register("japanese_red_boots", () -> new ArmorItem(ItemMillArmor.ARMOR_japaneseWarriorRed, EquipmentSlotType.FEET, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> mayanQuestCrown = ITEMS.register("mayan_quest_crown", () -> new mayanQuestCrown(ItemMillArmor.ARMOR_mayanQuest));

        //Wands
        public static final RegistryObject<Item> wandSummoning = ITEMS.register("wand_summoning", ItemMillWand::new);
        public static final RegistryObject<Item> wandNegation = ITEMS.register("wand_negation", ItemMillWand::new);
        public static final RegistryObject<Item> wandCreative = ITEMS.register("wand_creative", ItemMillWand::new);
        public static final RegistryObject<Item> tuningFork = ITEMS.register("tuning_fork", ItemMillWand::new);

        //Tools
        public static final RegistryObject<Item> normanAxe = ITEMS.register("norman_axe", () -> new ItemMillAxe(ItemMillTool.TOOLS_norman));
        public static final RegistryObject<Item> normanShovel = ITEMS.register("norman_shovel", () -> new ItemMillShovel(ItemMillTool.TOOLS_norman));
        public static final RegistryObject<Item> normanPickaxe = ITEMS.register("norman_pickaxe", () -> new ItemMillPickaxe(ItemMillTool.TOOLS_norman));
        public static final RegistryObject<Item> normanHoe = ITEMS.register("norman_hoe", () -> new ItemMillHoe(ItemMillTool.TOOLS_norman));
        public static final RegistryObject<Item> normanSword = ITEMS.register("norman_sword", () -> new SwordItem(ItemMillTool.TOOLS_norman, 3, -2.4F, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> mayanAxe = ITEMS.register("mayan_axe", () -> new ItemMillAxe(ItemMillTool.TOOLS_obsidian));
        public static final RegistryObject<Item> mayanShovel = ITEMS.register("mayan_shovel", () -> new ItemMillShovel(ItemMillTool.TOOLS_obsidian));
        public static final RegistryObject<Item> mayanPickaxe = ITEMS.register("mayan_pickaxe", () -> new ItemMillPickaxe(ItemMillTool.TOOLS_obsidian));
        public static final RegistryObject<Item> mayanHoe = ITEMS.register("mayan_hoe", () -> new ItemMillHoe(ItemMillTool.TOOLS_obsidian));
        public static final RegistryObject<Item> mayanMace = ITEMS.register("mayan_mace", () -> new SwordItem(ItemMillTool.TOOLS_obsidian, 3, -2.4F, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));

        public static final RegistryObject<Item> byzantineMace = ITEMS.register("byzantine_mace", () -> new ItemMillMace(ItemTier.IRON));

        public static final RegistryObject<Item> japaneseSword = ITEMS.register("japanese_sword", () -> new SwordItem(ItemTier.IRON, 3, -2.4F, new Item.Properties().tab(MillTabs.MILLENAIRE_TAB)));
        public static final RegistryObject<Item> japaneseBow = ITEMS.register("japanese_bow", () -> new ItemMillBow(2, 0.5F, "japanese_bow"));

        //Amulets
        public static final RegistryObject<Item> amuletSkollHati = ITEMS.register("amulet_skoll_hati", ItemMillAmulet::new);
        public static final RegistryObject<Item> amuletYggdrasil = ITEMS.register("amulet_yggdrasil", ItemMillAmulet::new);
        public static final RegistryObject<Item> amuletAlchemist = ITEMS.register("amulet_alchemist", ItemMillAmulet::new);
        public static final RegistryObject<Item> amuletVishnu = ITEMS.register("amulet_vishnu", ItemMillAmulet::new);

        //Wallet
        public static final RegistryObject<Item> itemMillPurse = ITEMS.register("item_mill_purse", ItemMillWallet::new);

        //Sign
        public static final RegistryObject<Item> itemMillSign = ITEMS.register("item_mill_sign", ItemMillSign::new);

        //Parchments
        public static final RegistryObject<Item> normanVillagerParchment = ITEMS.register("norman_villager_parchment", () -> new ItemMillParchment("scroll.normanVillager.title", new String[]{"scroll.normanVillager.leaders", "scroll.normanVillager.men", "scroll.normanVillager.women", "scroll.normanVillager.children"}));
        public static final RegistryObject<Item> normanBuildingParchment = ITEMS.register("norman_building_parchment", () -> new ItemMillParchment("scroll.normanBuilding.title", new String[]{"scroll.normanBuilding.centers", "scroll.normanBuilding.houses", "scroll.normanBuilding.uninhabited", "scroll.normanBuilding.player"}));
        public static final RegistryObject<Item> normanItemParchment = ITEMS.register("norman_item_parchment", () -> new ItemMillParchment("scroll.normanItem.title", new String[]{"scroll.normanItem.food", "scroll.normanItem.tools", "scroll.normanItem.weapons", "scroll.normanItem.construction"}));
        public static final RegistryObject<Item> normanAllParchment = ITEMS.register("norman_all_parchment", () -> new ItemMillParchment("scroll.normanVillager.title", new String[]{"scroll.normanVillager.leaders", "scroll.normanVillager.men", "scroll.normanVillager.women", "scroll.normanVillager.children", "scroll.normanBuilding.centers", "scroll.normanBuilding.houses", "scroll.normanBuilding.uninhabited", "scroll.normanBuilding.player", "scroll.normanItem.food", "scroll.normanItem.tools", "scroll.normanItem.weapons", "scroll.normanItem.construction"}));

        public static final RegistryObject<Item> byzantineVillagerParchment = ITEMS.register("byzantine_villager_parchment", () -> new ItemMillParchment("scroll.byzantineVillager.title", new String[]{"scroll.byzantineVillager.leaders", "scroll.byzantineVillager.men", "scroll.byzantineVillager.women", "scroll.byzantineVillager.children"}));
        public static final RegistryObject<Item> byzantineBuildingParchment = ITEMS.register("byzantine_building_parchment", () -> new ItemMillParchment("scroll.byzantineBuilding.title", new String[]{"scroll.byzantineBuilding.centers", "scroll.byzantineBuilding.houses", "scroll.byzantineBuilding.uninhabited", "scroll.byzantineBuilding.player"}));
        public static final RegistryObject<Item> byzantineItemParchment = ITEMS.register("byzantine_item_parchment", () -> new ItemMillParchment("scroll.byzantineItem.title", new String[]{"scroll.byzantineItem.food", "scroll.byzantineItem.tools", "scroll.byzantineItem.weapons", "scroll.byzantineItem.construction"}));
        public static final RegistryObject<Item> byzantineAllParchment = ITEMS.register("byzantine_all_parchment", () -> new ItemMillParchment("scroll.byzantineVillager.title", new String[]{"scroll.byzantineVillager.leaders", "scroll.byzantineVillager.men", "scroll.byzantineVillager.women", "scroll.byzantineVillager.children", "scroll.byzantineBuilding.centers", "scroll.byzantineBuilding.houses", "scroll.byzantineBuilding.uninhabited", "scroll.byzantineBuilding.player", "scroll.byzantineItem.food", "scroll.byzantineItem.tools", "scroll.byzantineItem.weapons", "scroll.byzantineItem.construction"}));

        public static final RegistryObject<Item> hindiVillagerParchment = ITEMS.register("hindi_villager_parchment", () -> new ItemMillParchment("scroll.hindiVillager.title", new String[]{"scroll.hindiVillager.leaders", "scroll.hindiVillager.men", "scroll.hindiVillager.women", "scroll.hindiVillager.children"}));
        public static final RegistryObject<Item> hindiBuildingParchment = ITEMS.register("hindi_building_parchment", () -> new ItemMillParchment("scroll.hindiBuilding.title", new String[]{"scroll.hindiBuilding.centers", "scroll.hindiBuilding.houses", "scroll.hindiBuilding.uninhabited", "scroll.hindiBuilding.player"}));
        public static final RegistryObject<Item> hindiItemParchment = ITEMS.register("hindi_item_parchment", () -> new ItemMillParchment("scroll.hindiItem.title", new String[]{"scroll.hindiItem.food", "scroll.hindiItem.tools", "scroll.hindiItem.weapons", "scroll.hindiItem.construction"}));
        public static final RegistryObject<Item> hindiAllParchment = ITEMS.register("hindi_all_parchment", () -> new ItemMillParchment("scroll.hindiVillager.title", new String[]{"scroll.hindiVillager.leaders", "scroll.hindiVillager.men", "scroll.hindiVillager.women", "scroll.hindiVillager.children", "scroll.hindiBuilding.centers", "scroll.hindiBuilding.houses", "scroll.hindiBuilding.uninhabited", "scroll.hindiBuilding.player", "scroll.hindiItem.food", "scroll.hindiItem.tools", "scroll.hindiItem.weapons", "scroll.hindiItem.construction"}));

        public static final RegistryObject<Item> mayanVillagerParchment = ITEMS.register("mayan_villager_parchment", () -> new ItemMillParchment("scroll.mayanVillager.title", new String[]{"scroll.mayanVillager.leaders", "scroll.mayanVillager.men", "scroll.mayanVillager.women", "scroll.mayanVillager.children"}));
        public static final RegistryObject<Item> mayanBuildingParchment = ITEMS.register("mayan_building_parchment", () -> new ItemMillParchment("scroll.mayanBuilding.title", new String[]{"scroll.mayanBuilding.centers", "scroll.mayanBuilding.houses", "scroll.mayanBuilding.uninhabited", "scroll.mayanBuilding.player"}));
        public static final RegistryObject<Item> mayanItemParchment = ITEMS.register("mayan_item_parchment", () -> new ItemMillParchment("scroll.mayanItem.title", new String[]{"scroll.mayanItem.food", "scroll.mayanItem.tools", "scroll.mayanItem.weapons", "scroll.mayanItem.construction"}));
        public static final RegistryObject<Item> mayanAllParchment = ITEMS.register("mayan_all_parchment", () -> new ItemMillParchment("scroll.mayanVillager.title", new String[]{"scroll.mayanVillager.leaders", "scroll.mayanVillager.men", "scroll.mayanVillager.women", "scroll.mayanVillager.children", "scroll.mayanBuilding.centers", "scroll.mayanBuilding.houses", "scroll.mayanBuilding.uninhabited", "scroll.mayanBuilding.player", "scroll.mayanItem.food", "scroll.mayanItem.tools", "scroll.mayanItem.weapons", "scroll.mayanItem.construction"}));

        public static final RegistryObject<Item> japaneseVillagerParchment = ITEMS.register("japanese_villager_parchment", () -> new ItemMillParchment("scroll.japaneseVillager.title", new String[]{"scroll.japaneseVillager.leaders", "scroll.japaneseVillager.men", "scroll.japaneseVillager.women", "scroll.japaneseVillager.children"}));
        public static final RegistryObject<Item> japaneseBuildingParchment = ITEMS.register("japanese_building_parchment", () -> new ItemMillParchment("scroll.japaneseBuilding.title", new String[]{"scroll.japaneseBuilding.centers", "scroll.japaneseBuilding.houses", "scroll.japaneseBuilding.uninhabited", "scroll.japaneseBuilding.player"}));
        public static final RegistryObject<Item> japaneseItemParchment = ITEMS.register("japanese_item_parchment", () -> new ItemMillParchment("scroll.japaneseItem.title", new String[]{"scroll.japaneseItem.food", "scroll.japaneseItem.tools", "scroll.japaneseItem.weapons", "scroll.japaneseItem.construction"}));
        public static final RegistryObject<Item> japaneseAllParchment = ITEMS.register("japanese_all_parchment", () -> new ItemMillParchment("scroll.japaneseVillager.title", new String[]{"scroll.japaneseVillager.leaders", "scroll.japaneseVillager.men", "scroll.japaneseVillager.women", "scroll.japaneseVillager.children", "scroll.japaneseBuilding.centers", "scroll.japaneseBuilding.houses", "scroll.japaneseBuilding.uninhabited", "scroll.japaneseBuilding.player", "scroll.japaneseItem.food", "scroll.japaneseItem.tools", "scroll.japaneseItem.weapons", "scroll.japaneseItem.construction"}));
	
        public static void preinitialize()
        {
                ((BlockMillCrops) MillBlocks.cropTurmeric.get()).setSeed((IPlantable) turmeric.get());
                ((BlockMillCrops) MillBlocks.cropRice.get()).setSeed((IPlantable) rice.get());
                ((BlockMillCrops) MillBlocks.cropMaize.get()).setSeed((IPlantable) maize.get());
                ((BlockMillCrops) MillBlocks.cropGrapeVine.get()).setSeed((IPlantable) grapes.get());
        }
	
        public static void recipies() {
                // crafting recipes are now defined via JSON in data/millenaire/recipes
        }
	
       @OnlyIn(Dist.CLIENT)
       public static void prerender() {
               // model registration handled automatically via registry names
       }
	
        @OnlyIn(Dist.CLIENT)
        public static void render() {
                // item model rendering now handled by Forge automatically
        }
	}
