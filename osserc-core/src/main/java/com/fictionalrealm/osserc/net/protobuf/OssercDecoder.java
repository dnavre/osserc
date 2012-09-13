package com.fictionalrealm.osserc.net.protobuf;

import com.fictionalrealm.osserc.net.PacketListener;
import com.fictionalrealm.osserc.net.PacketMap;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.MessageLite;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferInputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * Created with IntelliJ IDEA.
 * User: Yervand.Aghababyan
 * Date: 9/3/12
 * Time: 12:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class OssercDecoder extends OneToOneDecoder {
   /* private final MessageLite prototype;
    private final ExtensionRegistry extensionRegistry;*/

    /**
     * Creates a new instance.
     */
    public OssercDecoder(PacketMap packetMap) {
//        this(prototype, null);
    }

    @Override
    protected Object decode(
            ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
       /* if (!(msg instanceof ChannelBuffer)) {
            return msg;
        }

        ChannelBuffer buf = (ChannelBuffer) msg;
        if (buf.hasArray()) {
            final int offset = buf.readerIndex();
            if(extensionRegistry == null) {
                return prototype.newBuilderForType().mergeFrom(
                        buf.array(), buf.arrayOffset() + offset, buf.readableBytes()).build();
            } else {
                return prototype.newBuilderForType().mergeFrom(
                        buf.array(), buf.arrayOffset() + offset, buf.readableBytes(), extensionRegistry).build();
            }
        } else {
            if (extensionRegistry == null) {
                return prototype.newBuilderForType().mergeFrom(
                        new ChannelBufferInputStream((ChannelBuffer) msg)).build();
            } else {
                return prototype.newBuilderForType().mergeFrom(
                        new ChannelBufferInputStream((ChannelBuffer) msg), extensionRegistry).build();
            }
        }*/
        return null;
    }
}
