package fr.mrazerty31.antykflag.game;

import org.bukkit.entity.Player;

import fr.mrazerty31.antykflag.game.classes.Kit;

public class AFPlayer {
	private Player player;
	private boolean carrying = false;
	private Kit kit;
	
	public AFPlayer(Player player) {
		this.player = player;
	}
	
	public AFPlayer(Player player, Kit kit) {
		this(player);
		this.kit = kit;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public boolean isCarrying() {
		return carrying;
	}
	
	public void setCarrying(boolean carry) {
		this.carrying = carry;
	}
	
	public Kit getKit() {
		return kit;
	}
	
	public void setKit(Kit kit) {
		this.kit = kit;
	}
	
	public Team getTeam() throws NullPointerException {
		return Team.getPlayerTeam(this);
	}
	
	public static boolean inGame(Player player) {
		boolean inGame = false;
		for(AFPlayer pl : Game.getCurrentGame().getPlayers()) {
			if(pl.getPlayer().equals(player)) {
				inGame = true;
			}
		}
		return inGame;
	}
}
