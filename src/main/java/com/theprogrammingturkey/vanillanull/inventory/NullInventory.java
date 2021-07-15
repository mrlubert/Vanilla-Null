package com.theprogrammingturkey.vanillanull.inventory;

import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NullInventory
{
	private final Inventory inv;
	private ItemStack vanillaNull;
	private int realInvSize = 1;

	public NullInventory(ItemStack vanillaNull)
	{
		realInvSize = NullUtil.getNumSlots(vanillaNull);
		// Create a new inventory, with no owner (as this isn't a real inventory), a size of nine, called example
		inv = Bukkit.createInventory(null, (int) Math.ceil(realInvSize / 9.0) * 9, "/vanilla/null");
		NullInventoryManager.addInventory(inv, this);
		this.vanillaNull = vanillaNull;

		// Put the items into the inventory
		initializeItems();
	}

	// You can call this whenever you want to put the items in
	public void initializeItems()
	{
		for(int i = 0; i < inv.getSize(); i++)
		{
			if(i < realInvSize)
				inv.setItem(i, NullUtil.getNullItemStackAt(vanillaNull, i));
			else
				inv.setItem(i, createGuiItem(Material.BLACK_STAINED_GLASS_PANE, "BLOCKED"));
		}
	}

	// Nice little method to create a gui item with a custom name, and description
	protected ItemStack createGuiItem(final Material material, final String name, final String... lore)
	{
		final ItemStack item = new ItemStack(material, 1);
		final ItemMeta meta = item.getItemMeta();

		// Set the name of the item
		meta.setDisplayName(name);

		// Set the lore of the item
		meta.setLore(Arrays.asList(lore));

		item.setItemMeta(meta);

		return item;
	}

	public void save(Player player)
	{
		BlockStateMeta meta = (BlockStateMeta) vanillaNull.getItemMeta();
		if(meta != null && meta.getBlockState() instanceof ShulkerBox)
		{
			ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
			Inventory shulker_inv = shulker.getInventory();
			for(int i = 0; i < realInvSize; i++)
				shulker_inv.setItem(i, inv.getItem(i));

			meta.setBlockState(shulker);
			vanillaNull.setItemMeta(meta);
			NullUtil.updateVanillaNullDisplay(player, vanillaNull, null);
		}
	}

	// You can open the inventory with this
	public void openInventory(final HumanEntity ent)
	{
		ent.openInventory(inv);
	}

	public int getRealInvSize()
	{
		return this.realInvSize;
	}
}
