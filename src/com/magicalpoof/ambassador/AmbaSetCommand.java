package com.magicalpoof.ambassador;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class AmbaSetCommand implements CommandExecutor {

	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage(ChatColor.RED + "Недостаточно привилегий!");
			return false;
		}	
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "Укажите ник игрока!");
			return false;
		}
			
		Player ambassador = Bukkit.getPlayer(args[0]);
		if (ambassador == null) {
			sender.sendMessage(ChatColor.RED + "На сервере нет данного пользователя.");
			return false;
		}
		
		Main main = Main.getInstance();
		FileConfiguration config = main.getConfig();
		
		List<?> list = config.getList("ambassadors", null);
		
		if(list != null) {
			for (Object obj: list) {
				if (obj.equals(ambassador.getUniqueId().toString())) {
					sender.sendMessage(ChatColor.GREEN + "Данный пользователь уже является амбассадором сервера.");
					return false;
				}
			}
		}
		
		ArrayList<String> strlist = new ArrayList<String>();
		strlist = (ArrayList<String>) list;
		strlist.add(ambassador.getUniqueId().toString());
		config.set("ambassadors", list);
		main.saveConfig();
		Bukkit.broadcastMessage(ChatColor.GREEN + ambassador.getName() +" cтал амбассадором данного сервера.");		
		return true;
	}
}


