package com.fictionalrealm.osserc.net.lsnr;

import com.fictionalrealm.osserc.net.ConnectionMap;
import com.fictionalrealm.osserc.protocol.datatypes.DisconnectionReason;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * User: Yervand.Aghababyan
 * Date: 8/18/12
 * Time: 8:22 PM
 */
public class ConnectionHandler extends SimpleChannelUpstreamHandler {

    private final Logger logger = LoggerFactory.getLogger("listener");

    public final ConnectionMap connectionMap;

    @Inject
    public ConnectionHandler(ConnectionMap connectionMap) {
        this.connectionMap = connectionMap;
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        logger.info("Message received:" + e.getMessage().toString());
        ctx.sendUpstream(e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        logger.info("Client " + ctx.getChannel().getRemoteAddress() + " connected");

        connectionMap.addConnection(ctx);

        ctx.sendUpstream(e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        logger.info("Client " + ctx.getChannel().getRemoteAddress() + " disconnected");
        connectionMap.getConnection((Long)ctx.getAttachment()).disconnectConnection(DisconnectionReason.CONNECTION_ERROR);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) throws Exception {
        logger.info("Error in connection " + ctx.getChannel().getRemoteAddress() + " - " + e.getCause() != null ? e.getCause().getMessage() : "");
    }
}
