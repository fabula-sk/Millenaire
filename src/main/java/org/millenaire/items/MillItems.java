package org.millenaire.items;

import org.millenaire.Millenaire;
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
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MillItems
{
        public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Millenaire.MODID);
        //Class to hold basic items
	
	public static Item denier;
	public static Item denierOr;
	public static Item denierArgent;
	
	public static Item silk;
	public static Item obsidianFlake;
	public static Item unknownPowder;
	
	public static Item woolClothes;
	public static Item silkClothes;
	
	public static Item galianiteDust;
	
	//Crops
	public static Item turmeric;
	public static Item rice;
	public static Item maize;
	public static Item grapes;
	
	public static Item ciderApple;
	public static Item cider;
	public static Item calva;
	public static Item tripes;
	public static Item boudinNoir;
	
	public static Item vegCurry;
	public static Item murghCurry;
	public static Item rasgulla;
	
	public static Item cacauhaa;
	public static Item masa;
	public static Item wah;
	
	public static Item wine;
	public static Item malvasiaWine;
	public static Item feta;
	public static Item souvlaki;
	
	public static Item sake;
	public static Item udon;
	public static Item ikayaki;
	
	//Armour
	public static Item normanHelmet;
	public static Item normanChestplate;
	public static Item normanLeggings;
	public static Item normanBoots;
	
	public static Item byzantineHelmet;
	public static Item byzantineChestplate;
	public static Item byzantineLeggings;
	public static Item byzantineBoots;
	
	public static Item japaneseGuardHelmet;
	public static Item japaneseGuardChestplate;
	public static Item japaneseGuardLeggings;
	public static Item japaneseGuardBoots;
	
	public static Item japaneseBlueHelmet;
	public static Item japaneseBlueChestplate;
	public static Item japaneseBlueLeggings;
	public static Item japaneseBlueBoots;
	
	public static Item japaneseRedHelmet;
	public static Item japaneseRedChestplate;
	public static Item japaneseRedLeggings;
	public static Item japaneseRedBoots;
	
	public static Item mayanQuestCrown;
	
	//Wands
	public static Item wandSummoning;
	public static Item wandNegation;
	public static Item wandCreative;
	public static Item tuningFork;
	
	//Tools
	public static Item normanAxe;
	public static Item normanShovel;
	public static Item normanPickaxe;
	public static Item normanHoe;
	public static Item normanSword;
	
	public static Item mayanAxe;
	public static Item mayanShovel;
	public static Item mayanPickaxe;
	public static Item mayanHoe;
	public static Item mayanMace;
	
	public static Item byzantineMace;
	
	public static Item japaneseSword;
	public static Item japaneseBow;
	
	//Amulets
	public static Item amuletSkollHati;
	public static Item amuletYggdrasil;
	public static Item amuletAlchemist;
	public static Item amuletVishnu;
	
	//Wallet
	public static Item itemMillPurse;
	
	//Sign
	public static Item itemMillSign;
	
	//Parchments
	public static Item normanVillagerParchment;
	public static Item normanBuildingParchment;
	public static Item normanItemParchment;
	public static Item normanAllParchment;
	
	public static Item byzantineVillagerParchment;
	public static Item byzantineBuildingParchment;
	public static Item byzantineItemParchment;
	public static Item byzantineAllParchment;
	
	public static Item hindiVillagerParchment;
	public static Item hindiBuildingParchment;
	public static Item hindiItemParchment;
	public static Item hindiAllParchment;
	
	public static Item mayanVillagerParchment;
	public static Item mayanBuildingParchment;
	public static Item mayanItemParchment;
	public static Item mayanAllParchment;
	
	public static Item japaneseVillagerParchment;
	public static Item japaneseBuildingParchment;
	public static Item japaneseItemParchment;
	public static Item japaneseAllParchment;
	
	public static void preinitialize()
	{
                denier = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("denier", () -> denier);
                denierOr = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("denier_or", () -> denierOr);
                denierArgent = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("denier_argent", () -> denierArgent);
		
                silk = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("silk", () -> silk);
                obsidianFlake = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("obsidian_flake", () -> obsidianFlake);
                unknownPowder = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("unknown_powder", () -> unknownPowder);
		
		woolClothes = new Item().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("wool_clothes", () -> woolClothes);
		silkClothes = new Item().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("silk_clothes", () -> silkClothes);
		
                galianiteDust = new Item(new Item.Properties().tab(Millenaire.tabMillenaire));
                ITEMS.register("galianite_dust", () -> galianiteDust);
		
		//Crops
		turmeric = new ItemMillSeeds(MillBlocks.cropTurmeric).setCreativeTab(Millenaire.tabMillenaire);
    	((BlockMillCrops) MillBlocks.cropTurmeric).setSeed((IPlantable) turmeric);
    	ITEMS.register("turmeric", () -> turmeric);
    	rice = new ItemMillSeeds(MillBlocks.cropRice).setCreativeTab(Millenaire.tabMillenaire);
    	((BlockMillCrops) MillBlocks.cropRice).setSeed((IPlantable) rice);
    	ITEMS.register("rice", () -> rice);
    	maize = new ItemMillSeeds(MillBlocks.cropMaize).setCreativeTab(Millenaire.tabMillenaire);
    	((BlockMillCrops) MillBlocks.cropMaize).setSeed((IPlantable) maize);
    	ITEMS.register("maize", () -> maize);
    	grapes = new ItemMillSeeds(MillBlocks.cropGrapeVine).setCreativeTab(Millenaire.tabMillenaire);
    	((BlockMillCrops) MillBlocks.cropGrapeVine).setSeed((IPlantable) grapes);
    	ITEMS.register("grapes", () -> grapes);
    	ciderApple = new ItemMillFood(0, 0, 0, 1, 0.05F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("cider_apple", () -> ciderApple);
    	cider = new ItemMillFood(4, 15, 5, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("cider", () -> cider);
    	calva = ((ItemMillFood)new ItemMillFood(8, 30, 10, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.damageBoost.id, 180, 0, 1f);
    	ITEMS.register("calva", () -> calva);
    	tripes = new ItemMillFood(0, 0, 0, 10, 1.0F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("tripes", () -> tripes);
    	boudinNoir = new ItemMillFood(0, 0, 0, 10, 1.0F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("boudin_noir", () -> boudinNoir);
    	vegCurry = new ItemMillFood(0, 0, 0, 6, 0.6F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("veg_curry", () -> vegCurry);
    	murghCurry = ((ItemMillFood)new ItemMillFood(0, 0, 0, 8, 0.8F, false).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.fireResistance.id, 8 * 60, 0, 1f);
    	ITEMS.register("murgh_curry", () -> murghCurry);
    	rasgulla = ((ItemMillFood)new ItemMillFood(2, 30, 0, 0, 0.0F, false).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.moveSpeed.id, 8 * 60, 1, 1f).setAlwaysEdible();
    	ITEMS.register("rasgulla", () -> rasgulla);
    	cacauhaa = ((ItemMillFood)new ItemMillFood(6, 30, 3, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.nightVision.id, 8 * 60, 0, 1f);
    	ITEMS.register("cacauhaa", () -> cacauhaa);
    	masa = new ItemMillFood(0, 0, 0, 6, 0.6F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("masa", () -> masa);
    	wah = ((ItemMillFood)new ItemMillFood(0, 0, 0, 10, 1.0F, false).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.digSpeed.id, 8 * 60, 0, 1f);
    	ITEMS.register("wah", () -> wah);
    	wine = new ItemMillFood(3, 15, 5, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("wine", () -> wine);
    	malvasiaWine = ((ItemMillFood)new ItemMillFood(8, 30, 5, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.resistance.id, 8 * 60, 0, 1f);
    	ITEMS.register("malvasia_wine", () -> malvasiaWine);
    	feta = new ItemMillFood(3, 10, 0, 0, 0.0F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("feta", () -> feta);
    	souvlaki = ((ItemMillFood)new ItemMillFood(0, 0, 0, 10, 1.0F, false).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.heal.id, 1, 0, 1f);
    	ITEMS.register("souvlaki", () -> souvlaki);
    	sake = ((ItemMillFood)new ItemMillFood(8, 30, 10, 0, 0.0F, true).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.jump.id, 8 * 60, 1, 1f);
    	ITEMS.register("sake", () -> sake);
    	udon = new ItemMillFood(0, 0, 0, 8, 0.8F, false).setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("udon", () -> udon);
    	ikayaki = ((ItemMillFood)new ItemMillFood(0, 0, 0, 10, 1.0F, false).setCreativeTab(Millenaire.tabMillenaire)).setPotionEffect(Potion.waterBreathing.id, 8 * 60, 2, 1f);
    	ITEMS.register("ikayaki", () -> ikayaki);
    	
    	//Armour
    	normanHelmet = new ItemArmor(ItemMillArmor.ARMOR_norman, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_helmet", () -> normanHelmet);
		normanChestplate = new ItemArmor(ItemMillArmor.ARMOR_norman, 2, 1).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_chestplate", () -> normanChestplate);
		normanLeggings = new ItemArmor(ItemMillArmor.ARMOR_norman, 2, 2).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_leggings", () -> normanLeggings);
		normanBoots = new ItemArmor(ItemMillArmor.ARMOR_norman, 2, 3).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_boots", () -> normanBoots);
		
		byzantineHelmet = new ItemArmor(ItemMillArmor.ARMOR_byzantine, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("byzantine_helmet", () -> byzantineHelmet);
		byzantineChestplate = new ItemArmor(ItemMillArmor.ARMOR_byzantine, 2, 1).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("byzantine_chestplate", () -> byzantineChestplate);
		byzantineLeggings = new ItemArmor(ItemMillArmor.ARMOR_byzantine, 2, 2).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("byzantine_leggings", () -> byzantineLeggings);
		byzantineBoots = new ItemArmor(ItemMillArmor.ARMOR_byzantine, 2, 3).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("byzantine_boots", () -> byzantineBoots);
		
		japaneseGuardHelmet = new ItemArmor(ItemMillArmor.ARMOR_japaneseGuard, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_guard_helmet", () -> japaneseGuardHelmet);
		japaneseGuardChestplate = new ItemArmor(ItemMillArmor.ARMOR_japaneseGuard, 2, 1).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_guard_chestplate", () -> japaneseGuardChestplate);
		japaneseGuardLeggings = new ItemArmor(ItemMillArmor.ARMOR_japaneseGuard, 2, 2).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_guard_leggings", () -> japaneseGuardLeggings);
		japaneseGuardBoots = new ItemArmor(ItemMillArmor.ARMOR_japaneseGuard, 2, 3).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_guard_boots", () -> japaneseGuardBoots);
		
		japaneseBlueHelmet = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorBlue, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_blue_helmet", () -> japaneseBlueHelmet);
		japaneseBlueChestplate = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorBlue, 2, 1).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_blue_chestplate", () -> japaneseBlueChestplate);
		japaneseBlueLeggings = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorBlue, 2, 2).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_blue_leggings", () -> japaneseBlueLeggings);
		japaneseBlueBoots = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorBlue, 2, 3).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_blue_boots", () -> japaneseBlueBoots);
		
		japaneseRedHelmet = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorRed, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_red_helmet", () -> japaneseRedHelmet);
		japaneseRedChestplate = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorRed, 2, 1).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_red_chestplate", () -> japaneseRedChestplate);
		japaneseRedLeggings = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorRed, 2, 2).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_red_leggings", () -> japaneseRedLeggings);
		japaneseRedBoots = new ItemArmor(ItemMillArmor.ARMOR_japaneseWarriorRed, 2, 3).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_red_boots", () -> japaneseRedBoots);
		
		mayanQuestCrown = new mayanQuestCrown(ItemMillArmor.ARMOR_mayanQuest, 2, 0).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_quest_crown", () -> mayanQuestCrown);
		
		//Wands
		wandSummoning = new ItemMillWand().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("wand_summoning", () -> wandSummoning);
		wandNegation = new ItemMillWand().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("wand_negation", () -> wandNegation);
		wandCreative = new ItemMillWand().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("wand_creative", () -> wandCreative);
		
		tuningFork = new ItemMillWand().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("tuning_fork", () -> tuningFork);
		
		//Tools
		normanAxe = new ItemMillAxe(ItemMillTool.TOOLS_norman).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_axe", () -> normanAxe);
		normanShovel = new ItemMillShovel(ItemMillTool.TOOLS_norman).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_shovel", () -> normanShovel);
		normanPickaxe = new ItemMillPickaxe(ItemMillTool.TOOLS_norman).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_pickaxe", () -> normanPickaxe);
		normanHoe = new ItemMillHoe(ItemMillTool.TOOLS_norman).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_hoe", () -> normanHoe);
		normanSword = new ItemSword(ItemMillTool.TOOLS_norman).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("norman_sword", () -> normanSword);
		
		mayanAxe = new ItemMillAxe(ItemMillTool.TOOLS_obsidian).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_axe", () -> mayanAxe);
		mayanShovel = new ItemMillShovel(ItemMillTool.TOOLS_obsidian).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_shovel", () -> mayanShovel);
		mayanPickaxe = new ItemMillPickaxe(ItemMillTool.TOOLS_obsidian).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_pickaxe", () -> mayanPickaxe);
		mayanHoe = new ItemMillHoe(ItemMillTool.TOOLS_obsidian).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_hoe", () -> mayanHoe);
		mayanMace = new ItemSword(ItemMillTool.TOOLS_obsidian).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("mayan_mace", () -> mayanMace);
		
		byzantineMace = new ItemMillMace(Item.ToolMaterial.IRON).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("byzantine_mace", () -> byzantineMace);
		
		japaneseSword = new ItemSword(Item.ToolMaterial.IRON).setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_sword", () -> japaneseSword);
		japaneseBow = new ItemMillBow(2, 0.5F, "japanese_bow").setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("japanese_bow", () -> japaneseBow);
		
		//Amulets
		amuletSkollHati = new ItemMillAmulet().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("amulet_skoll_hati", () -> amuletSkollHati);
		amuletAlchemist = new ItemMillAmulet().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("amulet_alchemist", () -> amuletAlchemist);
		amuletVishnu = new ItemMillAmulet().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("amulet_vishnu", () -> amuletVishnu);
		amuletYggdrasil = new ItemMillAmulet().setCreativeTab(Millenaire.tabMillenaire);
		ITEMS.register("amulet_yggdrasil", () -> amuletYggdrasil);
		
		//Wallet
		itemMillPurse = new ItemMillWallet().setCreativeTab(Millenaire.tabMillenaire);
    	ITEMS.register("item_mill_purse", () -> itemMillPurse);
    	
    	//Sign
        itemMillSign = new ItemMillSign().setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("item_mill_sign", () -> itemMillSign);
		
		//Parchments
		normanVillagerParchment = new ItemMillParchment("scroll.normanVillager.title", new String[]{"scroll.normanVillager.leaders", "scroll.normanVillager.men", "scroll.normanVillager.women", "scroll.normanVillager.children"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("norman_villager_parchment", () -> normanVillagerParchment);
		normanBuildingParchment= new ItemMillParchment("scroll.normanBuilding.title", new String[]{"scroll.normanBuilding.centers", "scroll.normanBuilding.houses", "scroll.normanBuilding.uninhabited", "scroll.normanBuilding.player"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("norman_building_parchment", () -> normanBuildingParchment);
		normanItemParchment = new ItemMillParchment("scroll.normanItem.title", new String[]{"scroll.normanItem.food", "scroll.normanItem.tools", "scroll.normanItem.weapons", "scroll.normanItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("norman_item_parchment", () -> normanItemParchment);
		normanAllParchment = new ItemMillParchment("scroll.normanVillager.title", new String[]{"scroll.normanVillager.leaders", "scroll.normanVillager.men", "scroll.normanVillager.women", "scroll.normanVillager.children",
				"scroll.normanBuilding.centers", "scroll.normanBuilding.houses", "scroll.normanBuilding.uninhabited", "scroll.normanBuilding.player", "scroll.normanItem.food", "scroll.normanItem.tools", "scroll.normanItem.weapons", "scroll.normanItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("norman_all_parchment", () -> normanAllParchment);
		
		byzantineVillagerParchment = new ItemMillParchment("scroll.byzantineVillager.title", new String[]{"scroll.byzantineVillager.leaders", "scroll.byzantineVillager.men", "scroll.byzantineVillager.women", "scroll.byzantineVillager.children"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("byzantine_villager_parchment", () -> byzantineVillagerParchment);
		byzantineBuildingParchment= new ItemMillParchment("scroll.byzantineBuilding.title", new String[]{"scroll.byzantineBuilding.centers", "scroll.byzantineBuilding.houses", "scroll.byzantineBuilding.uninhabited", "scroll.byzantineBuilding.player"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("byzantine_building_parchment", () -> byzantineBuildingParchment);
		byzantineItemParchment = new ItemMillParchment("scroll.byzantineItem.title", new String[]{"scroll.byzantineItem.food", "scroll.byzantineItem.tools", "scroll.byzantineItem.weapons", "scroll.byzantineItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("byzantine_item_parchment", () -> byzantineItemParchment);
		byzantineAllParchment = new ItemMillParchment("scroll.byzantineVillager.title", new String[]{"scroll.byzantineVillager.leaders", "scroll.byzantineVillager.men", "scroll.byzantineVillager.women", "scroll.byzantineVillager.children",
				"scroll.byzantineBuilding.centers", "scroll.byzantineBuilding.houses", "scroll.byzantineBuilding.uninhabited", "scroll.byzantineBuilding.player", "scroll.byzantineItem.food", "scroll.byzantineItem.tools", "scroll.byzantineItem.weapons", "scroll.byzantineItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("byzantine_all_parchment", () -> byzantineAllParchment);
		
		hindiVillagerParchment = new ItemMillParchment("scroll.hindiVillager.title", new String[]{"scroll.hindiVillager.leaders", "scroll.hindiVillager.men", "scroll.hindiVillager.women", "scroll.hindiVillager.children"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("hindi_villager_parchment", () -> hindiVillagerParchment);
		hindiBuildingParchment= new ItemMillParchment("scroll.hindiBuilding.title", new String[]{"scroll.hindiBuilding.centers", "scroll.hindiBuilding.houses", "scroll.hindiBuilding.uninhabited", "scroll.hindiBuilding.player"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("hindi_building_parchment", () -> hindiBuildingParchment);
		hindiItemParchment = new ItemMillParchment("scroll.hindiItem.title", new String[]{"scroll.hindiItem.food", "scroll.hindiItem.tools", "scroll.hindiItem.weapons", "scroll.hindiItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("hindi_item_parchment", () -> hindiItemParchment);
		hindiAllParchment = new ItemMillParchment("scroll.hindiVillager.title", new String[]{"scroll.hindiVillager.leaders", "scroll.hindiVillager.men", "scroll.hindiVillager.women", "scroll.hindiVillager.children",
				"scroll.hindiBuilding.centers", "scroll.hindiBuilding.houses", "scroll.hindiBuilding.uninhabited", "scroll.hindiBuilding.player", "scroll.hindiItem.food", "scroll.hindiItem.tools", "scroll.hindiItem.weapons", "scroll.hindiItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("hindi_all_parchment", () -> hindiAllParchment);
		
		mayanVillagerParchment = new ItemMillParchment("scroll.mayanVillager.title", new String[]{"scroll.mayanVillager.leaders", "scroll.mayanVillager.men", "scroll.mayanVillager.women", "scroll.mayanVillager.children"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("mayan_villager_parchment", () -> mayanVillagerParchment);
		mayanBuildingParchment= new ItemMillParchment("scroll.mayanBuilding.title", new String[]{"scroll.mayanBuilding.centers", "scroll.mayanBuilding.houses", "scroll.mayanBuilding.uninhabited", "scroll.mayanBuilding.player"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("mayan_building_parchment", () -> mayanBuildingParchment);
		mayanItemParchment = new ItemMillParchment("scroll.mayanItem.title", new String[]{"scroll.mayanItem.food", "scroll.mayanItem.tools", "scroll.mayanItem.weapons", "scroll.mayanItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("mayan_item_parchment", () -> mayanItemParchment);
		mayanAllParchment = new ItemMillParchment("scroll.mayanVillager.title", new String[]{"scroll.mayanVillager.leaders", "scroll.mayanVillager.men", "scroll.mayanVillager.women", "scroll.mayanVillager.children",
				"scroll.mayanBuilding.centers", "scroll.mayanBuilding.houses", "scroll.mayanBuilding.uninhabited", "scroll.mayanBuilding.player", "scroll.mayanItem.food", "scroll.mayanItem.tools", "scroll.mayanItem.weapons", "scroll.mayanItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("mayan_all_parchment", () -> mayanAllParchment);
		
		japaneseVillagerParchment = new ItemMillParchment("scroll.japaneseVillager.title", new String[]{"scroll.japaneseVillager.leaders", "scroll.japaneseVillager.men", "scroll.japaneseVillager.women", "scroll.japaneseVillager.children"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("japanese_villager_parchment", () -> japaneseVillagerParchment);
		japaneseBuildingParchment= new ItemMillParchment("scroll.japaneseBuilding.title", new String[]{"scroll.japaneseBuilding.centers", "scroll.japaneseBuilding.houses", "scroll.japaneseBuilding.uninhabited", "scroll.japaneseBuilding.player"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("japanese_building_parchment", () -> japaneseBuildingParchment);
		japaneseItemParchment = new ItemMillParchment("scroll.japaneseItem.title", new String[]{"scroll.japaneseItem.food", "scroll.japaneseItem.tools", "scroll.japaneseItem.weapons", "scroll.japaneseItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("japanese_item_parchment", () -> japaneseItemParchment);
		japaneseAllParchment = new ItemMillParchment("scroll.japaneseVillager.title", new String[]{"scroll.japaneseVillager.leaders", "scroll.japaneseVillager.men", "scroll.japaneseVillager.women", "scroll.japaneseVillager.children",
				"scroll.japaneseBuilding.centers", "scroll.japaneseBuilding.houses", "scroll.japaneseBuilding.uninhabited", "scroll.japaneseBuilding.player", "scroll.japaneseItem.food", "scroll.japaneseItem.tools", "scroll.japaneseItem.weapons", "scroll.japaneseItem.construction"}).setCreativeTab(Millenaire.tabMillenaire);
                ITEMS.register("japanese_all_parchment", () -> japaneseAllParchment);
	}
	
        public static void recipies() {
                // crafting recipes are now defined via JSON in data/millenaire/recipes
        }
	
       @SideOnly(Side.CLIENT)
       public static void prerender() {
               // model registration handled automatically via registry names
       }
	
	@SideOnly(Side.CLIENT)
        public static void render() {
                // item model rendering now handled by Forge automatically
        }
	}
}
