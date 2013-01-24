package com.fictionalrealm.osserc.client.config;

import com.fictionalrealm.osserc.config.OssercConfigurationException;

/**
 * User: Yervand.Aghababyan
 * Date: 1/21/13
 * Time: 10:54 PM
 */
public class AndroidClientConfigBuilder implements OssercClientConfigBuilder {
    @Override
    public ClientConfig buildConfig() throws OssercConfigurationInitException, OssercConfigurationException {
        return new AndroidClientConfig();
    }
}
