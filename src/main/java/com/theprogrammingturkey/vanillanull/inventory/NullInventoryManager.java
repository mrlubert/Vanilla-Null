package com.theprogrammingturkey.vanillanull.inventory;

import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;

public class NullInventoryManager
{
	private static Map<Inventory, NullInventory> cachedInvs = new HashMap<>();

	public static void addInventory(Inventory inv, NullInventory ni)
	{
		cachedInvs.put(inv, ni);
	}

	public static NullInventory getNullInventory(Inventory inv)
	{
		return cachedInvs.get(inv);
	}

	public static void removeInventory(Inventory inv)
	{
		cachedInvs.remove(inv);
	}
}
