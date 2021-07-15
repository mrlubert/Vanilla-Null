package com.theprogrammingturkey.vanillanull.listener;

import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;

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
	
	@EventHandler
    public void onDyeCraft(PrepareItemCraftEvent e) {
        for(int i = 1; i <= 9; i++)
        {
            if (NullUtil.getVanillaNullTier(e.getInventory().getItem(i)) != -1) {
            e.getInventory().setResult(new ItemStack(Material.AIR));
            }
        }
    }
	
	@EventHandler
	public void onItemInNull(InventoryClickEvent e) {
		if (e.getView().getTitle() != "/vanilla/null") {
			return;
		} else

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) {
			e.setCancelled(false);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.WHITE_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.ORANGE_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.MAGENTA_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.LIGHT_BLUE_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.YELLOW_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.LIME_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.PINK_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.GRAY_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.LIGHT_GRAY_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.CYAN_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.PURPLE_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.BLUE_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.BROWN_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.GREEN_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.RED_SHULKER_BOX) {
			e.setCancelled(true);
		}

		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.BLACK_SHULKER_BOX) {
			e.setCancelled(true);
		}
	}
}
