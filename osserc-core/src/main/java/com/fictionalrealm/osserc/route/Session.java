package com.fictionalrealm.osserc.route;

import com.fictionalrealm.osserc.net.PacketListener;
import com.google.protobuf.GeneratedMessage;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 10:47 AM
 */
public interface Session extends PacketListener {

    @Override
    public void processPacket(GeneratedMessage packet);
}
