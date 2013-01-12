package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.PacketProcessor;
import com.fictionalrealm.osserc.net.AbstractConnection;
import com.google.protobuf.Message;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;

import java.util.Date;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:47 AM
 */
public class ClientMsgHandler extends SimpleChannelHandler {

    private final Logger logger = AbstractConnection.LOGGER;

    private final PacketProcessor packetProcessor;

    public ClientMsgHandler(PacketProcessor packetProcessor) {
        this.packetProcessor = packetProcessor;
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        ctx.sendUpstream(e);
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Message msg = (Message) e.getMessage();
        packetProcessor.processPacket(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        logger.error("Unknown error occurred", e.getCause());
        e.getChannel().close();
    }
}
