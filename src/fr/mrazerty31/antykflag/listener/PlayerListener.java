package fr.mrazerty31.antykflag.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.mrazerty31.antykflag.config.ConfigHandler;
import fr.mrazerty31.antykflag.game.AFPlayer;

public class PlayerListener implements Listener {
	@EventHandler
	public void rightClickBlock(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			Player player = e.getPlayer();
			if(player.getWorld().equals(ConfigHandler.getGameWorld())) {
				if(AFPlayer.inGame(player)) {
					// TODO: Check then pick wool block;
				}
			}
		}
	}
}
