package com.fictionalrealm.mmoje.route;

import com.fictionalrealm.mmoje.net.Connection;
import com.fictionalrealm.mmoje.net.Packet;
import com.fictionalrealm.mmoje.net.PacketListener;

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
    public void processPacket(Packet packet) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
