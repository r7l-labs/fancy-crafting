package de.ancash.fancycrafting.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ancash.fancycrafting.FancyCrafting;
import de.ancash.fancycrafting.recipe.IRecipe;

public class ListSubCommand extends FancyCraftingSubCommand {

	public ListSubCommand(FancyCrafting pl, String... str) {
		super(pl, str);
	}

	@Override
	public Boolean apply(CommandSender sender, String[] args) {
		if (!isPlayer(sender)) {
			// Allow console to list recipes
			int count = 0;
			sender.sendMessage("§a=== Custom Recipes ===");
			for (IRecipe recipe : pl.getRecipeManager().getCustomRecipes()) {
				sender.sendMessage("§7- §f" + recipe.getRecipeName() + " §7(Result: §f" + recipe.getResultName() + "§7)");
				count++;
			}
			sender.sendMessage("§aTotal: §f" + count + " §acustom recipes");
			return true;
		}

		Player player = (Player) sender;
		
		if (!player.hasPermission(FancyCrafting.VIEW_ALL_PERM) && !player.isOp()) {
			player.sendMessage(pl.getResponse().NO_PERMISSION);
			return true;
		}

		int customCount = pl.getRecipeManager().getCustomRecipes().size();
		int blacklistedCount = pl.getRecipeManager().getBlacklistedRecipes().size();
		
		player.sendMessage("§a§l=== FancyCrafting Recipe Statistics ===");
		player.sendMessage("§aCustom Recipes: §f" + customCount);
		player.sendMessage("§aBlacklisted Recipes: §f" + blacklistedCount);
		player.sendMessage("§7Use §f/fc view §7to browse all recipes");
		player.sendMessage("§7Use §f/fc view <recipe-name> §7to view a specific recipe");
		
		return true;
	}
}
