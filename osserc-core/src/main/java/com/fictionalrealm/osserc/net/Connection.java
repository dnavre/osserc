package com.fictionalrealm.osserc.net;

import org.jboss.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 2:22 AM
 */
public class Connection {

    private final long connectionId;
    private final ChannelHandlerContext ctx;

    public Connection(long connectionId, ChannelHandlerContext ctx) {
        this.connectionId = connectionId;
        this.ctx = ctx;
    }

    public void sendData(byte[] data) {

    }

    public void addReceivedData(byte[] data) {

    }
}
