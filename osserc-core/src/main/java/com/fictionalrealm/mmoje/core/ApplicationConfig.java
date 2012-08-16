package com.fictionalrealm.mmoje.core;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompositeConfiguration config;

    public ApplicationConfig() throws ConfigurationException {
        config = new CompositeConfiguration();
    }

    public void initialize() throws ConfigurationException {

        logger.info("Loading configuration");

        try {
            PropertiesConfiguration defaults = new PropertiesConfiguration("config.defaults.properties");
            PropertiesConfiguration customization = new PropertiesConfiguration("mmoje_config.properties");

            config.addConfiguration(defaults);
            config.addConfiguration(customization);
        } catch (ConfigurationException e) {
            logger.debug("Could not load configuration!", e);
            throw e;
        }
    }

    public int getListenerPort() {
        return config.getInt("lsnr.port");
    }

    public String getListenerHost() {
        return config.getString("lsnr.host");
    }
}
