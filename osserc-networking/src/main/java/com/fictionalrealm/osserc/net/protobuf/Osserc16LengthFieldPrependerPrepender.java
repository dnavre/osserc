package com.fictionalrealm.osserc.net.protobuf;

import com.fictionalrealm.osserc.net.AbstractPacketMap;
import com.google.protobuf.CodedOutputStream;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBufferOutputStream;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;


import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * User: Yervand.Aghababyan
 * Date: 9/2/12
 * Time: 2:11 PM
 */
public class Osserc16LengthFieldPrependerPrepender extends OneToOneEncoder {

    public Osserc16LengthFieldPrependerPrepender() {

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
