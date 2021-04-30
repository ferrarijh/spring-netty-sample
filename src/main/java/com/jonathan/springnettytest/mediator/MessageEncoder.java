package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MessageEncoder extends MessageToByteEncoder<Message> {

    private static final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, ByteBuf out) throws Exception {
        String outStr = msg.getSrc() + msg.getDst() + msg.getBody() + '\n';
        out.writeCharSequence(outStr, charset);
    }
}
