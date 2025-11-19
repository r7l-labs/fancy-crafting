package de.ancash.fancycrafting.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.ancash.fancycrafting.FancyCrafting;
import de.ancash.fancycrafting.recipe.IRecipe;

public class ExportSubCommand extends FancyCraftingSubCommand {

	public ExportSubCommand(FancyCrafting pl, String... str) {
		super(pl, str);
	}

	@Override
	public Boolean apply(CommandSender sender, String[] args) {
		if (!sender.isOp() && !sender.hasPermission(FancyCrafting.CREATE_PERM)) {
			sender.sendMessage(pl.getResponse().NO_PERMISSION);
			return true;
		}

		sender.sendMessage("§aExporting recipes to text file...");
		
		try {
			File exportDir = new File("plugins/FancyCrafting/exports");
			if (!exportDir.exists()) {
				exportDir.mkdirs();
			}
			
			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
			File exportFile = new File(exportDir, "recipes_" + timestamp + ".txt");
			
			try (FileWriter writer = new FileWriter(exportFile)) {
				writer.write("=== FancyCrafting Recipe Export ===\n");
				writer.write("Export Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "\n");
				writer.write("Total Custom Recipes: " + pl.getRecipeManager().getCustomRecipes().size() + "\n");
				writer.write("\n");
				
				for (IRecipe recipe : pl.getRecipeManager().getCustomRecipes()) {
					writer.write("----------------------------------------\n");
					writer.write("Recipe Name: " + recipe.getRecipeName() + "\n");
					writer.write("Result: " + recipe.getResultName() + "\n");
					writer.write("Type: " + (recipe instanceof de.ancash.fancycrafting.recipe.IShapedRecipe ? "Shaped" : "Shapeless") + "\n");
					writer.write("Dimensions: " + recipe.getWidth() + "x" + recipe.getHeight() + "\n");
					writer.write("UUID: " + recipe.getUUID() + "\n");
					writer.write("Category: " + (recipe.getCategory() != null ? recipe.getCategory().getName() : "None") + "\n");
					writer.write("\n");
				}
				
				writer.write("=== End of Export ===\n");
			}
			
			sender.sendMessage("§aSuccessfully exported §f" + pl.getRecipeManager().getCustomRecipes().size() + " §arecipes to:");
			sender.sendMessage("§7" + exportFile.getAbsolutePath());
			
		} catch (IOException e) {
			sender.sendMessage("§cFailed to export recipes! Check console for details.");
			if (pl != null) {
				System.err.println("[FancyCrafting] Failed to export recipes: " + e.getMessage());
				e.printStackTrace();
			}
		}
		
		return true;
	}
}
