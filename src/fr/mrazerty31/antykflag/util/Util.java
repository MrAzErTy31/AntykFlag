package fr.mrazerty31.antykflag.util;

import java.util.HashMap;

import org.bukkit.ChatColor;

public class Util {
	public static HashMap<ChatColor, Byte> colors = new HashMap<ChatColor, Byte>();
	public static void init() {
		// Fill colors
		// PINK and BROWN missing.
		colors.put(ChatColor.WHITE, (byte) 0);
		colors.put(ChatColor.GOLD, (byte) 1);
		colors.put(ChatColor.LIGHT_PURPLE, (byte) 2);
		colors.put(ChatColor.AQUA, (byte) 3);
		colors.put(ChatColor.YELLOW, (byte) 4);
		colors.put(ChatColor.GREEN, (byte) 5);
		colors.put(ChatColor.DARK_GRAY, (byte) 7);
		colors.put(ChatColor.GRAY, (byte) 8);
		colors.put(ChatColor.BLUE, (byte) 9);
		colors.put(ChatColor.DARK_PURPLE, (byte) 10);
		colors.put(ChatColor.DARK_BLUE, (byte) 11);
		colors.put(ChatColor.DARK_GREEN, (byte) 13);
		colors.put(ChatColor.RED, (byte) 14);
		colors.put(ChatColor.BLACK, (byte) 15);
	}
}
