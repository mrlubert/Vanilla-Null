package com.theprogrammingturkey.vanillanull.listener;

import com.theprogrammingturkey.vanillanull.inventory.NullInventory;
import com.theprogrammingturkey.vanillanull.inventory.NullInventoryManager;
import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class NullInventoryListener implements Listener
{
	// Check for clicks on items
	@EventHandler
	public void onInventoryClick(final InventoryClickEvent e)
	{
		NullInventory inv = NullInventoryManager.getNullInventory(e.getInventory());
		if(inv == null)
			return;

		if(e.getSlot() >= inv.getRealInvSize() && e.getClickedInventory() == e.getInventory())
			e.setCancelled(true);

		if(NullUtil.getVanillaNullTier(e.getCurrentItem()) != -1)
			e.setCancelled(true);
	}

	// Cancel dragging in our inventory
	@EventHandler
	public void onInventoryClick(final InventoryDragEvent e)
	{
		NullInventory inv = NullInventoryManager.getNullInventory(e.getInventory());
		if(inv == null)
			return;

		e.setCancelled(true);
	}

	@EventHandler
	public void onInventoryClose(final InventoryCloseEvent e)
	{
		NullInventory inv = NullInventoryManager.getNullInventory(e.getInventory());
		if(inv == null)
			return;

		if(e.getInventory().getViewers().size() <= 1)
		{
			inv.save((Player) e.getPlayer());
			NullInventoryManager.removeInventory(e.getInventory());
		}
	}

	@EventHandler
	public void onPlayerShiftClick(PlayerInteractEvent e)
	{
		ItemStack stack = e.getPlayer().getInventory().getItemInMainHand();
		if(NullUtil.getVanillaNullTier(stack) != -1)
		{
			if(e.getAction().equals(Action.RIGHT_CLICK_AIR) && e.getPlayer().isSneaking())
			{
				NullInventory inv = new NullInventory(stack);
				inv.openInventory(e.getPlayer());
			}
		}
	}
}
