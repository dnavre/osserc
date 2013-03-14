package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.protocol.datatypes.DisconnectionReason;

/**
 * User: Yervand.Aghababyan
 * Date: 3/2/13
 * Time: 12:06 AM
 */
public interface Connection {
    void disconnectConnection(DisconnectionReason reason);

    void disconnectConnection(DisconnectionReason reason, String msg);
}
