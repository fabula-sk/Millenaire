package org.millenaire;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.millenaire.building.BuildingPlan;
import org.millenaire.building.BuildingProject;
import org.millenaire.building.BuildingTypes;
import org.millenaire.util.JsonHelper;
import org.millenaire.util.JsonHelper.VillageTypes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class MillCulture 
{
	public final String cultureName;
	//Entry 0 is male child, entry 1 is female child
	private VillagerType[] villagerTypes;
	private VillageType[] villageTypes;
	private BuildingPlan[] loneBuildings;
	private String[] vocalizations;
	private HashMap<String, String[]> nameLists = new HashMap<String, String[]>();

	private MillCulture(String nameIn)
	{
		cultureName = nameIn;
	}
	
	private MillCulture addNameList(String title, String[] list)
	{
		this.nameLists.put(title, list);
		return this;
	}
	
	private MillCulture setVillagerTypes(VillagerType[] typeIn)
	{
		this.villagerTypes = typeIn;
		return this;
	}
	
	public MillCulture setVillageTypes(VillageType[] typeIn)
	{
		this.villageTypes = typeIn;
		return this;
	}
	
	public MillCulture setLoneBuildings(BuildingPlan[] loneIn)
	{
		this.loneBuildings = loneIn;
		return this;
	}
	
	public VillagerType[] getVillagerTypes() { return this.villagerTypes; }
	
	public VillagerType getVillagerType(String typeIn)
	{
		for (VillagerType villagerType : villagerTypes) 
		{
			if (villagerType.id.equalsIgnoreCase(typeIn)) 
			{
				return villagerType;
			}
		}
		
		System.err.println("villagerType " + typeIn + " not found in " + cultureName + " culture.");
		return null;
	}

	public VillagerType getChildType(int gender)
	{
		if(gender == 0)
		{
			return villagerTypes[0];
		}
		else
		{
			return villagerTypes[1];
		}
	}
	
	public VillageType getVillageType(String typeIn)
	{
		for (VillageType villageType : villageTypes) 
		{
			if (villageType.id.equalsIgnoreCase(typeIn)) 
			{
				return villageType;
			}
		}
		
		System.err.println("villageType " + typeIn + " not found in " + cultureName + " culture.");
		return null;
	}
	
	public VillageType getRandomVillageType()
	{
		Random rand = new Random();
		int i = rand.nextInt(villageTypes.length);
		
		return villageTypes[i];
	}
	
	public String getVillageName() { return "Whoville"; }
	
	public String getVocalSentence(String vTypeIn) { return "Hi.  How are ya."; }
	
	//Remember to catch the Exception and handle it when calling getCulture
	public static MillCulture getCulture(String nameIn) throws Exception
	{
		switch (nameIn) {
			case "norman":
			    return normanCulture;
            case "hindi":
                return hindiCulture;
            case "mayan":
                return mayanCulture;
            case "japanese":
                return japaneseCulture;
            case "byzantine":
                return byzantineCulture;
            default:
                throw new Exception("getCulture called with incorrect culture.");
		}
	}
	
	public void exportVillages(JsonHelper.VillageTypes villagetypes) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(villagetypes));
                File f = new File(ServerLifecycleHooks.getCurrentServer().getDataDirectory().getAbsolutePath() + File.separator + "millenaire" + File.separator + "exports" + File.separator);
		File f1 = new File(f, "villages.json");
		try {
			f.mkdirs();
			f1.createNewFile();
			String g = gson.toJson(villagetypes);
			FileWriter fw = new FileWriter(f1);
			fw.write(g);
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    private void loadVillageTypes() {
                Gson gson = new Gson();
                IResourceManager rm = ServerLifecycleHooks.getCurrentServer().getResourceManager();
                try {
                        Collection<ResourceLocation> locs = rm.getAllResourceLocations("cultures/" + this.cultureName.toLowerCase(), s -> s.endsWith("villages.json"));
                        for (ResourceLocation rl : locs) {
                                try (InputStream is = rm.getResource(rl).getInputStream()) {
                                        VillageTypes vt = gson.fromJson(new InputStreamReader(is), VillageTypes.class);
                                        this.villageTypes = vt.types;
                                }
                        }
                        BuildingTypes.cacheBuildingTypes(normanCulture);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
	
	//////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public static MillCulture normanCulture;
	private static MillCulture hindiCulture;
    private static MillCulture mayanCulture;
    private static MillCulture japaneseCulture;
    private static MillCulture byzantineCulture;

	//public static MillCulture millDefault;

	public static void preinitialize()
	{
		//Norman Initialization
		normanCulture= new MillCulture("norman").addNameList("familyNames", new String[]{"Waldemar", "Vilfrid", "Thorstein", "Tankred", "Svenning", "Sigvald", "Sigmar", "Roland", "Reginald", "Radulf", "Otvard", "Odomar", "Norbert", "Manfred", "Lothar", "Lambert", "Klothar", "Ingmar", "Hubert", "Gildwin", "Gervin", "Gerald", "Froward", "Fredegar", "Falko", "Elfride", "Erwin", "Ditmar", "Didrik", "Bernhard", "Answald", "Adalrik"})
				.addNameList("nobleFamilyNames", new String[]{"de Bayeux", "de Conteville", "de Mortain", "de Falaise", "de Ryes"})
				.addNameList("maleNames", new String[]{"Answald", "Arnbjorn", "Almut", "Arnvald", "Baldrik", "Dankrad", "Dltwin", "Erwin", "Elfride", "Frank", "Froward", "Gerulf", "Gildwin", "Grim", "Hagbard", "Hartmod", "Helge", "Henrik", "Ingvald", "Karl", "Klothar", "Lothar", "Ludvig", "Norbert", "Odomar", "Radulf", "Richard", "Robert", "Roland", "Sigfred", "Tankred", "Thorgal", "Wilhelm"})
				.addNameList("femaleNames", new String[]{"Alfgard", "Alwine", "Bathilde", "Bernhilde", "Borglinde", "Dithilde", "Frida", "Gisela", "Herleva", "Hermine", "Irmine", "Matilde", "Ottilia", "Ragnhild", "Sighild", "Sigrune", "Solvej", "Thilda", "Ulrika", "Valborg"});
	
		normanCulture.setVillagerTypes(
				new VillagerType[]{new VillagerType("normanBoy", "GarÃ§on", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_boy0.png", "millenaire:textures/entities/norman/norman_boy1.png"}, false, false, 0),
				new VillagerType("normanGirl", "Fille", 1, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_girl0.png", "millenaire:textures/entities/norman/norman_girl1.png"}, false, false, 0),
				new VillagerType("normanAbbot", "AbbÃ©", 0, normanCulture.nameLists.get("nobleFamilyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_abbot0.png"}, true, false, 0),
				new VillagerType("normanLoneAbbot", "AbbÃ©", 0, normanCulture.nameLists.get("nobleFamilyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_abbot0.png"}, false, false, 0),
				new VillagerType("normanGuildMaster", "MaÃ®tre de Guilde", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_guild_master0.png"}, true, false, 0),
				new VillagerType("normanSenechal", "SÃ©nÃ©chal", 0, normanCulture.nameLists.get("nobleFamilyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_seneschal0.png", "millenaire:textures/entities/norman/norman_seneschal1.png"}, true, false, 0),
				new VillagerType("normanKnight", "Chevalier", 0, normanCulture.nameLists.get("nobleFamilyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_knight0.png"}, true, false, 0),
				new VillagerType("normanLady", "Dame", 1, normanCulture.nameLists.get("nobleFamilyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_lady0.png", "millenaire:textures/entities/norman/norman_lady1.png"}, false, false, 0),
				new VillagerType("normanCarpenter", "Charpentier", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_carpenter0.png"}, false, false, 0),
				new VillagerType("normanFarmer", "Fermier", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_farmer0.png", "millenaire:textures/entities/norman/norman_farmer1.png"}, false, false, 0),
				new VillagerType("normanCattleFarmerMale", "Eleveur Bovin", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_male0.png"}, false, false, 0),
				new VillagerType("normanCattleFarmerFemale", "Eleveuse Bovine", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_female0.png"}, false, true, 0),
				new VillagerType("normanGuard", "Garde", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_guard0.png", "millenaire:textures/entities/norman/norman_guard1.png"}, false, false, 32),
				new VillagerType("normanLumberman", "BÃ»cheron", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_lumberman0.png", "millenaire:textures/entities/norman/norman_lumberman1.png", "millenaire:textures/entities/norman/norman_lumberman2.png", "millenaire:textures/entities/norman/norman_lumberman3.png"}, false, false, 16),
				new VillagerType("normanLoneLumberman", "BÃ»cheron", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_lumberman0.png", "millenaire:textures/entities/norman/norman_lumberman1.png", "millenaire:textures/entities/norman/norman_lumberman2.png", "millenaire:textures/entities/norman/norman_lumberman3.png"}, false, false, 16),
				new VillagerType("normanLoneMiller", "Meunier", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_farmer0.png", "millenaire:textures/entities/norman/norman_farmer1.png"}, false, false, 0),
				new VillagerType("normanMiner", "Mineur", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_miner0.png", "millenaire:textures/entities/norman/norman_miner1.png"}, false, false, 0),
				new VillagerType("normanLoneMiner", "Mineur", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_miner0.png", "millenaire:textures/entities/norman/norman_miner1.png"}, false, false, 0),
				new VillagerType("normanMonk", "Moine", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_monk0.png"}, false, false, 0),
				new VillagerType("normanLoneMonk", "Moine", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_monk0.png"}, false, false, 0),
				new VillagerType("normanMerchant", "Marchant", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_merchant0.png", "millenaire:textures/entities/norman/norman_merchant1.png", "millenaire:textures/entities/norman/norman_merchant2.png"}, false, false, 0),
				new VillagerType("normanFoodMerchant", "Marchand de Nourriture", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_merchant_food0.png"}, false, false, 0),
				new VillagerType("normanPlantMerchant", "Herboriste", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_merchant_plants0.png"}, false, false, 0),
				new VillagerType("normanPigherdMale", "Eleveur Porcin", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_male0.png"}, false, false, 0),
				new VillagerType("normanPigherdFemale", "Eleveuse Porcine", 1, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_female0.png"}, false, true, 0),
				new VillagerType("normanPriest", "PrÃªtre", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_priest0.png", "millenaire:textures/entities/norman/norman_priest1.png"}, false, false, 0),
				new VillagerType("normanShepherdMale", "Eleveur Ovin", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_male0.png"}, false, false, 0),
				new VillagerType("normanShepherdFemale", "Eleveuse Ovine", 1, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_cattle_farmer_female0.png"}, false, true, 0),
				new VillagerType("normanBlacksmith", "Forgeron", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_blacksmith0.png"}, false, false, 0),
				new VillagerType("normanWife", "Villageoise", 1, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/norman/norman_wife0.png", "millenaire:textures/entities/norman/norman_wife1.png"}, false, true, 0),
				new VillagerType("normanAlchemist", "Alchimiste", 0, new String[]{"Vif-argent"}, new String[]{"Guillaume"}, new String[]{"millenaire:textures/entities/norman/norman_alchemist0.png"}, false, false, 0),
				new VillagerType("normanAlchemistAssistant", "Assistant", 0, new String[]{"Ulric"}, new String[]{"Robert"}, new String[]{"millenaire:textures/entities/norman/norman_alchemist_assistant0.png"}, false, false, 0),
				new VillagerType("normanAlchemistApprentice", "Apprenti Alchimiste", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_alchemist_apprentice0.png"}, false, false, 0),
				new VillagerType("normanBandit", "Bandit", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_bandit0.png", "millenaire:textures/entities/norman/norman_bandit1.png"}, false, false, 0),
				new VillagerType("normanArmoredBandit", "ArmoredBandit", 0, normanCulture.nameLists.get("familyNames"), normanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/norman/norman_armored_bandit0.png", "millenaire:textures/entities/norman/norman_armored_bandit1.png"}, false, false, 0)
		});

		/*normanCulture.setVillageTypes(new VillageType[]{
				new VillageType("test").setBuildingTypes(new BuildingPlan[]{BuildingProject.normanCommunauteA0}, new BuildingPlan[]{BuildingProject.testBuilding}, new BuildingPlan[]{BuildingProject.testBuilding}).setStartingBuildings(new BuildingPlan[]{BuildingProject.normanCommunauteA0})
		});*/
		
		//Hindi Initialization
		hindiCulture = new MillCulture("hindi").addNameList("highCasteFamilyNames", new String[]{"Sinha", "Kuwar", "Kuwar", "Mishra", "Pandey", "Jha", "Khatri"})
				.addNameList("lowCasteFamilyNames", new String[]{"Sharma", "Paswan", "Karmakar", "Yadav", "Prasad", "Baghel", "Agariya", "Badhik", "Badi", "Baheliya", "Baiga", "Bajaniya", "Bajgi", "Balai", "Balmiki", "Bangali", "Banmanus", "Bansphor", "Barwar", "Basor", "Bawariya", "Bhantu", "Bhuiya", "Chamar", "Chero", "Dabgar", "Dhangar", "Dhanuk", "Dharkar", "Dhobi", "Domar", "Dusadh", "Gharami", "Ghasiya", "Gond", "Gual", "Habura", "Hari", "Hela", "Kalabaz", "Kanjar", "Kapariya", "Karwal", "Khairaha", "Khatik", "Kharot", "Kori", "Korwa", "Lal Begi", "Majhwar", "Mazhabi", "Musahar", "Nat", "Pankha", "Parahiya", "Pasi", "Patari", "Rawat", "Sahariya", "Sanaurhiya", "Sansiya", "Shilpkar", "Turaiha"})
				.addNameList("highCasteFemaleNames", new String[]{"Abha", "Aditi", "Deepti", "Manasi", "Jyoti", "Shobhana", "Shobha", "Akhila", "Amrita", "Anjali", "Anupama", "Aparajita", "Shalini", "Soumya", "Lavanya"})
				.addNameList("lowCasteFemaleNames", new String[]{"Abha", "Aditi", "Deepti", "Manasi", "Jyoti", "Shobhana", "Shobha", "Rani", "Mayuri", "Geeta", "Seeta", "Chanda", "Titli", "Vimla", "Sudha", "Suman", "Suneeta", "Babli", "Kamala"})
				.addNameList("maleNames", new String[]{"Ravi", "Rajiv", "Santosh", "Akash", "Akhil", "Raj", "Rahul", "Rohit", "Laxman", "Gopal", "Vishnu", "Ashok", "Akshay", "Chetan", "Dilip", "Deepak", "Govind", "Hari", "Harsh", "Kamal", "Madhav"});
		
		hindiCulture.setVillagerTypes(new VillagerType[]{
				new VillagerType("hindiBoy", "Larka", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_boy0.png", "millenaire:textures/entities/hindi/hindi_boy1.png", "millenaire:textures/entities/hindi/hindi_boy2.png", "millenaire:textures/entities/hindi/hindi_boy3.png"}, false, false, 0),
				new VillagerType("hindiGirl", "Larki", 1, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_girl0.png", "millenaire:textures/entities/hindi/hindi_girl1.png"}, false, false, 0),
				new VillagerType("hindiRaja", "Raja", 0, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_raja0.png", "millenaire:textures/entities/hindi/hindi_raja1.png"}, true, false, 0),
				new VillagerType("hindiRajputGeneral", "Rajput Senapati", 0, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_rajput_leader0.png", "millenaire:textures/entities/hindi/hindi_rajput_leader1.png"}, true, false, 0),
				new VillagerType("hindiVillageChief", "Gaanv ka Mukhiya", 0, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_chief0.png", "millenaire:textures/entities/hindi/hindi_chief1.png"}, true, false, 0),
				new VillagerType("hindiRani", "Rani", 1, hindiCulture.nameLists.get("highCasteFamilyNames"), new String[]{"Rani"}, new String[]{"millenaire:textures/entities/hindi/hindi_rich_woman0.png", "millenaire:textures/entities/hindi/hindi_rich_woman1.png", "millenaire:textures/entities/hindi/hindi_rich_woman2.png", "millenaire:textures/entities/hindi/hindi_rich_woman3.png"}, false, false, 0),
				new VillagerType("hindiRichWoman", "Malkin", 1, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_rich_woman0.png", "millenaire:textures/entities/hindi/hindi_rich_woman1.png", "millenaire:textures/entities/hindi/hindi_rich_woman2.png", "millenaire:textures/entities/hindi/hindi_rich_woman3.png"}, false, false, 0),
				new VillagerType("hindiAdivasiPeasant", "Adivasi Kisaan", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_peasant0.png", "millenaire:textures/entities/hindi/hindi_peasant1.png", "millenaire:textures/entities/hindi/hindi_peasant2.png", "millenaire:textures/entities/hindi/hindi_peasant3.png"}, false, false, 0),
				new VillagerType("hindiAdivasiPeasantWife", "Mahilaa Adivasi", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_peasant_wife0.png", "millenaire:textures/entities/hindi/hindi_peasant_wife1.png", "millenaire:textures/entities/hindi/hindi_peasant_wife2.png", "millenaire:textures/entities/hindi/hindi_peasant_wife3.png", "millenaire:textures/entities/hindi/hindi_peasant_wife4.png", "millenaire:textures/entities/hindi/hindi_peasant_wife5.png", "millenaire:textures/entities/hindi/hindi_peasant_wife6.png"}, false, false, 0),
				new VillagerType("hindiArmySmith", "Sena ka Loohaar", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_army_smith0.png"}, false, false, 0),
				new VillagerType("hindiLumberman", "Larkarhara", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_lumberman0.png", "millenaire:textures/entities/hindi/hindi_lumberman1.png", "millenaire:textures/entities/hindi/hindi_lumberman2.png", "millenaire:textures/entities/hindi/hindi_lumberman3.png"}, false, false, 16),
				new VillagerType("hindiMerchant", "Vyapari", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_merchant0.png", "millenaire:textures/entities/hindi/hindi_merchant1.png"}, false, false, 0),
				new VillagerType("hindiMerchantAdivasi", "Adivasi Vyapari", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_merchant0.png", "millenaire:textures/entities/hindi/hindi_merchant1.png"}, false, false, 0),
				new VillagerType("hindiMerchantVillageWoman", "Gaanv ki Murkha", 1, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_peasant_wife0.png", "millenaire:textures/entities/hindi/hindi_peasant_wife1.png", "millenaire:textures/entities/hindi/hindi_peasant_wife2.png", "millenaire:textures/entities/hindi/hindi_peasant_wife3.png", "millenaire:textures/entities/hindi/hindi_peasant_wife4.png", "millenaire:textures/entities/hindi/hindi_peasant_wife5.png", "millenaire:textures/entities/hindi/hindi_peasant_wife6.png"}, false, false, 0),
				new VillagerType("hindiLoneTrader", "Vyapari", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_merchant0.png", "millenaire:textures/entities/hindi/hindi_merchant1.png"}, false, false, 0),
				new VillagerType("hindiMiner", "Khanik", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_lumberman0.png", "millenaire:textures/entities/hindi/hindi_lumberman1.png", "millenaire:textures/entities/hindi/hindi_lumberman2.png", "millenaire:textures/entities/hindi/hindi_lumberman3.png"}, false, false, 0),
				new VillagerType("hindiPriest", "Pandit", 0, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_priest0.png", "millenaire:textures/entities/hindi/hindi_priest1.png"}, false, false, 0),
				new VillagerType("hindiPanditayin", "Panditayin", 1, hindiCulture.nameLists.get("highCasteFamilyNames"), hindiCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_rich_woman0.png", "millenaire:textures/entities/hindi/hindi_rich_woman1.png", "millenaire:textures/entities/hindi/hindi_rich_woman2.png", "millenaire:textures/entities/hindi/hindi_rich_woman3.png"}, false, false, 0),
				new VillagerType("hindiPeasant", "Kisaan", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_peasant0.png", "millenaire:textures/entities/hindi/hindi_peasant1.png", "millenaire:textures/entities/hindi/hindi_peasant2.png", "millenaire:textures/entities/hindi/hindi_peasant3.png"}, false, true, 0),
				new VillagerType("hindiPeasantWife", "Mahilaa Kisaan", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_peasant_wife0.png", "millenaire:textures/entities/hindi/hindi_peasant_wife1.png", "millenaire:textures/entities/hindi/hindi_peasant_wife2.png", "millenaire:textures/entities/hindi/hindi_peasant_wife3.png", "millenaire:textures/entities/hindi/hindi_peasant_wife4.png", "millenaire:textures/entities/hindi/hindi_peasant_wife5.png", "millenaire:textures/entities/hindi/hindi_peasant_wife6.png"}, false, false, 0),
				new VillagerType("hindiSculptor", "Muurtikaar", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_scultor0.png"}, false, false, 0),
				new VillagerType("hindiSmith", "Loohaar", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_smith0.png"}, false, false, 0),
				new VillagerType("hindiRajputSoldier", "Rajput Sainik", 0, hindiCulture.nameLists.get("lowCasteFamilyNames"), hindiCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/hindi/hindi_rajput_warrior0.png", "millenaire:textures/entities/hindi/hindi_rajput_warrior1.png", "millenaire:textures/entities/hindi/hindi_rajput_warrior2.png"}, false, false, 32),
				new VillagerType("hindiSadhu", "Sadhu", 0, new String[]{"Vidya"}, new String[]{"Sadhu"}, new String[]{"millenaire:textures/entities/hindi/hindi_sadhu0.png"}, false, false, 0)
		});
		
		//Mayan Initialization
		mayanCulture = new MillCulture("mayan").addNameList("highCasteFamilyNames", new String[]{"Yax Pasaj Chan Yoaat", "Ukit Took'", "K'inich Yak K'uk Mo'", "K'u Ix", "B'alam Nan", "Chan Imix K'awiil", "Waxaklajuun Ub'aah K'awiil"})
				.addNameList("lowCasteFamilyNames", new String[]{"Ichik", "Ikan", "Acat", "Ah Bolom Tzacab", "Ah Cancum", "Ah Chun Cann", "Ah Ciliz", "Ah Cuxtal", "Ah Huluneb", "Ah Kin", "Ah Kumix Uinicob", "Ah Mun", "Ah Muzencab", "Ah Patnar Uinicob", "Ah Peku", "Ah Puch", "Ah Uinicir Dz'acab", "Ah Uuc Ticab", "Backlum Chaam", "Bolontiku", "Camazots", "Chamer", "Chaob", "Chibirias", "Cit-Bolon-Tum", "Cocijo", "Colel Cab", "Cum Hau", "Hanhau", "Hunapu", "Huncame", "Hunhau", "Hurukan", "Ix Chebel Yax", "Ixzaluoh", "Kan-Xib-Yui", "Kinich-Ahau", "Cizin", "Nohochacyum", "Tlacolotl", "Vucub-Caquix", "Xmucane", "Xpiyacoc", "Zipakna", "Kabrakan", "Zots", "Yum Caax", "Colop U Uichkin", "Ab Kin Zoc", "Cacoch", "Cauac", "Mulac", "Naum", "Chiccan", "Ah Kunchil", "Ahpop-Achi", "Atlacatl", "Hunyg", "Ak", "Xipe-Topec"})
				.addNameList("highCasteFemaleNames", new String[]{"Itzel", "Ixchab", "Ixchel", "Ixchup", "Malinali", "Meztli", "Nhutalu", "Quibock-Nicte", "Tzytzyan", "Ysalane", "Zafrina", "Eme", "Yohl Ik'nal", "Emetaly", "Ichika"})
				.addNameList("lowCasteFemaleNames", new String[]{"Arana", "Nictha", "Tamay", "Can", "Chan", "Be", "Cantun", "Canche", "Chi", "Chuc", "Coyoc", "Dzib", "Dzul", "Ehuan", "Hoil", "Hau", "May", "Pool", "Zapo", "Ucan", "Pech", "Camal", "Xiu", "Canul", "Cocom", "Tun"})
				.addNameList("maleNames", new String[]{"Acan", "Ac Yanto", "Ah Kin Xoc", "Ah Tabai", "Bacab", "Balam", "Buluc Chabtan", "Chac Uayab Xoc", "Chantico", "Ekchuah", "Nachancan", "Gucumatz", "Hun-Hunapu", "Itzamna", "Ix", "Ixtab", "Kucumatz", "Tepeu", "Tohil", "Xbalanque", "Kukulcan"});
		
		mayanCulture.setVillagerTypes(new VillagerType[]{
				new VillagerType("mayanBoy", "Mijin", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_boy0.png", "millenaire:textures/entities/mayan/mayan_boy1.png", "millenaire:textures/entities/mayan/mayan_boy2.png", "millenaire:textures/entities/mayan/mayan_boy3.png"}, false, false, 0),
				new VillagerType("mayanGirl", "Mijin", 1, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_girl0.png", "millenaire:textures/entities/mayan/mayan_girl1.png"}, false, false, 0),
				new VillagerType("mayanChieftain", "Ajaw", 0, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_chieftain0.png", "millenaire:textures/entities/mayan/mayan_chieftain1.png"}, true, false, 0),
				new VillagerType("mayanKing", "Ajaw", 0, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_king0.png", "millenaire:textures/entities/mayan/mayan_king1.png"}, true, false, 0),
				new VillagerType("mayanLeader", "Chanan", 0, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_leader0.png", "millenaire:textures/entities/mayan/mayan_leader1.png"}, true, false, 0),
				new VillagerType("mayanRichWoman", "Ix", 1, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_rich_woman0.png", "millenaire:textures/entities/mayan/mayan_rich_woman1.png", "millenaire:textures/entities/mayan/mayan_rich_woman2.png", "millenaire:textures/entities/mayan/mayan_rich_woman3.png"}, true, false, 0),
				new VillagerType("mayanArmySmith", "Ah Tz'on", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_army_smith0.png"}, false, false, 0),
				new VillagerType("mayanChickenFarmer", "We'matz", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_chicken_farmer0.png"}, false, false, 0),
				new VillagerType("mayanCocoaFarmer", "Ka'kau' We'matz", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_farmer0.png", "millenaire:textures/entities/mayan/mayan_farmer1.png"}, false, false, 0),
				new VillagerType("mayanCrafter", "Tz'on", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_crafter0.png"}, false, false, 0),
				new VillagerType("mayanObsidianCrafter", "Ta'as Tz'on", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_crafter0.png"}, false, false, 0),
				new VillagerType("mayanFarmer", "We'matz", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_farmer0.png", "millenaire:textures/entities/mayan/mayan_farmer1.png"}, false, false, 0),
				new VillagerType("mayanLoneFarmer", "We'matz", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_farmer0.png", "millenaire:textures/entities/mayan/mayan_farmer1.png"}, false, false, 0),
				new VillagerType("mayanLoneFarmerWife", "Atan", 1, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_peasant_wife0.png", "millenaire:textures/entities/mayan/mayan_peasant_wife1.png", "millenaire:textures/entities/mayan/mayan_peasant_wife2.png", "millenaire:textures/entities/mayan/mayan_peasant_wife3.png", "millenaire:textures/entities/mayan/mayan_peasant_wife4.png", "millenaire:textures/entities/mayan/mayan_peasant_wife5.png", "millenaire:textures/entities/mayan/mayan_peasant_wife6.png"}, false, false, 0),
				new VillagerType("mayanLumberman", "Te'xu'", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_lumberman0.png", "millenaire:textures/entities/mayan/mayan_lumberman1.png", "millenaire:textures/entities/mayan/mayan_lumberman2.png", "millenaire:textures/entities/mayan/mayan_lumberman3.png"}, false, false, 16),
				new VillagerType("mayanMerchant", "Ajpay", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_merchant0.png", "millenaire:textures/entities/mayan/mayan_merchant1.png"}, false, false, 0),
				new VillagerType("mayanMerchantFarmer", "Nuunjul We'matz", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_farmer0.png", "millenaire:textures/entities/mayan/mayan_farmer1.png"}, false, false, 0),
				new VillagerType("mayanMerchantHunter", "Nuunjul Aaj Inic", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_lumberman0.png", "millenaire:textures/entities/mayan/mayan_lumberman1.png", "millenaire:textures/entities/mayan/mayan_lumberman2.png", "millenaire:textures/entities/mayan/mayan_lumberman3.png"}, false, false, 0),
				new VillagerType("mayanMerchantShaman", "Nuunjul Aj K'in", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_shaman0.png", "millenaire:textures/entities/mayan/mayan_shaman1.png"}, false, false, 0),
				new VillagerType("mayanMiner", "Pan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_lumberman0.png", "millenaire:textures/entities/mayan/mayan_lumberman1.png", "millenaire:textures/entities/mayan/mayan_lumberman2.png", "millenaire:textures/entities/mayan/mayan_lumberman3.png"}, false, false, 0),
				new VillagerType("mayanPeasant", "Winik", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_peasant0.png", "millenaire:textures/entities/mayan/mayan_peasant1.png", "millenaire:textures/entities/mayan/mayan_peasant2.png", "millenaire:textures/entities/mayan/mayan_peasant3.png"}, false, true, 0),
				new VillagerType("mayanPeasantWife", "Atan", 1, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_peasant_wife0.png", "millenaire:textures/entities/mayan/mayan_peasant_wife1.png", "millenaire:textures/entities/mayan/mayan_peasant_wife2.png", "millenaire:textures/entities/mayan/mayan_peasant_wife3.png", "millenaire:textures/entities/mayan/mayan_peasant_wife4.png", "millenaire:textures/entities/mayan/mayan_peasant_wife5.png", "millenaire:textures/entities/mayan/mayan_peasant_wife6.png"}, false, false, 0),
				new VillagerType("mayanSculptor", "Uxul", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_sculptor0.png"}, false, false, 0),
				new VillagerType("mayanShaman", "Aj K'in", 0, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_shaman0.png", "millenaire:textures/entities/mayan/mayan_shaman1.png"}, false, false, 0),
				new VillagerType("mayanShamanWife", "Aj", 1, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_rich_woman0.png", "millenaire:textures/entities/mayan/mayan_rich_woman1.png", "millenaire:textures/entities/mayan/mayan_rich_woman2.png", "millenaire:textures/entities/mayan/mayan_rich_woman3.png"}, false, false, 0),
				new VillagerType("mayanLoneShaman", "Aj K'in", 0, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_shaman0.png", "millenaire:textures/entities/mayan/mayan_shaman1.png"}, false, false, 0),
				new VillagerType("mayanLoneShamanWife", "Aj", 1, mayanCulture.nameLists.get("highCasteFamilyNames"), mayanCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_rich_woman0.png", "millenaire:textures/entities/mayan/mayan_rich_woman1.png", "millenaire:textures/entities/mayan/mayan_rich_woman2.png", "millenaire:textures/entities/mayan/mayan_rich_woman3.png"}, false, false, 0),
				new VillagerType("mayanWarrior", "Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_warrior0.png", "millenaire:textures/entities/mayan/mayan_warrior1.png", "millenaire:textures/entities/mayan/mayan_warrior2.png"}, false, false, 32),
				new VillagerType("mayanFallenKing", "Keban Ajaw", 0, new String[]{"K'u Ix"}, new String[]{"Ixtab"}, new String[]{"millenaire:textures/entities/mayan/mayan_king0.png"}, false, false, 0),
				new VillagerType("mayanArmyEliteWarrior", "Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_warrior0.png", "millenaire:textures/entities/mayan/mayan_warrior1.png", "millenaire:textures/entities/mayan/mayan_warrior2.png"}, false, false, 0),
				new VillagerType("mayanArmyLieutenant", "Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_warrior0.png", "millenaire:textures/entities/mayan/mayan_warrior1.png", "millenaire:textures/entities/mayan/mayan_warrior2.png"}, false, false, 0),
				new VillagerType("mayanArmyScout", "Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_warrior0.png", "millenaire:textures/entities/mayan/mayan_warrior1.png", "millenaire:textures/entities/mayan/mayan_warrior2.png"}, false, false, 0),
				new VillagerType("mayanArmyWarrior", "Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_warrior0.png", "millenaire:textures/entities/mayan/mayan_warrior1.png", "millenaire:textures/entities/mayan/mayan_warrior2.png"}, false, false, 0),
				new VillagerType("mayanQuestShaman", "Aj K'in", 0, new String[]{"Uchben"}, new String[]{"Tohil"}, new String[]{"millenaire:textures/entities/mayan/mayan_shaman0.png", "millenaire:textures/entities/mayan/mayan_shaman1.png"}, false, false, 0),
				new VillagerType("mayanBanditMale", "K'as Mijin", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_bandit_male0.png", "millenaire:textures/entities/mayan/mayan_bandit_male1.png"}, false, false, 0),
				new VillagerType("mayanBanditFemale", "K'as Aj", 1, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_bandit_female0.png", "millenaire:textures/entities/mayan/mayan_bandit_female1.png"}, false, false, 0),
				new VillagerType("mayanBanditWarrior", "K'as Kanan", 0, mayanCulture.nameLists.get("lowCasteFamilyNames"), mayanCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/mayan/mayan_bandit_warrior0.png", "millenaire:textures/entities/mayan/mayan_bandit_warrior1.png", "millenaire:textures/entities/mayan/mayan_bandit_warrior2.png"}, false, false, 0)
		});
		
		//Japanese Initialization
		japaneseCulture = new MillCulture("japanese").addNameList("highCasteFamilyNames", new String[]{"Minamoto", "Fujiwara", "Taira", "Shikibu", "Ki", "Ariwara", "Tachibana", "Soga"})
				.addNameList("lowCasteFamilyNames", new String[]{"Date", "Sakuma", "Sato", "Suzuki", "Takahashi", "Watanabe", "Nakamura", "Kobayashi", "Yoshida", "Mori", "Hattori", "Mako", "Hashiba", "Sasaki", "Ito", "Kudo", "Kimura", "Narita", "Chiba", "Kikuchi", "Endo", "Arai", "Yamamoto", "Yamada", "Fukazawa", "Mochizuki", "Kato", "Nishimura", "Maeda", "Tanaka", "Fujii", "Ochi", "Oonishi", "Yamashita", "Hamada", "Komatsu", "Inoue", "Ono", "Goto", "Abe", "Sakamoto", "Matsumoto", "Kai", "Kuroki", "Kawano", "Hidaka", "Arakaki", "Miyagi", "Oshiro", "Higa", "Murakami", "Yamaguchi", "Kinjo", "Hideyoshi"})
				.addNameList("highCasteFemaleNames", new String[]{"Takako", "Murasaki", "Izumi", "Komachi"})
				.addNameList("lowCasteFemaleNames", new String[]{"Akane", "Ami", "Asuka", "Aya", "Ayano", "Hina", "Kana", "Mai", "Mayu", "Miki", "Misaki", "Miyu", "Mizuki", "Nana", "Nanami", "Natsumi", "Reina", "Riko", "Rin", "Saika", "Saki", "Sakura", "Yui", "Yuukama"})
				.addNameList("maleNames", new String[]{"Akira", "Eiichi", "Entarou", "Gaku", "Gojirou", "Hachitarou", "Hajime", "Haruki", "Hideki", "Hiro", "Hitoshi", "Ichirou", "Itsuo", "Jin", "Kenichi", "Kentaro", "Mineo", "Mitsuru", "Noato", "Osamu", "Reijiro", "Renzo", "Saburo", "Shingo", "Shinjiro", "Shinya", "Shouji", "Tadashi", "Takuji", "Tai", "Toyotomi", "Tsuyoshi", "Yuichiro", "Yuijiro"});
		
		japaneseCulture.setVillagerTypes(new VillagerType[]{
				new VillagerType("japaneseBoy", "Danshi", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_boy0.png", "millenaire:textures/entities/japanese/japanese_boy1.png"}, false, false, 0),
				new VillagerType("japaneseGirl", "Jou", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_girl0.png", "millenaire:textures/entities/japanese/japanese_girl1.png"}, false, false, 0),
				new VillagerType("japaneseBrewer", "Touji", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_brewer0.png"}, true, false, 0),
				new VillagerType("japaneseFarmerChief", "Chokan", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant1.png"}, true, false, 0),
				new VillagerType("japaneseKuge", "Kokushi", 0, japaneseCulture.nameLists.get("highCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_overseer0.png", "millenaire:textures/entities/japanese/japanese_overseer1.png"}, true, false, 0),
				new VillagerType("japaneseSamuraiGeneral", "Shukun", 0, japaneseCulture.nameLists.get("highCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_warrior_master0.png"}, true, false, 0),
				new VillagerType("japaneseBrewerWife", "Kuramoto", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_brewer_woman0.png"}, false, false, 0),
				new VillagerType("japaneseBraveWoman", "Jojoufu", 1, japaneseCulture.nameLists.get("highCasteFamilyNames"), japaneseCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_brave_woman2.png", "millenaire:textures/entities/japanese/japanese_brave_woman1.png"}, false, false, 32),
				new VillagerType("japaneseRichWoman", "Reifujin", 1, japaneseCulture.nameLists.get("highCasteFamilyNames"), japaneseCulture.nameLists.get("highCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_lady_woman0.png", "millenaire:textures/entities/japanese/japanese_lady_woman1.png"}, false, false, 0),
				new VillagerType("japaneseFarmerWife", "Tsuma", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant_wife0.png", "millenaire:textures/entities/japanese/japanese_peasant_wife1.png", "millenaire:textures/entities/japanese/japanese_peasant_wife2.png", "millenaire:textures/entities/japanese/japanese_peasant_wife3.png"}, false, false, 0),
				new VillagerType("japaneseArmySmith", "Kaji", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_army_smith0.png"}, false, false, 0),
				new VillagerType("japaneseChickenFarmer", "Youkeika", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant0.png"}, false, false, 0),
				new VillagerType("japaneseCrafter", "Saikushi", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_crafter0.png"}, false, false, 0),
				new VillagerType("japaneseFarmer", "Hyakushou", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_farmer0.png"}, false, false, 0),
				new VillagerType("japaneseFisherman", "Ryoushi", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_fisherman0.png"}, false, false, 0),
				new VillagerType("japaneseInnkeeper", "Teishu", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_brewer0.png"}, false, false, 0),
				new VillagerType("japaneseInnkeeperWife", "Tsuma", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_brewer_woman0.png"}, false, false, 0),
				new VillagerType("japaneseInnkeeperServant", "Gejo", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant_wife0.png", "millenaire:textures/entities/japanese/japanese_peasant_wife1.png"}, false, false, 0),
				new VillagerType("japaneseLumberman", "Kikori", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_lumberman0.png"}, false, false, 16),
				new VillagerType("japaneseMiner", "Koufu", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_miner0.png"}, false, false, 0),
				new VillagerType("japaneseLoneMiner", "Koufu", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_miner0.png"}, false, false, 0),
				new VillagerType("japaneseMerchant", "Chounin", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_merchant0.png", "millenaire:textures/entities/japanese/japanese_merchant1.png"}, false, false, 0),
				new VillagerType("japaneseFoodMerchant", "Shokuryo no ShÅ�nin", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant_wife0.png", "millenaire:textures/entities/japanese/japanese_peasant_wife1.png"}, false, false, 0),
				new VillagerType("japaneseBlacksmithMerchant", "Kajiya no ShÅ�nin", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_crafter0.png"}, false, false, 0),
				new VillagerType("japaneseMonk", "Souryo", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_monk0.png"}, false, false, 0),
				new VillagerType("japanesePeasant", "Noufu", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant0.png", "millenaire:textures/entities/japanese/japanese_peasant1.png"}, false, true, 0),
				new VillagerType("japanesePeasantWife", "Tsuma", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant_wife0.png", "millenaire:textures/entities/japanese/japanese_peasant_wife1.png", "millenaire:textures/entities/japanese/japanese_peasant_wife2.png", "millenaire:textures/entities/japanese/japanese_peasant_wife3.png"}, false, false, 0),
				new VillagerType("japanesePainter", "Choukou", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_sculptor0.png"}, false, false, 0),
				new VillagerType("japaneseBlacksmith", "Kajiya", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_smith0.png"}, false, false, 0),
				new VillagerType("japaneseSamurai", "Keibou", 0, japaneseCulture.nameLists.get("highCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_warrior0.png", "millenaire:textures/entities/japanese/japanese_warrior1.png"}, true, false, 32),
				new VillagerType("japaneseFemaleServant", "Gejo", 1, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("lowCasteFemaleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_peasant_wife0.png", "millenaire:textures/entities/japanese/japanese_peasant_wife1.png"}, false, false, 0),
				new VillagerType("japaneseBandit", "Hito", 0, japaneseCulture.nameLists.get("lowCasteFamilyNames"), japaneseCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/japanese/japanese_bandit0.png"}, false, false, 0)
		});
		
		//Byzantine Initialization
		byzantineCulture = new MillCulture("byzantine").addNameList("familyNames", new String[]{"Philoponus", "Monachos", "Kinnamos", "Moschopoulos", "Kraikos", "Xenos", "Galanis", "Kandake", "Peleus", "Achilles", "Herodias", "Helios", "Amethea", "Demeter", "Eileithyia", "Eudoxia", "Sophronia", "Ligeia", "Pantagiota", "Rhea"})
				.addNameList("maleNames", new String[]{"Georgios", "Leo", "Nikephoros", "Eutocius", "Demetrius", "Philipos", "Sokrates", "Platon", "Alexandros", "Lisias", "Ilias", "Ikaros", "Thisseas", "Odysseus", "Egeas", "Iassonas", "Achilleas", "Menelaos", "Ioannis", "Iljios", "Jannis", "Demostenes", "Krateos", "Amphion"})
				.addNameList("femaleNames", new String[]{"Daphne", "Danae", "Medea", "Helena", "Elena", "Nephele", "Euphoria", "Ariadne", "Alkmene", "Eurydike", "Olympia", "Kassandra", "Athina", "Artemis", "Artemisisa", "Hestia", "Estia", "Antigone", "Alexandra", "Thalia", "Niki", "Nike", "Niobe", "Efgenia", "Ifigenia", "Ismene", "Xenia"});
		
		byzantineCulture.setVillagerTypes(new VillagerType[]{
				new VillagerType("byzantineBoy", "Neos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_boy0.png"}, false, false, 0),
				new VillagerType("byzantineGirl", "Kore", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_girl0.png"}, false, false, 0),
				new VillagerType("byzantineBaron", "Akrita", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_baron0.png", "millenaire:textures/entities/byzantine/byzantine_baron1.png"}, true, false, 0),
				new VillagerType("byzantineCenturio", "Kentarios", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_centurio0.png", "millenaire:textures/entities/byzantine/byzantine_centurio1.png"}, true, false, 0),
				new VillagerType("byzantinePatriarch", "Patriarches", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_patriarch0.png"}, true, false, 0),
				new VillagerType("byzantineRichWife", "Plousia Gynaika", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_rich_wife0.png"}, false, false, 0),
				new VillagerType("byzantineArchitect", "Architekton", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_miner0.png", "millenaire:textures/entities/byzantine/byzantine_miner1.png"}, false, true, 0),
				new VillagerType("byzantineArmySmith", "Sideras", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_smith0.png", "millenaire:textures/entities/byzantine/byzantine_smith1.png"}, false, false, 0),
				new VillagerType("byzantineArtisan", "Technites", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_artisan0.png"}, false, false, 0),
				new VillagerType("byzantineArtisanWife", "Technites Gynaika", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_artisan_wife0.png"}, false, false, 0),
				new VillagerType("byzantineFarmiller", "Agrotes", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_farmiller0.png", "millenaire:textures/entities/byzantine/byzantine_farmiller1.png"}, false, false, 0),
				new VillagerType("byzantineFarmillerWife", "Agrota", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_wife0.png", "millenaire:textures/entities/byzantine/byzantine_wife1.png", "millenaire:textures/entities/byzantine/byzantine_wife2.png"}, false, false, 0),
				new VillagerType("byzantineFisherman", "Psaras", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_farmer0.png", "millenaire:textures/entities/byzantine/byzantine_farmer1.png"}, false, false, 0),
				new VillagerType("byzantineWatchKeeper", "Kidemonas", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_keeper0.png", "millenaire:textures/entities/byzantine/byzantine_keeper1.png", "millenaire:textures/entities/byzantine/byzantine_keeper2.png", "millenaire:textures/entities/byzantine/byzantine_keeper3.png"}, false, false, 32),
				new VillagerType("byzantineLighthouseKeeper", "Pharophylakas", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_farmiller0.png", "millenaire:textures/entities/byzantine/byzantine_farmiller1.png"}, false, false, 0),
				new VillagerType("byzantineLumberman", "Xylokopos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_lumberman0.png", "millenaire:textures/entities/byzantine/byzantine_lumberman1.png"}, false, false, 16),
				new VillagerType("byzantineMerchant", "Emporos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_merchant0.png"}, false, false, 0),
				new VillagerType("byzantineFoodMerchant", "Epicheirimatias Trophi", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_farmer0.png"}, false, false, 0),
				new VillagerType("byzantineMaterialMerchant", "Epicheirimatias Yliko", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_artisan0.png"}, false, false, 0),
				new VillagerType("byzantineMiner", "Metallorychos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_miner0.png", "millenaire:textures/entities/byzantine/byzantine_miner1.png", "millenaire:textures/entities/byzantine/byzantine_miner2.png"}, false, false, 0),
				new VillagerType("byzantinePriest", "Pastoras", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_papas0.png", "millenaire:textures/entities/byzantine/byzantine_papas1.png", "millenaire:textures/entities/byzantine/byzantine_papas2.png"}, false, false, 0),
				new VillagerType("byzantineShepherd", "Boskos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_shepard0.png"}, false, false, 0),
				new VillagerType("byzantineShepherdWife", "Boskopoula", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_shepherd_wife0.png"}, false, false, 0),
				new VillagerType("byzantineLoneShepherd", "Boskos", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_shepard0.png"}, false, false, 0),
				new VillagerType("byzantineLoneShepherdWife", "Boskopoula", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_shepherd_wife0.png"}, false, false, 0),
				new VillagerType("byzantineSilkFarmer", "Kalliergites", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_farmer0.png", "millenaire:textures/entities/byzantine/byzantine_farmer1.png"}, false, false, 0),
				new VillagerType("byzantineSilkFarmerWife", "Demiourgo", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_frau0.png", "millenaire:textures/entities/byzantine/byzantine_frau1.png", "millenaire:textures/entities/byzantine/byzantine_frau2.png", "millenaire:textures/entities/byzantine/byzantine_frau3.png", "millenaire:textures/entities/byzantine/byzantine_frau4.png"}, false, false, 0),
				new VillagerType("byzantineSmith", "Sideras", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_smith0.png", "millenaire:textures/entities/byzantine/byzantine_smith1.png"}, false, false, 0),
				new VillagerType("byzantineSoldier", "Stratiotes", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_soldier0.png"}, false, false, 32),
				new VillagerType("byzantinePlayerSoldier", "Stratiotes", 0, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("maleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_soldier0.png"}, false, false, 16),
				new VillagerType("byzantineWife", "Gynaika", 1, byzantineCulture.nameLists.get("familyNames"), byzantineCulture.nameLists.get("femaleNames"), new String[]{"millenaire:textures/entities/byzantine/byzantine_wife0.png", "millenaire:textures/entities/byzantine/byzantine_wife1.png", "millenaire:textures/entities/byzantine/byzantine_wife2.png"}, false, false, 0)
		});
		/*
		final VillageTypes types = new VillageTypes(new VillageType[] {
			new VillageType("test1").setTier(1,
				new BuildingProject[] {
					new BuildingProject("grove2", 0),
					new BuildingProject("mine1", 0),
					new BuildingProject("house1", 0),
					new BuildingProject("house2", 1)
				}).setTier(2,
					new BuildingProject[] {
						new BuildingProject("mine1", 2),
						new BuildingProject("house1", 2)
				}).setStartingBuildings(new BuildingProject[] {new BuildingProject("townhall1", 0)}),//.setBuildingTypes(new String[]{"primary1", "primary2"}, new String[]{"secondary1", "secondary2"}, new String[]{"player1"}).setStartingBuildings(new String[] {"townhall1", "grove1", "mine1"}),
			new VillageType("test2").setTier(1,
				new BuildingProject[] {
					new BuildingProject("grove1", 0),
					new BuildingProject("mine1", 1),
					new BuildingProject("house1", 1),
					new BuildingProject("house2", 0)
				}).setTier(2,
					new BuildingProject[] {
						new BuildingProject("mine1", 3),
						new BuildingProject("house1", 2)
				}).setStartingBuildings(new BuildingProject[] {new BuildingProject("townhall2", 0)})//.setBuildingTypes(new String[]{"primary1", "primary2"}, new String[]{"secondary1", "secondary2"}, new String[]{"player1", "player2"}).setStartingBuildings(new String[] {"townhall2", "grove1", "grove2", "mine1"})
		});
		*/
		//normanCulture.exportVillages(types);
		
		normanCulture.loadVillageTypes();
	}

	//////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public static class VillageType 
	{
		public String id;

		//public String[] primaryBuildings;
		//public String[] secondaryBuildings;
		//public String[] playerBuildings;
		
		private Map<Integer, BuildingProject[]> tiers = new HashMap<>();
		
		//First Building in this array should always be the TownHall
		public BuildingProject[] startingBuildings;
		
		public VillageType() {}
		
		public VillageType(String idIn) { id = idIn; }
		
		public VillageType setTier(int tier, BuildingProject[] buildings) {
			tiers.put(tier, buildings);
			return this;
		}
		
		public VillageType setBuildingTypes(String[] primaryIn, String[] secondaryIn, String[] playerIn)
		{
			//this.primaryBuildings = primaryIn;
			//this.secondaryBuildings = secondaryIn;
			//this.playerBuildings = playerIn;

			return this;
		}
		
		public VillageType setID(String id) {
			this.id = id;
			return this;
		}

		public VillageType setStartingBuildings(BuildingProject[] startIn)
		{
			this.startingBuildings = startIn;
			return this;
		}
		
		public String getVillageName() { return id; }
	}
}
