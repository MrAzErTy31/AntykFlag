package fr.mrazerty31.antykflag.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import fr.mrazerty31.antykflag.game.Game;
import fr.mrazerty31.antykflag.game.Team;
import fr.mrazerty31.antykflag.game.classes.Kit;
import fr.mrazerty31.antykflag.lib.ItemLib;
import fr.mrazerty31.antykflag.util.Util;

public class GUIHandler {
	public static Inventory getKitSelector(Player player) throws NullPointerException {
		Inventory selector = Bukkit.createInventory(player, 9, "Sélection de kit");
		try {
			for(Kit kit : Kit.getKits()) {
				selector.addItem(kit.getIcon());
			}
		} catch(NullPointerException npe) {
			selector = null;
		}
		return selector;
	}

	public static Inventory getTeamSelector(Player player) throws NullPointerException {
		Inventory selector = Bukkit.createInventory(player, 9, "Sélection de team");
		for(Team team : Game.getCurrentGame().getTeams()) {
			selector.addItem(
					ItemLib.createItem(Material.WOOL, 1, (short) Util.colors.get(team.getColor()), team.getColor()+team.getName(), null)
					);
		}
		return selector;
	}
}
