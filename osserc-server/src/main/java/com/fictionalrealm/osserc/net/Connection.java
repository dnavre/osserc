package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.protocol.datatypes.DisconnectionReason;
import com.fictionalrealm.osserc.protocol.datatypes.ServerStatus;
import com.fictionalrealm.osserc.protocol.sp.DisconnectionNoticeSP;
import com.fictionalrealm.osserc.protocol.sp.WelcomeSP;
import com.google.protobuf.Message;
import org.jboss.netty.channel.ChannelHandlerContext;

/**
 * User: dnavre
 * Date: 8/9/12
 * Time: 2:22 AM
 */
public class Connection extends AbstractConnection {

    protected final long id;

    public Connection(long connectionId, ChannelHandlerContext ctx) {
        super(ctx.getChannel());

        this.id = connectionId;
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

            channel.close();
        }
    }

    public void sendWelcome() {
        WelcomeSP welcomeSP = WelcomeSP.newBuilder()
                .setVersion(1)
                .setServerStatus(ServerStatus.ONLINE)
                .build();

        write(welcomeSP);
    }

    public long getId() {
        return id;
    }
}
