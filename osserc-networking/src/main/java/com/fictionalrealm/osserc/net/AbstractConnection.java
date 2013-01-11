package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 2:22 AM
 */
public abstract class AbstractConnection {

    protected final Channel channel;

    public AbstractConnection(Channel channel) {
        this.channel = channel;
    }

    public void write(Message m) {



        if(channel.isWritable())
        channel.write(m);
    }

    public boolean isConnected() {
        return false;
    }
}
