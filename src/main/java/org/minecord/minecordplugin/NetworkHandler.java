package org.minecord.minecordplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.minecord.minecordplugin.messaging.MessageListener;
import org.minecord.minecordplugin.messaging.PacketMinecordOutConnectResponse;
import org.minecord.minecordplugin.messaging.PacketMinecordOutUpdatePresence;

public final class NetworkHandler{

    private static final String CHANNEL_INIT = "MINECORD|INIT";
    private static final String CHANNEL_RP = "MINECORD|RP";
    private static final String CHANNEL_EVENT = "MINECORD|EVENT";

    public static void sendPacketToPlayer(IPacket packet, Player p){
        if(packet instanceof PacketMinecordOutConnectResponse){
            p.sendPluginMessage(MinecordPlugin.INSTANCE, CHANNEL_INIT, packet.getMessage());
        }else if(packet instanceof PacketMinecordOutUpdatePresence){
            p.sendPluginMessage(MinecordPlugin.INSTANCE, CHANNEL_RP, packet.getMessage());
        }
    }

    public static void setupChannels(){
        Bukkit.getMessenger().registerIncomingPluginChannel(MinecordPlugin.INSTANCE, CHANNEL_INIT, new MessageListener());
        Bukkit.getMessenger().registerIncomingPluginChannel(MinecordPlugin.INSTANCE, CHANNEL_EVENT, new MessageListener());

        Bukkit.getMessenger().registerOutgoingPluginChannel(MinecordPlugin.INSTANCE, CHANNEL_INIT);
        Bukkit.getMessenger().registerOutgoingPluginChannel(MinecordPlugin.INSTANCE, CHANNEL_RP);
    }
}
