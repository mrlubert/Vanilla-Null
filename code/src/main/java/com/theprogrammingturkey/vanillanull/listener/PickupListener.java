package com.theprogrammingturkey.vanillanull.listener;

import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.List;

public class PickupListener implements Listener
{
	@EventHandler
	public void onItemPickup(EntityPickupItemEvent e)
	{
		if(e.getEntity() instanceof Player)
		{
			Player player = (Player) e.getEntity();
			List<ItemStack> vanillaNulls = NullUtil.getVanillaNulls(player);
			for(ItemStack vanillaNull : vanillaNulls)
			{
				boolean found = false;
				BlockStateMeta meta = (BlockStateMeta) vanillaNull.getItemMeta();
				if(meta != null && meta.getBlockState() instanceof ShulkerBox)
				{
					ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
					Inventory currentinv = shulker.getInventory();

					for(int i = 0; i < NullUtil.getNumSlots(vanillaNull); i++)
					{
						ItemStack nullStack = currentinv.getItem(i);
						if(nullStack != null && nullStack.isSimilar(e.getItem().getItemStack()))
						{
							nullStack.setAmount(Math.min(nullStack.getType().getMaxStackSize(), nullStack.getAmount() + e.getItem().getItemStack().getAmount()));
							meta.setBlockState(shulker);
							vanillaNull.setItemMeta(meta);
							NullUtil.updateVanillaNullDisplay(player, vanillaNull, e.getItem().getItemStack());
							e.getItem().remove();
							e.setCancelled(true);
							found = true;
							break;
						}
					}
				}

				if(found)
					break;
			}
		}
	}
}
