package com.fictionalrealm.osserc.client.net;

import com.fictionalrealm.osserc.client.ClientConfig;
import com.fictionalrealm.osserc.net.AbstractConnection;
import com.google.protobuf.Message;
import org.slf4j.Logger;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * TODO change the blocking strategy in this class
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:59 AM
 */
public class PacketProcessor {

    private final Logger logger = AbstractConnection.LOGGER;

    private final ClientPacketMap packetMap;

    private final ConcurrentMap<Class<? extends Message>, CopyOnWriteArrayList<SinglePacketProcessor>> queuedHandlers = new ConcurrentHashMap<Class<? extends Message>, CopyOnWriteArrayList<SinglePacketProcessor>>();

    private final long timeout;

    public PacketProcessor(ClientPacketMap packetMap, ClientConfig config) {
        this.packetMap = packetMap;
        this.timeout = config.getGeneralNetworkTimeout();

        for(Message m: packetMap.getReceivableTypes()) {
            queuedHandlers.put(m.getClass(), new CopyOnWriteArrayList<SinglePacketProcessor>());
        }
    }

    public void processPacket(Message m) {
        if(queuedHandlers.get(m.getClass()).size() > 0) {
            for (SinglePacketProcessor processor: queuedHandlers.get(m.getClass())) {
                processor.handle(m);

                synchronized (processor) {
                    processor.notify();
                }
            }
        }
    }

    public <T extends Message>void  waitForMessage(Class<T> clazz, SinglePacketProcessor processor ) {
        queuedHandlers.get(clazz).add(processor);

        try {
            synchronized (processor) {
                processor.wait(timeout);
            }
        } catch (InterruptedException e) {
            logger.error("Unknown error occurred", e);
        }
    }
}
