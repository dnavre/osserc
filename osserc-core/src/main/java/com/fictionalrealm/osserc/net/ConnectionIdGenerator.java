package com.fictionalrealm.osserc.net;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 8/21/12
 * Time: 12:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConnectionIdGenerator {

    private final long RESET_AFTER_VALUE = Long.MAX_VALUE - 100000;
    private final AtomicLong nextId = new AtomicLong(1);

    @Inject
    public ConnectionIdGenerator() {

    }

    public long getNewId() {

        long id = nextId.incrementAndGet();

        if(id > RESET_AFTER_VALUE) {
            nextId.set(1);
        }

        return id;
    }
}
