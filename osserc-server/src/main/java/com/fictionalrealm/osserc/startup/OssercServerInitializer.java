package com.fictionalrealm.osserc.startup;

import com.fictionalrealm.osserc.core.CoreInjectionModule;
import com.fictionalrealm.osserc.core.OssercServer;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * User: Yervand.Aghababyan
 * Date: 1/17/13
 * Time: 11:54 PM
 */
public class OssercServerInitializer {

    public static OssercServer create() {
        Injector injector = Guice.createInjector(new CoreInjectionModule());
        OssercServer app = injector.getInstance(OssercServer.class);

        return app;
    }
}
