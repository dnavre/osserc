package com.fictionalrealm.osserc.net.lsnr;

import com.fictionalrealm.osserc.net.lsnr.OssercPipelineFactory;
import com.google.inject.Singleton;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 12:04 PM
 */
@Singleton
public class Listener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ServerBootstrap bs;
    private final OssercPipelineFactory pipelineFactory;

    private final Object channelLock = new Object();
    private volatile Channel channel;


    @Inject
    public Listener(OssercPipelineFactory pipelineFactory) {
        bs = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        this.pipelineFactory = pipelineFactory;
    }

    public void bind(String listenerHost, int listenerPort) {
        InetSocketAddress addr = new InetSocketAddress(listenerHost, listenerPort);

        bs.setPipelineFactory(pipelineFactory);

        synchronized (channelLock) {
            channel = bs.bind(addr);
        }

        logger.info("Listening on " + addr.getAddress().getHostAddress() + ":" + addr.getPort());
    }

    public void stop() {
        synchronized (channelLock) {
            if(channel != null) {
                logger.info("Stopping listener");
                channel.close();
            }
        }
    }
}
