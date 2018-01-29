package org.minecord.minecordplugin.messaging;

import com.google.gson.JsonObject;
import org.minecord.minecordplugin.IPacket;
import org.minecord.minecordplugin.api.RichPresence;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class PacketMinecordOutUpdatePresence implements IPacket {

    private int discriminator;
    private RichPresence presence;

    public PacketMinecordOutUpdatePresence(int discriminator, RichPresence presence){
        this.discriminator = discriminator;
        this.presence = presence;
    }

    @Override
    public byte[] getMessage() {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bytes.write(discriminator);
        JsonObject json = new JsonObject();
        if(presence.state != null)
            json.addProperty("state", presence.state);
        if(presence.details != null)
            json.addProperty("details", presence.details);

        JsonObject timestamps = new JsonObject();
        if(presence.startTimestamp != 0)
            timestamps.addProperty("start", presence.startTimestamp);
        if(presence.endTimestamp != 0)
            timestamps.addProperty("end", presence.endTimestamp);
        if(timestamps.has("start") || timestamps.has("end"))
            json.add("timestamps", timestamps);

        JsonObject images = new JsonObject();
        JsonObject small = new JsonObject();
        JsonObject large = new JsonObject();
        if(presence.smallImageKey != null)
            small.addProperty("key", presence.smallImageKey);
        if(presence.smallImageText != null)
            small.addProperty("text", presence.smallImageText);
        if(presence.largeImageKey != null)
            large.addProperty("key", presence.largeImageKey);
        if(presence.largeImageText != null)
            large.addProperty("text", presence.largeImageText);
        if(small.has("key") || small.has("text"))
            images.add("small", small);
        if(large.has("key") || large.has("text"))
            images.add("large", large);
        if(images.has("small") || images.has("images"))
            json.add("images", images);

        JsonObject party = new JsonObject();
        if(presence.partyId != null)
            party.addProperty("id", presence.partyId);
        if(presence.partySize != 0)
            party.addProperty("size", presence.partySize);
        if(presence.partyMax != 0)
            party.addProperty("max", presence.partyMax);
        if(party.has("id") || party.has("size") || party.has("max"))
            json.add("party", party);

        JsonObject secrets = new JsonObject();
        if(presence.matchSecret != null)
            secrets.addProperty("match", presence.matchSecret);
        if(presence.spectateSecret != null)
            secrets.addProperty("spectate", presence.spectateSecret);
        if(presence.joinSecret != null)
            secrets.addProperty("join", presence.joinSecret);
        if(secrets.has("match") || secrets.has("spectate") || secrets.has("join"))
            json.add("secrets", secrets);

        if(presence.instance != 0)
            json.addProperty("instance", presence.instance);
        try{
            bytes.write(json.toString().getBytes(Charset.forName("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
        }

        return bytes.toByteArray();
    }

    @Override
    public void handle() {}
}
