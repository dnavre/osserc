package com.fictionalrealm.osserc.client.config;

import com.fictionalrealm.osserc.config.OssercConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * User: Yervand.Aghababyan
 * Date: 1/19/13
 * Time: 1:48 PM
 */
public abstract class ClientConfigFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientConfigFactory.class);

    public static ClientConfig getConfig() throws OssercConfigurationInitException, OssercConfigurationException {
        ServiceLoader<OssercClientConfigBuilder> sl = ServiceLoader.load(OssercClientConfigBuilder.class);
        Iterator<OssercClientConfigBuilder> iterator = sl.iterator();
        OssercClientConfigBuilder ccb;
        if(iterator.hasNext()) {
            LOGGER.debug("Configuration provider found, attempting to get configuration instance");

            ccb = iterator.next();
        } else {
            LOGGER.debug("Trying to fallback to 1. Android config 2. Desktop config");

            ccb = getConfigBuilder();
        }

        ClientConfig cc = ccb.buildConfig();

        return cc;
    }

    private static OssercClientConfigBuilder getConfigBuilder() throws OssercConfigurationInitException {
        Class<? extends OssercClientConfigBuilder> clazz = null;
        try {
            clazz = (Class<? extends OssercClientConfigBuilder>) Class.forName("com.fictionalrealm.osserc.client.config.AndroidClientConfigBuilder");
        } catch (ClassNotFoundException e) {
            LOGGER.debug("Android class builder not found");
        }

        if(clazz == null) {
            try {
                clazz = (Class<? extends OssercClientConfigBuilder>) Class.forName("com.fictionalrealm.osserc.client.config.OssercClientConfigBuilderImpl");
            } catch (ClassNotFoundException e) {
                LOGGER.debug("Android class builder not found");
            }
        }

        if(clazz == null) {
            throw new OssercConfigurationInitException("No configuration providers found! ");
        }

        try {
            OssercClientConfigBuilder instance = clazz.newInstance();

            return instance;
        } catch (InstantiationException e) {
            LOGGER.debug("Could not initialize config builder: " + clazz.getName());
        } catch (IllegalAccessException e) {
            LOGGER.debug("Could not initialize config builder: " + clazz.getName());
        }

        throw new OssercConfigurationInitException("No configuration providers found! ");
    }
}
