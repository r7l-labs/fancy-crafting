# Contributing to FancyCrafting

Thank you for considering contributing to FancyCrafting! This document outlines the process and guidelines.

## 🌟 Ways to Contribute

### Bug Reports
- Search existing issues before creating new ones
- Include Minecraft version, server software, and plugin version
- Provide error logs and steps to reproduce
- Describe expected vs actual behavior

### Feature Requests
- Check if feature already exists or is planned
- Explain the use case and benefits
- Consider backward compatibility
- Be open to discussion and alternatives

### Code Contributions
- Bug fixes
- New features
- Performance improvements
- Documentation improvements
- Test coverage

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven 3.6+
- Git
- IDE (IntelliJ IDEA, Eclipse, or VS Code recommended)
- Basic knowledge of Bukkit/Spigot API

### Development Setup

1. **Fork the Repository**
   ```bash
   # Click "Fork" on GitHub, then clone your fork
   git clone https://github.com/YOUR-USERNAME/fancy-crafting.git
   cd fancy-crafting
   ```

2. **Set Up Upstream**
   ```bash
   git remote add upstream https://github.com/r7l-labs/fancy-crafting.git
   git fetch upstream
   ```

3. **Build the Project**
   ```bash
   cd minecraft
   mvn clean install
   ```

4. **Import to IDE**
   - IntelliJ IDEA: File → Open → Select `minecraft/pom.xml`
   - Eclipse: File → Import → Maven → Existing Maven Project

### Project Structure
```
fancy-crafting/
├── minecraft/
│   ├── src/                          # Source code
│   │   ├── de/ancash/fancycrafting/
│   │   │   ├── FancyCrafting.java   # Main plugin class
│   │   │   ├── RecipeManager.java   # Recipe management
│   │   │   ├── commands/            # Command implementations
│   │   │   ├── gui/                 # GUI implementations
│   │   │   ├── recipe/              # Recipe types
│   │   │   ├── listeners/           # Event listeners
│   │   │   └── autocrafter/         # Auto crafter system
│   │   ├── plugin.yml               # Plugin metadata
│   │   └── resources/               # Configuration files
│   ├── pom.xml                      # Maven configuration
│   └── target/                      # Build output
├── README.md                        # Main documentation
├── QUICKSTART.md                    # Quick start guide
├── CHANGELOG.md                     # Version history
└── LICENSE                          # MIT License
```

## 📝 Development Guidelines

### Code Style

**Java Conventions:**
- Follow Java naming conventions
- Use meaningful variable and method names
- Maximum line length: 120 characters
- Indentation: Tabs (existing style)
- Braces: K&R style

**Example:**
```java
public void createRecipe(ItemStack result, String name) {
    if (result == null || name == null) {
        throw new IllegalArgumentException("Result and name cannot be null");
    }
    
    // Implementation
}
```

### Best Practices

1. **Exception Handling**
   ```java
   // ❌ Bad
   try {
       // code
   } catch (Exception e) {
       e.printStackTrace();
   }
   
   // ✅ Good
   try {
       // code
   } catch (Exception e) {
       plugin.getLogger().log(Level.SEVERE, "Failed to do X", e);
   }
   ```

2. **Logging**
   ```java
   // ❌ Bad
   System.out.println("Debug: " + value);
   
   // ✅ Good
   plugin.getLogger().fine("Debug: " + value);
   ```

3. **Null Safety**
   ```java
   // ❌ Bad
   String name = recipe.getName().toLowerCase();
   
   // ✅ Good
   String name = recipe.getName();
   if (name != null) {
       name = name.toLowerCase();
   }
   ```

4. **Resource Management**
   ```java
   // ✅ Good - Use try-with-resources
   try (FileWriter writer = new FileWriter(file)) {
       writer.write(data);
   } catch (IOException e) {
       plugin.getLogger().log(Level.SEVERE, "Failed to write file", e);
   }
   ```

### Bukkit/Spigot Specific

1. **Thread Safety**
   - Use `Bukkit.getScheduler()` for sync tasks
   - Use `BukkitRunnable` for repeated tasks
   - Mark async methods clearly

2. **Event Handlers**
   ```java
   @EventHandler(priority = EventPriority.NORMAL)
   public void onCraft(PrepareItemCraftEvent event) {
       // Handle event
   }
   ```

3. **Configuration**
   - Always provide default values
   - Validate configuration on load
   - Use meaningful config paths

## 🔄 Contribution Workflow

### Creating a Feature

1. **Create a Branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

2. **Make Changes**
   - Write clean, documented code
   - Follow existing code style
   - Add comments for complex logic

