package org.minecord.minecordplugin.api;

public interface Minecord {

    String VERSION = "0.1-ALPHA.0";

    static void connectMinecord(MinecordPlayer p, boolean connect) {}
    static void updatePresence(MinecordPlayer p, RichPresence presence) {}
}
