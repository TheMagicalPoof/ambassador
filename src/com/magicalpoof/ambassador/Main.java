package com.magicalpoof.ambassador;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	
	private static Main instance;
	public Main() {
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		Bukkit.setWhitelist(true);
		
		if (!(new File(getDataFolder(), "config.yml").exists())) {
			this.saveDefaultConfig();
		}
		
				
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
		getServer().getPluginManager().registerEvents(new LeaveListener(), this);
		getCommand("ambaset").setExecutor(new AmbaSetCommand());
		getCommand("ambarem").setExecutor(new AmbaRemCommand());

	}
	
	@Override
	public void onDisable() {
		
	}
	
	
}
