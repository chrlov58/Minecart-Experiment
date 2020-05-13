package me.chrlov58.minecarts;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Minecarts extends JavaPlugin implements Listener {

	private HashMap<Integer, Minecart> minecarts;
	
	public void onEnable() {
		minecarts = new HashMap<Integer, Minecart>();
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable() {
		minecarts.clear();
	}
	
	@EventHandler
	public void onMinecartClick(PlayerInteractEntityEvent event) {
		Player p = event.getPlayer();
		if (p.getInventory().getItemInMainHand().getType() != Material.BLAZE_ROD) return;
		if (event.getRightClicked().getType() != EntityType.MINECART) return;
		
		Minecart m = (Minecart) event.getRightClicked();
		m.setInvulnerable(true);
		m.setSlowWhenEmpty(false);
		
		minecarts.put(minecarts.size(), m);
		p.sendMessage(c("&7Added minecart &a#" + minecarts.size() + "&7."));
	}
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		
		if (event.getMessage().equalsIgnoreCase("clear")) {
			for (int i : minecarts.keySet()) {
				minecarts.get(i).remove();
			}
			minecarts.clear();
			p.sendMessage(c("&7Cleared all minecarts"));
		}
		
	}
	
	private String c(String input) {
		return ChatColor.translateAlternateColorCodes('&', input);
	}
	
}
