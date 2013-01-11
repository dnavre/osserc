package com.fictionalrealm.osserc.core;

import com.fictionalrealm.osserc.net.lsnr.Listener;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * User: Yervand.Aghababyan
 * Date: 9/14/12
 * Time: 10:00 PM
 */
@Singleton
public class ApplicationManager {

    public final Logger logger = LoggerFactory.getLogger(getClass());

    private final Injector injector;

    @Inject
    public ApplicationManager(Injector injector) {
        this.injector = injector;
    }

    /**
     * Shuts down the server
     */
    public void stop() {

        logger.info("Stopping Osserc...");

        injector.getInstance(Listener.class).stop();
    }
}
