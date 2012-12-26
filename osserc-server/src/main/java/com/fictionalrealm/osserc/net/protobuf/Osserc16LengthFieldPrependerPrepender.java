package com.fictionalrealm.osserc.net.protobuf;

import com.google.protobuf.CodedOutputStream;
import com.fictionalrealm.osserc.net.PacketMap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import javax.inject.Inject;

import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * User: Yervand.Aghababyan
 * Date: 9/2/12
 * Time: 2:11 PM
 */
public class Osserc16LengthFieldPrependerPrepender extends OneToOneEncoder {
    @Inject
    public Osserc16LengthFieldPrependerPrepender(PacketMap packetMap) {

    }

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        if (!(msg instanceof ChannelBuffer)) {
            return msg;
        }

        ChannelBuffer body = (ChannelBuffer) msg;
        int length = body.readableBytes();
        ChannelBuffer header =
                channel.getConfig().getBufferFactory().getBuffer(
                        body.order(),
                        CodedOutputStream.computeRawVarint32Size(length));
        CodedOutputStream codedOutputStream = CodedOutputStream
                .newInstance(new ChannelBufferOutputStream(header));
        codedOutputStream.writeRawVarint32(length);
        codedOutputStream.flush();
        return wrappedBuffer(header, body);
    }
}
