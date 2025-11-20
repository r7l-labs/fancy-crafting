package com.r7llabs.fancycrafting.gui;

import com.r7llabs.fancycrafting.FancyCraftingPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Handles all crafting GUI interactions
 */
public class CraftingGUIListener implements Listener {
    
    private final CraftingGUIManager manager;
    
    public CraftingGUIListener(CraftingGUIManager manager) {
        this.manager = manager;
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getClickedInventory();
        
        if (clickedInventory == null) {
            return;
        }
        
        InventoryHolder holder = clickedInventory.getHolder();
        if (!(holder instanceof CraftingGUI)) {
            // Check if top inventory is crafting GUI
            holder = event.getView().getTopInventory().getHolder();
            if (!(holder instanceof CraftingGUI)) {
                return;
            }
        }
        
        CraftingGUI gui = (CraftingGUI) holder;
        int slot = event.getRawSlot();
        
        // If clicking in the crafting GUI
        if (clickedInventory.equals(gui.getInventory())) {
            // Allow crafting slot interactions
            if (gui.isCraftingSlot(slot)) {
                // Schedule update after click is processed
                org.bukkit.Bukkit.getScheduler().runTaskLater(
                    manager.getPlugin(),
                    gui::updateCrafting,
                    1L
                );
                return;
            }
            
            // Handle result slot
            if (gui.isResultSlot(slot)) {
                event.setCancelled(true);
                gui.handleResultClick(player);
                return;
            }
            
            // Cancel all other clicks in GUI
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();
        
        if (!(holder instanceof CraftingGUI)) {
            return;
        }
        
        CraftingGUI gui = (CraftingGUI) holder;
        
        // Check if any dragged slot is in the crafting GUI
        for (int slot : event.getRawSlots()) {
            if (slot < inventory.getSize()) {
                // Only allow drag in crafting slots
                if (!gui.isCraftingSlot(slot)) {
                    event.setCancelled(true);
                    return;
                }
            }
        }
        
        // Update crafting after drag
        if (!event.isCancelled()) {
            org.bukkit.Bukkit.getScheduler().runTaskLater(
                manager.getPlugin(),
                gui::updateCrafting,
                1L
            );
        }
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getInventory();
        InventoryHolder holder = inventory.getHolder();
        
        if (holder instanceof CraftingGUI) {
            manager.closeGUI(player.getUniqueId());
        }
    }
}
