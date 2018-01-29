package org.minecord.minecordplugin.api;

import org.bukkit.Bukkit;

import java.util.UUID;

public class MinecordPlayer {

    private int discriminator = 0;
    private UUID uuid;
    private String name;
    private boolean connected = false;

    public MinecordPlayer(UUID uuid){
        this.uuid = uuid;
        name = Bukkit.getOfflinePlayer(uuid).getName();
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public boolean isConnected(){
        return connected;
    }

    public UUID getUuid(){
        return uuid;
    }

    public int getDiscriminator() {
        return discriminator;
    }

    public void setDiscriminator(int discriminator){
        this.discriminator = discriminator;
    }

    public String getName() {
        return name;
    }
}
