package com.fictionalrealm.osserc.config;

import java.util.Map;

/**
 * User: Yervand.Aghababyan
 * Date: 1/18/13
 * Time: 11:46 PM
 */
public interface OssercBaseConfiguration {
    public Map<String, String> getClientPackets();

    public Map<String, String> getServerPackets();

    public long getGeneralNetworkTimeout();
}
