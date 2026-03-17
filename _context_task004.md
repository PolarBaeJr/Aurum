# Task 004: Migrate ChatColor in command/ package to Adventure Component API

## Overview
Replace all `org.bukkit.ChatColor` usage and `sendMessage(String)` calls with Adventure `Component` API in the command/ package.

## Files
- `/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11/src/main/java/goldenshadow/aurum/command/Commands.java`
- `/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11/src/main/java/goldenshadow/aurum/command/CommandHelper.java`

## Migration Rules (from architectural decisions)

1. **sendMessage patterns:**
   - `player.sendMessage(ChatColor.GOLD + "[Aurum] " + ChatColor.YELLOW + "message")` → `player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("message", NamedTextColor.YELLOW)))`
   - `player.sendMessage(ChatColor.RED + "[Aurum] Error: message")` → `player.sendMessage(Component.text("[Aurum] Error: message", NamedTextColor.RED))`
   - `sender.sendMessage("plain text")` → `sender.sendMessage(Component.text("plain text"))`
   - `player.sendMessage(ChatColor.DARK_AQUA + "[Aurum] text")` → `player.sendMessage(Component.text("[Aurum] text", NamedTextColor.DARK_AQUA))`
   - `player.sendMessage(ChatColor.GRAY + "text")` → `player.sendMessage(Component.text("text", NamedTextColor.GRAY))`

2. **For complex multi-color messages with dynamic content:**
   - `player.sendMessage(ChatColor.GOLD + "[Aurum] " + ChatColor.YELLOW + "Gave " + player.getName() + " gold coin!")` →
     `player.sendMessage(Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("Gave " + player.getName() + " gold coin!", NamedTextColor.YELLOW)))`

3. **For messages with 3+ color segments:**
   - `ChatColor.GOLD + "[Aurum] " + ChatColor.YELLOW + "text " + ChatColor.GRAY + "more text"` →
     `Component.text("[Aurum] ", NamedTextColor.GOLD).append(Component.text("text ", NamedTextColor.YELLOW)).append(Component.text("more text", NamedTextColor.GRAY))`

4. **meta.setDisplayName(ChatColor.X + String.valueOf(ChatColor.BOLD) + text)** →
   `meta.displayName(Component.text(text, NamedTextColor.X, TextDecoration.BOLD))`

5. **meta.setDisplayName(text)** → `meta.displayName(Component.text(text))`

6. **meta.getDisplayName()** → `PlainTextComponentSerializer.plainText().serialize(meta.displayName())` when you need a String

7. **ChatColor.stripColor(string)** → `PlainTextComponentSerializer.plainText().serialize(meta.displayName())` (when working with Component) or use `PlainTextComponentSerializer` on the relevant component

8. **ChatColor.translateAlternateColorCodes('&', string)** → `LegacyComponentSerializer.legacyAmpersand().deserialize(string)` — this returns a Component directly, which can be used with `meta.displayName(component)`

9. **meta.setLore(List<String>)** → `meta.lore(List<Component>)`
   - Each lore string `ChatColor.X + "text"` becomes `Component.text("text", NamedTextColor.X)`
   - Multi-color lore: `ChatColor.GREEN + "+20% " + ChatColor.GRAY + "Fish Bait"` → `Component.text("+20% ", NamedTextColor.GREEN).append(Component.text("Fish Bait", NamedTextColor.GRAY))`

10. **meta.getLore()** → `meta.lore()` returns `List<Component>`
    - When checking lore content with `.contains("text")`, use `PlainTextComponentSerializer.plainText().serialize(component).contains("text")`

11. **Lore manipulation (get/set/add on List)**:
    - The lore is now `List<Component>` instead of `List<String>`
    - `lore.add("text")` → `lore.add(Component.text("text"))`
    - `lore.set(i, ChatColor.X + "text")` → `lore.set(i, Component.text("text", NamedTextColor.X))`
    - `((String)lore.get(i)).contains("text")` → `PlainTextComponentSerializer.plainText().serialize(lore.get(i)).contains("text")`

12. **For the name regex pattern (set_consumable_uses)**:
    - `meta.getDisplayName()` → serialize the displayName Component to legacy, do regex, then deserialize back OR use PlainTextComponentSerializer + rebuild Component

## Required Imports (add to both files)
```java
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
```

## Remove Imports
```java
import org.bukkit.ChatColor;  // REMOVE from both files
```

## Special Cases in CommandHelper.java

### editItemSubCommand (lines 202-520)
- `List<String> lore = meta.getLore()` → `List<Component> lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : null`
- All `lore.add(...)` / `lore.set(...)` / `lore.get(...)` need Component types
- When checking `lore.get(i).contains("text")`, use `PlainTextComponentSerializer.plainText().serialize(lore.get(i)).contains("text")`
- `meta.setLore(lore)` → `meta.lore(lore)`
- Line 371: `ChatColor.stripColor(meta.getDisplayName())` → `PlainTextComponentSerializer.plainText().serialize(meta.displayName())`
- Line 392: `ChatColor.stripColor(meta.getDisplayName())` → same as above
- Line 416: `ChatColor.translateAlternateColorCodes('&', name.toString())` → `LegacyComponentSerializer.legacyAmpersand().deserialize(name.toString())` used as `meta.displayName(result)`
- Line 455: `ChatColor.translateAlternateColorCodes('&', name.toString())` → same, but added as lore line
- Line 477: same pattern for set lore line
- Line 504-508: `meta.getDisplayName()` + regex + `meta.setDisplayName()` — use `LegacyComponentSerializer.legacySection().serialize(meta.displayName())` for the string manipulation, then `LegacyComponentSerializer.legacySection().deserialize(result)` to convert back

### Lore Content Checks
Many places check: `((String)lore.get(lore.size() - 1)).contains("Common")` etc.
These become: `PlainTextComponentSerializer.plainText().serialize(lore.get(lore.size() - 1)).contains("Common")`

### The " " empty lore lines
`lore.add(" ")` → `lore.add(Component.text(" "))`
`lore.add("")` → `lore.add(Component.empty())`

## IMPORTANT: Leave the `meta.getLore()` call as-is initially in editItemSubCommand
Actually, since the codebase is transitioning AND other files may still use String lore, we need to be consistent. Since task-003 (items package) is being done in parallel, CommandHelper.java's lore manipulation interacts with items created by ItemFactory/ItemCreationHelper.

Since task-003 will also migrate setLore/getLore in items/, we should migrate CommandHelper to use Component lore too, to stay consistent.
