package com.fictionalrealm.osserc.core;

import com.fictionalrealm.osserc.net.PacketMap;
import com.fictionalrealm.osserc.net.lsnr.Listener;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import org.jboss.netty.channel.ChannelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/3/12
 * Time: 3:07 AM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class Application {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Listener listener;
    private final ApplicationConfig config;
    private final PacketMap packetMap;

    @Inject
    public Application(Listener lsnr, ApplicationConfig config, PacketMap packetMap) {
        this.listener = lsnr;
        this.config = config;
        this.packetMap = packetMap;
    }

    public void start() {
        try {
            logger.info("Starting...");

            packetMap.initialize(config);

            listener.bind(config.getListenerHost(), config.getListenerPort());
        } catch (ChannelException e) {
            logger.error("Couldn't listen to port! Quitting...", e.getMessage());
        }
    }

    private void initGuice() {

    }
}
