package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.*;
import com.fictionalrealm.osserc.core.ApplicationConfig;
import com.fictionalrealm.osserc.protocol.cp.InitUser;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.ScopeAnnotation;
import com.google.inject.Singleton;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.inject.Scope;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Yervand.Aghababyan
 * Date: 8/9/12
 * Time: 2:05 AM
 */
@Singleton
public class PacketMap {

    private final ConcurrentMap<Integer, Message> clientPackets = new ConcurrentHashMap<Integer, Message>();
    private final ConcurrentMap<Class<?>, Byte[]> serverPackets = new ConcurrentHashMap<Class<?>, Byte[]>();

    @Inject
    public PacketMap() {
        // empty constructor
    }

    public void initialize(ApplicationConfig config) {

        try{
            for (Map.Entry<String, String> e: config.getClientPackets().entrySet()) {
                clientPackets.put(NumberUtils.createInteger(e.getKey()), getMessageByClassName(e.getValue()));
            }

            for (Map.Entry<String, String> e: config.getServerPackets().entrySet()) {
                String key = Integer.toHexString(NumberUtils.createInteger(e.getKey()));
                serverPackets.put(getClassByName(e.getValue()), ArrayUtils.toObject(Hex.decodeHex(key.toCharArray())));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // TODO fixme
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  // TODO fixme
        } catch (IllegalAccessException e) {
            e.printStackTrace();  // TODO fixme
        } catch (InvocationTargetException e) {
            e.printStackTrace();  // TODO fixme
        } catch (DecoderException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private Message getMessageByClassName(String className) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class c = Class.forName(className);

        Method m = c.getMethod("getDefaultInstance");
        Message message = (Message) m.invoke(null);

        return message;
    }

    public byte[] getServerPacketHeader(Class<? extends Message> clazz) {
        return ArrayUtils.toPrimitive(serverPackets.get(clazz));
    }

    private Class getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
