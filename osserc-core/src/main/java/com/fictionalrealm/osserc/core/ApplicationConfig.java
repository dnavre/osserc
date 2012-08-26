package com.fictionalrealm.osserc.core;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 12:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationConfig {

    private final String CONFIG_PATH = "com/fictionalrealm/osserc/resources/";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final CompositeConfiguration config;
    private final Map<String, String> serverPackets;
    private final Map<String, String> clientPackets;

    public ApplicationConfig() throws ConfigurationException {
        config = new CompositeConfiguration();

        logger.info("Loading configuration");

        try {
            PropertiesConfiguration defaults = new PropertiesConfiguration(CONFIG_PATH + "config.defaults.properties");
            PropertiesConfiguration customization = new PropertiesConfiguration("osserc_config.properties");

            config.addConfiguration(defaults);
            config.addConfiguration(customization);

            // loading client packet list
            String[] cFiles = config.getStringArray("packetlist.client");
            CompositeConfiguration cPackets = loadConfigFiles(cFiles);
            clientPackets = (Map)Collections.unmodifiableMap(ConfigurationConverter.getMap(cPackets));

            // loading server packet list
            String[] sFiles = config.getStringArray("packetlist.server");
            CompositeConfiguration sPackets = loadConfigFiles(sFiles);
            serverPackets = (Map)Collections.unmodifiableMap(ConfigurationConverter.getMap(cPackets));

        } catch (ConfigurationException e) {
            logger.debug("Couldn't load config! Quitting...", e);
            System.exit(1);
            throw e;
        }
    }

    private CompositeConfiguration loadConfigFiles(String[] files) throws ConfigurationException {
        CompositeConfiguration allConfig = new CompositeConfiguration();
        for (String f: files) {
            allConfig.addConfiguration(new PropertiesConfiguration(f));
        }

        return allConfig;
    }

    public int getListenerPort() {
        return config.getInt("lsnr.port");
    }

    public String getListenerHost() {
        return config.getString("lsnr.host");
    }

    public List<String> getAnnotationScanPaths() {
        List<String> paths = new ArrayList<String>();

        paths.add(config.getString("osserc.annotations.scan"));

        for (Object o: config.getList("annotations.scan")) {
            if(o != null && o instanceof String) {
                paths.add((String)o);
            }
        }

        return paths;
    }

    public Map<String, String> getClientPackets() {
        return clientPackets;
    }

    public Map<String, String> getServerPackets() {
        return serverPackets;
    }
}
