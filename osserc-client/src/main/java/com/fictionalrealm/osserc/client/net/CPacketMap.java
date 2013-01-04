package com.fictionalrealm.osserc.client.net;

import com.fictionalrealm.osserc.client.ClientConfig;
import com.fictionalrealm.osserc.net.AbstractPacketMap;
import com.fictionalrealm.osserc.net.PacketMapInitializationException;
import org.apache.commons.codec.DecoderException;

import java.lang.reflect.InvocationTargetException;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:46 AM
 */
public class CPacketMap extends AbstractPacketMap {

    public CPacketMap() {

    }

    public void initialize(ClientConfig config) throws PacketMapInitializationException {
        super.initialize(config.getClientPackets(), config.getServerPackets());
    }
}
