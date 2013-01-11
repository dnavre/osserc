package com.fictionalrealm.osserc.net;

import com.fictionalrealm.osserc.net.protobuf.Osserc16FrameDecoder;
import com.fictionalrealm.osserc.net.protobuf.Osserc16LengthFieldPrependerPrepender;
import com.fictionalrealm.osserc.net.protobuf.OssercDecoder;
import com.fictionalrealm.osserc.net.protobuf.OssercEncoder;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

import static org.jboss.netty.channel.Channels.pipeline;

/**
 * User: Yervand.Aghababyan
 * Date: 8/18/12
 * Time: 8:27 PM
 */
public class OssercPipelineFactory implements ChannelPipelineFactory {

    private final ChannelHandlerFactory handlerFactory;
    private final AbstractPacketMap packetMap;

    public OssercPipelineFactory(AbstractPacketMap packetMap, ChannelHandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
        this.packetMap = packetMap;
    }

    @Override
    public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline p = pipeline();
        p.addLast("frameDecoder", new Osserc16FrameDecoder());
        p.addLast("protobufDecoder", new OssercDecoder(packetMap));

        p.addLast("frameEncoder", new Osserc16LengthFieldPrependerPrepender());
        p.addLast("protobufEncoder", new OssercEncoder(packetMap));

        p.addLast("handler", handlerFactory.getNewHandler());
        return p;
    }
}
