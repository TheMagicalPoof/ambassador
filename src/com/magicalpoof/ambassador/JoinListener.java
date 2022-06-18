package com.magicalpoof.ambassador;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import java.util.List;


public class JoinListener implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent joinEv) {
		
		String playerUuid = joinEv.getPlayer().getUniqueId().toString();
		List<?> ambaList = Main.getInstance().getConfig().getList("ambassadors", null);
		
		if (ambaList != null) {
			for (Object obj: ambaList) {
				if (obj.equals(playerUuid)) {
					Admission.getList().add(playerUuid);
					Bukkit.setWhitelist(false);
				}
			}
		}
		
		if (Admission.isFree()) {
			joinEv.getPlayer().setWhitelisted(true);
		}
		
	}
}
	
