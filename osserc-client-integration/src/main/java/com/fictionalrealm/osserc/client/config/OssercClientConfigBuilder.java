package com.fictionalrealm.osserc.client.config;

import com.fictionalrealm.osserc.config.OssercConfigurationException;

/**
 * User: Yervand.Aghababyan
 * Date: 1/19/13
 * Time: 12:25 AM
 */
public interface OssercClientConfigBuilder {
    public ClientConfig buildConfig() throws OssercConfigurationInitException, OssercConfigurationException;
}
