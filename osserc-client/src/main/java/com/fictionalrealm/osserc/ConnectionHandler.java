package com.fictionalrealm.osserc;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * User: Yervand.Aghababyan
 * Date: 12/27/12
 * Time: 2:47 AM
 */
public class ConnectionHandler implements IoHandler {
    @Override
    public void sessionCreated(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sessionOpened(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sessionClosed(IoSession ioSession) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void sessionIdle(IoSession ioSession, IdleStatus idleStatus) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void exceptionCaught(IoSession ioSession, Throwable throwable) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void messageReceived(IoSession ioSession, Object o) throws Exception {

        System.out.println("qaqa");
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void messageSent(IoSession ioSession, Object o) throws Exception {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
