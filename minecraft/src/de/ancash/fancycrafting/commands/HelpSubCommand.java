package de.ancash.fancycrafting.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ancash.fancycrafting.FancyCrafting;

public class HelpSubCommand extends FancyCraftingSubCommand {

	public HelpSubCommand(FancyCrafting pl, String... str) {
		super(pl, str);
	}

	@Override
	public Boolean apply(CommandSender sender, String[] args) {
		sender.sendMessage("§a§l=== FancyCrafting Help ===");
		sender.sendMessage("");
		sender.sendMessage("§e§lPlayer Commands:");
		sender.sendMessage("§a/fc open §7- Open custom crafting GUI");
		sender.sendMessage("§a/fc open <width> <height> §7- Open specific size GUI (1-8 x 1-6)");
		sender.sendMessage("§a/fc view §7- Browse all custom recipes");
		sender.sendMessage("§a/fc view <recipe-name> §7- View a specific recipe");
		sender.sendMessage("");
		
		if (sender.hasPermission(FancyCrafting.CREATE_PERM) || sender.isOp()) {
			sender.sendMessage("§e§lAdmin Commands:");
			sender.sendMessage("§a/fc create §7- Create a new recipe");
			sender.sendMessage("§a/fc edit §7- Edit existing custom recipes");
			sender.sendMessage("§a/fc list §7- Show recipe statistics");
			sender.sendMessage("§a/fc blacklist §7or §a/fc bl §7- Manage blacklisted recipes");
			sender.sendMessage("§a/fc reload §7- Reload plugin configuration");
			sender.sendMessage("§a/fc acie §7- Auto crafter item editor");
			sender.sendMessage("§a/fc acii §7- Make item in hand an auto crafter");
			sender.sendMessage("");
		}
		
		sender.sendMessage("§7Plugin Version: §f4.11.0");
		sender.sendMessage("§7For more info: §fhttps://www.spigotmc.org/resources/87300/");
		
		return true;
	}
}
