package org.millenaire.entities;

import java.util.List;

import org.millenaire.MillCulture;
import org.millenaire.Millenaire;
import org.millenaire.VillagerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.millenaire.entities.ai.EntityAIGateOpen;
import org.millenaire.gui.MillAchievement;
import org.millenaire.pathing.MillPathNavigate;
import org.millenaire.rendering.RenderMillVillager;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.world.entity.ai.goal.OpenDoorGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.world.phys.AABB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

public class EntityMillVillager extends PathfinderMob
{
        public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Millenaire.MODID);
       public static final RegistryObject<EntityType<EntityMillVillager>> MILL_VILLAGER = ENTITIES.register("mill_villager", () -> EntityType.Builder.create(EntityMillVillager::new, EntityClassification.MISC).size(0.6F, 1.95F).build("mill_villager"));

       public static AttributeSupplier.Builder createAttributes()
       {
               return Mob.createMobAttributes()
                               .add(Attributes.MOVEMENT_SPEED, 0.55D)
                               .add(Attributes.MAX_HEALTH, 20.0D);
       }
        public int villagerID;
	private MillCulture culture;
        private VillagerType type;
        private static final Logger LOGGER = LogManager.getLogger(EntityMillVillager.class);
       private static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(EntityMillVillager.class, EntityDataSerializers.STRING);
       private static final EntityDataAccessor<Integer> AGE = SynchedEntityData.defineId(EntityMillVillager.class, EntityDataSerializers.INT);
       private static final EntityDataAccessor<Integer> GENDER = SynchedEntityData.defineId(EntityMillVillager.class, EntityDataSerializers.INT);
       private static final EntityDataAccessor<String> NAME = SynchedEntityData.defineId(EntityMillVillager.class, EntityDataSerializers.STRING);

	private boolean isVillagerSleeping = false;
	public boolean isPlayerInteracting = false;
	
	private InventoryBasic villagerInventory;
	
        public EntityMillVillager(EntityType<? extends PathfinderMob> type, World worldIn)
        {
                super(type, worldIn);
		
                this.villagerInventory = new InventoryBasic("Items", false, 16);
                isImmuneToFire = true;
	}

        public EntityMillVillager(EntityType<? extends PathfinderMob> type, World worldIn, int idIn, MillCulture cultureIn)
        {
                super(type, worldIn);
                villagerID = idIn;
                culture = cultureIn;
		
                this.villagerInventory = new InventoryBasic("Items", false, 16);
                isImmuneToFire = true;
	}
	
        protected void registerGoals()
        {
                //((PathNavigateGround)this.getNavigator()).setBreakDoors(true);
                //((PathNavigateGround)this.getNavigator()).setAvoidsWater(true);
                this.goalSelector.addGoal(0, new FloatGoal(this));
                this.goalSelector.addGoal(1, new OpenDoorGoal(this, true));
                this.goalSelector.addGoal(1, new EntityAIGateOpen(this, true));
                this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 3.0F, 0.5F));
                this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, EntityMillVillager.class, 6.0F));
                this.goalSelector.addGoal(9, new RandomStrollGoal(this, 0.6D));
        }
	
	@Override
    protected PathNavigate createNavigation(World worldIn)
    {
        return new MillPathNavigate(this, worldIn);
    }
	
	@Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(0.55D);
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
    }
	
	@Override
       protected void defineSynchedData()
    {
        super.defineSynchedData();
        this.entityData.define(TEXTURE, "texture");
        this.entityData.define(NAME, "name");
        //0 is Adult
        this.entityData.define(AGE, 0);
        //0 for male, 1 for female, 2 for Sym Female
        this.entityData.define(GENDER, 0);
    }
	
	public EntityMillVillager setTypeAndGender(VillagerType typeIn, int genderIn)
	{
               this.type = typeIn;
               this.entityData.set(GENDER, genderIn);
               this.entityData.set(TEXTURE, type.getTexture());
               return this;
       }
	
       public void setChild() { this.entityData.set(AGE, 1); }
	
       public void setName(String nameIn) { this.entityData.set(NAME, nameIn); }
	
       public String getTexture() { return this.entityData.get(TEXTURE); }
	
       public int getGender() { return this.entityData.get(GENDER); }
	
       public String getName() { return this.entityData.get(NAME); }

	public VillagerType getVillagerType() { return type; }
	
	@Override
       public boolean isChild() { return (this.entityData.get(AGE) > 0); }
	
    public boolean allowLeashing() { return false; }
    
    @Override
    public void onDeath(DamageSource cause)
    {
        InventoryHelper.dropInventoryItems(this.level, this.getPosition(), this.villagerInventory);
    }
    
    //Controls what happens when Villager encounters an Item on ground
    @Override
    protected void updateEquipmentIfNeeded(EntityItem itemEntity)
    {
    	
    }
	
	/*@Override
	public void attackEntity(final Entity entity, final float f) {
		if (vtype.isArcher && f > 5 && hasBow()) {
			attackEntityBow(entity, f);
			isUsingBow = true;
		} else {
			if (attackTime <= 0 && f < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY) {
				attackTime = 20;
				entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength());
				swingItem();
			}
			isUsingHandToHand = true;
		}
	}*/
	

	/*public void attackEntityBow(final Entity entity, final float f) {
		if (!(entity instanceof EntityLivingBase)) {
			return;
		}

		if (f < 10F) {
			final double d = entity.posX - posX;
			final double d1 = entity.posZ - posZ;
			if (attackTime == 0) {

				float speedFactor = 1;
				float damageBonus = 0;

				final ItemStack weapon = getWeapon();

				if (weapon != null) {
					final Item item = weapon.getItem();

					if (item instanceof ItemMillenaireBow) {
						final ItemMillenaireBow bow = (ItemMillenaireBow) item;

						if (bow.speedFactor > speedFactor) {
							speedFactor = bow.speedFactor;
						}
						if (bow.damageBonus > damageBonus) {
							damageBonus = bow.damageBonus;
						}
					}
				}

                                final EntityArrow arrow = new EntityArrow(this.level, this, (EntityLivingBase) entity, 1.6F, 12.0F);

                                this.level.playSound(null, this.getPosition(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
                                this.level.spawnEntityInWorld(arrow);

				attackTime = 60;

				// faster MLN arrows
				arrow.motionX *= speedFactor;
				arrow.motionY *= speedFactor;
				arrow.motionZ *= speedFactor;

				// extra arrow damage
				arrow.setDamage(arrow.getDamage() + damageBonus);
			}
			rotationYaw = (float) (Math.atan2(d1, d) * 180D / 3.1415927410125732D) - 90F;
			hasAttacked = true;
		}
	}*/

	/*@Override
	public boolean attackEntityFrom(final DamageSource ds, final float i) {

		if (ds.getSourceOfDamage() == null && ds != DamageSource.outOfWorld) {
			return false;
		}

		final boolean hadFullHealth = getMaxHealth() == getHealth();

		final boolean b = super.attackEntityFrom(ds, i);

		final Entity entity = ds.getSourceOfDamage();

		lastAttackByPlayer = false;

		if (entity != null) {
			if (entity instanceof EntityLivingBase) {
				if (entity instanceof Player) {
					lastAttackByPlayer = true;

					final Player player = (Player) entity;

					if (!isRaider) {
						if (!vtype.hostile) {
                                                        MillCommonUtilities.getServerProfile(player.level, player.getDisplayName()).adjustReputation(getTownHall(), (int) (-i * 10));
						}
                                                if (level.difficultySetting != EnumDifficulty.PEACEFUL && this.getHealth() < getMaxHealth() - 10) {
							entityToAttack = entity;
							clearGoal();
							if (getTownHall() != null) {
								getTownHall().callForHelp(entity);
							}
						}

                                                if (hadFullHealth && (player.getMainHandItem() == null || MillCommonUtilities.getItemWeaponDamage(player.getMainHandItem().getItem()) <= 1) && !level.isRemote) {
							ServerSender.sendTranslatedSentence(player, MLN.ORANGE, "ui.communicationexplanations");
						}
					}

					if (lastAttackByPlayer && getHealth() <= 0) {
						if (vtype.hostile) {
							player.addStat(MillAchievements.selfdefense, 1);
						} else {
							player.addStat(MillAchievements.darkside, 1);
						}
					}

				} else {
					entityToAttack = entity;
					clearGoal();

					if (getTownHall() != null) {
						getTownHall().callForHelp(entity);
					}

				}
			}
		}

		return b;
	}*/
	
	//maybe in other class(if changed to Vanilla Villager)
	@Override
	public boolean canDespawn() 
	{
		return false;
	}
	
	//Goals need to be a thing
	
	/*public void detrampleCrops() 
	{
                if (getPosition().sameBlock(prevPoint) && (previousBlock == Blocks.WHEAT || previousBlock instanceof BlockMillCrops) && getBlock(getPosition()) != Blocks.AIR
                                && getBlock(getPosition().getBelow()) == Blocks.DIRT) {
			setBlock(getPosition(), previousBlock);
			setBlockMetadata(getPosition(), previousBlockMeta);
			setBlock(getPosition().getBelow(), Blocks.farmland);
		}

		previousBlock = getBlock(getPosition());
		previousBlockMeta = getBlockMeta(getPosition());
	}*/
	
	// emptied to prevent generic code from turning the villagers' heads toward
	// the player
	//@Override
	//public void faceEntity(final Entity par1Entity, final float par2, final float par3) {}
	
	public void faceDirection()
	{
		//Face an Entity or specific BlockPos when we want then to
	}

	//Foreign Merchant leaves at night if stock is empty && price goes up by 1.5 if in a different culture
	
	//Find function for checking armor equipment, should return the equipment if in inventory
	
	//GetBedOrientation()?
	
	//Set up where Villagers have an appropriate tool in inventory, tool updates as village expands/building upgrades
	
	//Pick up EntityItems???
	
	//Remember to use setCurrentItemOrArmor
	
	@Override
	protected int getExperiencePoints(final Player playerIn) 
	{
		//return villagertype.expgiven;
		return super.getExperiencePoints(playerIn);
	}
	
	//GetOccupationTitle(Player playerIn)
	
	//getSpeech/Dialogue(Player playerIn)
	//Make this smart to language learning
	
	/*public Item[] getGoodsToBringBackHome() 
	{
		return vtype.bringBackHomeGoods;
	}

	public Item[] getGoodsToCollect() 
	{
		return vtype.collectGoods;
	}*/

	/*public int getHireCost(final Player player) 
	{

		int cost = vtype.hireCost;

		//if (getTownHall().controlledBy(player.getDisplayName())) {
		//	cost = cost / 2;
		//}

		return cost;
	}*/
	
	/*public String getName() 
	{
		return firstName + " " + familyName;
	}*/
	
	//handleDoorsAndFenceGates() needs to be malisis-compatible(dummy player with villagers rotationYaw)
	
	@Override
	public boolean interact(final Player playerIn) 
	{
		playerIn.addStat(MillAchievement.firstContact, 1);
		if(type.hireCost > 0)
		{
			this.isPlayerInteracting = true;
                        playerIn.openGui(Millenaire.instance, 5, playerIn.level, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
			return true;
		}
		if(type.isChief)
		{
			this.isPlayerInteracting = true;
                        playerIn.openGui(Millenaire.instance, 4, playerIn.level, this.getPosition().getX(), this.getPosition().getY(), this.getPosition().getZ());
			return true;
		}
		//for Sadhu and Alchemist maitrepenser achievement
		//Display Quest GUI if appropriate
		//Display Hire GUI if Appropriate
		//Display Chief GUI if Chief
		// Display Trade Window if Trading (Foreign Merchant, trading for Townhall or local shop)
		return false;
	}
	
	/**
	 * Dead and sleeping entities cannot move
	 */
	@Override
	protected boolean isMovementBlocked() 
	{
		return this.getHealth() <= 0 || this.isVillagerSleeping || this.isPlayerInteracting;
	}
	
	//When Villager dies, the entity is dead, per normal.  Drop stuff and display messages. Respawn must just create another instance of the same villager (reason to store culture info in V. Stone)
	//Why villagerID is important
	
	//Local merchants have inn or townhall as 'house', handle moving them, taking items from townhall, and what happens if inn is full
	
	@Override
	public void onLivingUpdate() 
	{
		super.onLivingUpdate();

		this.updateArmSwingProgress();

		//setFacingDirection(); Look toward goal (or entity to attack, but I think attacking something is a goal)(entityToAttack also used in raids and against stupid players, may still be possible to use goal)

		if (isVillagerSleeping) {
			motionX = 0;
			motionY = 0;
			motionZ = 0;
		}
	}
	
	//teenager leaving to find other village...possibly useful in creating new villages?
	
	//be smart with teleportTo, use coordinates or entity, check for surrounding blocks
	
	@Override
	public void onUpdate() 
	{
		//Check(isRemote) and do nothing?
		
		if (this.isDead) 
		{
			super.onUpdate();
			return;
		}
		
		if(isPlayerInteracting)
		{
                        List<Player> playersNear = this.level.getEntitiesWithinAABB(Player.class, new AABB(getX() - 5, getY() - 1, getZ() - 5, getX() + 5, getY() + 1, getZ() + 5));
			
			if(playersNear.isEmpty())
				isPlayerInteracting = false;
		}

		/*if (hiredBy != null) 
		{
			//updateHired();
			super.onUpdate();
			return;
		}*/
		
		//if(village is under attack)
		//{
			//Clear other goals and either hide or defend.
		//}
		
		//Check to Attack something (needs to be more player specific)
		
		//Check Time (day or night)
			//Chance to speak something		SPEAKING SENTENCES AND DIALOGUE CAN BE CLIENT-SIDE!!
			//pick up items?
			//updateLocalMerchant()
			//updateForeignMerchant()
			//checkGoals()
		//checkGoals() (sleep...?)
		
		//Update path finding
			//check Long Distance Stuck
			//check short Distance Stuck
			//handleDoorsAndFenceGates
			//setPathing
		
		//sendUpdatePacket to client
		
		//trigger Mob Attacks on Villagers (currently only spiders, which is odd...villagers will seek out other mobs, but should be targetable).
		
		//update Dialogue?
		
		//put away weapons (might revise this to check if attacking first)
		
		super.onUpdate();
	}
	
	//performNightActions() does not appear to be called in the code anymore...perhaps it has been outdated?  This undoes growChildSize, conception, and ForiegnMerchantNightAction
	
	@Override
	public void writeToNBT(final NBTTagCompound nbt) 
	{
		super.writeToNBT(nbt);
		nbt.setInteger("villagerID", villagerID);
		nbt.setString("culture", culture.cultureName);
               nbt.setInteger("gender", this.entityData.get(GENDER));
		nbt.setString("villagerType", type.id);
		nbt.setBoolean("sleeping", isVillagerSleeping);
		
               nbt.setString("texture", this.entityData.get(TEXTURE));
               nbt.setInteger("age", this.entityData.get(AGE));
               nbt.setString("name", this.entityData.get(NAME));
		
		NBTTagList nbttaglist = new NBTTagList();
        for (int i = 0; i < this.villagerInventory.getSizeInventory(); ++i)
        {
            ItemStack itemstack = this.villagerInventory.getStackInSlot(i);

            if (itemstack != null)
            {
                nbttaglist.appendTag(itemstack.writeToNBT(new NBTTagCompound()));
            }
        }
        nbt.setTag("Inventory", nbttaglist);
		//Write in All relevant data
	}
	
	@Override
	public void readFromNBT(final NBTTagCompound nbt) 
	{
		super.readFromNBT(nbt);
		villagerID = nbt.getInteger("villagerID");
		try 
		{
			culture = MillCulture.getCulture(nbt.getString("culture"));
		} 
		catch (Exception ex) 
		{
			System.err.println("Villager failed to read from NBT correctly");
			ex.printStackTrace();
		}
                this.entityData.set(GENDER, nbt.getInteger("gender"));
                if(culture == null)
                {
                        LOGGER.warn("Unknown culture '{}' for villager {}. Using default.", nbt.getString("culture"), villagerID);
                        culture = MillCulture.normanCulture;
                        type = culture.getChildType(getGender());
                }
                else
                {
                        type = culture.getVillagerType(nbt.getString("villagerType"));
                        if (type == null)
                        {
                                type = culture.getChildType(getGender());
                        }
                }
                isVillagerSleeping = nbt.getBoolean("sleeping");
		
               this.entityData.set(TEXTURE, nbt.getString("texture"));
               this.entityData.set(AGE, nbt.getInteger("age"));
               this.entityData.set(NAME, nbt.getString("name"));
		
		NBTTagList nbttaglist = nbt.getTagList("Inventory", 10);
        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttaglist.getCompoundTagAt(i));

            if (itemstack != null)
            {
                this.villagerInventory.func_174894_a(itemstack);
            }
        }
		//Read in all relevant data
	}
	
	@Override
	public String toString() 
	{
                return this.getClass().getSimpleName() + "@" + ": " + getName() + "/" + this.villagerID + "/" + level;
	}
	
	//Update Texture for Byzantines with silk clothes, possibly further expand on this
	
	private void updateHired() 
	{
		//find target (base this on stance, change stance in onInteract)
		
		//pathFind to entity you want to attack (or following player)
		//handledoorsandfencegates
	}
	
	//override onlivingsound?
	
	//////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
        public static void preinitialize()
        {
                // registration handled via DeferredRegister
        }
	
	public static void prerender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityMillVillager.class, new millVillagerRenderFactory());
	}
	
	//////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	
	public static class millVillagerRenderFactory implements IRenderFactory<EntityMillVillager>
	{
		@Override
		public Render<EntityMillVillager> createRenderFor(RenderManager manager) 
		{
			return new RenderMillVillager(manager, new ModelBiped(), 0.5F);
		}
	}
}
