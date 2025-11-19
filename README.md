# FancyCrafting

![Minecraft](https://img.shields.io/badge/Minecraft-1.8+-brightgreen.svg)
![Spigot](https://img.shields.io/badge/Spigot-API-orange.svg)
![Version](https://img.shields.io/badge/version-4.11.0-blue.svg)
![License](https://img.shields.io/badge/license-MIT-green.svg)

A powerful Minecraft plugin that allows server administrators and players to create custom crafting recipes with advanced features including shaped, shapeless, and random recipes in both vanilla crafting tables and custom GUIs.

## 🌟 Features

### Core Features
- **Custom Recipe Creation**: Create shaped and shapeless recipes with a user-friendly GUI
- **Random Recipes**: Define recipes with multiple possible outcomes and configurable probabilities
- **Flexible Crafting Grids**: Support for crafting dimensions from 1x1 to 8x6
- **Vanilla Integration**: Works with both vanilla 3x3 and 2x2 crafting tables
- **Custom GUI**: Beautiful and intuitive custom crafting interface
- **Quick Crafting Slots**: Auto-craft recipes without manually placing ingredients
- **Recipe Blacklisting**: Block specific vanilla recipes from being crafted
- **Auto Crafter Items**: Create items that automatically craft recipes for players
- **Recipe Categories**: Organize recipes into custom categories
- **Permission System**: Granular control over who can craft, view, and edit recipes

### Advanced Features
- **Async Recipe Processing**: Configurable asynchronous recipe checking for performance
- **Recipe Export**: Export all recipes to a text file for documentation
- **Update Checker**: Automatic notifications for new plugin versions
- **Hot Reload**: Reload all configurations and recipes without restarting
- **Debug Mode**: Comprehensive logging for troubleshooting
- **NBT Support**: Full NBT tag support for complex items
- **Metrics**: Anonymous usage statistics via bStats
- **Multi-language Support**: Internationalization support (NLS)

## 📋 Requirements

- **Minecraft Server**: 1.8 or higher
- **Server Software**: Spigot, Paper, or compatible forks
- **Java**: Java 8 or higher
- **Dependencies**: 
  - ILibrary (version 3.13.0+) - Required
  - CustomCrafting (optional) - Soft dependency

## 📥 Installation

1. Download the latest version of FancyCrafting from [SpigotMC](https://www.spigotmc.org/resources/87300/)
2. Download the required ILibrary dependency
3. Place both `.jar` files in your server's `plugins` folder
4. Restart your server
5. Configure the plugin in `plugins/FancyCrafting/config.yml`

## 🎮 Commands

### Player Commands
| Command | Aliases | Description | Permission |
|---------|---------|-------------|------------|
| `/fc help` | `/fc ?` | Display help menu | None |
| `/fc open` | - | Open the custom crafting GUI | `fancycrafting.open` |
| `/fc open <width> <height>` | - | Open a specific sized crafting GUI (1-8 x 1-6) | `fancycrafting.open` |
| `/fc open <player>` | - | Open crafting GUI for another player | `fancycrafting.open.other` |
| `/fc view` | - | Browse all custom recipes | Permission varies |
| `/fc view <recipe-name>` | - | View a specific recipe (use `-` for spaces) | Permission varies |

### Admin Commands
| Command | Aliases | Description | Permission |
|---------|---------|-------------|------------|
| `/fc create` | - | Create a new custom recipe | `fancycrafting.admin.create` |
| `/fc edit` | - | Edit existing custom recipes | `fancycrafting.admin.edit` |
| `/fc list` | `/fc ls` | Show recipe statistics | `fancycrafting.admin.view` |
| `/fc blacklist` | `/fc bl` | Manage blacklisted recipes | `fancycrafting.admin.blmanage` |
| `/fc reload` | `/fc rl` | Reload plugin configuration | `fancycrafting.admin.reload` |
| `/fc export` | - | Export recipes to text file | `fancycrafting.admin.create` |
| `/fc acie` | - | Open auto crafter item editor | `fancycrafting.aceditor` |
| `/fc acii` | - | Make item in hand an auto crafter | `fancycrafting.admin.acinit` |

## 🔑 Permissions

### General Permissions
- `fancycrafting.open` - Allow opening the custom crafting GUI
- `fancycrafting.open.other` - Allow opening GUI for other players
- `fancycrafting.qc` - Allow using quick crafting slots

### Admin Permissions
- `fancycrafting.admin.create` - Create new recipes
- `fancycrafting.admin.edit` - Edit existing recipes
- `fancycrafting.admin.view` - View all recipes
- `fancycrafting.admin.reload` - Reload plugin configuration
- `fancycrafting.admin.blmanage` - Manage recipe blacklist
- `fancycrafting.aceditor` - Use auto crafter item editor
- `fancycrafting.admin.acinit` - Initialize auto crafter items

### Recipe-Specific Permissions
- `fancycrafting.craft.<recipe-name>` - Permission to craft a specific recipe
- `fancycrafting.view.<recipe-name>` - Permission to view a specific recipe

*Note: Replace spaces in recipe names with hyphens for permissions*

## ⚙️ Configuration

The main configuration file is located at `plugins/FancyCrafting/config.yml`

### Key Configuration Options

```yaml
# Sort recipes by recipe name (true) or result name (false)
sort-recipes-by-recipe-name: true

# Enable debug logging
debug: false

# Default crafting template dimensions
crafting:
  default-template-width: 3
  default-template-height: 3
  
  # Performance options
  check-recipes-async: false
  check-quick-crafting-async: false
  
  # Permission requirements
  perms-for-custom-recipes: false
  perms-for-vanilla-recipes: false
  perms-for-quick-crafting: false
  
  # Vanilla crafting table support
  support-vanilla-3x3: true
  support-vanilla-2x2: true
  vanilla-recipes-accept-plain-items-only: true
  
  # GUI options
  use-custom-gui: true
  cooldown: 3

# Permission defaults (TRUE, FALSE, OP, NOT_OP)
craft-recipe-permission-default: FALSE
view-recipe-permission-default: FALSE
```

## 📖 Usage Guide

### Creating a Recipe

1. Run `/fc create` command
2. Enter a unique recipe name
3. Choose recipe type:
   - **Normal Recipe**: Standard shaped or shapeless recipe
   - **Random Recipe**: Recipe with multiple possible outcomes
4. Click "Manage Ingredients" to set required items
5. Toggle between shaped/shapeless modes
6. Set the result item
7. For random recipes, configure result probabilities
8. Click "Save Recipe"

### Blacklisting a Recipe

1. Run `/fc blacklist` or `/fc bl`
2. Click "Add Recipe to Blacklist"
3. Create a recipe matching the ingredients you want to block
4. Save the blacklist entry
5. Players can no longer craft that combination

### Auto Crafter Items

Auto crafter items allow players to craft specific recipes automatically:

1. Hold an item in your hand
2. Run `/fc acii` to make it an auto crafter
3. Right-click the item to open the auto crafter editor
4. Add recipes that should auto-craft when the item is in inventory

## 🎨 Recipe Types

### Shaped Recipes
Ingredients must be placed in a specific pattern. Example: Crafting a sword requires sticks and materials in the exact vanilla pattern.

### Shapeless Recipes
Ingredients can be placed in any order. Example: Crafting cake - eggs, sugar, milk, and wheat in any arrangement.

### Random Recipes
Multiple possible outcomes with configurable probabilities. Example: Mining recipe that gives either diamond (10%), gold (30%), or coal (60%).

## 📁 File Structure

```
plugins/FancyCrafting/
├── config.yml                    # Main configuration
├── recipes.yml                   # Custom recipes storage
├── crafting-WxH.yml              # Crafting GUI templates (1x1 to 8x6)
├── blacklist/
│   ├── config.yml               # Blacklist configuration
│   └── recipes.yml              # Blacklisted recipes
└── exports/
    └── recipes_YYYY-MM-DD.txt   # Exported recipe lists
```

## 🔧 API Usage

Developers can integrate with FancyCrafting:

```java
// Get the plugin instance
FancyCrafting plugin = (FancyCrafting) Bukkit.getPluginManager().getPlugin("FancyCrafting");

// Register a custom recipe
IRecipe recipe = new IShapedRecipe(ingredients, width, height, result, name, uuid, category);
FancyCrafting.registerRecipe(recipe);

// Get recipe manager
RecipeManager manager = plugin.getRecipeManager();

// Check if recipe is blacklisted
boolean isBlacklisted = manager.isBlacklisted(hashList);

// Get all custom recipes
Set<IRecipe> customRecipes = manager.getCustomRecipes();
```

## 🐛 Troubleshooting

### Recipes not working?
- Check that ingredients match exactly (including NBT data)
- Verify permissions are correctly configured
- Enable debug mode in config.yml
- Check console for error messages

### Performance issues?
- Enable async recipe checking in config.yml
- Reduce the number of recipes
- Check for conflicts with other crafting plugins

### GUI not opening?
- Ensure `use-custom-gui: true` in config.yml
- Check player has permission `fancycrafting.open`
- Verify no inventory conflicts with other plugins

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests or open issues for bugs and feature requests.

### Building from Source

```bash
# Clone the repository
git clone https://github.com/r7l-labs/fancy-crafting.git
cd fancy-crafting/minecraft

# Build with Maven
mvn clean package

# The compiled jar will be in target/fancycrafting-4.11.0.jar
```

## 📜 Credits

### Original Author
- **Ancash** - Original plugin creator and primary developer

### Contributors
- r7l-labs - Maintenance, bug fixes, and additional features
- GitHub Copilot - Code quality improvements and feature enhancements

### Dependencies
- **ILibrary** by Ancash - Core library dependency
- **NBTNexus** - NBT handling
- **XSeries** by CryptoMorin - Cross-version material support
- **bStats** - Anonymous metrics
- **SimpleYAML** - Configuration management

### Special Thanks
- The Spigot community for continuous feedback
- All users who report bugs and suggest features
- CustomCrafting plugin for soft integration support

## 📞 Support

- **SpigotMC**: https://www.spigotmc.org/resources/87300/
- **Issues**: https://github.com/r7l-labs/fancy-crafting/issues
- **Wiki**: See SpigotMC resource page for tutorials

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2022 Ancash

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```

## 🔄 Version History

### Version 4.11.0 (Current)
- ✨ Added `/fc help` command with comprehensive help system
- ✨ Added `/fc list` command for recipe statistics
- ✨ Added `/fc export` command to export recipes to text file
- 🐛 Fixed empty catch blocks with proper exception logging
- 🐛 Replaced printStackTrace() with proper logger usage
- 🔧 Improved error handling and user feedback
- 📝 Added comprehensive README documentation
- 🎨 Enhanced command structure and organization

### Previous Versions
See [SpigotMC resource page](https://www.spigotmc.org/resources/87300/) for full version history.

---

**Made with ❤️ for the Minecraft community**
