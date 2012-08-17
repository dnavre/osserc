package com.fictionalrealm.osserc.net;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/9/12
 * Time: 2:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class PacketParser {

    private final PacketMap packetMap;

    @Inject
    public PacketParser(PacketMap packetMap) {
        this.packetMap = packetMap;
    }
}
