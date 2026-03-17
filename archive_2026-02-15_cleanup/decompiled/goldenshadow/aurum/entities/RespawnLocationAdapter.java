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
import goldenshadow.aurum.other.RespawnLocation;
import java.lang.reflect.Type;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class RespawnLocationAdapter
implements JsonSerializer<RespawnLocation>,
JsonDeserializer<RespawnLocation> {
    @Override
    public RespawnLocation deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        double x = jsonObject.get("x").getAsDouble();
        double y = jsonObject.get("y").getAsDouble();
        double z = jsonObject.get("z").getAsDouble();
        String worldName = jsonObject.get("worldName").getAsString();
        int range = jsonObject.get("range").getAsInt();
        World world = Bukkit.getWorld((String)worldName);
        if (world == null) {
            throw new JsonParseException("Invalid world name: " + worldName);
        }
        return new RespawnLocation(world, x, y, z, range);
    }

    @Override
    public JsonElement serialize(RespawnLocation spawnLocation, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("x", spawnLocation.getLocation().getX());
        jsonObject.addProperty("y", spawnLocation.getLocation().getY());
        jsonObject.addProperty("z", spawnLocation.getLocation().getZ());
        jsonObject.addProperty("worldName", spawnLocation.getWorld().getName());
        jsonObject.addProperty("range", spawnLocation.getRange());
        return jsonObject;
    }
}

