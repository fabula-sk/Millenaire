package org.millenaire.items;

import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.inventory.EquipmentSlotType;

public enum MillArmorMaterial implements IArmorMaterial {
    NORMAN("millenaire:normanArmor", 66, new int[]{3,8,6,3}, 10),
    JAPANESE_WARRIOR_RED("millenaire:japaneseWarriorArmorRed", 33, new int[]{2,6,5,2}, 25),
    JAPANESE_WARRIOR_BLUE("millenaire:japaneseWarriorArmorBlue", 33, new int[]{2,6,5,2}, 25),
    JAPANESE_GUARD("millenaire:japaneseGuardArmor", 25, new int[]{2,5,4,1}, 25),
    BYZANTINE("millenaire:byzantineArmor", 33, new int[]{3,8,6,3}, 20),
    MAYAN_QUEST("millenaire:mayanQuest", 5, new int[]{1,3,2,1}, 35);

    private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};

    private final String name;
    private final int durabilityFactor;
    private final int[] damageReduction;
    private final int enchantability;

    MillArmorMaterial(String name, int durabilityFactor, int[] damageReduction, int enchantability) {
        this.name = name;
        this.durabilityFactor = durabilityFactor;
        this.damageReduction = damageReduction;
        this.enchantability = enchantability;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slot) {
        return BASE_DURABILITY[slot.getIndex()] * this.durabilityFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slot) {
        return this.damageReduction[slot.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.EMPTY;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return 0.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 0.0F;
    }
}
