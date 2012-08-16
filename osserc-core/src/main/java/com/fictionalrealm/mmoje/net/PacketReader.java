package com.fictionalrealm.mmoje.net;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class PacketReader {
    private final ByteBuffer buffer = ByteBuffer.allocate(64 * 1024);
    private Selector selector;
    private final Thread dataPollerThread;

    @Inject
    public PacketReader() {
        dataPollerThread = new Thread(new DataPoller(), "packetReader");
    }

    public void startPolling() throws IOException {
        selector = Selector.open();
        dataPollerThread.start();
    }

    public void register(SocketChannel client) throws ClosedChannelException {
        client.register(selector, SelectionKey.OP_READ);
    }

    public void stop() throws IOException {
        selector.close();
    }

    private class DataPoller implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel client = null;
                while (true) {

                    // after 10ms do a new select so new connections get accounted for
                    selector.select(10);

                    Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                    while (iter.hasNext()) {
                        SelectionKey key = iter.next();
                        iter.remove();

                        client = (SocketChannel) key.channel();
                        buffer.clear();
                        if (client.read(buffer) != -1) {
                            buffer.flip();
                            String line = new String(buffer.array(), buffer.position(), buffer.remaining());
                            System.out.print(line);
                            if (line.startsWith("CLOSE")) {
                                client.close();
                            }
                        } else {
                            key.cancel();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


