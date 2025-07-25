package org.millenaire.blocks;


import net.minecraft.block.Block;
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

public class BlockDecorativeStone extends Block
{
	public static final PropertyEnum VARIANT = PropertyEnum.create("variant", BlockDecorativeStone.EnumType.class);
	
	BlockDecorativeStone() { super(Material.rock); }
	

	public IProperty getVariantProperty() { return VARIANT; }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list)
    {
        BlockDecorativeStone.EnumType[] aenumtype = BlockDecorativeStone.EnumType.values();

        Item itemIn = Item.getItemFromBlock(this);
        for (EnumType enumtype : aenumtype) {
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    public String getUnlocalizedName(int meta)
    {
        return super.getUnlocalizedName() + "." + BlockDecorativeStone.EnumType.byMetadata(meta).getUnlocalizedName();
    }

    // Metadata handling removed in favor of pure block states

    @Override
    protected BlockState createBlockState() { return new BlockState(this, VARIANT); }

    //////////////////////////////////////////////////////////\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public static enum EnumType implements IStringSerializable
    {
        GOLDORNAMENT(0, "goldOrnament"),
    	COOKEDBRICK(1, "cookedBrick"),
    	GALIANITEBLOCK(2, "galianiteBlock");
        
        private static final BlockDecorativeStone.EnumType[] META_LOOKUP = new BlockDecorativeStone.EnumType[values().length];
        private final int meta;
        private final String name;

        EnumType(int meta, String name)
        {
            this.meta = meta;
            this.name = name;
        }

        public int getMetadata() { return this.meta; }

        public String toString() { return this.name; }

        public static BlockDecorativeStone.EnumType byMetadata(int meta)
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
        	BlockDecorativeStone.EnumType[] var0 = values();

            for (EnumType var3 : var0) {
                META_LOOKUP[var3.getMetadata()] = var3;
            }
        }
    }
}
