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
        if(iterator.hasNext()) {
            LOGGER.debug("Configuration provider found, attempting to get configuration instance");

            OssercClientConfigBuilder ccb = iterator.next();
            ClientConfig cc = ccb.buildConfig();

            return cc;
        } else {
            throw new OssercConfigurationInitException("No configuration providers found!");
        }
    }
}
