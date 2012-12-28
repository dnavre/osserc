package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.PacketDispatcher;
import com.fictionalrealm.osserc.client.net.PacketProcessor;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
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

    public void connect(String host, int port) {
        ChannelFactory factory =
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
            public org.jboss.netty.channel.ChannelPipeline getPipeline() {
                return Channels.pipeline(new ClientPipeline(new PacketProcessor()));
            }
        });

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        bootstrap.connect(new InetSocketAddress(host, port));
    }
}
