package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.ClientConnection;
import com.fictionalrealm.osserc.client.net.ClientPacketMap;
import com.fictionalrealm.osserc.client.net.PacketProcessor;
import com.fictionalrealm.osserc.client.net.SinglePacketProcessor;
import com.fictionalrealm.osserc.config.OssercConfigurationException;
import com.fictionalrealm.osserc.net.ChannelHandlerFactory;
import com.fictionalrealm.osserc.net.OssercPipelineFactory;
import com.fictionalrealm.osserc.net.PacketMapInitializationException;
import com.fictionalrealm.osserc.protocol.cp.InitUser;
import com.fictionalrealm.osserc.protocol.sp.WelcomeSP;
import com.google.protobuf.Message;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.*;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:07 AM
 */
public class OssercClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(OssercClient.class);

    private ChannelFuture channelFuture;

    private final ClientConfig config;
    private final ClientInitializer ci;

    private final PacketProcessor packetProcessor;
    private final ClientPacketMap packetMap;

    private volatile ClientConnection connection = null;

    public OssercClient() {

        ci = new ClientInitializer();
        try {
            config = ci.getClientConfig();

            packetMap = new ClientPacketMap();
            packetMap.initialize(config);

            packetProcessor = new PacketProcessor(packetMap, config);


        } catch (OssercConfigurationException e) {
            throw new OssercClientInitException(e);
        } catch (PacketMapInitializationException e) {
            throw new OssercClientInitException(e);
        }
    }

    public void connect(String host, int port) {
        ChannelFactory factory =
                new NioClientSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool());

        ClientBootstrap bootstrap = new ClientBootstrap(factory);

        bootstrap.setPipelineFactory(new OssercPipelineFactory(packetMap, new ChannelHandlerFactory() {
            @Override
            public ChannelHandler getNewHandler() {
                return new ClientMsgHandler(packetProcessor);
            }
        }));

        bootstrap.setOption("tcpNoDelay", true);
        bootstrap.setOption("keepAlive", true);

        channelFuture = bootstrap.connect(new InetSocketAddress(host, port));

        connection = new ClientConnection(channelFuture.getChannel());

        packetProcessor.waitForMessage(WelcomeSP.class, new SinglePacketProcessor<WelcomeSP>() {
            @Override
            public void handle(WelcomeSP packet) {
                LOGGER.info("Welcome msg from server status:" + packet.getServerStatus());
            }
        });
    }


    public void authenticate(String username, String password) {
        InitUser iu = InitUser.newBuilder()
                .setUsername(username)
                .setPassword(password)
                .build();

        connection.write(iu);
    }

    public ClientConnection getConnection() {
        return connection;
    }
}
