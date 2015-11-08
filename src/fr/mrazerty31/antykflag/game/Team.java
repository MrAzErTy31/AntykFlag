package fr.mrazerty31.antykflag.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import fr.mrazerty31.antykflag.config.ConfigHandler;

public class Team {
	private String name;
	private ChatColor color;
	private List<AFPlayer> players = new ArrayList<AFPlayer>();
	private int score = 0;
	
	public Team(String name, ChatColor color) {
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ChatColor getColor() {
		return color;
	}
	
	public void setColor(ChatColor color) {
		this.color = color;
	}
	
	public List<AFPlayer> getPlayers() {
		return players;
	}
	
	public void setPlayers(List<AFPlayer> players) {
		this.players = players;
	}
	
	public void addPlayer(AFPlayer player) {
		players.add(player);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void serialize() {
		ConfigHandler.serializeTeam(this);
	}
	
	public static Team getPlayerTeam(AFPlayer player) throws NullPointerException {
		Team team = null;
		for(Team tm : Game.getCurrentGame().getTeams()) {
			if(team.getPlayers().contains(player)) {
				team = tm;
			}
		}
		return team;
	}
	
	public static Team deserializeTeam(String team) {
		return ConfigHandler.deserializeTeam(team);
	}
}
