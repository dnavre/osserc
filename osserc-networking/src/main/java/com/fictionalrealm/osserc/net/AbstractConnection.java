package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 2:22 AM
 */
public abstract class AbstractConnection {

    public static final Logger LOGGER = LoggerFactory.getLogger("com.fictionalrealm.osserc.net.Connection");

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
