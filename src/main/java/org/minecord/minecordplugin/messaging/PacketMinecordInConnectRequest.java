package org.minecord.minecordplugin.messaging;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.bukkit.Bukkit;
import org.minecord.minecordplugin.IPacket;
import org.minecord.minecordplugin.api.events.ConnectRequestEvent;

import java.util.UUID;

public class PacketMinecordInConnectRequest implements IPacket {

    private String version ;
    private UUID uuid;

    public PacketMinecordInConnectRequest(String message){
        JsonObject json = new JsonParser().parse(message).getAsJsonObject();
        version = json.get("version").getAsString();
        uuid = UUID.fromString(json.get("uuid").getAsString());
    }

    @Override
    public byte[] getMessage() {return null;}

    @Override
    public void handle(){
        ConnectRequestEvent event = new ConnectRequestEvent(version, uuid);
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
