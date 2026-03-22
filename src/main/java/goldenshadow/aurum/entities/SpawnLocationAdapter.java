/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.Bukkit
 *  org.bukkit.World
 */
package goldenshadow.aurum.entities;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import goldenshadow.aurum.entities.SpawnInterval;
import goldenshadow.aurum.entities.SpawnLocation;
import java.lang.reflect.Type;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class SpawnLocationAdapter
implements JsonSerializer<SpawnLocation>,
JsonDeserializer<SpawnLocation> {
    @Override
    public SpawnLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double z = jsonObject.get("z").getAsDouble();
        String worldName = jsonObject.get("worldName").getAsString();
        String entity = jsonObject.get("entity").getAsString();
        String spawnInterval = jsonObject.get("spawnInterval").getAsString();
        int respawnTolerance = jsonObject.get("respawnTolerance").getAsInt();
        World world = Bukkit.getWorld((String)worldName);
        if (world == null) {
            throw new JsonParseException("Invalid world name: " + worldName);
        }
        return new SpawnLocation(world, x, y, z, entity, this.parseInterval(spawnInterval), respawnTolerance);
    }

    @Override
    public JsonElement serialize(SpawnLocation spawnLocation, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", spawnLocation.getX());
        jsonObject.addProperty("y", spawnLocation.getY());
        jsonObject.addProperty("z", spawnLocation.getZ());
        jsonObject.addProperty("worldName", spawnLocation.getWorld().getName());
        jsonObject.addProperty("entity", spawnLocation.getEntity());
        jsonObject.addProperty("spawnInterval", spawnLocation.getSpawn_interval().toString());
        jsonObject.addProperty("respawnTolerance", spawnLocation.getRespawn_tolerance());
        return jsonObject;
    }

    private SpawnInterval parseInterval(String string) {
        switch (string) {
            case "VERY_SLOW": {
                return SpawnInterval.VERY_SLOW;
            }
            case "SLOW": {
                return SpawnInterval.SLOW;
            }
            case "FAST": {
                return SpawnInterval.FAST;
            }
        }
        return SpawnInterval.NORMAL;
    }
}

