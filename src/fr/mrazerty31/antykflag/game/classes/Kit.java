package fr.mrazerty31.antykflag.game.classes;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.mrazerty31.antykflag.lib.ItemLib;

public class Kit {
	private static List<Kit> kits = new ArrayList<Kit>();

	private String name;
	private ItemStack icon;
	private ItemStack[] armor, items;
	private PotionEffect[] effects;

	public Kit(String name, ItemStack icon) {
		this.name = name;
		this.icon = icon;
		
		kits.add(this);
	}

	public Kit(String name, ItemStack icon, ItemStack[] items, ItemStack[] armor) {
		this(name, icon);
		this.armor = armor;
		this.items = items;
		this.effects = null;
	}

	public Kit(String name, ItemStack icon, ItemStack[] items, ItemStack[] armor, PotionEffect[] effects) {
		this(name, icon, items, armor);
		this.effects = effects;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ItemStack getIcon() {
		return icon;
	}

	public void setIcon(ItemStack icon) {
		this.icon = icon;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	public ItemStack[] getItems() {
		return items;
	}

	public void setItems(ItemStack[] items) {
		this.items = items;
	}

	public PotionEffect[] getEffects() {
		return effects;
	}

	public void setEffects(PotionEffect[] effects) {
		this.effects = effects;
	}
	
	public void give(Player p) {
		try {
			PlayerInventory inv = p.getInventory();
			inv.clear();
			inv.setArmorContents(null);
			for(PotionEffect effect : p.getActivePotionEffects()) {
				p.removePotionEffect(effect.getType());
			}
			for(ItemStack item : getItems()) {
				inv.addItem(item);
			}
			inv.setArmorContents(getArmor());
			for(PotionEffect effect : getEffects()) {
				p.addPotionEffect(effect);
			}
		} catch(NullPointerException npe) {}
	}

	public static List<Kit> getKits() {
		return kits;
	}

	public static void register() {
		// Guerrier
		ItemStack[] items = new ItemStack[] {new ItemStack(Material.IRON_SWORD)};
		ItemStack[] armor = new ItemStack[] {
				new ItemStack(Material.IRON_BOOTS), 
				new ItemStack(Material.IRON_LEGGINGS), 
				new ItemStack(Material.IRON_CHESTPLATE), 
				new ItemStack(Material.IRON_HELMET)
		};
		new Kit("Guerrier", ItemLib.addDisplayName(new ItemStack(Material.IRON_SWORD), "§7Guerrier"), items, armor);

		// Archer
		items = new ItemStack[] {
				ItemLib.addEnchantments(
						new ItemStack(Material.BOW), 
						new Enchantment[] {Enchantment.ARROW_DAMAGE, Enchantment.ARROW_INFINITE, Enchantment.DURABILITY}, 
						new int[] {1, 1, 5}
						),
				new ItemStack(Material.ARROW)
		};
		armor = new ItemStack[] {
				ItemLib.addEnchantments(ItemLib.colorLeatherArmor(
						new ItemStack(Material.LEATHER_BOOTS), Color.BLACK), 
						new Enchantment[] {Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL}, 
						new int[] {5, 1}
				), ItemLib.addEnchantments(ItemLib.colorLeatherArmor(
						new ItemStack(Material.LEATHER_LEGGINGS), Color.BLACK), 
						new Enchantment[] {Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL}, 
						new int[] {5, 1}
				),
				ItemLib.addEnchantments(new ItemStack(Material.IRON_CHESTPLATE), new Enchantment[] {Enchantment.DURABILITY}, new int[] {5}),
				ItemLib.addEnchantments(ItemLib.colorLeatherArmor(
						new ItemStack(Material.LEATHER_HELMET), Color.BLACK), 
						new Enchantment[] {Enchantment.DURABILITY, Enchantment.PROTECTION_ENVIRONMENTAL}, 
						new int[] {5, 1}
				)
		};
		PotionEffect[] effects = new PotionEffect[] {new PotionEffect(PotionEffectType.SPEED, 3600*20, 0)};
		new Kit("Archer", ItemLib.addDisplayName(new ItemStack(Material.BOW), "§eArcher"), items, armor, effects);
	}
}
