package org.minecord.minecordplugin.messaging;

import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.minecord.minecordplugin.IPacket;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

public class PacketMinecordOutConnectResponse implements IPacket {

    private final int ID = 1;

    private boolean success;
    private int discriminator;

    public PacketMinecordOutConnectResponse(boolean success, int discriminator){
        this.success = success;
        this.discriminator = discriminator;
    }

    @Override
    public byte[] getMessage(){
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        buf.write(ID);

        JsonObject json = new JsonObject();
        json.addProperty("success", success);
        json.addProperty("discriminator", discriminator);
        Bukkit.broadcastMessage(json.toString());
        try {
            buf.write(json.toString().getBytes(Charset.forName("UTF-8")));
        }catch(Exception e){
            e.printStackTrace();
        }
        return buf.toByteArray();
    }

    @Override
    public void handle() {}
}
