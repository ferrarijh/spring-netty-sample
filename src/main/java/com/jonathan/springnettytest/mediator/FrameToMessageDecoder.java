package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

class FrameToMessageDecoder extends ByteToMessageDecoder {

    private static final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String msgStr = in.readCharSequence(in.readableBytes(), charset).toString();
        String src = msgStr.substring(0, Message.SRC_LEN);
        String dst = msgStr.substring(Message.SRC_LEN, Message.SRC_LEN + Message.DST_LEN);
        String body = msgStr.substring(Message.SRC_LEN + Message.DST_LEN);
        Message msg = new Message(src, dst, body);

        out.add(msg);
    }
}
