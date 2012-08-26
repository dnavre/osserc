package com.fictionalrealm.osserc.route;

import com.fictionalrealm.osserc.net.Connection;
import com.fictionalrealm.osserc.net.PacketListener;
import com.google.protobuf.GeneratedMessage;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 10:47 AM
 */
public class Session implements PacketListener{
    private final Connection c;


    public Session(Connection c) {
        this.c = c;
    }

    @Override
    public void processPacket(GeneratedMessage packet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
