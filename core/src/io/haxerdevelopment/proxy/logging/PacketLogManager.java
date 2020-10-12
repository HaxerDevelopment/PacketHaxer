package io.haxerdevelopment.proxy.logging;

import java.util.ArrayList;

public class PacketLogManager {
    public ArrayList<LogPacket> logPackets;

    public PacketLogManager() {
        logPackets = new ArrayList<LogPacket>();
    }
}
