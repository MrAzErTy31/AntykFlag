package fr.mrazerty31.antykflag.game;

import org.bukkit.entity.Player;

import fr.mrazerty31.antykflag.game.classes.Kit;

public class AFPlayer {
	private Player player;
	private boolean carrying = false;
	private Kit kit;

	public AFPlayer(Player player) {
		this.player = player;

		Game.getCurrentGame().addPlayer(this);
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

	public Team getTeam() {
		return Team.getPlayerTeam(this);
	}

	public boolean hasTeam() {
		boolean hasTeam = false;
		try {
			getTeam();
			hasTeam = true;
		} catch(NullPointerException npe) {
			hasTeam = false;
		}
		return hasTeam;
	}

	public void takeFlag(Team team) {
		if(!Game.getCurrentGame().getCarriers().containsKey(this)) {
			Game.getCurrentGame().getCarriers().put(this, team);
		}
	}

	public void putFlag(Team team) {
		if(Game.getCurrentGame().getCarriers().containsKey(this)) {
			Game.getCurrentGame().getCarriers().remove(this);
			getTeam().setScore(getTeam().getScore() + 1);
		}
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

	public static AFPlayer getPlayer(Player player) {
		AFPlayer pl = null;
		try {
			for(AFPlayer plr : Game.getCurrentGame().getPlayers()) {
				if(plr.getPlayer().equals(player)) {
					pl = plr;
				}
			}
		} catch(NullPointerException npe) {}
		return pl;
	}
}
