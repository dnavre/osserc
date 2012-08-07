package com.fictionalrealm.mmoje.core;

import com.fictionalrealm.mmoje.net.Listener;
import com.google.inject.Singleton;
import org.apache.commons.configuration.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;

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

    @Inject
    public Application(Listener lsnr, ApplicationConfig config) {
        this.listener = lsnr;
        this.config = config;
    }

    public void start() {
        try {
            logger.info("Starting...");
            config.initialize();

            listener.bind(config.getListenerHost(), config.getListenerPort());
            listener.startListening();
        } catch (ConfigurationException e) {
            logger.error("Couldn't load config! Quitting...", e.getMessage());
        } catch (IOException e) {
            logger.error("Couldn't listen to port! Quitting...", e.getMessage());
        }
    }
}
