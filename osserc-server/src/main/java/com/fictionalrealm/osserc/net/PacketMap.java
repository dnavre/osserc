package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.core.ApplicationConfig;
import com.fictionalrealm.osserc.core.ApplicationManager;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.commons.codec.DecoderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

/**
 * User: Yervand.Aghababyan
 * Date: 8/9/12
 * Time: 2:05 AM
 */
@Singleton
public class PacketMap extends AbstractPacketMap {

    private final Logger logger = LoggerFactory.getLogger("c.f.osserc.PacketMap");

    private final ApplicationManager appManager;

    @Inject
    public PacketMap(ApplicationManager appManager) {
        this.appManager = appManager;
    }

    public void initialize(ApplicationConfig config) {

        try{
            super.initialize(config.getClientPackets(), config.getServerPackets());
        } catch (PacketMapInitializationException e) {
            logger.error("An error occurred while loading packet list.");
            appManager.stop();
        }
    }
}
