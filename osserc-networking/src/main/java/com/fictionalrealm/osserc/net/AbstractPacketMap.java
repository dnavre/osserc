package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:55 AM
 */
public class AbstractPacketMap {

    private final Logger logger = LoggerFactory.getLogger(AbstractPacketMap.class);

    private final ConcurrentMap<Integer, Message> receivableMessages = new ConcurrentHashMap<Integer, Message>();
    private final ConcurrentMap<Class<?>, Byte[]> sendableMessages = new ConcurrentHashMap<Class<?>, Byte[]>();

    protected void initialize(Map<String, String> receivable, Map<String, String> sendable) throws DecoderException, ClassNotFoundException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (Map.Entry<String, String> e: receivable.entrySet()) {
            receivableMessages.put(NumberUtils.createInteger(e.getKey()), getMessageByClassName(e.getValue()));
        }

        for (Map.Entry<String, String> e: sendable.entrySet()) {
            String key = Integer.toHexString(NumberUtils.createInteger(e.getKey()));
            sendableMessages.put(getClassByName(e.getValue()), ArrayUtils.toObject(Hex.decodeHex(key.toCharArray())));
        }
    }

    private Message getMessageByClassName(String className) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException {
        Class c;

        try {
            c = Class.forName(className);
        } catch (ClassNotFoundException e) {
            logger.error("Failed to load packet:" + className + " class not found!");
            throw e;
        }

        if(!Message.class.isAssignableFrom(c)) {
            logger.error("Failed to load packet:" + className + " class does extend Message class!");
        }

        Method m = c.getMethod("getDefaultInstance");
        Message message = (Message) m.invoke(null);

        return message;
    }

    public byte[] getSendablePacketHeader(Class<? extends Message> clazz) {
        return ArrayUtils.toPrimitive(sendableMessages.get(clazz));
    }

    private Class getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
