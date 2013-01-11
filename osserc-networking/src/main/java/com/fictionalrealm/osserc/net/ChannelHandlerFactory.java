package com.fictionalrealm.osserc.net;

import org.jboss.netty.channel.ChannelHandler;

/**
 * User: Yervand.Aghababyan
 * Date: 1/11/13
 * Time: 5:20 AM
 */
public interface ChannelHandlerFactory {
    public ChannelHandler getNewHandler();
}
