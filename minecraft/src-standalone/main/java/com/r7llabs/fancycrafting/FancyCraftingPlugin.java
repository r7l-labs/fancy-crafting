package com.r7llabs.fancycrafting;

import com.r7llabs.fancycrafting.gui.CraftingGUIManager;
import com.r7llabs.fancycrafting.listeners.CraftingBlockerListener;
import com.r7llabs.fancycrafting.listeners.WorkbenchListener;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * FancyCrafting - Standalone custom crafting plugin
 * Blocks vanilla crafting and provides a custom GUI interface
 * 
 * @author R7L Labs
 * @version 1.0.0
 */
public class FancyCraftingPlugin extends JavaPlugin {
    
    private CraftingGUIManager guiManager;
    private boolean disableVanillaCrafting;
    private boolean enableCustomGUI;
    private int defaultWidth;
    private int defaultHeight;
    
    @Override
    public void onEnable() {
        // Save default config
        saveDefaultConfig();
        
        // Load configuration
        loadConfiguration();
        
        // Initialize GUI manager
        guiManager = new CraftingGUIManager(this);
        
        // Register listeners
        registerListeners();
        
        getLogger().info("FancyCrafting has been enabled!");
        getLogger().info("Vanilla crafting disabled: " + disableVanillaCrafting);
        getLogger().info("Custom GUI enabled: " + enableCustomGUI);
    }
    
    @Override
    public void onDisable() {
        // Close all open GUIs
        if (guiManager != null) {
            guiManager.closeAll();
        }
        
        getLogger().info("FancyCrafting has been disabled!");
    }
    
    private void loadConfiguration() {
        reloadConfig();
        disableVanillaCrafting = getConfig().getBoolean("disable-vanilla-crafting", true);
        enableCustomGUI = getConfig().getBoolean("enable-custom-gui", true);
        defaultWidth = getConfig().getInt("default-grid-size.width", 3);
        defaultHeight = getConfig().getInt("default-grid-size.height", 3);
        
        // Validate grid size
        defaultWidth = Math.max(3, Math.min(6, defaultWidth));
        defaultHeight = Math.max(3, Math.min(6, defaultHeight));
    }
    
    private void registerListeners() {
        if (disableVanillaCrafting) {
            getServer().getPluginManager().registerEvents(new CraftingBlockerListener(this), this);
            getLogger().info("Vanilla crafting blocker registered");
        }
        
        if (enableCustomGUI) {
            getServer().getPluginManager().registerEvents(new WorkbenchListener(this), this);
            getLogger().info("Workbench listener registered");
        }
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(colorize("&cThis command can only be used by players!"));
            return true;
        }
        
        Player player = (Player) sender;
        
        if (command.getName().equalsIgnoreCase("craft")) {
            if (!player.hasPermission("fancycrafting.use")) {
                player.sendMessage(colorize(getConfig().getString("messages.no-permission")));
                return true;
            }
            
            int size = defaultWidth;
            
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (!player.hasPermission("fancycrafting.admin")) {
                        player.sendMessage(colorize(getConfig().getString("messages.no-permission")));
                        return true;
                    }
                    loadConfiguration();
                    player.sendMessage(colorize(getConfig().getString("messages.reload-success")));
                    return true;
                }
                
                try {
                    size = Integer.parseInt(args[0]);
                    if (size < 3 || size > 6) {
                        player.sendMessage(colorize(getConfig().getString("messages.usage")));
                        return true;
                    }
                } catch (NumberFormatException e) {
                    player.sendMessage(colorize(getConfig().getString("messages.usage")));
                    return true;
                }
            }
            
            guiManager.openCraftingGUI(player, size);
            return true;
        }
        
        return false;
    }
    
    public CraftingGUIManager getGuiManager() {
        return guiManager;
    }
    
    public boolean isVanillaCraftingDisabled() {
        return disableVanillaCrafting;
    }
    
    public boolean isCustomGUIEnabled() {
        return enableCustomGUI;
    }
    
    public int getDefaultWidth() {
        return defaultWidth;
    }
    
    public int getDefaultHeight() {
        return defaultHeight;
    }
    
    public String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
