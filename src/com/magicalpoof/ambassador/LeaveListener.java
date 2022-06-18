package com.magicalpoof.ambassador;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;


public class LeaveListener implements Listener{
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent quitEv) {
		String uuid = quitEv.getPlayer().getUniqueId().toString();
		Admission.getList().remove(uuid);
		if (!Admission.isFree()) {
			Bukkit.setWhitelist(true);
		}
	}
}

	
