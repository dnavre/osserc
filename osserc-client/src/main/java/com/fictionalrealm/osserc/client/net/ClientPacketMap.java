package com.fictionalrealm.osserc.client.net;

import com.fictionalrealm.osserc.client.config.ClientConfig;
import com.fictionalrealm.osserc.net.AbstractPacketMap;
import com.fictionalrealm.osserc.net.PacketMapInitializationException;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:46 AM
 */
public class ClientPacketMap extends AbstractPacketMap {

    public ClientPacketMap() {

    }

    public void initialize(ClientConfig config) throws PacketMapInitializationException {
        super.initialize(config.getServerPackets(), config.getClientPackets());
    }
}
