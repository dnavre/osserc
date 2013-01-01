package com.fictionalrealm.osserc.client.net;

import com.fictionalrealm.osserc.client.ClientConfig;
import com.fictionalrealm.osserc.net.AbstractPacketMap;
import org.apache.commons.codec.DecoderException;

import java.lang.reflect.InvocationTargetException;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:46 AM
 */
public class CPacketMap extends AbstractPacketMap {

    public CPacketMap() {

    }

    public void initialize(ClientConfig config) {

        try{
            super.initialize(config.getClientPackets(), config.getServerPackets());
        } catch (ClassNotFoundException e) {
            appManager.stop();
        } catch (NoSuchMethodException e) {
            appManager.stop();
        } catch (IllegalAccessException e) {
            appManager.stop();
        } catch (InvocationTargetException e) {
            appManager.stop();
        } catch (DecoderException e) {
            logger.error("An unknown error occurred while loading packet list.");
            appManager.stop();
        }
    }
}
