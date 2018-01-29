package org.minecord.minecordplugin.messaging;

import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.nio.charset.Charset;

public class MessageListener implements PluginMessageListener{

    @Override
    public void onPluginMessageReceived(String s, Player player, byte[] bytes) {
        int identifier = (int)bytes[0];
        String message = new String(bytes, Charset.forName("UTF-8")).substring(1);

        switch(identifier){
            case 0:
                new PacketMinecordInConnectRequest(message).handle();
                break;
        }

    }
}
