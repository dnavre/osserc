package com.fictionalrealm.osserc.net;

import com.google.protobuf.Message;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:55 AM
 */
public abstract class AbstractPacketMap {

    private final Logger logger = LoggerFactory.getLogger(AbstractPacketMap.class);

    private final ConcurrentMap<Short, Message> receivableMessages = new ConcurrentHashMap<Short, Message>();
    private final ConcurrentMap<Class<?>, Short> sendableMessages = new ConcurrentHashMap<Class<?>, Short>();

    protected void initialize(Map<String, String> receivable, Map<String, String> sendable) throws PacketMapInitializationException {
        try {
            for (Map.Entry<String, String> e: receivable.entrySet()) {
                receivableMessages.put(hexStr2Short(e.getKey()), getMessageByClassName(e.getValue()));
            }

            for (Map.Entry<String, String> e: sendable.entrySet()) {
                sendableMessages.put(getClassByName(e.getValue()), hexStr2Short(e.getKey()));
            }
        } catch (InvocationTargetException e1) {
            throw new PacketMapInitializationException(e1);
        } catch (IllegalAccessException e1) {
            throw new PacketMapInitializationException(e1);
        } catch (ClassNotFoundException e1) {
            throw new PacketMapInitializationException(e1);
        } catch (NoSuchMethodException e1) {
            throw new PacketMapInitializationException(e1);
        }
    }

    private short hexStr2Short(String str) {
        byte[] type = DatatypeConverter.parseHexBinary(str.replace("0x", ""));
        ByteBuffer buf =  ByteBuffer.allocate(2).put(type);
        buf.rewind();
        return buf.getShort();
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
        return ByteBuffer.allocate(2).putShort(sendableMessages.get(clazz)).array();
    }

    public Message getReceivableMessage(byte[] type) {
        return receivableMessages.get(ByteBuffer.wrap(type).getShort());
    }

    private Class getClassByName(String className) throws ClassNotFoundException {
        return Class.forName(className);
    }
}
