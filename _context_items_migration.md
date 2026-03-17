# ChatColor to Adventure Component Migration - Items Package

## Task
Migrate all `org.bukkit.ChatColor` usage and legacy string-based API calls to the Adventure Component API in the `items/` package of the Aurum plugin.

## Source Root
`/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11/src/main/java/goldenshadow/aurum/items/`

## Migration Patterns

### Imports to add:
```java
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
```

### Imports to remove:
```java
import org.bukkit.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.BaseComponent;
```

### Key conversions:

1. **ChatColor to NamedTextColor mapping:**
   - `ChatColor.RED` -> `NamedTextColor.RED`
   - `ChatColor.GREEN` -> `NamedTextColor.GREEN`
   - `ChatColor.GOLD` -> `NamedTextColor.GOLD`
   - `ChatColor.YELLOW` -> `NamedTextColor.YELLOW`
   - `ChatColor.GRAY` -> `NamedTextColor.GRAY`
   - `ChatColor.WHITE` -> `NamedTextColor.WHITE`
   - `ChatColor.AQUA` -> `NamedTextColor.AQUA`
   - `ChatColor.DARK_AQUA` -> `NamedTextColor.DARK_AQUA`
   - `ChatColor.DARK_GRAY` -> `NamedTextColor.DARK_GRAY`
   - `ChatColor.DARK_RED` -> `NamedTextColor.DARK_RED`
   - `ChatColor.LIGHT_PURPLE` -> `NamedTextColor.LIGHT_PURPLE`
   - `ChatColor.DARK_PURPLE` -> `NamedTextColor.DARK_PURPLE`

2. **ChatColor.BOLD** -> `TextDecoration.BOLD` (used as `.decorate(TextDecoration.BOLD)`)

3. **player.sendMessage(ChatColor.X + "text")** -> `player.sendMessage(Component.text("text", NamedTextColor.X))`

4. **player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(...))** -> `player.sendActionBar(Component.text(...))`

5. **meta.setDisplayName(ChatColor.X + "text")** -> `meta.displayName(Component.text("text", NamedTextColor.X))`
   - For bold display names: `meta.displayName(Component.text("text", NamedTextColor.X).decorate(TextDecoration.BOLD))`

6. **meta.setLore(List<String>)** -> `meta.lore(List<Component>)`
   - CRITICAL: The lore lists throughout the codebase use `List<String>`. When migrating, change the type to `List<Component>`.
   - `lore.add(ChatColor.RED + "text")` -> `lore.add(Component.text("text", NamedTextColor.RED))`
   - `lore.add(" ")` -> `lore.add(Component.empty())`
   - `lore.add("")` -> `lore.add(Component.empty())`

7. **meta.getDisplayName()** -> `meta.displayName()` (returns Component)
   - If you need a plain string: `PlainTextComponentSerializer.plainText().serialize(meta.displayName())`
   - Add `import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;`

8. **meta.getLore()** -> `meta.lore()` (returns List<Component>)

9. **Bukkit.createInventory(holder, size, String)** -> `Bukkit.createInventory(holder, size, Component.text(...))`

10. **ChatColor.stripColor(string)** -> `PlainTextComponentSerializer.plainText().serialize(LegacyComponentSerializer.legacySection().deserialize(string))`
    - Or if you already have a Component: `PlainTextComponentSerializer.plainText().serialize(component)`

11. **For concatenated colored strings like `ChatColor.RED + value + ChatColor.GRAY + " text"`:**
    ```java
    Component.text(value, NamedTextColor.RED).append(Component.text(" text", NamedTextColor.GRAY))
    ```

## Important Notes

### Rarity.java Changes
The `Rarity` enum currently stores `name` as a legacy ChatColor-prefixed string. It should be changed to store a `Component`:
```java
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public enum Rarity {
    COMMON(Component.text("Common Item", NamedTextColor.DARK_AQUA)),
    RARE(Component.text("Rare Item", NamedTextColor.AQUA)),
    EPIC(Component.text("Epic Item", NamedTextColor.LIGHT_PURPLE)),
    LEGENDARY(Component.text("Legendary Item", NamedTextColor.RED)),
    ARTIFACT(Component.text("Artifact", NamedTextColor.GOLD)),
    ELDRITCH(Component.text("Eldritch Artifact", NamedTextColor.GREEN));

    private final Component name;

    private Rarity(Component name) { this.name = name; }

    public Component getName() { return this.name; }

    public static NamedTextColor getTextColor(Rarity rarity) {
        return switch (rarity) {
            case COMMON -> NamedTextColor.DARK_AQUA;
            case RARE -> NamedTextColor.AQUA;
            case EPIC -> NamedTextColor.LIGHT_PURPLE;
            case LEGENDARY -> NamedTextColor.RED;
            case ARTIFACT -> NamedTextColor.GOLD;
            case ELDRITCH -> NamedTextColor.GREEN;
        };
    }
}
```

### Lore List Migration
When methods accept or return `List<String>` for lore, change to `List<Component>`. Key methods affected:
- `ItemCreationHelper.addAttribute(ItemMeta, int, AttributeID, List<String>, ItemType)` -> change to `List<Component>`
- `ItemCreationHelper.insertAttribute(ItemMeta, int, AttributeID, List<String>, Material)` -> change to `List<Component>`
- `ItemCreationHelper.addRuneAbility(ItemMeta, Rune, List<String>, boolean)` -> change to `List<Component>`
- `ItemCreationHelper.addConsumableEffect(ItemMeta, List<String>, String, String, int, int)` -> change to `List<Component>`
- Various places where `meta.getLore()` is assigned to a `List<String>` variable

### getRarity method in ItemCreationHelper
The `getRarity(String s)` method compares the lore string with `Rarity.*.getName()`. After migration, since lore entries will be Component objects, this comparison needs to use Component comparison or serialize to plain text.

### Compilation
After making changes, verify compilation with:
```
cd "/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11" && mvn clean compile -q
```
