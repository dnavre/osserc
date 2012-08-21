package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.protocol.datatypes.ServerStatus;
import com.fictionalrealm.osserc.protocol.sp.WelcomeSP;
import org.jboss.netty.channel.ChannelHandlerContext;

import javax.inject.Inject;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/21/12
 * Time: 12:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionMap {

    private final int CONNECTION_MNGR_THREAD_NUM = 3;

    private final ConcurrentMap<Long, Connection> connections = new ConcurrentHashMap<Long, Connection>();
    private final ConnectionIdGenerator idGenerator;
    private final ScheduledExecutorService threadPool = new ScheduledThreadPoolExecutor(CONNECTION_MNGR_THREAD_NUM);

    @Inject
    public ConnectionMap(ConnectionIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public void addConnection(ChannelHandlerContext ctx) {
        threadPool.schedule(new NewConnectionInitializer(ctx), 0, TimeUnit.MILLISECONDS);
    }

    private class NewConnectionInitializer implements Runnable {

        private final ChannelHandlerContext ctx;

        public NewConnectionInitializer(ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {

            long id = idGenerator.getNewId();
            Connection c = new Connection(id, ctx);

            connections.put(id, c);

            WelcomeSP welcomeSP = WelcomeSP.newBuilder()
                    .setVersion(1)
                    .setServerStatus(ServerStatus.ONLINE)
                    .build();

            ctx.getChannel().write(welcomeSP);
        }
    }
}
