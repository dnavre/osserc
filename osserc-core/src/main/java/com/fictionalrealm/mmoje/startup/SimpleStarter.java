package com.fictionalrealm.mmoje.startup;

import com.fictionalrealm.mmoje.CoreInjectionModule;
import com.fictionalrealm.mmoje.core.Application;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/3/12
 * Time: 2:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class SimpleStarter {



    public static void main(String args[]) {

        Injector injector = Guice.createInjector(new CoreInjectionModule());
        Application app = injector.getInstance(Application.class);
        app.start();
    }
}
