package com.fictionalrealm.osserc.client.config;

import com.fictionalrealm.osserc.config.OssercConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * User: Yervand.Aghababyan
 * Date: 1/21/13
 * Time: 10:54 PM
 */
public class AndroidClientConfig implements ClientConfig {

    private static final String CLIENT_DEFAULT_PATH = "/com/fictionalrealm/osserc/resources/" +
            "client_config.defaults.properties";

    private static final String CLIENT_CUSTOM_PATH = "/osserc_client.properties";

    private static final Logger LOGGER = LoggerFactory.getLogger(AndroidClientConfig.class);

    private final Map<String, String> clientPackets;
    private final Map<String, String> serverPackets;

    private final Properties config;

    public AndroidClientConfig() throws OssercConfigurationException, OssercConfigurationInitException {

        try {
            List<String> clientPacketMaps = new ArrayList<String>();
            List<String> serverPacketMaps = new ArrayList<String>();

            // Loading client configuration
            config = new Properties();
            config.load(getClass().getResourceAsStream(CLIENT_DEFAULT_PATH));

            clientPacketMaps.add(config.getProperty("packetlist.client"));
            serverPacketMaps.add(config.getProperty("packetlist.server"));

            Properties customConfig = new Properties();
            try {

                InputStream is = getClass().getResourceAsStream(CLIENT_CUSTOM_PATH);

                if(is == null) {
                    throw new OssercConfigurationInitException("Missing required configuration file in classpath: "
                            + CLIENT_CUSTOM_PATH);
                }

                customConfig.load(is);

                if(customConfig.getProperty("packetlist.client") != null) {
                    clientPacketMaps.add("/" + customConfig.getProperty("packetlist.client"));
                }

                if(customConfig.getProperty("packetlist.server") != null) {
                    serverPacketMaps.add("/" + customConfig.getProperty("packetlist.server"));
                }

            } catch (IOException e1) {
                LOGGER.warn("Couldn't locate " + CLIENT_CUSTOM_PATH + " file with Osserc configuration, " +
                        "falling back to defaults.");
            }

            config.putAll(customConfig);

            // Loading packet maps
            Map<String, String> clientPackets = new HashMap<String, String>();
            Map<String, String> serverPackets = new HashMap<String, String>();

            clientPackets = loadPacketList(clientPacketMaps.toArray(new String[]{}));
            serverPackets = loadPacketList(serverPacketMaps.toArray(new String[]{}));

            this.clientPackets = Collections.unmodifiableMap(clientPackets);
            this.serverPackets = Collections.unmodifiableMap(serverPackets);

        } catch (IOException e) {
            throw new OssercConfigurationException(e);
        }
    }

    @Override
    public Map<String, String> getClientPackets() {
        return clientPackets;
    }

    @Override
    public Map<String, String> getServerPackets() {
        return serverPackets;
    }

    @Override
    public long getGeneralNetworkTimeout() {
        return 30000;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Map<String, String> loadPacketList(String ... files) {
        Properties mainList = new Properties();
        for(String str: files) {
            try {
                Properties p = new Properties();

                InputStream is = ClassLoader.getSystemResourceAsStream(str);

                if(is == null) {
                    is = getClass().getResourceAsStream(str);
                }

                p.load(is);

                mainList.putAll(p);
            } catch (IOException e) {
                LOGGER.warn("Packet map configuration file \"" + str + "\" was not found!");
            }
        }

        return  new HashMap(mainList);
    }
}
