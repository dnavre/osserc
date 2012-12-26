package com.fictionalrealm.osserc.net;

import org.jboss.netty.channel.ChannelHandlerContext;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Inject;
import java.util.concurrent.*;

/**
 * User: Yervand.Aghababyan
 * Date: 8/21/12
 * Time: 12:28 AM
 */
@ThreadSafe
public class ConnectionMap {

    private static final int CONNECTION_MNGR_THREAD_NUM = 3;

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

    public void removeFromMap(Connection c) {
        removeFromMap(c.getId());
    }

    public void removeFromMap(long connectionId) {
        connections.remove(connectionId);
    }

    public Connection getConnection(long id) {
        return connections.get(id);
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

            ctx.setAttachment(id);
            connections.put(id, c);

            c.sendWelcome();
        }
    }
}
