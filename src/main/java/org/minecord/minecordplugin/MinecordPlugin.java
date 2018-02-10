package org.minecord.minecordplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.minecord.minecordplugin.api.Minecord;
import org.minecord.minecordplugin.api.MinecordPlayer;
import org.minecord.minecordplugin.messaging.PacketMinecordOutUpdatePresence;
import org.minecord.minecordplugin.api.RichPresence;
import org.minecord.minecordplugin.messaging.PacketMinecordOutConnectResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public final class MinecordPlugin extends JavaPlugin implements Minecord{

    public static MinecordPlugin INSTANCE;
    public static Logger LOGGER = Bukkit.getLogger();

    public static Map<MinecordPlayer, Integer> minecordPlayer = new HashMap<>();

    @Override
    public void onEnable() {
        INSTANCE = this;
        NetworkHandler.setupChannels();
    }

    @Override
    public void onDisable() {
        INSTANCE = null;
    }

    public static void connectMinecord(MinecordPlayer p, boolean connect) {
        if (connect) {
            p.setDiscriminator(minecordPlayer.size());
            p.setConnected(true);
            minecordPlayer.put(p, p.getDiscriminator());
            PacketMinecordOutConnectResponse response = new PacketMinecordOutConnectResponse(true, p.getDiscriminator());
            NetworkHandler.sendPacketToPlayer(response, Bukkit.getPlayer(p.getUuid()));
            LOGGER.fine("Connected player \"" + p.getName() + "\" to server.");
        } else {
            PacketMinecordOutConnectResponse response = new PacketMinecordOutConnectResponse(false, 0);
            NetworkHandler.sendPacketToPlayer(response, Bukkit.getPlayer(p.getUuid()));
            LOGGER.fine("Refused connection of player \"" + p.getName() + "\".");
        }
    }

    public static void updatePresence(MinecordPlayer p, RichPresence presence){
        PacketMinecordOutUpdatePresence packet = new PacketMinecordOutUpdatePresence(p.getDiscriminator(), presence);
        NetworkHandler.sendPacketToPlayer(packet, Bukkit.getPlayer(p.getUuid()));
        System.out.println(new String(packet.getMessage()));
        System.out.println("Updated presence of player \"" + p.getName() + "\".");
    }
}
