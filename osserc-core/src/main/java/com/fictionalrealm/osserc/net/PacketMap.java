package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.*;
import com.fictionalrealm.osserc.core.ApplicationConfig;
import com.fictionalrealm.osserc.protocol.cp.InitUser;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/9/12
 * Time: 2:05 AM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class PacketMap {

    private final ConcurrentMap<Integer, Message> clientPackets = new ConcurrentHashMap<Integer, Message>();
    private final ConcurrentMap<Class<?>, Integer> serverPackets = new ConcurrentHashMap<Class<?>, Integer>();

    @Inject
    public PacketMap() {
        // empty constructor
    }

    public void initialize(ApplicationConfig config) {

        try{
            for (Map.Entry<String, String> e: config.getClientPackets().entrySet()) {
                clientPackets.put(Integer.parseInt(e.getKey()), getMessageByClassName(e.getValue()));
            }

            for (Map.Entry<String, String> e: config.getClientPackets().entrySet()) {
                serverPackets.put(getClassByName(e.getValue()), Integer.parseInt(e.getKey()));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Message getMessageByClassName(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName(className);

        Method m = c.getMethod("getDefaultInstance");
        Message message = (Message) m.invoke(null);

        return message;
    }

    private Class getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
