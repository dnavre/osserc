package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.net.Packet;

/**
 * User: dnavre
 * Date: 8/11/12
 * Time: 7:47 AM
 */
public interface PacketListener {
    public void processPacket(Packet packet);
}
