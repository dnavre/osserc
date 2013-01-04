package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.PacketProcessor;
import com.fictionalrealm.osserc.config.OssercConfigurationException;
import com.fictionalrealm.osserc.net.PacketMapInitializationException;
import org.apache.commons.configuration.ConfigurationException;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:07 AM
 */
public class OssercClient {

    private ChannelFuture channel;

    private final ClientConfig config;
    private final ClientInitializer ci;

    public OssercClient() {

        ci = new ClientInitializer();
        try {
            config = ci.getClientConfig();
        } catch (OssercConfigurationException e) {
            throw new OssercClientInitException(e);
        }
    }

    public void connect(String host, int port) {
        ChannelFactory factory =
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public org.jboss.netty.channel.ChannelPipeline getPipeline() throws PacketMapInitializationException {
                try {
                    return Channels.pipeline(ci.getPipeline(config));
                } catch (PacketMapInitializationException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    throw e;
                }
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        channel = bootstrap.connect(new InetSocketAddress(host, port));
    }


    public void authenticate(String username, String password) {
        //channel.
    }
}
