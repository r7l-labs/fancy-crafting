# FancyCrafting Standalone

A standalone Minecraft/Spigot plugin that provides a custom crafting GUI system with variable grid sizes (3x3 to 6x6) and completely replaces vanilla crafting tables.

## Features

- **Custom Crafting GUI**: Beautiful crafting interface with customizable grid sizes
- **Vanilla Crafting Disabled**: Completely blocks default crafting table usage
- **Variable Grid Sizes**: Support for 3x3, 4x4, 5x5, and 6x6 crafting grids
- **Zero External Dependencies**: Completely standalone - no external library dependencies
- **Lightweight**: Only 17KB plugin size
- **Simple Commands**: Easy-to-use `/craft` command with aliases
- **Configurable**: Extensive configuration options via `config.yml`

## Installation

1. Download `fancycrafting-standalone-1.0.0.jar` from the [Releases](../../releases) page
2. Place the JAR file in your server's `plugins/` folder
3. Restart your server
4. Configure the plugin by editing `plugins/FancyCrafting/config.yml`

## Requirements

- **Server Software**: Spigot, Paper, or compatible fork
- **Minecraft Version**: 1.19.2 or compatible
- **Java Version**: Java 8 or higher

## Commands

| Command | Aliases | Description | Permission |
|---------|---------|-------------|------------|
| `/craft [size]` | `/crafting`, `/c` | Open a custom crafting GUI | `fancycrafting.use` |

### Command Examples

```
/craft          # Opens default size (3x3) crafting GUI
/craft 4        # Opens 4x4 crafting GUI
/craft 6        # Opens 6x6 crafting GUI
```

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `fancycrafting.use` | Allows player to use `/craft` command | true |
| `fancycrafting.admin` | Allows player to reload config | op |

## Configuration

The plugin creates a `config.yml` file in `plugins/FancyCrafting/`:

```yaml
# Disable vanilla crafting tables
disable-vanilla-crafting: true

# Enable custom crafting GUI
enable-custom-gui: true

# Default grid size (3-6)
default-grid-size: 3

# Minimum grid size
min-grid-size: 3

# Maximum grid size
max-grid-size: 6

messages:
  no-permission: "&cYou don't have permission to use this command!"
  invalid-size: "&cInvalid grid size! Use a number between %min% and %max%."
  gui-opened: "&aOpened crafting GUI!"
  crafted: "&aItem crafted!"
  config-reloaded: "&aConfiguration reloaded!"
```

## Building from Source

### Prerequisites

- Java 8 or higher
- Maven 3.x
- Git

### Build Steps

```bash
git clone https://github.com/r7l-labs/fancy-crafting.git
cd fancy-crafting/minecraft
mvn -f pom-standalone.xml clean package
```

The compiled JAR will be in `minecraft/target/fancycrafting-standalone-1.0.0.jar`

## Development

### Project Structure

```
minecraft/
├── src-standalone/
│   └── main/
│       ├── java/com/r7llabs/fancycrafting/
│       │   ├── FancyCraftingPlugin.java       # Main plugin class
│       │   ├── gui/
│       │   │   ├── CraftingGUIManager.java    # GUI manager
│       │   │   ├── CraftingGUI.java           # Custom GUI implementation
│       │   │   └── CraftingGUIListener.java   # GUI event handlers
│       │   └── listeners/
│       │       ├── CraftingBlockerListener.java # Blocks vanilla crafting
│       │       └── WorkbenchListener.java      # Intercepts workbench opens
│       └── resources/
│           ├── plugin.yml                      # Plugin metadata
│           └── config.yml                      # Default configuration
└── pom-standalone.xml                          # Maven build configuration
```

### Key Classes

- **FancyCraftingPlugin**: Main plugin class, handles initialization and commands
- **CraftingGUIManager**: Manages all active crafting GUIs
- **CraftingGUI**: Custom inventory implementation for crafting interface
- **CraftingGUIListener**: Handles inventory interactions
- **CraftingBlockerListener**: Blocks vanilla crafting table usage
- **WorkbenchListener**: Intercepts workbench opens to show custom GUI

## Differences from Original

This standalone version differs from the original FancyCrafting plugin:

- **No External Dependencies**: All functionality is self-contained
- **Simplified Architecture**: Removed dependency on ILibrary and NBTNexus
- **Lighter Weight**: Smaller JAR size (17KB vs several MB)
- **Easier Installation**: No need to download multiple dependencies
- **Modern Package Structure**: Uses `com.r7llabs.fancycrafting` package

## Changelog

See [CHANGELOG.md](../../CHANGELOG.md) for version history.

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](../../CONTRIBUTING.md) for guidelines.

## License

This project is licensed under the MIT License - see [LICENSE](../../LICENSE) for details.

## Support

- **Issues**: Report bugs or request features on [GitHub Issues](../../issues)
- **Discussions**: Ask questions on [GitHub Discussions](../../discussions)

## Credits

- Original concept and codebase foundation
- Rewritten as standalone plugin for R7L Labs
- Built with [Spigot API](https://hub.spigotmc.org/javadocs/spigot/)

## Links

- **GitHub Repository**: https://github.com/r7l-labs/fancy-crafting
- **Releases**: https://github.com/r7l-labs/fancy-crafting/releases
- **Spigot API**: https://hub.spigotmc.org/javadocs/spigot/
