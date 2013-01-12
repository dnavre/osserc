package com.fictionalrealm.osserc.net.protobuf;

import com.fictionalrealm.osserc.net.AbstractConnection;
import com.fictionalrealm.osserc.net.AbstractPacketMap;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.Message;
import org.apache.commons.lang.ArrayUtils;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: Yervand.Aghababyan
 * Date: 9/3/12
 * Time: 12:07 AM
 */
public class OssercDecoder extends OneToOneDecoder {

    private final Logger logger = AbstractConnection.LOGGER;

    private final AbstractPacketMap packetMap;

    /**
     * Creates a new instance.
     */
    public OssercDecoder(AbstractPacketMap packetMap) {
//        this(prototype, null);
        this.packetMap = packetMap;
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {

        if (!(msg instanceof ChannelBuffer)) {
            return msg;
        }

        ChannelBuffer buf = (ChannelBuffer) msg;

        byte [] msgTypeHeader = buf.readBytes(2).array();

        Message m = packetMap.getReceivableMessage(msgTypeHeader);
        Message result;
        if (buf.hasArray()) {
            final int offset = buf.readerIndex();
            result =  m.newBuilderForType().mergeFrom(buf.array(), buf.arrayOffset() + offset, buf.readableBytes()).build();
        } else {
            result = m.newBuilderForType().mergeFrom(new ChannelBufferInputStream((ChannelBuffer) msg)).build();
        }

        logger.debug(" Rx:" + result.getClass().getSimpleName() + " " + result.toString().replace("\n", " ") );

        return result;
    }
}
