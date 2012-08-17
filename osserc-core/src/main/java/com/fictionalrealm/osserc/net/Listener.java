package com.fictionalrealm.osserc.net;

import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import static java.nio.channels.SelectionKey.*;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class Listener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Thread accepterThread = new Thread(new Accepter(), "listener");

    private PacketReader reader;

    private ServerSocketChannel socket;
    private Selector selector;

    @Inject
    public Listener(PacketReader reader) {
        this.reader = reader;
    }

    public void bind(String listenerHost, int listenerPort) throws IOException {
        InetSocketAddress addr = new InetSocketAddress(listenerHost, listenerPort);

        try {
            selector = Selector.open();
            socket = ServerSocketChannel.open();
            socket.configureBlocking(false);
            socket.socket().bind(addr);
            socket.register(selector, OP_ACCEPT);

            reader.startPolling();

            logger.info("Listening to " + addr.getHostName() + ":" + addr.getPort());
        } catch (IOException e) {
            logger.debug("Couldn't bind to address!", e);
            throw e;
        }
    }

    public void startListening() {
        accepterThread.start();
    }

    public void stopListener() {
        try {
            for (SelectionKey k : selector.keys()) {
                k.cancel();
            }

            socket.close();
            selector.close();

            reader.stop();
        } catch (IOException e) {
            logger.error("Unexpected error!", e);
        }
    }

    private class Accepter implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    selector.select();
                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();

                    while (iter.hasNext()) {
                        SocketChannel client;
                        SelectionKey key = iter.next();
                        iter.remove();

                        client = ((ServerSocketChannel) key.channel()).accept();
                        client.configureBlocking(false);
                        reader.register(client);

                        InetAddress remoteAddr = client.socket().getInetAddress();

                        logger.info("Connected client - " + remoteAddr.getHostAddress());
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }
}
