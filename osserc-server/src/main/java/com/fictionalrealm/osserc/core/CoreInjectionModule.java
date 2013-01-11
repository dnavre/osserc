package com.fictionalrealm.osserc.core;

import com.fictionalrealm.osserc.net.AbstractPacketMap;
import com.fictionalrealm.osserc.net.PacketMap;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Scopes;


/**
 * User: Yervand.Aghababyan
 * Date: 8/3/12
 * Time: 3:05 AM
 */
public class CoreInjectionModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(AbstractPacketMap.class).to(PacketMap.class).in(Scopes.SINGLETON);
    }
}
