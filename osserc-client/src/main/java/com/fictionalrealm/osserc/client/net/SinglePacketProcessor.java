package com.fictionalrealm.osserc.client.net;

import com.google.protobuf.Message;

/**
 * User: Yervand.Aghababyan
 * Date: 1/13/13
 * Time: 12:17 AM
 */
public interface SinglePacketProcessor<T extends Message> {
    public void handle(T packet);
}
