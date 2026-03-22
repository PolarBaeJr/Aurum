package goldenshadow.aurum.entities;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Gson TypeAdapters for Bukkit Registry-backed types that changed from
 * plain enums (serialized as strings) to Registry types (serialized as
 * objects with ordinal/name/holder) in Paper 1.21.x.
 * These adapters handle both the old string format and new object format.
 */
public class RegistryTypeAdapters {

    private static final Map<String, String> ENTITY_TYPE_RENAMES = new HashMap<>();

    static {
        ENTITY_TYPE_RENAMES.put("MUSHROOM_COW", "MOOSHROOM");
        ENTITY_TYPE_RENAMES.put("SNOWMAN", "SNOW_GOLEM");
    }

    /**
     * Creates a TypeAdapter for a Registry-backed Keyed type that can
     * deserialize from either a plain string or an object with a "name" field.
     */
    public static <T extends Keyed> TypeAdapter<T> forRegistry(Registry<T> registry) {
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value.getKey().getKey().toUpperCase());
            }

            @Override
            public T read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }

                String name;
                if (in.peek() == JsonToken.STRING) {
                    name = in.nextString();
                } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    name = null;
                    in.beginObject();
                    while (in.hasNext()) {
                        String key = in.nextName();
                        if (key.equals("name")) {
                            name = in.nextString();
                        } else {
                            in.skipValue();
                        }
                    }
                    in.endObject();
                } else {
                    in.skipValue();
                    return null;
                }

                if (name == null) return null;
                return registry.get(NamespacedKey.minecraft(name.toLowerCase()));
            }
        };
    }

    /**
     * TypeAdapter for EntityType that handles renamed entity types
     * (e.g., MUSHROOM_COW -> MOOSHROOM).
     */
    public static TypeAdapter<EntityType> entityTypeAdapter() {
        return new TypeAdapter<EntityType>() {
            @Override
            public void write(JsonWriter out, EntityType value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }
                out.value(value.name());
            }

            @Override
            public EntityType read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                String name = in.nextString();
                String renamed = ENTITY_TYPE_RENAMES.get(name);
                if (renamed != null) {
                    name = renamed;
                }
                try {
                    return EntityType.valueOf(name);
                } catch (IllegalArgumentException e) {
                    return null;
                }
            }
        };
    }

    /**
     * Register all necessary adapters on a GsonBuilder for loading EntityData.
     */
    public static GsonBuilder registerAll(GsonBuilder builder) {
        return builder
                .registerTypeAdapter(Frog.Variant.class, forRegistry(Registry.FROG_VARIANT))
                .registerTypeAdapter(Villager.Type.class, forRegistry(Registry.VILLAGER_TYPE))
                .registerTypeAdapter(Villager.Profession.class, forRegistry(Registry.VILLAGER_PROFESSION))
                .registerTypeAdapter(EntityType.class, entityTypeAdapter());
    }
}
