package org.minecord.minecordplugin.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.minecord.minecordplugin.api.Minecord;
import org.minecord.minecordplugin.api.MinecordPlayer;

import java.util.UUID;

public class ConnectRequestEvent extends Event{

    private static final HandlerList handlers = new HandlerList();
    private String version;
    private MinecordPlayer player;

    public ConnectRequestEvent(String version, UUID uuid){
        this.version = version;
        this.player = new MinecordPlayer(uuid);
    }

    public String getVersion() {
        return version;
    }

    public MinecordPlayer getPlayer() {
        return player;
    }

    public boolean isSameVersion(){
        return version.equals(Minecord.VERSION);
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
