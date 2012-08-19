package com.fictionalrealm.osserc.net.lsnr;

import static org.jboss.netty.channel.Channels.*;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

/**
 * User: Yervand.Aghababyan
 * Date: 8/18/12
 * Time: 8:27 PM
 */
public class OssercPipelineFactory implements ChannelPipelineFactory {

    public OssercPipelineFactory() {

    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline p = pipeline();
        p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        //p.addLast("protobufDecoder", new ProtobufDecoder(LocalTimeProtocol.Locations.getDefaultInstance()));

        p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        p.addLast("protobufEncoder", new ProtobufEncoder());

        p.addLast("handler", new ConnectionHandler());
        return p;
    }
}
