package com.fictionalrealm.osserc.net.protobuf;

import com.fictionalrealm.osserc.net.PacketMap;
import com.fictionalrealm.osserc.net.lsnr.ConnectionHandler;
import com.google.protobuf.Message;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static org.jboss.netty.buffer.ChannelBuffers.wrappedBuffer;

/**
 * User: Yervand.Aghababyan
 * Date: 9/3/12
 * Time: 12:07 AM
 */
public class OssercEncoder extends OneToOneEncoder {

    private final Logger logger = LoggerFactory.getLogger(ConnectionHandler.class);

    private final PacketMap packetMap;

    /**
     * Creates a new instance.
     */
    @Inject
    public OssercEncoder(PacketMap packetMap) {
        super();
        this.packetMap = packetMap;
    }

    @Override
    protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
        if (msg instanceof Message) {
            Message mMessage = (Message)msg;
            byte[] messageTypePrefix = packetMap.getSendablePacketHeader(mMessage.getClass());

            logger.debug("cId:" + (ctx.getAttachment() != null ? ctx.getAttachment() : "N/A") + " SP:"
                    + msg.getClass().getSimpleName() + " " + msg.toString().replace("\n", " ") );

            return wrappedBuffer(messageTypePrefix, mMessage.toByteArray());
        }

        return msg;
    }
}
