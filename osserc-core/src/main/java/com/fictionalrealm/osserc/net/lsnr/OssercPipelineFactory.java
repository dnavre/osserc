package com.fictionalrealm.osserc.net.lsnr;

import static org.jboss.netty.channel.Channels.*;

import com.fictionalrealm.osserc.net.protobuf.Osserc16LengthFieldPrependerPrepender;
import com.fictionalrealm.osserc.net.protobuf.OssercDecoder;
import com.fictionalrealm.osserc.net.protobuf.OssercEncoder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.codec.protobuf.ProtobufDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufEncoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

import javax.inject.Inject;

/**
 * User: Yervand.Aghababyan
 * Date: 8/18/12
 * Time: 8:27 PM
 */
public class OssercPipelineFactory implements ChannelPipelineFactory {

    private final ConnectionHandler connectionHandler;
    private final Injector injector;

    @Inject
    public OssercPipelineFactory(ConnectionHandler connectionHandler, Injector injector) {
        this.connectionHandler = connectionHandler;
        this.injector = injector;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline p = pipeline();
        p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        //p.addLast("protobufDecoder", new ProtobufDecoder(LocalTimeProtocol.Locations.getDefaultInstance()));

        p.addLast("frameEncoder", injector.getInstance(Osserc16LengthFieldPrependerPrepender.class));
        p.addLast("protobufEncoder", injector.getInstance(OssercEncoder.class));

        p.addLast("handler", connectionHandler);
        return p;
    }
}
