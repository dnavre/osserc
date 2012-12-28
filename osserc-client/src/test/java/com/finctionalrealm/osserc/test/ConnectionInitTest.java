package com.finctionalrealm.osserc.test;



import com.fictionalrealm.osserc.client.OssercClient;
import org.junit.Test;

/**
 * User: Yervand.Aghababyan
 * Date: 12/28/12
 * Time: 1:15 AM
 */
public class ConnectionInitTest {

    @Test
    public void testConnect() {
        OssercClient c = new OssercClient();

        c.connect("localhost", 8039);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
