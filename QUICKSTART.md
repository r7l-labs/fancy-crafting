# FancyCrafting - Quick Start Guide

Get started with FancyCrafting in just 5 minutes! 🚀

## Installation (2 minutes)

1. **Download Required Files**
   - Download FancyCrafting from [SpigotMC](https://www.spigotmc.org/resources/87300/)
   - Download ILibrary (required dependency)

2. **Install**
   ```
   server/
   └── plugins/
       ├── FancyCrafting-4.11.0.jar
       └── ILibrary-3.13.0.jar
   ```

3. **Start Server**
   - Start/restart your Minecraft server
   - Plugin will auto-generate configuration files

## First Recipe (2 minutes)

### Method 1: In-Game GUI (Recommended)
1. Join your server as OP or with `fancycrafting.admin.create` permission
2. Run command: `/fc create`
3. Enter a recipe name (e.g., "diamond-stick")
4. Choose recipe type: Click "Normal recipe"
5. Click "Manage Ingredients"
6. Place items in the grid (shaped pattern or shapeless)
7. Click back and toggle shaped/shapeless as needed
8. Place result item in your inventory and click the result slot
9. Click "Save Recipe" ✅

### Method 2: Manual Configuration
Edit `plugins/FancyCrafting/recipes.yml`:
```yaml
recipe-uuid-here:
  name: "diamond-stick"
  shaped: true
  width: 1
  height: 2
  ingredients:
    0:
      XMaterial: DIAMOND
      Amount: 1
    1:
      XMaterial: STICK
      Amount: 1
  result:
    XMaterial: STICK
    Amount: 1
    NBTNexusItem:
      type: SERIALIZED
      UnspecificMeta:
        DisplayName: "&bDiamond Stick"
```

Then reload: `/fc reload`

## Quick Command Reference

```bash
# Player Commands
/fc help              # Show all commands
/fc open              # Open crafting GUI
/fc view              # Browse recipes

# Admin Commands  
/fc create            # Create new recipe
/fc edit              # Edit recipes
/fc list              # Show statistics
/fc reload            # Reload config
/fc export            # Export recipes
```

## Common Recipes

### Shaped Recipe Example: Custom Sword
```
Pattern:
  D = Diamond
  S = Stick
  _ = Empty

Grid:
  D _ _
  D _ _
  S _ _

Result: Custom enchanted diamond sword
```

### Shapeless Recipe Example: Custom Potion
```
Ingredients (any order):
  - Water Bottle
  - Glowstone
  - Redstone
  - Blaze Powder

Result: Custom strength potion
```

### Random Recipe Example: Mystery Box
```
Ingredients:
  - Chest
  - Diamond
  
Results:
  - 50% chance: Diamond Sword
  - 30% chance: Diamond Armor
  - 20% chance: Emerald (x16)
```

## Basic Permissions Setup

### For Regular Players
```yaml
permissions:
  fancycrafting.open: true      # Allow using /fc open
  fancycrafting.qc: true        # Allow quick crafting
```

### For Moderators
```yaml
permissions:
  fancycrafting.open: true
  fancycrafting.qc: true
  fancycrafting.admin.view: true      # View all recipes
  fancycrafting.admin.reload: true    # Reload plugin
```

### For Admins
```yaml
permissions:
  fancycrafting.*: true         # All permissions
```

## Configuration Tips

### Performance Optimization
```yaml
crafting:
  check-recipes-async: true           # Enable for better TPS
  check-quick-crafting-async: true    # Enable for better TPS
```

### Security/Control
```yaml
crafting:
  perms-for-custom-recipes: true      # Require perms per recipe
  perms-for-vanilla-recipes: false    # Keep vanilla recipes free
```

### User Experience
```yaml
crafting:
  use-custom-gui: true               # Beautiful custom interface
  cooldown: 3                        # Prevent spam (in ticks)
  support-vanilla-3x3: true          # Also work in vanilla tables
```

## Common Issues & Solutions

### Issue: "Recipe not working"
**Solution:** Check that:
- Ingredients match exactly (including durability/NBT)
- Player has crafting permission for that recipe
- Recipe is not blacklisted

### Issue: "GUI won't open"
**Solution:**
- Verify `use-custom-gui: true` in config
- Check player has permission `fancycrafting.open`
- Look for conflicts with other GUI plugins

### Issue: "Lag with many recipes"
**Solution:**
- Enable async checking in config.yml
- Use `/fc list` to check recipe count
- Consider using categories to organize recipes

## Pro Tips 💡

1. **Recipe Naming**: Use descriptive names like "enchanted-diamond-sword" not "recipe1"
2. **Categories**: Organize recipes into categories (Weapons, Tools, Potions, etc.)
3. **Testing**: Use `/fc view` to preview recipes before giving to players
4. **Backups**: Use `/fc export` regularly to backup your recipes
5. **Permissions**: Use per-recipe permissions for special/rare crafts
6. **Quick Crafting**: Enable for VIP players to skip the crafting grid

## Next Steps

Now that you've set up FancyCrafting:

1. ✅ Create 3-5 basic recipes
2. 📝 Set up permissions for your player groups
3. 📊 Run `/fc list` to verify recipes loaded
4. 💾 Run `/fc export` to create a backup
5. 🎮 Test crafting in-game
6. 📖 Read the full [README.md](README.md) for advanced features

## Getting Help

- **Commands**: Type `/fc help` in-game
- **Documentation**: See [README.md](README.md)
- **Support**: Visit [SpigotMC resource page](https://www.spigotmc.org/resources/87300/)
- **Issues**: Report at GitHub Issues

## Video Tutorials

Check out these helpful video tutorials:
- [Quick Start Guide](https://www.youtube.com/watch?v=CH3JKhNvSzI)
- [Creating Recipes](https://www.youtube.com/watch?v=zkSA5_uTIq0)
- [Random Recipes](https://www.youtube.com/watch?v=h92XrcFPgaw)
- [Recipe Blacklisting](https://www.youtube.com/watch?v=V3avV4Ys3pk)

---

**Ready to craft? Run `/fc help` in-game to get started!** 🎮

**Questions?** Join our community or check the full documentation in [README.md](README.md)
