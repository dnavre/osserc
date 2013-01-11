package com.fictionalrealm.osserc.client;

import com.fictionalrealm.osserc.client.net.ClientPacketMap;
import com.fictionalrealm.osserc.client.net.PacketProcessor;
import com.fictionalrealm.osserc.config.OssercConfigurationException;
import com.fictionalrealm.osserc.net.PacketMapInitializationException;

/**
 * User: Yervand.Aghababyan
 * Date: 1/2/13
 * Time: 1:01 AM
 */
public class ClientInitializer {

    public ClientConfig getClientConfig() throws OssercConfigurationException {
        return new ClientConfig();
    }
}
