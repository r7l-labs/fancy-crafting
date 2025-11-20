# FancyCrafting v1.0.0-standalone Release

## 🎉 Release Information

**Version**: 1.0.0-standalone  
**Release Date**: November 20, 2025  
**Branch**: `rewrite-standalone`  
**Tag**: `v1.0.0-standalone`

## 📦 Downloads

Download the plugin JAR from the GitHub release:
- **File**: `fancycrafting-standalone-1.0.0.jar`
- **Size**: 17KB
- **Location**: `minecraft/target/fancycrafting-standalone-1.0.0.jar`

## ✨ What's New

This is a **complete rewrite** of FancyCrafting as a standalone plugin:

### Zero Dependencies
- No external library dependencies required
- Completely self-contained plugin
- Only requires Spigot API (provided by server)

### Custom Crafting System
- Beautiful custom crafting GUI with glass pane borders
- Support for variable grid sizes (3x3, 4x4, 5x5, 6x6)
- Arrow indicator showing craft result location
- Automatic ingredient consumption on craft
- Item return when closing GUI

### Vanilla Crafting Blocker
- Completely disables vanilla crafting tables
- Blocks workbench inventory opens
- Blocks player crafting inventory (2x2 grid)
- Redirects all crafting to custom GUI

### Simple & Lightweight
- Only 17KB plugin size
- No bloated dependencies
- Fast load time
- Minimal server impact

### Easy to Use
- Simple `/craft` command with aliases
- Permission-based access control
- Comprehensive configuration file
- Color-coded messages

## 🎯 Key Features

### Commands
```
/craft [size]    - Open custom crafting GUI (default 3x3)
/crafting [size] - Alias for /craft
/c [size]        - Short alias for /craft
```

### Permissions
```
fancycrafting.use   - Allow using /craft command (default: true)
fancycrafting.admin - Allow config reload (default: op)
```

### Configuration
```yaml
disable-vanilla-crafting: true
enable-custom-gui: true
default-grid-size: 3
min-grid-size: 3
max-grid-size: 6
```

## 📋 Installation Instructions

1. Download `fancycrafting-standalone-1.0.0.jar` from this release
2. Place in your server's `plugins/` folder
3. Restart your server
4. Configure in `plugins/FancyCrafting/config.yml` if needed
5. Use `/craft` to open the custom crafting GUI!

## 🔧 Technical Details

### Architecture
- **Package**: `com.r7llabs.fancycrafting`
- **Main Class**: `FancyCraftingPlugin`
- **API Version**: Spigot 1.19.2-R0.1-SNAPSHOT
- **Java Version**: Java 8+

### Components
- **FancyCraftingPlugin**: Main plugin class
- **CraftingGUIManager**: Manages all active GUIs
- **CraftingGUI**: Custom inventory implementation
- **CraftingGUIListener**: Handles GUI interactions
- **CraftingBlockerListener**: Blocks vanilla crafting
- **WorkbenchListener**: Intercepts workbench opens

### Build Information
- **Maven Version**: 3.x
- **Compiler**: Java 8 (source & target)
- **Shade Plugin**: 3.4.1
- **Build Command**: `mvn -f pom-standalone.xml clean package`

## 📚 Documentation

- [README-STANDALONE.md](../README-STANDALONE.md) - Complete documentation
- [README.md](../README.md) - Project overview
- [QUICKSTART.md](../QUICKSTART.md) - Quick start guide
- [FEATURES.md](../FEATURES.md) - Feature list
- [CHANGELOG.md](../CHANGELOG.md) - Version history

## 🐛 Known Issues

None currently known. Please report any bugs on GitHub Issues.

## 🔮 Future Plans

- Recipe system implementation
- Recipe configuration files
- More GUI customization options
- Bukkit/Minecraft version compatibility expansion
- Additional grid size support
- Crafting animations

## 🙏 Credits

- Original FancyCrafting concept
- Rewritten by R7L Labs
- Built with Spigot API

## 📞 Support

- **GitHub Issues**: https://github.com/r7l-labs/fancy-crafting/issues
- **GitHub Discussions**: https://github.com/r7l-labs/fancy-crafting/discussions

## 📄 License

MIT License - see [LICENSE](../LICENSE) for details

---

**Full Changelog**: https://github.com/r7l-labs/fancy-crafting/compare/INTERIM-DEV-1.0.0...v1.0.0-standalone
