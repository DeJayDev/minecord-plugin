package org.minecord.minecordplugin;

public interface IPacket {
    byte[] getMessage();
    void handle();
}
