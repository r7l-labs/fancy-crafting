package com.r7llabs.fancycrafting.listeners;

import com.r7llabs.fancycrafting.FancyCraftingPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

/**
 * Blocks all vanilla crafting when configured
 */
public class CraftingBlockerListener implements Listener {
    
    private final FancyCraftingPlugin plugin;
    
    public CraftingBlockerListener(FancyCraftingPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPrepareCraft(PrepareItemCraftEvent event) {
        // Block all vanilla crafting by setting result to null
        event.getInventory().setResult(null);
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        InventoryType type = event.getInventory().getType();
        
        // Block clicking on result slots in crafting inventories
        if (type == InventoryType.CRAFTING || type == InventoryType.WORKBENCH) {
            if (event.getSlotType() == InventoryType.SlotType.RESULT) {
                event.setCancelled(true);
                
                // Optionally open custom GUI
                if (plugin.isCustomGUIEnabled() && type == InventoryType.CRAFTING) {
                    Player player = (Player) event.getWhoClicked();
                    player.closeInventory();
                    plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                        plugin.getGuiManager().openCraftingGUI(player, plugin.getDefaultWidth());
                    }, 1L);
                }
            }
        }
    }
}
