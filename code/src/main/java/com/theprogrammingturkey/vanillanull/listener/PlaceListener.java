package com.theprogrammingturkey.vanillanull.listener;

import com.theprogrammingturkey.vanillanull.VanillaNullCore;
import com.theprogrammingturkey.vanillanull.util.NullUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.ShulkerBox;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PlaceListener implements Listener
{
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e)
	{
		if(NullUtil.getVanillaNullTier(e.getItemInHand()) != -1)
		{
			Bukkit.getScheduler().scheduleSyncDelayedTask(VanillaNullCore.getPlugin(), () ->
			{
				BlockStateMeta meta = (BlockStateMeta) e.getItemInHand().getItemMeta();
				if(meta != null && meta.getBlockState() instanceof ShulkerBox)
				{
					ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
					Inventory currentinv = shulker.getInventory();
					ItemStack nullStack = currentinv.getItem(0);
					if(nullStack != null)
					{
						if(nullStack.getAmount() > 0)
						{
							Block b = e.getBlock();
							b.getWorld().getBlockAt(b.getLocation()).setType(nullStack.getType());
							nullStack.setAmount(nullStack.getAmount() - 1);
						}
						else
						{
							currentinv.setItem(0, null);
						}

						meta.setBlockState(shulker);
						e.getItemInHand().setItemMeta(meta);
						NullUtil.updateVanillaNullDisplay(e.getPlayer(), e.getItemInHand(), nullStack);
					}
				}
			}, 1);
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerShiftClick(PlayerInteractEvent e)
	{
		ItemStack stack = e.getPlayer().getInventory().getItemInMainHand();
		if(NullUtil.getVanillaNullTier(stack) != -1)
		{
			if((e.getAction().equals(Action.LEFT_CLICK_AIR) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) && e.getPlayer().isSneaking())
			{
				BlockStateMeta meta = (BlockStateMeta) stack.getItemMeta();
				if(meta != null && meta.getBlockState() instanceof ShulkerBox)
				{
					ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
					Inventory currentinv = shulker.getInventory();

					List<ItemStack> containedStacks = Arrays.stream(currentinv.getContents()).filter(fstack -> fstack != null && !fstack.getType().equals(Material.AIR)).collect(Collectors.toList());

					if(containedStacks.size() == 0)
					{
						NullUtil.updateVanillaNullDisplay(e.getPlayer(), stack, null);
					}
					else if(containedStacks.size() == 1)
					{
						currentinv.clear();
						currentinv.setItem(0, containedStacks.get(0));
						meta.setBlockState(shulker);
						stack.setItemMeta(meta);
						NullUtil.updateVanillaNullDisplay(e.getPlayer(), stack, containedStacks.get(0));
					}
					else
					{
						containedStacks.add(containedStacks.remove(0));
						currentinv.clear();
						for(int i = 0; i < containedStacks.size(); i++)
							currentinv.setItem(i, containedStacks.get(i));

						meta.setBlockState(shulker);
						stack.setItemMeta(meta);
						NullUtil.updateVanillaNullDisplay(e.getPlayer(), stack, containedStacks.get(0));
					}
				}
			}
		}
	}
}
