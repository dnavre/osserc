package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.*;
import com.fictionalrealm.osserc.core.ApplicationConfig;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/9/12
 * Time: 2:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class PacketMap {

    @Inject
    public PacketMap() {
        // empty constructor
    }

    public void initialize(ApplicationConfig config) {
        List<String> paths = config.getAnnotationScanPaths();

        ConfigurationBuilder cb = new ConfigurationBuilder();

        for (String path: paths) {
            cb.addUrls(ClasspathHelper.forPackage(path));
        }

        cb.addScanners(new TypeAnnotationsScanner());

        Reflections r = new Reflections(cb);

        r.getTypesAnnotatedWith(com.fictionalrealm.osserc.Packet.class);
    }
}
