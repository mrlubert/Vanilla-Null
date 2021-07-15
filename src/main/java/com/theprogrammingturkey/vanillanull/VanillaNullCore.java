package com.theprogrammingturkey.vanillanull;

import com.theprogrammingturkey.vanillanull.items.VanillaNullItems;
import com.theprogrammingturkey.vanillanull.listener.NullInventoryListener;
import com.theprogrammingturkey.vanillanull.listener.PickupListener;
import com.theprogrammingturkey.vanillanull.listener.PlaceListener;
import com.theprogrammingturkey.vanillanull.listener.AntiCheatListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class VanillaNullCore extends JavaPlugin
{


	@Override
	public void onEnable()
	{
		super.onEnable();

		VanillaNullItems.initItems();
		VanillaNullItems.initCratingRecipes();
		registerEvents();
	}

	public void registerEvents()
	{
		PluginManager m = getServer().getPluginManager();
		m.registerEvents(new PickupListener(), this);
		m.registerEvents(new PlaceListener(), this);
		m.registerEvents(new NullInventoryListener(), this);
		m.registerEvents(new AntiCheatListener(), this);
	}

	public static VanillaNullCore getPlugin()
	{
		return JavaPlugin.getPlugin(VanillaNullCore.class);
	}
}
