package com.fictionalrealm.osserc.net.lsnr;

import com.fictionalrealm.osserc.protocol.datatypes.ServerStatus;
import com.fictionalrealm.osserc.protocol.sp.WelcomeSP;
import org.jboss.netty.channel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/18/12
 * Time: 8:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionHandler extends SimpleChannelUpstreamHandler {

    private Logger logger = LoggerFactory.getLogger("listener");

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
        logger.info("Message received:" + e.getMessage().toString());
        ctx.sendUpstream(e);
    }

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
        logger.info("Client " + ctx.getChannel().getRemoteAddress() + " connected");

        WelcomeSP welcomeSP = WelcomeSP.newBuilder()
                .setVersion(1)
                .setServerStatus(ServerStatus.ONLINE)
                .build();

        ctx.getChannel().write(welcomeSP);

        ctx.sendUpstream(e);
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        logger.info("Client connected");
    }
}
