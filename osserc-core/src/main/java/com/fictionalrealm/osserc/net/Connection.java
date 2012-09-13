package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.protocol.datatypes.DisconnectionReason;
import com.fictionalrealm.osserc.protocol.datatypes.ServerStatus;
import com.fictionalrealm.osserc.protocol.sp.DisconnectionNoticeSP;
import com.fictionalrealm.osserc.protocol.sp.WelcomeSP;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import org.jboss.netty.channel.ChannelHandlerContext;

import java.nio.ByteBuffer;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 2:22 AM
 */
public class Connection {

    private final ChannelHandlerContext ctx;
    private final long id;

    public Connection(long connectionId, ChannelHandlerContext ctx) {
        this.id = connectionId;
        this.ctx = ctx;
    }

    public void write(Message m) {
        if(ctx.getChannel().isWritable())
        ctx.getChannel().write(m);
    }

    public boolean isConnected() {
        return false;
    }

    public long getId() {
        return id;
    }

    public void disconnectConnection(DisconnectionReason reason) {
        disconnectConnection(reason, null);
    }

    public void disconnectConnection(DisconnectionReason reason, String msg) {

        if(isConnected()) {
            DisconnectionNoticeSP noticeSP = DisconnectionNoticeSP.newBuilder()
                    .setReason(reason)
                    .setMsg(msg)
                    .build();

            write(noticeSP);

            ctx.getChannel().close();
        }
    }

    public void sendWelcome() {
        WelcomeSP welcomeSP = WelcomeSP.newBuilder()
                .setVersion(1)
                .setServerStatus(ServerStatus.ONLINE)
                .build();

        write(welcomeSP);
    }
}
