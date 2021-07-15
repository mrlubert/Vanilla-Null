package com.theprogrammingturkey.vanillanull.listener;

import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;

public class AntiCheatListener implements Listener
{
	@EventHandler
	public void onItemRename(InventoryClickEvent e)
	{
		if(e.getInventory() instanceof AnvilInventory)
			if(NullUtil.getVanillaNullTier(e.getInventory().getItem(0)) != -1)
				if(e.getClickedInventory() == e.getInventory() && e.getSlot() == 2)
					e.setCancelled(true);
	}

	@EventHandler
	public void onUnDye(PlayerInteractEvent e)
	{
		if(NullUtil.getVanillaNullTier(e.getItem()) != -1)
			if(e.getClickedBlock() != null && e.getClickedBlock().getType().equals(Material.CAULDRON))
				e.setCancelled(true);
	}

	@EventHandler
	public void onUnDye(BlockDispenseEvent e)
	{
		if(NullUtil.getVanillaNullTier(e.getItem()) != -1)
			e.setCancelled(true);
	}
}
