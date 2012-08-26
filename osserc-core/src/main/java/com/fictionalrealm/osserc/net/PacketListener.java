package com.fictionalrealm.osserc.net;

import com.google.protobuf.GeneratedMessage;

/**
 * User: dnavre
 * Date: 8/11/12
 * Time: 7:47 AM
 */
public interface PacketListener {
    public void processPacket(GeneratedMessage packet);
}
