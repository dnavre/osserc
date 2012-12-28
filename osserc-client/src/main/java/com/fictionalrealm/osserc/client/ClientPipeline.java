package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.PacketDispatcher;
import com.fictionalrealm.osserc.client.net.PacketProcessor;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;

import java.util.Date;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:47 AM
 */
public class ClientPipeline extends SimpleChannelHandler {

    public ClientPipeline(PacketProcessor packetDispatcher) {
        //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        ChannelBuffer buf = (ChannelBuffer) e.getMessage();
        long currentTimeMillis = buf.readInt() * 1000L;
        System.out.println(new Date(currentTimeMillis));
        e.getChannel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        e.getCause().printStackTrace();
        e.getChannel().close();
    }
}
