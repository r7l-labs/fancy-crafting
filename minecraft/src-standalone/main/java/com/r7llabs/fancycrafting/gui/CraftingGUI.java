package com.r7llabs.fancycrafting.gui;

import com.r7llabs.fancycrafting.FancyCraftingPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Custom crafting GUI for players
 */
public class CraftingGUI implements InventoryHolder {
    
    private final FancyCraftingPlugin plugin;
    private final Player player;
    private final int gridSize;
    private final Inventory inventory;
    private final Map<Integer, ItemStack> craftingGrid;
    private final int resultSlot;
    
    public CraftingGUI(FancyCraftingPlugin plugin, Player player, int gridSize) {
        this.plugin = plugin;
        this.player = player;
        this.gridSize = Math.max(3, Math.min(gridSize, 6));
        this.craftingGrid = new HashMap<>();
        
        // Calculate inventory size
        int rows = calculateRows();
        this.inventory = Bukkit.createInventory(this, rows * 9, "§6§lCrafting " + this.gridSize + "x" + this.gridSize);
        
        // Calculate result slot position (right side, centered)
        this.resultSlot = calculateResultSlot();
        
        // Setup GUI layout
        setupGUI();
    }
    
    private int calculateRows() {
        // Need enough rows for grid + decorations
        int minRows = (int) Math.ceil((double) gridSize / 9) + 2;
        return Math.min(6, Math.max(3, minRows));
    }
    
    private int calculateResultSlot() {
        // Place result slot on right side, vertically centered
        int centerRow = (inventory.getSize() / 9) / 2;
        return centerRow * 9 + 8;
    }
    
    private void setupGUI() {
        // Fill with gray glass panes
        ItemStack grayPane = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, grayPane);
        }
        
        // Clear crafting grid slots
        int startSlot = 10;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int slot = startSlot + (row * 9) + col;
                if (slot < inventory.getSize()) {
                    inventory.setItem(slot, null);
                }
            }
        }
        
        // Clear result slot and add arrow
        inventory.setItem(resultSlot, null);
        if (resultSlot - 1 >= 0) {
            ItemStack arrow = new ItemStack(Material.ARROW);
            inventory.setItem(resultSlot - 1, arrow);
        }
    }
    
    public void open() {
        player.openInventory(inventory);
    }
    
    public void updateCrafting() {
        // Collect items from crafting grid
        ItemStack[][] grid = new ItemStack[gridSize][gridSize];
        int startSlot = 10;
        
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int slot = startSlot + (row * 9) + col;
                if (slot < inventory.getSize()) {
                    ItemStack item = inventory.getItem(slot);
                    grid[row][col] = (item != null && item.getType() != Material.AIR) ? item : null;
                }
            }
        }
        
        // Find matching recipe
        ItemStack result = findRecipe(grid);
        inventory.setItem(resultSlot, result);
    }
    
    private ItemStack findRecipe(ItemStack[][] grid) {
        // Simple recipe matching - can be extended later
        // For now, just return null (no recipe found)
        // This is where recipe checking logic would go
        return null;
    }
    
    public void handleResultClick(Player player) {
        ItemStack result = inventory.getItem(resultSlot);
        if (result == null || result.getType() == Material.AIR) {
            return;
        }
        
        // Give result to player
        Map<Integer, ItemStack> leftover = player.getInventory().addItem(result);
        if (!leftover.isEmpty()) {
            // Drop items that don't fit
            for (ItemStack item : leftover.values()) {
                player.getWorld().dropItem(player.getLocation(), item);
            }
        }
        
        // Consume ingredients
        consumeIngredients();
        
        // Update GUI
        updateCrafting();
        
        player.sendMessage(plugin.getConfig().getString("messages.crafted", "§aItem crafted!"));
    }
    
    private void consumeIngredients() {
        int startSlot = 10;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int slot = startSlot + (row * 9) + col;
                if (slot < inventory.getSize()) {
                    ItemStack item = inventory.getItem(slot);
                    if (item != null && item.getType() != Material.AIR) {
                        item.setAmount(item.getAmount() - 1);
                        if (item.getAmount() <= 0) {
                            inventory.setItem(slot, null);
                        }
                    }
                }
            }
        }
    }
    
    public void returnItems() {
        int startSlot = 10;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int slot = startSlot + (row * 9) + col;
                if (slot < inventory.getSize()) {
                    ItemStack item = inventory.getItem(slot);
                    if (item != null && item.getType() != Material.AIR) {
                        Map<Integer, ItemStack> leftover = player.getInventory().addItem(item);
                        if (!leftover.isEmpty()) {
                            for (ItemStack drop : leftover.values()) {
                                player.getWorld().dropItem(player.getLocation(), drop);
                            }
                        }
                    }
                }
            }
        }
    }
    
    public boolean isCraftingSlot(int slot) {
        int startSlot = 10;
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                int gridSlot = startSlot + (row * 9) + col;
                if (gridSlot == slot) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isResultSlot(int slot) {
        return slot == resultSlot;
    }
    
    @Override
    public Inventory getInventory() {
        return inventory;
    }
    
    public Player getPlayer() {
        return player;
    }
}
