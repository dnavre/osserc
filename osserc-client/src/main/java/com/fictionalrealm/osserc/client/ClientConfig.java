package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.config.AbstractOssercConfiguration;
import com.fictionalrealm.osserc.config.OssercConfigurationException;
import org.apache.commons.configuration.ConfigurationException;

/**
 * User: Yervand.Aghababyan
 * Date: 1/2/13
 * Time: 1:04 AM
 */
public class ClientConfig extends AbstractOssercConfiguration {

    private static final String DEFAULT_CONFIG = "com/fictionalrealm/osserc/resources/client_config.defaults.properties";
    private static final String CONFIG_CUSTOMIZATION = "osserc_client.properties";

    public ClientConfig() throws OssercConfigurationException {
        super(DEFAULT_CONFIG, CONFIG_CUSTOMIZATION);
    }
}
