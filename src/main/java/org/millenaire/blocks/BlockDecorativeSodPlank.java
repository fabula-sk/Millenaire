package org.millenaire.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.NonNullList;
import net.minecraft.util.IStringSerializable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockDecorativeSodPlank extends Block
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDecorativeSodPlank.EnumType.class);

    BlockDecorativeSodPlank()
	{
		super(Material.wood);
		this.setHardness(2F);
                this.setResistance(15F);
                this.setSoundType(SoundType.WOOD);
	}
	

	public IProperty getVariantProperty() { return VARIANT; }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list)
    {
        BlockDecorativeSodPlank.EnumType[] aenumtype = BlockDecorativeSodPlank.EnumType.values();

        Item itemIn = Item.getItemFromBlock(this);
        for (EnumType enumtype : aenumtype) {
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + BlockDecorativeSodPlank.EnumType.byMetadata(meta).getUnlocalizedName();
    }

    // Metadata handling removed in favor of pure block states

    @Override
    protected BlockState createBlockState() { return new BlockState(this, VARIANT); }

    //////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static enum EnumType implements IStringSerializable
    {
    	OAK(0, "oak"),
    	PINE(1, "pine"),
    	BIRCH(2, "birch"),
    	JUNGLE(3, "jungle"),
    	ACACIA(4, "acacia"),
    	DARKOAK(5, "darkoak");
        
        private static final BlockDecorativeSodPlank.EnumType[] META_LOOKUP = new BlockDecorativeSodPlank.EnumType[values().length];
        private final int meta;
        private final String name;

        private EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata() { return this.meta; }

        public String toString() { return this.name; }

        public static BlockDecorativeSodPlank.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName() { return this.name; }

        public String getUnlocalizedName() { return this.name; }

        static
        {
        	BlockDecorativeSodPlank.EnumType[] var0 = values();

            for (EnumType var3 : var0) {
                META_LOOKUP[var3.getMetadata()] = var3;
            }
        }
    }
}
