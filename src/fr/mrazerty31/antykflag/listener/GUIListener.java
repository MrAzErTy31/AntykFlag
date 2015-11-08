package fr.mrazerty31.antykflag.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import fr.mrazerty31.antykflag.config.ConfigHandler;
import fr.mrazerty31.antykflag.game.AFPlayer;
import fr.mrazerty31.antykflag.game.Game;
import fr.mrazerty31.antykflag.game.Team;
import fr.mrazerty31.antykflag.game.classes.Kit;
import fr.mrazerty31.antykflag.gui.GUIHandler;

public class GUIListener implements Listener {
	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if(player.getWorld().equals(ConfigHandler.getGameWorld())) {
			if(e.getCurrentItem().getType() != Material.AIR) {
				Inventory inventory = e.getInventory();
				ItemStack item = e.getCurrentItem();
				if(inventory.getName().equals(GUIHandler.getKitSelector(player).getName())) {
					try {
						for(Kit kit : Kit.getKits()) {
							if(kit.getIcon().equals(item)) {
								e.setCancelled(true);
								player.closeInventory();
								kit.give(player);
								player.sendMessage("§aVous avez bien reçu le kit \""+kit.getName()+"\".");
							}
						}
					} catch(NullPointerException npe) {
						player.sendMessage("§cAucun kit n'est disponible pour le moment.");
						player.closeInventory();
					}
				} else if(inventory.getName().equals(GUIHandler.getTeamSelector(player).getName())) {
					try {
						for(Team team : Game.getCurrentGame().getTeams()) {
							String name = ChatColor.stripColor(item.getItemMeta().getDisplayName());
							try {
								team.removePlayer(AFPlayer.getPlayer(player));
							} catch(NullPointerException npe) {}
							if(team.getName().equals(name)) {
								team.addPlayer(new AFPlayer(player));
								player.sendMessage("§aVous êtes dans la team \""+team.getColor()+team.getName()+"§a\".");
								player.closeInventory();
							}
						}
					} catch(NullPointerException npe) {
						npe.printStackTrace();
						player.sendMessage("§cAucune team n'est disponible pour le moment.");
						player.closeInventory();
					}
				}
			}
		}
	}
}
