package fr.mrazerty31.antykflag.listener;

import java.util.HashMap;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.mrazerty31.antykflag.config.ConfigHandler;
import fr.mrazerty31.antykflag.game.Team;

public class PlayerListener implements Listener {
	public static HashMap<Player, Team> settingTeam = new HashMap<Player, Team>();
	@EventHandler
	public void rightClickBlock(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(player.getWorld().equals(ConfigHandler.getGameWorld())) {
				if(!settingTeam.containsKey(player)) {
					 // TODO: Take flag
				} else {
					Block block = e.getClickedBlock();
					Team team = settingTeam.get(player);
					ConfigHandler.setTeamFlag(block, team);
					player.sendMessage("§aLe drapeau de la team \""+team.getColor()+team.getName()+"§a\" a été défini.");
					settingTeam.remove(player);
				}
			}
		} else if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
			if(player.getWorld().equals(ConfigHandler.getGameWorld())) {
				if(settingTeam.containsKey(player)) {
					settingTeam.remove(player);
				}
			}
		}
	}
}
