package com.magicalpoof.ambassador;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.FileConfiguration;


public class AmbaRemCommand implements CommandExecutor {
	
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
				
		if (config.getList("ambassadors", null) == null) {
			sender.sendMessage(ChatColor.RED + "Список амбассадоров пуст. Сначала необходимо использовать комманду /ambaset");
			return false;
		}
		
		List<?> list = config.getList("ambassadors");
		
		for (Object obj: list) {
			if (obj.equals(ambassador.getUniqueId().toString())) {
				list.remove(obj);
				config.set("ambassadors", list);
				main.saveConfig();
				Bukkit.broadcastMessage(ChatColor.RED + ambassador.getName() + " удалён из списка амбассадоров данного сервера.");
				return true;
			}
		}
		
		sender.sendMessage(ChatColor.GREEN + ambassador.getName() + " не является амбассадором данного сервера.");
		return false;
	}
}
