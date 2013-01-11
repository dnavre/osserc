package com.fictionalrealm.osserc.client.net;

import com.fictionalrealm.osserc.net.AbstractConnection;
import org.jboss.netty.channel.Channel;

/**
 * User: Yervand.Aghababyan
 * Date: 1/9/13
 * Time: 7:07 PM
 */
public class ClientConnection extends AbstractConnection {

    public ClientConnection(Channel c) {
        super(c);
    }
}
