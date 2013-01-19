package com.fictionalrealm.osserc.config;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;

/**
 * User: Yervand.Aghababyan
 * Date: 1/2/13
 * Time: 1:10 AM
 */
public abstract class AbstractOssercConfiguration implements OssercBaseConfiguration {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Main application configuration container
     */
    protected final CompositeConfiguration config;

    private final Map<String, String> serverPackets;
    private final Map<String, String> clientPackets;

    public AbstractOssercConfiguration(String defaultConfig, String ... configCustomizations) throws OssercConfigurationException {
        config = new CompositeConfiguration();

        logger.info("Loading configuration");

        try {
            loadMainConfig(defaultConfig, configCustomizations);

            // loading client packet list
            String[] cFiles = config.getStringArray("packetlist.client");
            CompositeConfiguration cPackets = loadConfigFiles(cFiles);
            clientPackets = (Map) Collections.unmodifiableMap(ConfigurationConverter.getMap(cPackets));

            // loading server packet list
            String[] sFiles = config.getStringArray("packetlist.server");
            CompositeConfiguration sPackets = loadConfigFiles(sFiles);
            serverPackets = (Map)Collections.unmodifiableMap(ConfigurationConverter.getMap(sPackets));

        } catch (ConfigurationException e) {
            logger.debug("Couldn't load config! Quitting...", e);
            System.exit(1);
            throw new OssercConfigurationException(e);
        }
    }

    private CompositeConfiguration loadConfigFiles(String[] files) throws ConfigurationException {
        CompositeConfiguration allConfig = new CompositeConfiguration();
        for (String f: files) {
            allConfig.addConfiguration(new PropertiesConfiguration(f));
        }

        return allConfig;
    }

    /**'
     * Loads the main configuration file
     *
     * @param defaultConfig
     * @param customizations
     * @throws ConfigurationException
     */
    private void loadMainConfig(String defaultConfig, String[] customizations) throws ConfigurationException {
        PropertiesConfiguration defaults = new PropertiesConfiguration(defaultConfig);

        config.addConfiguration(defaults);

        for(String configFile: customizations) {
            PropertiesConfiguration customization = new PropertiesConfiguration(configFile);
            config.addConfiguration(customization);
        }
    }

    @Override
    public Map<String, String> getClientPackets() {
        return clientPackets;
    }

    @Override
    public Map<String, String> getServerPackets() {
        return serverPackets;
    }

    public long getGeneralNetworkTimeout() {
        return config.getLong("net.timeout");
    }
}
