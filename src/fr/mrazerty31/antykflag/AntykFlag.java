package fr.mrazerty31.antykflag;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.mrazerty31.antykflag.config.ConfigHandler;
import fr.mrazerty31.antykflag.game.Game;
import fr.mrazerty31.antykflag.game.Team;
import fr.mrazerty31.antykflag.game.classes.Kit;
import fr.mrazerty31.antykflag.gui.GUIHandler;
import fr.mrazerty31.antykflag.listener.GUIListener;
import fr.mrazerty31.antykflag.listener.PlayerListener;
import fr.mrazerty31.antykflag.util.Util;

public class AntykFlag extends JavaPlugin {
	public static AntykFlag instance;
	public static FileConfiguration config;

	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		this.getServer().getPluginManager().registerEvents(new GUIListener(), this);
		saveDefaultConfig();
		config = this.getConfig();

		Util.init();
		Kit.register();
		
		Game.updateGame();
		System.out.println("Game infos: ");
		System.out.println("World: " + Game.getCurrentGame().getWorld().getName());
		System.out.println("Length: " + Game.getCurrentGame().getGameLength());
		System.out.println("Game teams: ");
		for(Team team : Game.getCurrentGame().getTeams()) {
			System.out.println(" - " + team.getName() + ": ");
			System.out.println("    Color: " + team.getColor().name());
		}
	}

	public void onDisable() {
		instance = null;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("antykflag")) {
				if(args.length > 0) {
					if(args[0].equalsIgnoreCase("set")) {
						if(args.length > 1) {
							if(args[1].equalsIgnoreCase("world")) {
								ConfigHandler.setGameWorld(p.getWorld());
								p.sendMessage("§aLe monde du jeu est désormais \""+p.getWorld().getName()+"\".");
							} else if(args[1].equalsIgnoreCase("length")) {
								try {
									int length = Integer.parseInt(args[2]);
									ConfigHandler.setGameLength(length);
									p.sendMessage("§aLa durée du jeu est maintenant de " + length + " minute(s).");
								} catch(NumberFormatException e) {
									p.sendMessage("§cVeuillez indiquer un nombre entier.");
								}
							}
						} else {
							p.sendMessage("§eUtilisation: /antykflag set <world, length>");
						}
					} else if(args[0].equalsIgnoreCase("team")) {
						if(args.length > 1) {
							if(args[1].equalsIgnoreCase("add")) {
								if(args.length == 4) {
									String name = args[2];
									try {
										ChatColor color = ChatColor.valueOf(args[3].toUpperCase());
										if(!ConfigHandler.teamExists(name)) {
											new Team(name, color).serialize();
											p.sendMessage("§aLa team \""+name+"\" de couleur §l" + color + args[3].toUpperCase() + "§r§a a bien été ajoutée.");
										} else {
											p.sendMessage("§cUne team portant ce nom existe déjà.");
										}
									} catch(Exception ex) {
										ex.printStackTrace();
										p.sendMessage("§cVeuillez indiquer une couleur correcte.");
									}
								} else {
									p.sendMessage("§cNombre d'arguments incorrect.");
								}
							} else if(args[1].equalsIgnoreCase("list")) {
								try {
									if(!ConfigHandler.getTeamList().isEmpty()) {
										p.sendMessage("§aTeams présentes: ");
										for(Team team : Game.getCurrentGame().getTeams()) {
											p.sendMessage(" §6- " + team.getName() + " ("+team.getColor()+team.getColor().name()+"§6)");
										}
									}
								} catch(NullPointerException npe) {
									p.sendMessage("§eAucune team n'a été enregistrée pour le moment.");
								}
							} else if(args[1].equalsIgnoreCase("reset")) {
								ConfigHandler.resetTeams();
								p.sendMessage("§aLes teams ont été effacées.");
							}
						} else {
							p.sendMessage("§eUtilisation: /antykflag team <add, list, reset>");
						}
					} else {
						p.sendMessage("§eUtilisation: /antykflag <set>");
					}
				} else {
					p.sendMessage("§eUtilisation: /antykflag <args>");
				}
				Game.updateGame();
			}
		} 
		if(cmd.getName().equalsIgnoreCase("kit")) {
			if(args.length == 1) {
				if(!args[0].equalsIgnoreCase("list")) {
					try {
						Player player = Bukkit.getPlayer(args[0]);
						player.openInventory(GUIHandler.getKitSelector(player));
					} catch(Exception e) {}
				} else {
					sender.sendMessage("§aListe des kits: ");
					for(Kit kit : Kit.getKits()) {
						sender.sendMessage(" §6- §l"+kit.getIcon().getItemMeta().getDisplayName());
					}
				}
			}
		}
		return true;
	}
}
