package com.jonathan.springnettytest.mediator;

import com.jonathan.springnettytest.model.Message;
import com.jonathan.springnettytest.repository.ChannelRepository;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class SimpleMediatorHandler extends SimpleChannelInboundHandler<Message> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Message msg) throws Exception {
        msg.show();

        String src = msg.getSrc();
        String dst = msg.getDst();
        if(dst.equals("AA"))
            ChannelRepository.put(src, ctx.channel());
        else if (('A'<=dst.charAt(0) || dst.charAt(0) <= 'Z')
                && ('0'<=dst.charAt(1) || '9' <= dst.charAt(1))){
            Channel dstChannel = ChannelRepository.get(msg.getDst());
            dstChannel.writeAndFlush(msg);
        }
    }
}
