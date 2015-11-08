package fr.mrazerty31.antykflag.config;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import fr.mrazerty31.antykflag.AntykFlag;
import fr.mrazerty31.antykflag.game.Game;
import fr.mrazerty31.antykflag.game.Team;

public class ConfigHandler {
	public static World getGameWorld() {
		return Bukkit.getWorld(AntykFlag.config.getString("game.world"));
	}

	public static void setGameWorld(World world) {
		AntykFlag.config.set("game.world", world.getName());
		AntykFlag.instance.saveConfig();
	}

	public static int getGameLength() {
		return AntykFlag.config.getInt("game.length");
	}

	public static void setGameLength(int length) {
		AntykFlag.config.set("game.length", length);
		AntykFlag.instance.saveConfig();
	}

	public static String getTeamName(String team) {
		return AntykFlag.config.getString("game.teams."+team+".name");
	}

	public static void setTeamName(String team, String name) {
		AntykFlag.config.set("game.teams."+team+".name", name);
		AntykFlag.instance.saveConfig();
	}

	public static ChatColor getTeamColor(String team) {
		return ChatColor.valueOf(AntykFlag.config.getString("game.teams."+team+".color"));
	}

	public static void setTeamColor(String team, ChatColor color) {
		AntykFlag.config.set("game.teams."+team+".color", color.name());
		AntykFlag.instance.saveConfig();
	}

	public static List<Team> getTeamList() {
		List<Team> teams = new ArrayList<Team>();
		try {
			for(String team : AntykFlag.config.getConfigurationSection("game.teams").getKeys(false)) {
				teams.add(deserializeTeam(team));
			}
		} catch(NullPointerException npe) {}
		return teams;
	}

	public static void serializeGame(Game game) {
		setGameWorld(game.getWorld());
		setGameLength(game.getGameLength());
		AntykFlag.instance.saveConfig();
	}

	public static void serializeTeam(Team team) {
		setTeamName(team.getName(), team.getName());
		setTeamColor(team.getName(), team.getColor());
	}

	public static Game deserializeGame() {
		World world = getGameWorld();
		int length = getGameLength();
		Game game = new Game(world, length);
		game.setTeams(getTeamList());
		return game;
	}

	public static Team deserializeTeam(String team) {
		String name = getTeamName(team);
		ChatColor color = getTeamColor(team);
		return new Team(name, color);
	}

	public static boolean teamExists(String name) {
		return AntykFlag.config.isConfigurationSection("game.teams."+name);
	}

	public static void resetTeams() {
		AntykFlag.config.set("game.teams", null);
		AntykFlag.instance.saveConfig();
	}

	public static Block getBlockFlag(Team team) {
		int x = AntykFlag.config.getInt("game.teams."+team.getName()+".block.x");
		int y = AntykFlag.config.getInt("game.teams."+team.getName()+".block.y");
		int z = AntykFlag.config.getInt("game.teams."+team.getName()+".block.z");

		return getGameWorld().getBlockAt(new Location(getGameWorld(), x, y, z));
	}

	public static void setTeamFlag(Block block, Team team) {
		Location location = block.getLocation();
		AntykFlag.config.set("game.teams."+team.getName()+".block.x", location.getBlockX());
		AntykFlag.config.set("game.teams."+team.getName()+".block.y", location.getBlockY());
		AntykFlag.config.set("game.teams."+team.getName()+".block.z", location.getBlockZ());

		AntykFlag.instance.saveConfig();
	}
}
