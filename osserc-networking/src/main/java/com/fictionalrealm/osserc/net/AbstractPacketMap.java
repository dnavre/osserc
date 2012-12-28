package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:55 AM
 */
public class AbstractPacketMap {
    private final ConcurrentMap<Integer, Message> receivableMessages = new ConcurrentHashMap<Integer, Message>();
    private final ConcurrentMap<Class<?>, Byte[]> sendableMessages = new ConcurrentHashMap<Class<?>, Byte[]>();
}
