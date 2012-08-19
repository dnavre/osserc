package com.fictionalrealm.osserc.core;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


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
            PropertiesConfiguration customization = new PropertiesConfiguration("osserc_config.properties");

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
}
