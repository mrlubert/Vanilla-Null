package com.theprogrammingturkey.vanillanull.items;

import com.theprogrammingturkey.vanillanull.VanillaNullCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class VanillaNullItems
{
	public static final ItemStack t1VanillaNull = vanillaNullWrapper(Material.SHULKER_BOX);
	public static final ItemStack t2VanillaNull = vanillaNullWrapper(Material.LIGHT_GRAY_SHULKER_BOX);
	public static final ItemStack t3VanillaNull = vanillaNullWrapper(Material.YELLOW_SHULKER_BOX);
	public static final ItemStack t4VanillaNull = vanillaNullWrapper(Material.LIGHT_BLUE_SHULKER_BOX);
	public static final ItemStack t5VanillaNull = vanillaNullWrapper(Material.GREEN_SHULKER_BOX);
	public static final ItemStack t6VanillaNull = vanillaNullWrapper(Material.GRAY_SHULKER_BOX);

	public static void initItems()
	{
		initItemMeta(t1VanillaNull);
		initItemMeta(t2VanillaNull);
		initItemMeta(t3VanillaNull);
		initItemMeta(t4VanillaNull);
		initItemMeta(t5VanillaNull);
		initItemMeta(t6VanillaNull);
	}

	public static void initItemMeta(ItemStack stack)
	{
		ItemMeta meta = stack.getItemMeta();
		meta.setDisplayName("/vanilla/null");
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		stack.setItemMeta(meta);
	}

	public static void initCratingRecipes()
	{
		ShapelessRecipe t1Recipe = new ShapelessRecipe(new NamespacedKey(VanillaNullCore.getPlugin(), "vanilla/null"), VanillaNullItems.t1VanillaNull.clone());
		t1Recipe.addIngredient(Material.APPLE);
		t1Recipe.addIngredient(Material.STONE);
		Bukkit.getServer().addRecipe(t1Recipe);

		createRecipe(2, t2VanillaNull, Material.IRON_INGOT);
		createRecipe(3, t3VanillaNull, Material.GOLD_INGOT);
		createRecipe(4, t4VanillaNull, Material.DIAMOND);
		createRecipe(5, t5VanillaNull, Material.EMERALD);
		createRecipe(6, t6VanillaNull, Material.NETHERITE_INGOT);
	}

	public static void createRecipe(int tier, ItemStack out, Material ingot)
	{
		ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(VanillaNullCore.getPlugin(), "vanilla/null/" + tier), out.clone());
		recipe.shape("iii", "ivi", "iii");
		recipe.setIngredient('v', Material.APPLE);
		recipe.setIngredient('i', ingot);
		Bukkit.getServer().addRecipe(recipe);
	}

	public static ItemStack vanillaNullWrapper(Material mat)
	{
		return new ItemStack(mat)
		{
			@Override
			public int getMaxStackSize()
			{
				return 1;
			}
		};
	}
}
