package com.theprogrammingturkey.vanillanull.util;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.block.ShulkerBox;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class NullUtil
{
	public static int getVanillaNullTier(ItemStack stack)
	{
		if(stack == null)
			return -1;

		ItemMeta meta = stack.getItemMeta();
		if(meta == null)
			return -1;

		if(!meta.getDisplayName().equals("/vanilla/null") || !meta.hasEnchant(Enchantment.ARROW_DAMAGE))
			return -1;

		switch(stack.getType())
		{
			case SHULKER_BOX:
				return 1;
			case LIGHT_GRAY_SHULKER_BOX:
				return 2;
			case YELLOW_SHULKER_BOX:
				return 3;
			case LIGHT_BLUE_SHULKER_BOX:
				return 4;
			case GREEN_SHULKER_BOX:
				return 5;
			case GRAY_SHULKER_BOX:
				return 6;
			default:
				return -1;
		}
	}

	public static List<ItemStack> getVanillaNulls(Player player)
	{
		List<ItemStack> nulls = new ArrayList<>();
		for(ItemStack stack : player.getInventory().getContents())
			if(getVanillaNullTier(stack) != -1)
				nulls.add(stack);
		return nulls;
	}

	public static int getNumSlots(ItemStack stack)
	{
		return getNumSlots(getVanillaNullTier(stack));
	}

	public static int getNumSlots(int tier)
	{
		switch(tier)
		{
			case 1:
				return 1;
			case 2:
				return 3;
			case 3:
				return 6;
			case 4:
				return 9;
			case 5:
				return 12;
			case 6:
				return 15;
			default:
				return 0;
		}
	}

	public static void updateVanillaNullDisplay(Player player, ItemStack vanillaNull, ItemStack changed)
	{
		if(getVanillaNullTier(vanillaNull) != -1 && vanillaNull.getItemMeta() instanceof BlockStateMeta)
		{
			BlockStateMeta meta = (BlockStateMeta) vanillaNull.getItemMeta();
			if(meta != null && meta.getBlockState() instanceof ShulkerBox)
			{
				ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
				Inventory shulkerInv = shulker.getInventory();
				ItemStack contained = shulkerInv.getItem(0);
				if(changed != null)
				{
					for(int i = 0; i < shulker.getInventory().getSize(); i++)
					{
						ItemStack s = shulkerInv.getItem(i);
						if(s != null && s.getType().equals(changed.getType()))
							contained = s;
					}
				}

				if(contained != null)
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(contained.getType().name() + " x" + contained.getAmount()));
				else
					player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Empty"));
			}
		}
	}

	public static ItemStack[] getNullItemStacks(ItemStack vanillaNull)
	{
		if(vanillaNull != null)
		{
			BlockStateMeta meta = (BlockStateMeta) vanillaNull.getItemMeta();
			if(meta != null && meta.getBlockState() instanceof ShulkerBox)
			{
				ShulkerBox shulker = (ShulkerBox) meta.getBlockState();
				return shulker.getInventory().getContents();
			}
		}
		return new ItemStack[0];
	}

	public static ItemStack getNullItemStackAt(ItemStack vanillaNull, int slot)
	{
		ItemStack[] stacks = getNullItemStacks(vanillaNull);
		if(stacks.length <= slot)
			return null;
		return stacks[slot];
	}
}
