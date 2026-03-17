# Task-009: Migrate setCustomModelData/getCustomModelData to CustomModelDataComponent API

## Migration Pattern

### Setter: `setCustomModelData(Integer)` → `setCustomModelDataComponent(CustomModelDataComponent)`

Old:
```java
meta.setCustomModelData(Integer.valueOf(42));
```

New:
```java
CustomModelDataComponent customModelDataComponent = meta.getCustomModelDataComponent();
customModelDataComponent.setFloats(List.of(42f));
meta.setCustomModelDataComponent(customModelDataComponent);
```

### Getter: `getCustomModelData()` → `getCustomModelDataComponent().getFloats()`

Old:
```java
int cmd = item.getItemMeta().getCustomModelData();
```

New:
```java
List<Float> floats = item.getItemMeta().getCustomModelDataComponent().getFloats();
float cmd = floats.isEmpty() ? 0f : floats.get(0);
```

For comparisons like `getCustomModelData() == 2`, use:
```java
List<Float> floats = item.getItemMeta().getCustomModelDataComponent().getFloats();
(!floats.isEmpty() && floats.get(0) == 2f)
```

Or for `getCustomModelData() != 2`:
```java
List<Float> floats = item.getItemMeta().getCustomModelDataComponent().getFloats();
(floats.isEmpty() || floats.get(0) != 2f)
```

### Required Imports to ADD:
```java
import java.util.List;
import org.bukkit.inventory.meta.components.CustomModelDataComponent;
```

### DO NOT touch any other code. Only change setCustomModelData/getCustomModelData calls.

## Project root:
`/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11/`

## Source root:
`/Users/matthewcheng/Projects/Aurum copy/archive_2026-02-15_cleanup/aurum-1.21.11/src/main/java/goldenshadow/aurum/`
