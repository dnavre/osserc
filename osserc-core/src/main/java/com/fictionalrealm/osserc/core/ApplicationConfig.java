package com.fictionalrealm.osserc.core;

import com.fictionalrealm.osserc.config.AbstractOssercConfiguration;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * User: Yervand.Aghababyan
 * Date: 8/7/12
 * Time: 12:12 PM
 */
public class ApplicationConfig extends AbstractOssercConfiguration {

    private static final String DEFAULT_CONFIG = "com/fictionalrealm/osserc/resources/config.defaults.properties";
    private static final String CUSTOM_CONFIG = "osserc.properties";
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    public ApplicationConfig() throws ConfigurationException {
        super(DEFAULT_CONFIG, CUSTOM_CONFIG);
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
