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

    private final ServerBootstrap bs;
    private final OssercPipelineFactory pipelineFactory;

    @Inject
    public Listener(OssercPipelineFactory pipelineFactory) {
        bs = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
        this.pipelineFactory = pipelineFactory;
    }

    public void bind(String listenerHost, int listenerPort) {
        InetSocketAddress addr = new InetSocketAddress(listenerHost, listenerPort);

        bs.setPipelineFactory(pipelineFactory);

        bs.bind(addr);

        logger.info("Listening on " + addr.getAddress().getHostAddress() + ":" + addr.getPort());
    }
}
