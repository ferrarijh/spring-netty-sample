package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FrameToMessageDecoder extends ByteToMessageDecoder {

    private static final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        String msgStr = in.readCharSequence(in.readableBytes(), charset).toString();
        int begin = 0, end = Message.SRC_LEN;
        String src = msgStr.substring(begin, end);
        begin = end;
        end += Message.SVC_LEN;
        String svc = msgStr.substring(begin, end);
        begin = end;
        end += Message.DST_LEN;
        String dst = msgStr.substring(begin, end);
        begin = end;
        end = msgStr.length();
        String body = msgStr.substring(begin, end);
        Message msg = new Message(src, svc, dst, body);

        out.add(msg);
    }
}
