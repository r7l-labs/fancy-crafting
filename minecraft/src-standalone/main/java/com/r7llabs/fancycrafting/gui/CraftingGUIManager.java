package com.r7llabs.fancycrafting.gui;

import com.r7llabs.fancycrafting.FancyCraftingPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Manages all crafting GUIs
 */
public class CraftingGUIManager {
    
    private final FancyCraftingPlugin plugin;
    private final Map<UUID, CraftingGUI> activeGUIs;
    
    public CraftingGUIManager(FancyCraftingPlugin plugin) {
        this.plugin = plugin;
        this.activeGUIs = new HashMap<>();
        
        // Register GUI listener
        Bukkit.getPluginManager().registerEvents(new CraftingGUIListener(this), plugin);
    }
    
    public void openCraftingGUI(Player player, int gridSize) {
        // Close existing GUI if any
        closeGUI(player.getUniqueId());
        
        // Create and open new GUI
        CraftingGUI gui = new CraftingGUI(plugin, player, gridSize);
        activeGUIs.put(player.getUniqueId(), gui);
        gui.open();
    }
    
    public CraftingGUI getGUI(UUID playerId) {
        return activeGUIs.get(playerId);
    }
    
    public CraftingGUI getGUI(Inventory inventory) {
        for (CraftingGUI gui : activeGUIs.values()) {
            if (gui.getInventory().equals(inventory)) {
                return gui;
            }
        }
        return null;
    }
    
    public void closeGUI(UUID playerId) {
        CraftingGUI gui = activeGUIs.remove(playerId);
        if (gui != null) {
            gui.returnItems();
        }
    }
    
    public void closeAll() {
        for (UUID playerId : activeGUIs.keySet()) {
            Player player = Bukkit.getPlayer(playerId);
            if (player != null && player.isOnline()) {
                player.closeInventory();
            }
        }
        activeGUIs.clear();
    }
    
    public FancyCraftingPlugin getPlugin() {
        return plugin;
    }
}
