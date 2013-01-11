package com.fictionalrealm.osserc.client;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * User: Yervand.Aghababyan
 * Date: 1/11/13
 * Time: 5:07 AM
 */
public class ClientPipelineFactory implements ChannelPipelineFactory {
    @Override
    public ChannelPipeline getPipeline() throws Exception {

        ChannelPipeline p = Channels.pipeline();

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
