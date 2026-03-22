/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Keyed
 *  org.bukkit.NamespacedKey
 *  org.bukkit.Registry
 *  org.bukkit.entity.EntityType
 *  org.bukkit.entity.Frog$Variant
 *  org.bukkit.entity.Villager$Profession
 *  org.bukkit.entity.Villager$Type
 */
package goldenshadow.aurum.entities;

import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Villager;

public class RegistryTypeAdapters {
    private static final Map<String, String> ENTITY_TYPE_RENAMES = new HashMap<String, String>();

    public static <T extends Keyed> TypeAdapter<T> forRegistry(final Registry<T> registry) {
        return new TypeAdapter<T>(){

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
                String name;
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                if (in.peek() == JsonToken.STRING) {
                    name = in.nextString();
                } else if (in.peek() == JsonToken.BEGIN_OBJECT) {
                    name = null;
                    in.beginObject();
                    while (in.hasNext()) {
                        String key = in.nextName();
                        if (key.equals("name")) {
                            name = in.nextString();
                            continue;
                        }
                        in.skipValue();
                    }
                    in.endObject();
                } else {
                    in.skipValue();
                    return null;
                }
                if (name == null) {
                    return null;
                }
                return registry.get(NamespacedKey.minecraft((String)name.toLowerCase()));
            }
        };
    }

    public static TypeAdapter<EntityType> entityTypeAdapter() {
        return new TypeAdapter<EntityType>(){

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
                    return EntityType.valueOf((String)name);
                }
                catch (IllegalArgumentException e) {
                    return null;
                }
            }
        };
    }

    public static GsonBuilder registerAll(GsonBuilder builder) {
        return builder.registerTypeAdapter((Type)((Object)Frog.Variant.class), RegistryTypeAdapters.forRegistry(Registry.FROG_VARIANT)).registerTypeAdapter((Type)((Object)Villager.Type.class), RegistryTypeAdapters.forRegistry(Registry.VILLAGER_TYPE)).registerTypeAdapter((Type)((Object)Villager.Profession.class), RegistryTypeAdapters.forRegistry(Registry.VILLAGER_PROFESSION)).registerTypeAdapter((Type)((Object)EntityType.class), RegistryTypeAdapters.entityTypeAdapter());
    }

    static {
        ENTITY_TYPE_RENAMES.put("MUSHROOM_COW", "MOOSHROOM");
        ENTITY_TYPE_RENAMES.put("SNOWMAN", "SNOW_GOLEM");
    }
}

