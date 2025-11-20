package de.ancash.fancycrafting.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

import de.ancash.fancycrafting.FancyCrafting;
import de.ancash.fancycrafting.gui.WorkspaceTemplate;

/**
 * Blocks all vanilla crafting (both 2x2 player inventory and 3x3 workbench)
 * and redirects players to use the custom crafting GUI instead.
 */
public class InventoryCraftingBlocker implements Listener {

	private final FancyCrafting plugin;

	public InventoryCraftingBlocker(FancyCrafting plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPrepareItemCraft(PrepareItemCraftEvent event) {
		// Block all vanilla crafting by clearing the result
		event.getInventory().setResult(null);
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent event) {
		InventoryType type = event.getInventory().getType();
		
		// Block crafting in player inventory (2x2 grid)
		if (type == InventoryType.CRAFTING) {
			if (event.getSlotType() == org.bukkit.event.inventory.InventoryType.SlotType.RESULT) {
				event.setCancelled(true);
				if (event.getWhoClicked() instanceof Player) {
					Player player = (Player) event.getWhoClicked();
					// Open custom crafting GUI instead
					plugin.openCraftingWorkspace(player, WorkspaceTemplate
							.get(plugin.getDefaultDimension().getWidth(), plugin.getDefaultDimension().getHeight()));
				}
			}
		}
		
		// Block crafting in workbench (3x3 grid) - though this should already be handled by WorkbenchOpenListener
		if (type == InventoryType.WORKBENCH) {
			if (event.getSlotType() == org.bukkit.event.inventory.InventoryType.SlotType.RESULT) {
				event.setCancelled(true);
			}
		}
	}
}
