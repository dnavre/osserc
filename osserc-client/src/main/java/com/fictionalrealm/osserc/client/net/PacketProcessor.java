package com.fictionalrealm.osserc.client.net;

import com.google.protobuf.Message;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:59 AM
 */
public class PacketProcessor {

    private final CPacketMap packetMap;

    public PacketProcessor(CPacketMap packetMap) {
        this.packetMap = packetMap;
    }

    public void processPacket(Message m) {

    }
}
