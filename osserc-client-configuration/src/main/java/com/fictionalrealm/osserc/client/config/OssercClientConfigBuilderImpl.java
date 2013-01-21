package com.fictionalrealm.osserc.client.config;

import com.fictionalrealm.osserc.config.OssercConfigurationException;

/**
 * User: Yervand.Aghababyan
 * Date: 1/19/13
 * Time: 2:13 PM
 */
public class OssercClientConfigBuilderImpl implements OssercClientConfigBuilder {

    @Override
    public ClientConfig buildConfig() throws OssercConfigurationInitException, OssercConfigurationException {
        return new ClientConfigImpl();
    }
}