3. **Test Thoroughly**
   - Test on multiple Minecraft versions
   - Check for compatibility issues
   - Verify no performance regression

4. **Commit**
   ```bash
   git add .
   git commit -m "Add feature: description of feature"
   ```
   
   **Commit Message Format:**
   - `Add: New feature`
   - `Fix: Bug description`
   - `Update: Changed functionality`
   - `Refactor: Code improvement`
   - `Docs: Documentation update`

5. **Push and Create PR**
   ```bash
   git push origin feature/your-feature-name
   ```
   Then create a Pull Request on GitHub

### Pull Request Guidelines

**PR Title Format:**
- `[Feature] Add recipe import functionality`
- `[Fix] Resolve null pointer in recipe manager`
- `[Refactor] Improve command structure`
- `[Docs] Update README with new commands`

**PR Description Should Include:**
- What changes were made
- Why the changes were necessary
- How to test the changes
- Any breaking changes
- Related issue numbers (if applicable)

**Example PR Description:**
```markdown
## Description
Adds a new `/fc import` command to import recipes from exported files.

## Changes
- Added ImportSubCommand.java
- Modified RecipeManager to support batch recipe loading
- Updated help command with new import command

## Testing
1. Export recipes with `/fc export`
2. Delete a recipe
3. Run `/fc import recipes_2024-01-01.txt`
4. Verify recipe is restored

## Breaking Changes
None

## Related Issues
Closes #123
```

## 🧪 Testing

### Manual Testing Checklist
- [ ] Plugin loads without errors
- [ ] Configuration loads correctly
- [ ] Commands work as expected
- [ ] GUI interactions function properly
- [ ] Recipes craft correctly
- [ ] No errors in console
- [ ] Performance is acceptable
- [ ] Works on multiple MC versions (if applicable)

### Test Server Setup
```bash
# Use a test server for development
1. Copy compiled jar to test server
2. Start server
3. Monitor console for errors
4. Test your changes in-game
5. Check logs for warnings
```

## 📚 Documentation

### Code Documentation
```java
/**
 * Creates a new custom recipe
 * 
 * @param result The resulting ItemStack when crafted
 * @param ingredients Array of ingredient ItemStacks
 * @param shaped Whether the recipe is shaped (true) or shapeless (false)
 * @param name Unique name identifier for the recipe
 * @throws InvalidRecipeException if recipe validation fails
 */
public void createRecipe(ItemStack result, ItemStack[] ingredients, 
                        boolean shaped, String name) throws InvalidRecipeException {
    // Implementation
}
```

### Update Documentation Files
- Update README.md for new features
- Add entries to CHANGELOG.md
- Update command reference if applicable
- Add examples for new functionality

## ❌ What NOT to Do

1. **Don't** commit compiled `.class` files or JARs
2. **Don't** include personal server configurations
3. **Don't** make massive PRs (split into smaller ones)
4. **Don't** break backward compatibility without discussion
5. **Don't** ignore existing code style
6. **Don't** add dependencies without justification
7. **Don't** hardcode values (use configuration)
8. **Don't** submit untested code

## 🐛 Bug Fix Process

1. **Reproduce the Bug**
   - Understand the issue completely
   - Create test case to reproduce

2. **Identify Root Cause**
   - Use debugging tools
   - Check related code

3. **Fix the Issue**
   - Make minimal changes
   - Don't introduce new bugs

4. **Test the Fix**
   - Verify bug is resolved
   - Test edge cases
   - Ensure no regression

5. **Submit PR**
   - Reference issue number
   - Explain fix approach

## 🎯 Feature Request Process

1. **Open an Issue First**
   - Discuss the feature before coding
   - Get feedback from maintainers
   - Ensure alignment with project goals

2. **Design the Feature**
   - Consider edge cases
   - Plan configuration options
   - Think about backward compatibility

3. **Implement**
   - Follow guidelines above
   - Write clean code
   - Add documentation

4. **Submit PR**
   - Link to discussion issue
   - Provide usage examples

## 📞 Getting Help

- **Questions**: Open a GitHub Discussion
- **Bugs**: Create a GitHub Issue
- **Security**: Email maintainers privately
- **Chat**: Join Discord (if available)

## 🏆 Recognition

Contributors will be:
- Added to CHANGELOG.md for their contributions
- Credited in plugin description (for major contributions)
- Thanked in release notes

## 📜 License

By contributing, you agree that your contributions will be licensed under the MIT License.

## 🙏 Thank You!

Every contribution, no matter how small, helps make FancyCrafting better for everyone. We appreciate your time and effort!

---

**Happy Coding!** 🚀

For questions about contributing, open an issue or discussion on GitHub.
