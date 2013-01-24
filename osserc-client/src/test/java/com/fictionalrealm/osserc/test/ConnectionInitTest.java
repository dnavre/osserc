package com.fictionalrealm.osserc.test;



import com.fictionalrealm.osserc.client.OssercClient;
import com.fictionalrealm.osserc.core.OssercServer;
import com.fictionalrealm.osserc.startup.OssercServerInitializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:15 AM
 */
public class ConnectionInitTest {

    private OssercServer server;

    @Before
    public void init() {
        server = OssercServerInitializer.create();
        server.start();
    }

    @After
    public void cleanup() {
        server.stop();
    }

    @Test
    public void testConnect() {
        OssercClient c = new OssercClient();

        c.connect("localhost", 8039);
        c.authenticate("sadasda", "ada");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
