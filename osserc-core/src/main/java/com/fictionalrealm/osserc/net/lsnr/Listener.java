package com.fictionalrealm.osserc.net.lsnr;

import com.fictionalrealm.osserc.net.lsnr.OssercPipelineFactory;
import com.google.inject.Singleton;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

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

    private ServerBootstrap bs;

    @Inject
    public Listener() {

    }

    public void bind(String listenerHost, int listenerPort) {
        InetSocketAddress addr = new InetSocketAddress(listenerHost, listenerPort);

        bs = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        bs.setPipelineFactory(new OssercPipelineFactory());

        bs.bind(addr);

        logger.info("Listening on " + addr.getAddress().getHostAddress() + ":" + addr.getPort());
    }
}
