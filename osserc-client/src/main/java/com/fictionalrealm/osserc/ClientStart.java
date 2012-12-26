package com.fictionalrealm.osserc;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:33 AM
 */
public class ClientStart {
    public static void main(String args[]) throws InterruptedException {
        NioSocketConnector connector = new NioSocketConnector();
//        connector.setConnectTimeoutMillis(CONNECT_TIMEOUT);

//        if (USE_CUSTOM_CODEC) {
//            connector.getFilterChain().addLast("codec",
//                    new ProtocolCodecFilter(new SumUpProtocolCodecFactory(false)));
//        } else {
            connector.getFilterChain().addLast("codec",
                    new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
//        }

        connector.getFilterChain().addLast("logger", new LoggingFilter());
        connector.setHandler(new ConnectionHandler());
        IoSession session;

        for (;;) {
            try {
                ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", 8039));
                future.awaitUninterruptibly();
                session = future.getSession();
                break;
            } catch (RuntimeIoException e) {
                System.err.println("Failed to connect.");
                e.printStackTrace();
                Thread.sleep(5000);
            }
        }

        // wait until the summation is done
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
