package com.r7llabs.fancycrafting.listeners;

import com.r7llabs.fancycrafting.FancyCraftingPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * Intercepts workbench opens and redirects to custom GUI
 */
public class WorkbenchListener implements Listener {
    
    private final FancyCraftingPlugin plugin;
    
    public WorkbenchListener(FancyCraftingPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWorkbenchOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        
        if (event.getInventory().getType() == InventoryType.WORKBENCH) {
            event.setCancelled(true);
            
            Player player = (Player) event.getPlayer();
            
            // Open custom GUI with a slight delay to avoid conflicts
            plugin.getServer().getScheduler().runTaskLater(plugin, () -> {
                plugin.getGuiManager().openCraftingGUI(player, plugin.getDefaultWidth());
            }, 1L);
        }
    }
}
