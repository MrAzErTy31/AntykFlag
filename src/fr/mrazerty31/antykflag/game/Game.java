package fr.mrazerty31.antykflag.game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

import fr.mrazerty31.antykflag.config.ConfigHandler;

public class Game {
	private static Game currentGame;
	private List<Team> teams = new ArrayList<Team>();
	private List<AFPlayer> players = new ArrayList<AFPlayer>();
	private World world;
	private int currentTime, length;

	public Game(World world, int time) {
		this.world = world;
		this.length = time*60;
		this.currentTime = time*60;
		
		currentGame = this;
	}

	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public void addTeam(Team team) {
		teams.add(team);
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
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public int getGameLength() {
		return length;
	}
	
	public void setGameLength(int length) {
		this.length = length;
	}
	
	public int getCurrentTime() {
		return currentTime;
	}
	
	public void setCurrentTime(int time) {
		this.currentTime = time;
	}
	
	public void serialize() {
		ConfigHandler.serializeGame(this);
	}
	
	public static Game getCurrentGame() {
		return currentGame;
	}
	
	public static void setCurrentGame(Game game) {
		currentGame = game;
	}
	
	public static void createGame(Player p, int time) {
		ConfigHandler.setGameWorld(p.getWorld());
		new Game(p.getWorld(), time);
	}
	
	public static void updateGame() {
		setCurrentGame(ConfigHandler.deserializeGame());
	}

}
