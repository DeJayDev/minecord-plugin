package org.minecord.minecordplugin.api;


import org.minecord.minecordplugin.MinecordPlugin;

public final class Minecord {

    public static final String VERSION = "0.1-ALPHA.0";
    private static final MinecordPlugin INSTANCE = MinecordPlugin.INSTANCE;

    public static void connectMinecord(MinecordPlayer p, boolean connect) {
        INSTANCE.connectMinecord(p, connect);
    }
    public static void updatePresence(MinecordPlayer p, RichPresence presence) {
        INSTANCE.updatePresence(p, presence);
    }
}
