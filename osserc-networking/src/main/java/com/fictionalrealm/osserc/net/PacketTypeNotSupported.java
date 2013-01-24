package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;

/**
 * User: Yervand.Aghababyan
 * Date: 1/23/13
 * Time: 11:06 PM
 */
public class PacketTypeNotSupported extends RuntimeException {

    public PacketTypeNotSupported() {
        super();
    }

    public PacketTypeNotSupported(Class<? extends Message> clazz) {
        super("Packet type " + clazz.getSimpleName() + " is not supported");
    }
}
