package iafmagic.arms;

import iafmagic.registry.items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ice_stick_materials implements ToolMaterial{
	public static final ice_stick_materials INSTANCE = new ice_stick_materials();
	
	
	@Override
	public float getAttackDamage() {
		return 14;
	}

	@Override
	public int getDurability() {
		return 1200;
	}

	@Override
	public int getEnchantability() {
		return 30;
	}

	@Override
	public int getMiningLevel() {
		return 1;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return 1.0F;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(items.ice_blood);
	}
}
